package io.pivotal.training.web;

import io.pivotal.training.web.pages.GitHubLoginPage;
import io.pivotal.training.web.pages.HelloPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;


public class GitHubLoginTest extends AbstractWebTest {

    @Page
    private HelloPage helloPage;

    @Page
    private GitHubLoginPage gitHubLoginPage;

    @Test
    public void visitProtectedPageRedirectsToGitHubLogin() throws Exception {
        goTo(helloPage);
        gitHubLoginPage.isAt();
    }
}
