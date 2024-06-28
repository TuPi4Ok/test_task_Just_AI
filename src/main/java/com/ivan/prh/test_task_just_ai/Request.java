package com.ivan.prh.test_task_just_ai;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Data
public class Request {
    private String type;

    private String eventId;

    private double v;

    @JsonDeserialize(using = ObjectToStringDeserializer.class)
    private String object;

    private long groupId;


    public static class ObjectToStringDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            return jsonParser.readValueAsTree().toString();
        }
    }
}
