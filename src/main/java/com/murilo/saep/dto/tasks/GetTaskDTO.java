package com.murilo.saep.dto.tasks;

import com.murilo.saep.entities.Task;
import com.murilo.saep.entities.User;
import com.murilo.saep.enums.TaskPriority;
import com.murilo.saep.enums.TaskStatus;

import java.time.LocalDateTime;

public record GetTaskDTO(Long id, String task_description, String sector, TaskStatus taskStatus, TaskPriority priority, User user, LocalDateTime registered_date) {
    public GetTaskDTO(Task task) {
        this(task.getId_task(), task.getTask_description(), task.getSector(), task.getTaskStatus(), task.getPriority(), task.getUser(), task.getRegisteredDate());
    }
}
