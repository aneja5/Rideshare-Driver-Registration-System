package option2;

import java.util.Objects;
/**
 * Represents a postal address.
 */
public class Address {
    private String street;
    private String city;
    private String state;
    private String zipcode;

    /**
     * Constructs an Address object with the specified street, city, state, and zipcode.
     *
     * @param street  The street of the address.
     * @param city    The city of the address.
     * @param state   The state of the address.
     * @param zipcode The ZIP code of the address.
     */
    public Address(String street, String city, String state, String zipcode) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    /**
     * Gets the street of the address.
     *
     * @return The street.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Gets the city of the address.
     *
     * @return The city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the state of the address.
     *
     * @return The state.
     */
    public String getState() {
        return state;
    }

    /**
     * Gets the ZIP code of the address.
     *
     * @return The ZIP code.
     */
    public String getZipcode() {
        return zipcode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) && Objects.equals(city, address.city) && Objects.equals(state, address.state) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, state, zipcode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
