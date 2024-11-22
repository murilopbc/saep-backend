package com.murilo.saep.repositories;

import com.murilo.saep.entities.Task;
import com.murilo.saep.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByTaskStatus(TaskStatus taskStatus);
}
