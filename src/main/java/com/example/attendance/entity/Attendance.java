package com.example.attendance.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "student_id", length = 20, nullable = false)
    private String studentId;

    @Column(name = "student_name", length = 50, nullable = false)
    private String studentName;

    @Column(name = "course_id", length = 20, nullable = false)
    private String courseId;

    @Column(name = "check_in_time", nullable = false)
    private LocalDateTime checkInTime;

    @Column(name = "seat_row")
    private Integer seatRow;

    @Column(name = "seat_col")
    private Integer seatCol;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "ip", length = 15)
    private String ip;
}