package com.example.demo.controller;

import com.example.demo.converter.StringToDayEnum;
import com.example.demo.entity.DayEnum;
import com.example.demo.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WeatherController {

    private static final String template = "report";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    StringToDayEnum stde;

    @GetMapping("/weather")
    public Weather weather(@RequestParam("day") DayEnum day) {

        System.out.println("R");

        return new Weather(counter.incrementAndGet(), generateMessage(day), LocalDate.now());


    }

    String generateMessage(DayEnum day) {

        LocalDate currentMoment = LocalDate.now();
        DayOfWeek dayAsked = currentMoment.getDayOfWeek();
        String message;
        switch (day) {
            case TODAY:
                message = "This " + dayAsked.toString() + " is okay, I guess";
                break;


            case TOMORROW:
                message = "This " + dayAsked.plus(1).toString() + " is okay, I guess";
                break;

            default:
                message = "I don't know what day that is, bucko.";
        }
        return message;
    }
}
