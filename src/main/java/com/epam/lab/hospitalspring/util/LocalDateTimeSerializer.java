package com.epam.lab.hospitalspring.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
    private Locale locale;
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, java.lang.reflect.Type type, JsonSerializationContext jsonSerializationContext) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale);
        return new JsonPrimitive(dateTimeFormatter.format(localDateTime));
    }
}