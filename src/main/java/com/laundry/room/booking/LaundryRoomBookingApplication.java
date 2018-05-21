package com.laundry.room.booking;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableAutoConfiguration
@Configuration
public class LaundryRoomBookingApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(LaundryRoomBookingApplication.class).run(args);
    }
}
