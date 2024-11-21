package com.murilo.saep.entities;

import com.murilo.saep.dto.tasks.PostTaskDTO;
import com.murilo.saep.dto.tasks.PutTaskDTO;
import com.murilo.saep.dto.users.PostUserDTO;
import com.murilo.saep.enums.TaskPriority;
import com.murilo.saep.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Table(name = "tasks")
@Entity(name = "tasks")
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_task;

    private String task_description;

    private String sector;

    @Enumerated(EnumType.STRING)
    private TaskStatus task_status;

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
        this.task_status = TaskStatus.FAZER;
        this.priority = data.priority();
        this.user = user;
    }


//    public void updateTaskInfo(User user,@Valid PutTaskDTO data){
//        if (data.user() != null){
//            this.localName = data.local_name();
//        }
//
//        if (data.localStatus() != null) {
//            this.localStatus = data.localStatus();
//        }
//
//
//    }



}