package com.ivan.prh.test_task_just_ai;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

@Service("confirmation")
@RequiredArgsConstructor
public class Access implements Command {

    private final RestClient restClient;

    @Value("${accessToken}")
    private String accessToken;

    @Value("${groupId}")
    private String groupId;

    @Override
    public String execute(Request request) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("https://api.vk.ru/method/groups.getCallbackConfirmationCode")
                .queryParam("group_id", groupId)
                .queryParam("v", "5.199");

        return restClient.get()
                .uri(builder.toUriString())
                .header("Authorization", "Bearer " + accessToken)
                .retrieve().body(AccessResponse.class).response().code();
    }
}
