package com.broskietask.taskmanager;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @Test
    void testCreateTask() throws Exception {
        Task task = new Task(1, "Test Task", "Desc");

        when(taskService.createTask(task)).thenReturn(task);

        mockMvc.perform(post("/api/taskmanger/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"title\":\"Test Task\",\"description\":\"Desc\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test Task"));
    }


    @Test
    void testReadAllTasks() throws Exception {
        Task task = new Task(1, "Task1", "Desc1");
        when(taskService.readAll()).thenReturn(Arrays.asList(task));

        mockMvc.perform(get("/api/taskmanger/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void testGetById_Found() throws Exception {
        Task task = new Task(1, "Task1", "Desc1");
        when(taskService.getByid(1)).thenReturn(task);

        mockMvc.perform(get("/api/taskmanger/by")
                .param("TaskId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }


    @Test
    void testGetById_NotFound() throws Exception {
        when(taskService.getByid(99)).thenReturn(null);

        mockMvc.perform(get("/api/taskmanger/by")
                .param("TaskId", "99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Task not found enter the correct task id"));
    }

    @Test
    void testUpdateTask_Success() throws Exception {
        Task updatedTask = new Task(1, "Updated", "Desc");
        when(taskService.UpdateTask(updatedTask)).thenReturn(updatedTask);

        mockMvc.perform(put("/api/taskmanger/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"title\":\"Updated\",\"description\":\"Desc\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("updated Task id 1"));
    }


    @Test
    void testUpdateTask_NotFound() throws Exception {
        Task nonExistent = new Task(2, "X", "Y");
        when(taskService.UpdateTask(nonExistent)).thenReturn(null);

        mockMvc.perform(put("/api/taskmanger/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"title\":\"X\",\"description\":\"Y\"}"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("updation not done task does not exists!"));
    }


    @Test
    void testDeleteTask_Success() throws Exception {
        doNothing().when(taskService).deleteTask(1);

        mockMvc.perform(delete("/api/taskmanger/delete")
                .param("taskId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("deletion done!"));
    }

    
    @Test
    void testDeleteTask_Failed() throws Exception {
        doThrow(new RuntimeException("Not found")).when(taskService).deleteTask(99);

        mockMvc.perform(delete("/api/taskmanger/delete")
                .param("taskId", "99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("deletion failed"));
    }
}
