package bg.softuni.heroes.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BaseTest {
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    protected int putki;
}
