package io.pivotal.training.web.pages;

import io.pivotal.training.model.Address;
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

    public void submitAddress(Address address) {
        fill("#name").with(address.getName());
        fill("#surname").with(address.getSurname());
        fill("#postcode").with(address.getPostcode());

        click("#my-form .submit");
    }
}
