package io.pivotal.training.web;

import io.pivotal.training.web.pages.HelloPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;

import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;

public class HelloPageTest extends AbstractWebTest {

    @Page
    private HelloPage helloPage;

    @Test
    public void visitHelloPage() throws Exception {
        goTo(helloPage);

        assertThat(helloPage).isAt();
    }
}
