
package tests;

import org.junit.jupiter.api.Test;
import support.ApiClient;
import support.TestUser;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ApiClientTest {

    @Test
    public void shouldCreateAndDeleteTestUser() throws Exception {
        ApiClient apiClient = new ApiClient();
        TestUser user = new TestUser();

        String accessToken = apiClient.createUser(user);

        try {
            assertNotNull(accessToken, "Токен пользователя не должен быть null");
            assertFalse(accessToken.isEmpty(), "Токен пользователя не должен быть пустым");
        } finally {
            apiClient.deleteUser(accessToken);
        }
    }
}

