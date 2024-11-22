package com.murilo.saep.dto.tasks;

import com.murilo.saep.enums.TaskPriority;
import com.murilo.saep.enums.TaskStatus;

public record PutTaskDTO(String task_description, String sector, TaskPriority priority, TaskStatus taskStatus, Long user) {
}
