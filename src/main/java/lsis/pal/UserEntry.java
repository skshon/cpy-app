package lsis.pal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_entries")
public class UserEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String account;
    private String password;

    public UserEntry() {

    }

    public UserEntry(long id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntry{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntry userEntry = (UserEntry) o;
        return id == userEntry.id &&
                Objects.equals(account, userEntry.account) &&
                Objects.equals(password, userEntry.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, password);
    }
}
