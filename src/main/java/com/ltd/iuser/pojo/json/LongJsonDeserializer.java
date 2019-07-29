package com.ltd.iuser.pojo.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class LongJsonDeserializer extends JsonDeserializer<Long> {
    @Override
    public Long deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String text = jsonParser.getText();
        if (StringUtils.isBlank(text)) {
            return null;
        }
        return Long.parseLong(text);
    }
}
