package com.murilo.saep.entities;

import com.murilo.saep.dto.tasks.PostTaskDTO;
import com.murilo.saep.dto.tasks.PutTaskDTO;
import com.murilo.saep.enums.TaskPriority;
import com.murilo.saep.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "tasks")
@Entity(name = "tasks")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_task;

    private String task_description;

    private String sector;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Enumerated(EnumType.STRING)
    private TaskPriority priority;


     @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(name = "registered_date", updatable = false, nullable = false)
    @CreationTimestamp
    private LocalDateTime registeredDate;


    public Task(User user, PostTaskDTO data){
        this.task_description = data.task_description();
        this.sector = data.sector();
        this.taskStatus = TaskStatus.FAZER;
        this.priority = data.priority();
        this.user = user;
    }


    public void updateTaskInfo(User user,@Valid PutTaskDTO data){
        if (data.user() != null){
            this.user = user;
        }

        if (data.task_description() != null) {
            this.task_description = data.task_description();
        }

        if (data.sector() != null) {
            this.sector = data.sector();
        }

        if (data.priority() != null) {
            this.priority = data.priority();
        }

        if (data.taskStatus() != null) {
            this.taskStatus = data.taskStatus();
        }


    }



}