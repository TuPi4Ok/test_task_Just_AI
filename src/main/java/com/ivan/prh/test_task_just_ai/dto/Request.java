package com.ivan.prh.test_task_just_ai.dto;

import com.fasterxml.jackson.databind.JsonNode;

public record Request(
    String type,
    String eventId,
    double v,
    JsonNode object,
    long groupId
){
}
