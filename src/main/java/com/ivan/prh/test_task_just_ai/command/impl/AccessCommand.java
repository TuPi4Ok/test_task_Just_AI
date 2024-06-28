package com.ivan.prh.test_task_just_ai.command.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivan.prh.test_task_just_ai.dto.Request;
import com.ivan.prh.test_task_just_ai.command.Command;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import static com.ivan.prh.test_task_just_ai.constant.CommandConstant.VK_API_URL;

@Service("confirmation")
@RequiredArgsConstructor
public class AccessCommand implements Command {

    public static final String GET_CALLBACK_CONFIRMATION_CODE = "groups.getCallbackConfirmationCode";

    private final RestClient restClient;

    @Value("${accessToken}")
    private String accessToken;

    @Value("${groupId}")
    private String groupId;

    @Value("${apiVersion}")
    private String apiVersion;

    private final ObjectMapper objectMapper;

    @Override
    public String execute(Request request) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(VK_API_URL + GET_CALLBACK_CONFIRMATION_CODE)
                .queryParam("group_id", groupId)
                .queryParam("v", apiVersion);

        String AccessResponseBody = restClient.get()
                .uri(builder.toUriString())
                .header("Authorization", "Bearer " + accessToken)
                .retrieve().body(String.class);
        return getAccessCode(AccessResponseBody);
    }

    @SneakyThrows
    private String getAccessCode(String AccessResponseBody) {
        return objectMapper.readTree(AccessResponseBody).path("response").path("code").asText();
    }
}
