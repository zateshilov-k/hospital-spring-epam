package com.epam.lab.hospitalspring.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;
import java.util.Locale;

public class GsonFactory {
    public static Gson buildGson(Locale locale) {
        GsonBuilder builder = new GsonBuilder();
        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer();
        localDateTimeSerializer.setLocale(locale);
        builder.registerTypeAdapter(LocalDateTime.class, localDateTimeSerializer);
        return builder.excludeFieldsWithoutExposeAnnotation().create();
    }
}
