package jpa.shop.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
    private String city;

    private String street;

    private String zipCode;

}
