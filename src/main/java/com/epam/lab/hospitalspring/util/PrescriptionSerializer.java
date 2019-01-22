package com.epam.lab.hospitalspring.util;

import com.epam.lab.hospitalspring.model.Prescription;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class PrescriptionSerializer implements JsonSerializer<Prescription> {
    Locale locale;
    public void setLocale(Locale locale){
        this.locale = locale;
    }
    String bundleName = "messages";
    @Override
    public JsonElement serialize(Prescription prescription, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("id",new JsonPrimitive(prescription.getId()));
        jsonObject.add("description",new JsonPrimitive(prescription.getDescription()));
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleName,locale);
        if (prescription.getDone()) {
            jsonObject.add("done",new JsonPrimitive(resourceBundle.getString("prescriptionIsDone")));
        } else {
            jsonObject.add("done",new JsonPrimitive(resourceBundle.getString("prescriptionIsNotDone")));
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(locale);
        jsonObject.add("time",new JsonPrimitive(dateTimeFormatter.format(prescription.getTime())));
        switch (prescription.getType()) {
            case DRUG:
                jsonObject.add("type",new JsonPrimitive(resourceBundle.getString("drug")));
                break;
            case OPERATION:
                jsonObject.add("type",new JsonPrimitive(resourceBundle.getString("operation")));
                break;
            case PROCEDURE:
                jsonObject.add("type",new JsonPrimitive(resourceBundle.getString("procedure")));
                break;
            default:
                jsonObject.add("type",new JsonPrimitive(resourceBundle.getString("default type")));
        }
        return jsonObject;
    }
}
