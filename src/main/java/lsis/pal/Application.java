package lsis.pal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public JdbcUserEntryRepository userEntryRepository(DataSource dataSource) {
        return new JdbcUserEntryRepository(dataSource);
    }
}
