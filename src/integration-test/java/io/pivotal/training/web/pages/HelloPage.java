package io.pivotal.training.web.pages;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import static io.pivotal.training.web.environment.IntegrationTestConfig.BASE_URL;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloPage extends FluentPage {

    @FindBy(css = ".logout")
    FluentWebElement logoutLink;

    @Override
    public String getUrl() {
        return "/hello";
    }

    @Override
    public void isAt() {
        assertThat(title()).contains("Hello");
    }

    public void logout() {
        logoutLink.click();
    }
}
