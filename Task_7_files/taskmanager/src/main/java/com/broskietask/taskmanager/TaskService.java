package com.broskietask.taskmanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepo;
    public Task createTask(Task event){
        return taskRepo.save(event);
    }

    public List<Task> readAll(){
        return taskRepo.findAll();
    }

    public Task getByid(Integer id){
        return taskRepo.findById(id).orElse(null);
    }
}
