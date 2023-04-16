package task1;

/**
 * @author Nechaev Roman
 */
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Nechaev Roman
 */
public class User {

    private final String username;
    private final String email;
    private final byte[] passwordHash;


    User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.passwordHash = decodePassword(password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(email, user.email) && Arrays.equals(passwordHash, user.passwordHash);
    }

    private byte[] decodePassword(String password) {
        return DigestUtils.sha256(password);
    }


    @Override
    public int hashCode() {
        int result = Objects.hash(username, email);
        result = 31 * result + Arrays.hashCode(passwordHash);
        return result;
    }

    @Override
    public String toString() {
        return username + ";" + email;
    }
}