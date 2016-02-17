package io.pivotal.training.web.pages;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

import static io.pivotal.training.web.environment.IntegrationTestConfig.BASE_URL;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage extends FluentPage {

    @FindBy(css = ".login")
    FluentWebElement loginButton;

    @Override
    public String getUrl() {
        return BASE_URL + "/login";
    }

    @Override
    public void isAt() {
        assertThat(title()).contains("Login");
    }

    public void validLogin() {
        fill(".username").with("user");
        fill(".password").with("my-super-password");
        loginButton.click();
    }
}
