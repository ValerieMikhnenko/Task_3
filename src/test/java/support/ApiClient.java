
package support;

import io.qameta.allure.Step;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApiClient {

    private static final String BASE_URL =
            "https://qa-stellarburgers.education-services.ru";

    private final HttpClient client = HttpClient.newHttpClient();

    @Step("Создать тестового пользователя через API")
    public String createUser(TestUser user)
            throws IOException, InterruptedException {

        String body = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"name\":\"%s\"}",
                user.getEmail(),
                user.getPassword(),
                user.getName()
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/auth/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(
                        body, StandardCharsets.UTF_8))
                .build();

        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IllegalStateException(
                    "Не удалось создать пользователя. HTTP "
                            + response.statusCode() + ": " + response.body()
            );
        }

        Matcher matcher = Pattern.compile(
                "\"accessToken\"\\s*:\\s*\"([^\"]+)\""
        ).matcher(response.body());

        if (!matcher.find()) {
            throw new IllegalStateException(
                    "В ответе API не найден accessToken: " + response.body()
            );
        }

        return matcher.group(1);
    }

    @Step("Удалить тестового пользователя через API")
    public void deleteUser(String accessToken)
            throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/api/auth/user"))
                .header("Authorization", accessToken)
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(
                request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200 && response.statusCode() != 202) {
            throw new IllegalStateException(
                    "Не удалось удалить пользователя. HTTP "
                            + response.statusCode() + ": " + response.body()
            );
        }
    }
}

