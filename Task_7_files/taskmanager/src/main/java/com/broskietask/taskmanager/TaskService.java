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

    public Task UpdateTask(Task event){
        if(getByid(event.Id) == null){
            return null;
        }
        return taskRepo.save(event);
    }

    public void deleteTask(Integer id) throws Exception{
        Task event = getByid(id);
        if(event == null){
            throw new Exception("Task not found");
        }
        
        taskRepo.delete(event);
    }
}
