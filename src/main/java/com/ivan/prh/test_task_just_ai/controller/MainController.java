package com.ivan.prh.test_task_just_ai.controller;


import com.ivan.prh.test_task_just_ai.command.Command;
import com.ivan.prh.test_task_just_ai.dto.Request;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final Map<String, Command> commands;

    @PostMapping
    public String access(@RequestBody Request request) {
        return commands.getOrDefault(request.type(), commands.get("not_supported")).execute(request);
    }
}
