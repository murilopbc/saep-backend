package com.murilo.saep.services;

import com.murilo.saep.dto.tasks.GetTaskDTO;
import com.murilo.saep.dto.tasks.PostTaskDTO;
import com.murilo.saep.dto.tasks.PutTaskDTO;
import com.murilo.saep.enums.TaskStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {
    GetTaskDTO getTaskById(Long id);
    List<GetTaskDTO> getAllTasks();
    GetTaskDTO createTask(PostTaskDTO data);
    GetTaskDTO updateTask(Long id, PutTaskDTO data);
    GetTaskDTO deleteTask(Long id);
    ResponseEntity<Void> updateTaskStatus(Long id, TaskStatus status);
}
