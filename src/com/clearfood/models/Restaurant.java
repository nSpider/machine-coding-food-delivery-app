package com.clearfood.models;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Restaurant {
    String name;
    String itemName;
    AtomicInteger price; //added because of in memory implementation, not needed for DB implementation
    AtomicInteger quantity; //added because of in memory implementation, not needed for DB implementation
    float rating;
    AtomicInteger ratingCount; //added because of in memory implementation, not needed for DB implementation
    Set<String> serviceableAreas;

    public Restaurant (int price) {
        this.price = new AtomicInteger(price);
    }
}
