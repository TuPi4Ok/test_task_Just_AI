package com.ivan.prh.test_task_just_ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Random;

@Component("message_new")
@RequiredArgsConstructor
@Slf4j
public class NewMessage implements Command {

    private final ObjectMapper objectMapper;

    private final RestClient restClient;

    @Value("${accessToken}")
    private String accessToken;

    @Override
    @SneakyThrows
    public String execute(Request request) {
        JsonNode jsonNode = objectMapper.readTree(request.getObject());
        String messageText = jsonNode.path("message").path("text").asText();
        messageText = "Вы написали: " + messageText;
        String userId = jsonNode.path("message").path("from_id").asText();

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("https://api.vk.ru/method/messages.send")
                .queryParam("user_id", userId)
                .queryParam("v", "5.199")
                .queryParam("random_id", new Random().nextInt());

        restClient.get()
                .uri(builder.toUriString() + "&message=" + messageText)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve();
        return "ok";
    }
}
