package com.epam.lab.hospitalspring.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.time.LocalDateTime;

public class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
    @Override
    public JsonElement serialize(LocalDateTime localDateTime, java.lang.reflect.Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(localDateTime.toString());
    }
}