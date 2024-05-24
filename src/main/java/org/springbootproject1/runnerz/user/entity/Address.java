package org.springbootproject1.runnerz.user.entity;

public record Address(
        String street,
        String suite,
        String city,
        String zipcode,
        Geo geo
) {
}
