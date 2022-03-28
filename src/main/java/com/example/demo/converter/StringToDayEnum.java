package com.example.demo.converter;

import com.example.demo.entity.DayEnum;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class StringToDayEnum implements Converter<String, DayEnum> {
    {

    }

    @Override
    public DayEnum convert(String source) {

        try {
            if (source.isEmpty()) {
                return DayEnum.TODAY;
            } else {
                return DayEnum.valueOf(source.trim().toUpperCase(Locale.ROOT));
            }
        } catch (Exception e) {
            return DayEnum.TODAY;
        }

    }


}
