package com.clearfood.controller;

import com.clearfood.models.Restaurant;
import com.clearfood.service.RestaurantService;

public class AdminController {

    RestaurantService restaurantService;

    public AdminController(RestaurantService restaurantService) {
        this.restaurantService =  restaurantService;
    }

    public int registerRestaurant(Restaurant restaurant) {

        // todo validate restaurant request, if validation failed, return user error

        try {
            boolean isCreated = restaurantService.createRestaurant(restaurant);
            if (!isCreated) {
                //return rx already created
                return 400;
            }
        } catch (Exception e) {
            //todo some error happened, handle error and return proper response
            return 500;
        }
        return 201;
    }

    public int updateQuantity(String restaurantName, int quantity) {

        if (!validateUpdateQuantity(restaurantName, quantity)) {
            return 400;
        }

        try {
            boolean isUpdated = restaurantService.updateQuantity(restaurantName, quantity);
            if (!isUpdated) {
                //rx not present
                return 404;
            }
        } catch (Exception e) {
            //todo some error happened, handle error and return proper response
            return 500;
        }

        return 200;
    }

    private boolean validateUpdateQuantity(String restaurantName, int quantity) {
        if (restaurantName == null || restaurantName.isEmpty()) {
            return false;
        }

        return quantity >= 0;
    }
}
