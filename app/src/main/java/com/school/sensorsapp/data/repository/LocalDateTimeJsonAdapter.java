package com.school.sensorsapp.data.repository;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonWriter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.ToJson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class LocalDateTimeJsonAdapter implements JsonAdapter.Factory {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
        if (type.equals(LocalDateTime.class)) {
            return new JsonAdapter<LocalDateTime>() {
                @FromJson
                @Override
                public LocalDateTime fromJson(JsonReader reader) throws IOException {
                    return LocalDateTime.parse(reader.nextString(), FORMATTER);
                }

                @ToJson
                @Override
                public void toJson(JsonWriter writer, LocalDateTime value) throws IOException {
                    writer.value(FORMATTER.format(value));
                }
            };
        } else {
            return null;
        }
    }
}