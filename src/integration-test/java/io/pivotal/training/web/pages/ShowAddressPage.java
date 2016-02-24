package io.pivotal.training.web.pages;

import org.fluentlenium.core.FluentPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ShowAddressPage extends FluentPage {
    @Override
    public String getUrl() {
        return "/address";
    }

    @Override
    public void isAt() {
        assertThat(title()).contains("Show address");
    }

    public void displaysAddress(String name, String surname, String postcode) {
        assertThat(find(".name").getText()).isEqualTo(name);
        assertThat(find(".surname").getText()).isEqualTo(surname);
        assertThat(find(".postcode").getText()).isEqualTo(postcode);
    }
}
