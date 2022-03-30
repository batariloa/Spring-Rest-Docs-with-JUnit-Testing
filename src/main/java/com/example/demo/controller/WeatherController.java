package com.example.demo.controller;

import com.example.demo.converter.StringToDayEnum;
import com.example.demo.entity.DayEnum;
import com.example.demo.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class WeatherController {

    private static final String template = "report";
    private final AtomicLong counter = new AtomicLong();


    StringToDayEnum stde;

    @GetMapping("/weather/{day}")
    public Weather weather(@PathVariable("day") DayEnum day) {

        System.out.println("R");

        return generateWeather(day);


    }

    Weather generateWeather(DayEnum day) {

        long id = counter.incrementAndGet();
        LocalDate currentMoment = LocalDate.now();
        Weather weather;
        String message;
        switch (day) { //switch to easier add further options


            case TOMORROW:
                message = "This " + currentMoment.plusDays(1).getDayOfWeek().toString() + " is okay, I guess";
                weather = new Weather(id, message, currentMoment.plusDays(1));
                break;

            default:
                message = "This " + currentMoment.plusDays(1).getDayOfWeek().toString() + " is okay, I guess";  //Today by default
                weather = new Weather(id, message, currentMoment);
        }
        return weather;
    }


}
