package io.pivotal.training.model;

public final class Address {
    private String name;
    private String surname;
    private String postcode;

    public Address(String name, String surname, String postcode) {
        this.name = name;
        this.surname = surname;
        this.postcode = postcode;
    }

    public Address() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (name != null ? !name.equals(address.name) : address.name != null) return false;
        if (surname != null ? !surname.equals(address.surname) : address.surname != null) return false;
        return !(postcode != null ? !postcode.equals(address.postcode) : address.postcode != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (postcode != null ? postcode.hashCode() : 0);
        return result;
    }
}
