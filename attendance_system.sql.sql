-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.45 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  12.16.0.7229
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- 导出 attendance_system 的数据库结构
CREATE DATABASE IF NOT EXISTS `attendance_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `attendance_system`;

-- 导出  表 attendance_system.attendance 结构
CREATE TABLE IF NOT EXISTS `attendance` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `student_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '学生姓名',
  `course_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程编号',
  `check_in_time` datetime NOT NULL COMMENT '签到时间',
  `seat_row` tinyint DEFAULT NULL COMMENT '座位行号',
  `seat_col` tinyint DEFAULT NULL COMMENT '座位列号',
  `status` varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'NORMAL' COMMENT '状态: NORMAL正常/LATE迟到/EARLY早退/ABSENT缺勤',
  `ip` varchar(15) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '签到IP地址',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_student_id` (`student_id`),
  CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='考勤记录表';

-- 正在导出表  attendance_system.attendance 的数据：~0 rows (大约)
INSERT INTO `attendance` (`id`, `student_id`, `student_name`, `course_id`, `check_in_time`, `seat_row`, `seat_col`, `status`, `ip`, `create_time`) VALUES
	(1, '20230001', '张三', 'CS101', '2025-04-01 08:30:00', 1, 1, 'NORMAL', '192.168.1.100', '2026-04-08 19:11:38'),
	(2, '20230002', '李四', 'CS101', '2025-04-01 08:35:00', 2, 3, 'LATE', '192.168.1.101', '2026-04-08 19:11:38'),
	(3, '20230003', '王五', 'CS101', '2025-04-01 08:28:00', 3, 2, 'NORMAL', '192.168.1.102', '2026-04-08 19:11:38');

-- 导出  表 attendance_system.course 结构
CREATE TABLE IF NOT EXISTS `course` (
  `course_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程编号 (主键)',
  `course_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程名称',
  `class_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '班级名称',
  `teacher_id` bigint NOT NULL COMMENT '教师ID',
  `classroom_name` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教室名称',
  `rows` tinyint DEFAULT NULL COMMENT '教室行数',
  `cols` tinyint DEFAULT NULL COMMENT '教室列数',
  `exclude_seats` varchar(200) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '不可坐的座位位置 (格式: row,col;row,col)',
  `weekday` tinyint DEFAULT NULL COMMENT '星期几 (1-7)',
  `start_week` int DEFAULT NULL COMMENT '开始周次',
  `end_week` int DEFAULT NULL COMMENT '结束周次',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`course_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='课程表';

-- 正在导出表  attendance_system.course 的数据：~0 rows (大约)
INSERT INTO `course` (`course_id`, `course_name`, `class_name`, `teacher_id`, `classroom_name`, `rows`, `cols`, `exclude_seats`, `weekday`, `start_week`, `end_week`, `create_time`) VALUES
	('CS101', 'Java程序设计', '软件工程1班', 2, 'A101', 5, 6, '3,4;5,6', 1, 1, 16, '2026-04-08 19:11:12');

-- 导出  表 attendance_system.course_selection 结构
CREATE TABLE IF NOT EXISTS `course_selection` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `student_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '学号',
  `student_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '学生姓名',
  `gender` char(2) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '性别',
  `course_id` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程编号',
  `select_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '选课时间',
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `course_selection_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='选课表';

-- 正在导出表  attendance_system.course_selection 的数据：~0 rows (大约)
INSERT INTO `course_selection` (`id`, `student_id`, `student_name`, `gender`, `course_id`, `select_time`) VALUES
	(1, '20230001', '张三', '男', 'CS101', '2026-04-08 19:11:28'),
	(2, '20230002', '李四', '女', 'CS101', '2026-04-08 19:11:28'),
	(3, '20230003', '王五', '男', 'CS101', '2026-04-08 19:11:28'),
	(4, '20230004', '赵六', '男', 'CS101', '2026-04-08 19:11:28'),
	(5, '20230005', '小明', '男', 'CS101', '2026-04-08 19:11:28');

-- 导出  表 attendance_system.user 结构
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码 (BCrypt加密)',
  `real_name` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '真实姓名',
  `role` varchar(20) COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'TEACHER' COMMENT '角色: TEACHER/ADMIN',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

-- 正在导出表  attendance_system.user 的数据：~0 rows (大约)
INSERT INTO `user` (`id`, `username`, `password`, `real_name`, `role`, `create_time`) VALUES
	(1, 'admin', '123456', '系统管理员', 'ADMIN', '2026-04-08 19:09:33'),
	(2, 'zhang_teacher', '123456', '张老师', 'TEACHER', '2026-04-08 19:09:33');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
