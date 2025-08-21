package com.broskietask.taskmanager;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/taskmanger")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    private TaskService TService;
    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task event){
        return new ResponseEntity<>(TService.createTask(event),HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> ReadAllTask(){
        return new ResponseEntity<>(TService.readAll(),HttpStatus.OK);
    }

    @GetMapping("/by")
    public ResponseEntity<?> GetbyId(@RequestParam("TaskId") Integer id){
        ResponseEntity<Task> response = new ResponseEntity<>(TService.getByid(id),HttpStatus.OK);
        if(response.hasBody()){
            return response;
        }
        return new ResponseEntity<>("Task not found enter the correct task id", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTask(@RequestBody Task event){
        Task update = TService.UpdateTask(event); 
        if(update != null){
            return new ResponseEntity<>("updated Task id "+update.Id,HttpStatus.OK);
        }
        return new ResponseEntity<>("updation not done task does not exists!",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestParam("taskId") Integer id){
        try {
            TService.deleteTask(id);
            return new ResponseEntity<>("deletion done!",HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("deletion failed",HttpStatus.NOT_FOUND);
        }
    }
}
