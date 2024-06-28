package com.ivan.prh.test_task_just_ai.command.impl;

import com.ivan.prh.test_task_just_ai.command.Command;
import com.ivan.prh.test_task_just_ai.dto.Request;
import org.springframework.stereotype.Service;

import static com.ivan.prh.test_task_just_ai.constant.CommandConstant.COMMAND_NOT_SUPPORTED_MESSAGE;

@Service("not_supported")
public class NotSupportedCommand implements Command {

    @Override
    public String execute(Request request) {
        return COMMAND_NOT_SUPPORTED_MESSAGE;
    }
}
