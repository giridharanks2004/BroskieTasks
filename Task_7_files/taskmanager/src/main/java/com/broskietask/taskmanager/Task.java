package com.broskietask.taskmanager;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "taskmanager")
public class Task {
    @Id
    Integer Id;
    String Title;
    String Description;
}
