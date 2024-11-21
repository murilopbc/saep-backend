package com.murilo.saep.dto.tasks;

import com.murilo.saep.enums.TaskPriority;

public record PutTaskDTO(String task_description, String sector, TaskPriority priority, Long user) {
}
