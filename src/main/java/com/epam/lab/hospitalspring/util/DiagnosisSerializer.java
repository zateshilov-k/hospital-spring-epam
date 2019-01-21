package com.epam.lab.hospitalspring.util;

import com.epam.lab.hospitalspring.model.Diagnosis;
import com.google.gson.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class DiagnosisSerializer implements JsonSerializer<Diagnosis> {
    private Locale locale;
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    String bundleName = "messages";
    @Override
    public JsonElement serialize(Diagnosis diagnosis, java.lang.reflect.Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale);
        jsonObject.add("time",new JsonPrimitive(dateTimeFormatter.format(diagnosis.getTime())));
        jsonObject.add("id",new JsonPrimitive(diagnosis.getId()));
        jsonObject.add("description",new JsonPrimitive(diagnosis.getDescription()));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName,locale);
        if (diagnosis.getOpened()) {
            jsonObject.add("opened",new JsonPrimitive(resourceBundle.getString("diagnosisIsOpened")));
        } else {
            jsonObject.add("opened",new JsonPrimitive(resourceBundle.getString("diagnosisIsNotOpened")));
        }
        return jsonObject;
    }
}