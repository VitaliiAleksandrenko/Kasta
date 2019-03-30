package test.org.kastatest.data.facilities;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class RestDateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private final DateFormat mDateFormat;

    public RestDateTypeAdapter(String datePattern) {
        mDateFormat = new SimpleDateFormat(datePattern, Locale.US);
        mDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        synchronized (mDateFormat) {
            String dateFormatAsString = mDateFormat.format(src);
            return new JsonPrimitive(dateFormatAsString);
        }
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json == null || !json.isJsonPrimitive() || !((JsonPrimitive)json).isString()) {
            throw new JsonParseException("Expected date as string");
        }
        synchronized (mDateFormat) {
            try {
                return mDateFormat.parse(json.getAsString());
            } catch (ParseException e) {
                throw new JsonSyntaxException(json.getAsString(), e);
            }
        }
    }
}
