package com.ivan.prh.test_task_just_ai.command.impl;

import com.ivan.prh.test_task_just_ai.dto.Request;
import com.ivan.prh.test_task_just_ai.command.Command;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Random;

import static com.ivan.prh.test_task_just_ai.constant.CommandConstant.OK_MESSAGE;
import static com.ivan.prh.test_task_just_ai.constant.CommandConstant.VK_API_URL;

@Service("message_new")
@RequiredArgsConstructor
@Slf4j
public class NewMessageCommand implements Command {

    public static final String MESSAGES_SEND = "messages.send";

    private final RestClient restClient;

    @Value("${accessToken}")
    private String accessToken;

    @Value("${addedText}")
    private String addedText;

    @Value("${apiVersion}")
    private String apiVersion;

    @Override
    @SneakyThrows
    public String execute(Request request) {
        String messageText = addedText + request.object()
                .path("message")
                .path("text")
                .asText();

        String userId = request.object()
                .path("message")
                .path("from_id")
                .asText();

        String uriString = UriComponentsBuilder
                .fromHttpUrl(VK_API_URL + MESSAGES_SEND)
                .queryParam("user_id", userId)
                .queryParam("v", apiVersion)
                .queryParam("random_id", new Random().nextInt())
                .queryParam("message", messageText)
                .build(false)
                .toUriString();

        restClient.get()
                .uri(uriString)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve();
        return OK_MESSAGE;
    }
}
