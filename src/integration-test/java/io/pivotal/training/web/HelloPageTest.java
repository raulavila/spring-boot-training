package io.pivotal.training.web;

import io.pivotal.training.web.pages.HelloPage;
import io.pivotal.training.web.pages.LoginPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;

import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;

public class HelloPageTest extends AbstractWebTest {

    @Page
    private HelloPage helloPage;

    @Page
    private LoginPage loginPage;

    @Test
    public void visitHelloPage_whenNotLoggedIn() throws Exception {
        goTo(helloPage);
        assertThat(loginPage).isAt();
        loginPage.validLogin();
        assertThat(helloPage).isAt();
    }
}
