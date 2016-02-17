package io.pivotal.training.web;

import io.pivotal.training.SpringTrainingApplication;
import org.fluentlenium.adapter.FluentTest;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.pivotal.training.web.environment.IntegrationTestConfig.SERVER_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringTrainingApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:" + SERVER_PORT)
public abstract class AbstractWebTest extends FluentTest {
    public WebDriver webDriver = new HtmlUnitDriver();

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }
}
