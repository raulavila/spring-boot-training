package io.pivotal.training.web;

import io.pivotal.training.web.pages.HelloPage;
import io.pivotal.training.web.pages.LoginPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Before;
import org.junit.Test;

import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;

public class HelloPageTest extends AbstractWebTest {

    @Page
    private HelloPage helloPage;

    @Page
    private LoginPage loginPage;

    @Before
    public void setUp() {
        goTo(helloPage);
        loginPage.validLogin();
    }

    @Test
    public void visitHelloPage_whenNotLoggedIn() throws Exception {
        assertThat(helloPage).isAt();
    }

    @Test
    public void logoutFromHelloPage() throws Exception {
        helloPage.logout();

        assertThat(loginPage).isAt();

        goTo(helloPage);
        assertThat(loginPage).isAt();
    }
}
