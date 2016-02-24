package io.pivotal.training.web;

import io.pivotal.training.model.Address;
import io.pivotal.training.web.pages.CreateAddressPage;
import io.pivotal.training.web.pages.ShowAddressPage;
import org.fluentlenium.core.annotation.Page;
import org.junit.Test;

import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;

public class CreateAddressIT extends AbstractWebTest {
    @Page
    public CreateAddressPage createAddressPage;

    @Page
    public ShowAddressPage showAddressPage;

    @Test
    public void visitFormPage() throws Exception {
        goTo(createAddressPage);

        createAddressPage.isAt();
    }

    @Test
    public void fillForm() throws Exception {
        goTo(createAddressPage);

        Address address = new Address("Raul", "Avila", "SW1A 1AA");

        createAddressPage.submitAddress(address);

        showAddressPage.isAt();

        showAddressPage.displaysAddress(address);
    }
}
