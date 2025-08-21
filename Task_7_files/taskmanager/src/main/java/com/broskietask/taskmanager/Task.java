package com.broskietask.taskmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "taskmanager")
public class Task {
    @Id
    @GeneratedValue
    Integer Id;
    String Title;
    String Description;
}
