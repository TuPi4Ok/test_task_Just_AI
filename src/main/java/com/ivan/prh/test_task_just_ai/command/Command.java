package com.ivan.prh.test_task_just_ai.command;

import com.ivan.prh.test_task_just_ai.dto.Request;

public interface Command {

    String execute(Request request);
}
