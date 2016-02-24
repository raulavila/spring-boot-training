package io.pivotal.training.web.pages;

import org.fluentlenium.core.FluentPage;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateAddressPage extends FluentPage {
    @Override
    public String getUrl() {
        return "/createAddress";
    }

    @Override
    public void isAt() {
        assertThat(title()).contains("Create address");
    }

    public void submitAddress(String name, String surname, String postcode) {
        fill("#myForm input[name='name']").with("Raul");
        fill("#myForm input[name='surname']").with("Avila");
        fill("#myForm input[name='postcode']").with("SW1A 1AA");

        click("#myForm input[type='submit']");
    }
}
