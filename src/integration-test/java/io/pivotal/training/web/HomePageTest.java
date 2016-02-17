package io.pivotal.training.web;

import io.pivotal.training.web.pages.HomePage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;

import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;

public class HomePageTest extends AbstractWebTest {
    @Page
    public HomePage homePage;

    @Test
    public void visitHomePage() throws Exception {
        goTo(homePage);

        assertThat(homePage).isAt();
    }
}
