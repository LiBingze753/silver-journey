package com.example.attendance.repository;

import com.example.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
    // 根据课程 ID 查询考勤记录
    List<Attendance> findByCourseId(String courseId);

    // 根据学生 ID 查询
    List<Attendance> findByStudentId(String studentId);

    // 根据状态查询
    List<Attendance> findByStatus(String status);
}