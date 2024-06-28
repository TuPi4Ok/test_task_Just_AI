package com.ivan.prh.test_task_just_ai;

import com.fasterxml.jackson.annotation.JsonAlias;

public record NewMessageRequest(
        @JsonAlias("user_id")
        int userId,
        @JsonAlias("random_id")
        int RandomId,
        String message
) {
}
