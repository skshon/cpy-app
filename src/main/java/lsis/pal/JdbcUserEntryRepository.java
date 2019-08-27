package lsis.pal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class JdbcUserEntryRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserEntryRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    public UserEntry create(UserEntry userEntry) {
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO user_entries (account, password) " +
                            "VALUES (?, ?)",
                    RETURN_GENERATED_KEYS
            );

            statement.setString(1, userEntry.getAccount());
            statement.setString(2, userEntry.getPassword());

            return statement;
        }, generatedKeyHolder);

        return find(generatedKeyHolder.getKey().longValue());
    }

    public UserEntry find(Long id) {
        return jdbcTemplate.query(
                "SELECT id, account, password FROM user_entries WHERE id = ?",
                new Object[]{id},
                extractor);
    }

    public List<UserEntry> list() {
        return jdbcTemplate.query("SELECT id, account, password FROM user_entries", mapper);
    }

    public UserEntry update(Long id, UserEntry userEntry) {
        jdbcTemplate.update("UPDATE user_entries " +
                        "SET account = ?, password = ? " +
                        "WHERE id = ?",
                userEntry.getAccount(),
                userEntry.getPassword(),
                id);

        return find(id);
    }

    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM user_entries WHERE id = ?", id);
    }

    private final RowMapper<UserEntry> mapper = (rs, rowNum) -> new UserEntry(
            rs.getLong("id"),
            rs.getString("account"),
            rs.getString("password")
    );

    private final ResultSetExtractor<UserEntry> extractor =
            (rs) -> rs.next() ? mapper.mapRow(rs, 1) : null;
}
