package Models;

public interface User {
    String getUsername();
    String getPassword();

    String toFileString();
}
