package io.pivotal.training.web.pages;

import io.pivotal.training.model.Address;
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

    public void displaysAddress(Address address) {
        assertThat(find(".name").getText()).isEqualTo(address.getName());
        assertThat(find(".surname").getText()).isEqualTo(address.getSurname());
        assertThat(find(".postcode").getText()).isEqualTo(address.getPostcode());
    }
}
