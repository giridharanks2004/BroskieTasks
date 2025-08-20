package com.broskietask.taskmanager;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/taskmanger")
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
}
