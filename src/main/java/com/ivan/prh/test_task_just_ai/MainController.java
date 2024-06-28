package com.ivan.prh.test_task_just_ai;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final Map<String, Command> commands;

    @PostMapping
    public String access(@RequestBody Request request) {
        return commands.get(request.getType()).execute(request);
    }
}
