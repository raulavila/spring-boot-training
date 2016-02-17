package io.pivotal.training.web;

import io.pivotal.training.SpringTrainingApplication;
import io.pivotal.training.web.environment.IntegrationTestConfig;
import io.pivotal.training.web.pages.HomePage;
import org.fluentlenium.adapter.FluentTest;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static io.pivotal.training.web.environment.IntegrationTestConfig.SERVER_PORT;
import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
        classes = { SpringTrainingApplication.class, IntegrationTestConfig.class })
@WebAppConfiguration
@IntegrationTest("server.port:" + SERVER_PORT)
public class HomePageTest extends FluentTest {
    public WebDriver webDriver = new HtmlUnitDriver();

    @Page
    public HomePage page;

    @Override
    public WebDriver getDefaultDriver() {
        return webDriver;
    }

    @Test
    public void visitHomePage() throws Exception {
        goTo(page);

        assertThat(page).isAt();
    }
}
