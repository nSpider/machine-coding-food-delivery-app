package com.clearfood.service;

import com.clearfood.models.Restaurant;
import com.clearfood.models.SortBy;

import java.util.*;

public class RestaurantInMemoryService implements RestaurantService{

    // this will store all rx for it's name as it's unique
    Map<String, Restaurant> restaurantMap = new HashMap<>();

    // this will store all rx for a given pincode, will help in searching/sorting
    Map<String, List<Restaurant>> pinToRestaurantList = new HashMap<>();
    // todo: Add two heaps for restaurant sorting based on ratings and pricing

    @Override
    public boolean createRestaurant(Restaurant restaurant) {
        if (restaurantMap.containsKey(restaurant.getName())) {
            return false;
        }

        restaurantMap.put(restaurant.getName(), restaurant);

        restaurant.getServiceableAreas().forEach(serviceableArea -> {
            List<Restaurant> restaurants = pinToRestaurantList.getOrDefault(serviceableArea, new ArrayList<>());
            restaurants.add(restaurant);
            pinToRestaurantList.put(serviceableArea, restaurants);
        });
        return true;
    }

    public boolean updateQuantity(String restaurantName, int quantity) {
        if (restaurantMap.containsKey(restaurantName)) {
            Restaurant restaurant = restaurantMap.get(restaurantName);
            restaurant.getQuantity().getAndSet(quantity);
            return true;
        }
        return false;
    }

    @Override
    public List<Restaurant> showRestaurants(SortBy sortBy, String pinCode) {
        List<Restaurant> restaurants = pinToRestaurantList.getOrDefault(pinCode, Collections.emptyList());
        if (restaurants.isEmpty()) {
            return restaurants;
        }

        SortProvider provider = getSortProvider(sortBy);
        return provider.sort(restaurants);
    }

    @Override
    public int placeOrder(String restaurantName, int quantityRequested) {
        Restaurant restaurant = restaurantMap.get(restaurantName);

        if (restaurant == null ) {
            return 1; //restaurant not found
        }

        int earlyQuantity = restaurant.getQuantity().get();
        if (earlyQuantity < quantityRequested) {
            return 2; // food not available
        }

        int laterQuantity = restaurant.getQuantity().addAndGet(quantityRequested * -1);

        if (laterQuantity < 0) {
            //revert transaction
            restaurant.getQuantity().addAndGet(quantityRequested);
            return 2;
        }

        return 0;
    }

    private SortProvider getSortProvider(SortBy sortBy) {
        switch (sortBy) {
            case PRICING:
                return new PricingSortProvider();
            case RATING:
                return new RatingsSortProvider();
            default:
                throw new RuntimeException("Undefined sort config received");
        }
    }

}
