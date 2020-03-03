package bg.softuni.heroes.web.view.base;

import bg.softuni.heroes.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class BaseViewTest extends BaseTest {

    @Autowired
    protected MockMvc mockMvc;
}
