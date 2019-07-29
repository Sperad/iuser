package com.ltd.iuser.pojo.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class LongJsonSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {
        if (null == value) {
            return;
        }
        jsonGenerator.writeString(String.valueOf(value));
    }
}
