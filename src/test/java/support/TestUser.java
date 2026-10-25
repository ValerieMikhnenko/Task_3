
package support;

import java.util.UUID;

public class TestUser {

    private final String name;
    private final String email;
    private final String password;

    public TestUser() {
        this.name = "Test User";
        this.email = "test_" + UUID.randomUUID() + "@yandex.ru";
        this.password = "Pass" + UUID.randomUUID().toString().substring(0, 8);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
