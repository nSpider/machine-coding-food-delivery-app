package com.clearfood;

import com.clearfood.controller.AdminController;
import com.clearfood.controller.UserController;
import com.clearfood.models.Restaurant;
import com.clearfood.models.SortBy;
import com.clearfood.models.User;
import com.clearfood.service.RestaurantInMemoryService;
import com.clearfood.service.RestaurantService;
import com.clearfood.service.UserInMemoryService;
import com.clearfood.service.UserService;

import java.util.HashSet;
import java.util.List;

public class Application {


    public static void main(String[] args) {

        UserService userService = new UserInMemoryService();
        RestaurantService restaurantService = new RestaurantInMemoryService();

        AdminController adminController = new AdminController(restaurantService);
        UserController userController = new UserController(restaurantService, userService);

        Restaurant r1 = new Restaurant(300);
        r1.setName("rx 1");
        r1.setItemName("item 1");
        r1.setServiceableAreas(new HashSet<>(List.of("10", "20", "30")));

        Restaurant r2 = new Restaurant(200);
        r2.setName("rx 2");
        r2.setItemName("item 2");
        r2.setServiceableAreas(new HashSet<>(List.of("10", "20")));

        int result = adminController.registerRestaurant(r1);
        System.out.println(result);
        result = adminController.registerRestaurant(r2);
        System.out.println(result);

        result = userController.loginUser("123");
        System.out.println(result);

        User u1 = new User();
        u1.setName("u1");
        u1.setGender("M");
        u1.setPhoneNumber("123");
        u1.setPinCode("20");

        User u2 = new User();
        u2.setName("u2");
        u2.setGender("M");
        u2.setPhoneNumber("234");
        u2.setPinCode("30");

        result = userController.registerUser(u1);
        System.out.println(result);

        result = userController.registerUser(u2);
        System.out.println(result);

        result = userController.loginUser("123");
        System.out.println(result);

        System.out.println(userController.showRestaurants(SortBy.PRICING));

        result = userController.loginUser("234");
        System.out.println(result);

        System.out.println(userController.showRestaurants(SortBy.PRICING));

    }

}
