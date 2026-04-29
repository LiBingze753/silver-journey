package com.example.attendance.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "student_id", length = 20)
    private String studentId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "gender", length = 2)
    private String gender;

    @Column(name = "class_name", length = 50)
    private String className;
}