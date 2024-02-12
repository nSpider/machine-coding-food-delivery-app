package com.clearfood.models;

import lombok.Builder;
import lombok.Data;

@Data
public class User {
    String name;
    String gender;
    String phoneNumber;
    String pinCode;
}
