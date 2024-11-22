package com.murilo.saep.controllers;


import com.murilo.saep.dto.tasks.GetTaskDTO;
import com.murilo.saep.dto.tasks.PostTaskDTO;
import com.murilo.saep.dto.tasks.PutTaskDTO;
import com.murilo.saep.enums.TaskStatus;
import com.murilo.saep.services.TaskService;
import com.murilo.saep.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<GetTaskDTO> createTask(@RequestBody @Valid PostTaskDTO data, UriComponentsBuilder uriBuilder){

        GetTaskDTO task = taskService.createTask(data);

        var uri = uriBuilder.path("/api/tasks/{id}").buildAndExpand(task.id()).toUri();

        return ResponseEntity.created(uri).body(task);
    }

    @GetMapping
    public ResponseEntity <List<GetTaskDTO>> getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTaskDTO> getTaskById(@PathVariable Long id) {

        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GetTaskDTO> updateTask(@PathVariable Long id, @RequestBody @Valid PutTaskDTO data) {
        return new ResponseEntity<>(taskService.updateTask(id, data), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<GetTaskDTO> deleteTask(@PathVariable Long id){

        return new ResponseEntity<>(taskService.deleteTask(id), HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateTaskStatus(
            @PathVariable Long id,
            @RequestBody TaskStatus taskStatus) {
        taskService.updateTaskStatus(id, taskStatus);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status")
    public ResponseEntity<ResponseEntity<List<String>>> getTaskStatus(){
        return new ResponseEntity<>(taskService.getTaskStatus(), HttpStatus.OK);
    }

    @GetMapping("/priority")
    public ResponseEntity<ResponseEntity<List<String>>> getTaskPriority(){
        return new ResponseEntity<>(taskService.getTaskPriority(), HttpStatus.OK);
    }


    @GetMapping("/do")
    public ResponseEntity<List<GetTaskDTO>> getToDoTasks() {
        List<GetTaskDTO> tasks = taskService.getTaskByStatus(TaskStatus.FAZER);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/doing")
    public ResponseEntity<List<GetTaskDTO>> getDoingTasks() {
        List<GetTaskDTO> tasks = taskService.getTaskByStatus(TaskStatus.FAZENDO);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/done")
    public ResponseEntity<List<GetTaskDTO>> getDoneTasks() {
        List<GetTaskDTO> tasks = taskService.getTaskByStatus(TaskStatus.PRONTO);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

}
