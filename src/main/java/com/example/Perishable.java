package com.example;

import java.time.LocalDate;

public interface Perishable {


    LocalDate expirationDate();

    //returns true if expirationdate is after todays date.
    default boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate());
    }
}
