package bg.softuni.heroes.web.api.base;

import bg.softuni.heroes.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaseRestTest extends BaseTest {
    @Autowired
    protected TestRestTemplate restTemplate;

    @LocalServerPort
    protected int port;

    protected String getFullRoute(String route) {
        return "http://localhost:" + port + route;
    }
}
