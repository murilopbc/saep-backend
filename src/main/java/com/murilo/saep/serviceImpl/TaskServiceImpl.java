package com.murilo.saep.serviceImpl;

import com.murilo.saep.dto.tasks.GetTaskDTO;
import com.murilo.saep.dto.tasks.PostTaskDTO;
import com.murilo.saep.dto.tasks.PutTaskDTO;
import com.murilo.saep.entities.Task;
import com.murilo.saep.enums.TaskPriority;
import com.murilo.saep.enums.TaskStatus;
import com.murilo.saep.exceptions.NotFoundException;
import com.murilo.saep.repositories.TaskRepository;
import com.murilo.saep.repositories.UserRepository;
import com.murilo.saep.services.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;



    @Override
    @Transactional
    public GetTaskDTO createTask(PostTaskDTO data) {
        var user = userRepository.findById(data.user()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Task task = new Task(user, data);
        this.taskRepository.save(task);

        return new GetTaskDTO(task);
    }


    @Override
    public List<GetTaskDTO> getAllTasks() {
        return this.taskRepository.findAll().stream().map(GetTaskDTO::new).toList();
    }


    @Override
    public GetTaskDTO getTaskById(Long id) {
        return new GetTaskDTO(this.taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found!")));
    }

    @Override
    @Transactional
    public GetTaskDTO updateTask(Long id, PutTaskDTO data) {

        var user = userRepository.findById(data.user()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found!"));
        task.updateTaskInfo(user,data);
        return new GetTaskDTO(task);
    }


    @Override
    @Transactional
    public GetTaskDTO deleteTask(Long id) {
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found!"));
        this.taskRepository.deleteById(id);

        return new GetTaskDTO(task);
    }

    @Override
    @Transactional
    public void updateTaskStatus(Long id, TaskStatus status){
        Task task = this.taskRepository.findById(id).orElseThrow(() -> new NotFoundException("Task not found!"));
        task.setTaskStatus(status);
        taskRepository.save(task);
        ResponseEntity.ok().build();
    }



    @Override
    public ResponseEntity<List<String>> getTaskStatus() {
        List<String> categories = Arrays.stream(TaskStatus.values())
                .map(TaskStatus::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }

    @Override
    public ResponseEntity<List<String>> getTaskPriority() {
        List<String> categories = Arrays.stream(TaskPriority.values())
                .map(TaskPriority::name)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }


    @Override
    public List<GetTaskDTO> getTaskByStatus(TaskStatus taskStatus) {
        return this.taskRepository.findByTaskStatus(taskStatus)
                .stream()
                .map(GetTaskDTO::new)
                .toList();
    }

}
