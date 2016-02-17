package io.pivotal.training.web.pages;

import org.fluentlenium.core.FluentPage;

import static io.pivotal.training.web.environment.IntegrationTestConfig.BASE_URL;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloPage extends FluentPage {

    @Override
    public String getUrl() {
        return BASE_URL + "/hello";
    }

    @Override
    public void isAt() {
        assertThat(title()).contains("Hello");
    }
}
