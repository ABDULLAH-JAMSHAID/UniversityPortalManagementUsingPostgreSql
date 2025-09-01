package main.java.com.universityportal.util;

public class Sql {

    // Checking If Admin Credentials Are Correct

    public static final String checkAdmin = "SELECT username ,password FROM ums.admin";

    // Register Teacher

    public static final String registerTeacher = "INSERT INTO ums.teachers (name,email,password) VALUES (?,?,?)";

    // Check If Login Of Teacher Is Correct

    public static final String loginTeacher = "SELECT id from ums.teachers WHERE email=? AND password=?";

    // Register Student

    public static final String registerStudent = "INSERT INTO ums.students (name,email,password) VALUES (?,?,?)";

    // Check If Login Of Student Is Correct

    public static final String loginStudent = "SELECT id from ums.students WHERE email=? AND password=?";

    // viewAllCourses

    public static final String viewAllCourses = "SELECT id,title,credit_hours FROM ums.courses";

    // View Relevant teachers

    public static final String viewRelevantTeachers="select tc.id as teacher_id ,t.name as teacher_name from ums.teacher_course tc \n" +
            "\n" +
            "join ums.teachers t on t.id=tc.teacher_id \n" +
            "\n" +
            "where tc.course_id=?;";


    public static final String enrollingStudent = "INSERT INTO ums.enrollments " +
            "(student_id ,teacher_course_id ) VALUES (?,?)";

    public static final String viewProfile="select id ,name,email from ums.students where id=?";

    public static final String viewStudentPickedCourse = "SELECT c.title AS course_title\n" +
            "            FROM ums.enrollments e\n" +
            "            JOIN ums.teacher_course tc on tc.id=e.id \n" +
            "            join ums.courses c on c.id=tc.course_id\n" +
            "            WHERE e.student_id = ?;";

    public static final String getEnrollmentId ="select e.id as enrollment_id from ums.enrollments e where student_id=?";

    public static final String viewStudentAttendance = "SELECT attendance_date,status from ums.students_attendance where enrollment_id=?";

    public static final String updateStudentName = "UPDATE ums.students\n" +
            "SET name = ?\n" +
            "WHERE id = ?;";

    public static final String updateStudentEmail = "UPDATE ums.students\n" +
            "SET email = ?\n" +
            "WHERE id = ?;";

    public static final String enrollingTeacher = "INSERT INTO ums.teacher_course " +
            "(teacher_id ,course_id ) VALUES (?,?)";


    public static final String viewPickedCourses = "SELECT tc.id as teacher_course_id ,c.title AS course_title\n" +
            "FROM ums.teacher_course tc\n" +
            "JOIN ums.courses c ON c.id = tc.course_id\n" +
            "WHERE tc.teacher_id = ?;";

    public static final String unselectCourse = "DELETE FROM ums.teacher_course WHERE id=?";

    public static final String viewStudentsInMySection = "SELECT e.id AS student_id , s.name AS student_name\n" +
            "FROM ums.enrollments e\n" +
            "JOIN ums.students s ON e.student_id = s.id\n" +
            "WHERE e.teacher_course_id = ?;";

    public static final String insertStudentAttendances =
            "INSERT INTO ums.students_attendance (enrollment_id,attendance_date, status) " +
                    "VALUES (?, ?, ?::ums.attendance_status)";


    public static final String viewTeacherAttendance = "SELECT attendance_date,status from ums.teachers_attendance where teacher_course_id=?";

    public static final String viewTeacherProfile ="SELECT id,name,email,password from ums.teachers WHERE id=?";

    public static final String updateTeacherName = "UPDATE ums.teachers\n" +
            "SET name = ?\n" +
            "WHERE id = ?;";

    public static final String updateTeacherEmail = "UPDATE ums.teachers\n" +
            "SET email = ?\n" +
            "WHERE id = ?;";




    public static final String viewAllUniversityStudents="SELECT s.id,s.name,s.email,c.title as course_name from ums.enrollments e\n" +
            "join ums.students s on s.id=e.student_id\n" +
            "join ums.teacher_course tc on tc.id=e.id\n" +
            "join ums.courses c on c.id=tc.course_id";

    public static final String viewAllTeachers="select id,name,email from ums.teachers";

    public static final String viewAllEnrollments="select * from ums.viewAllEnrollments";


    public static final String deleteStudent="DELETE FROM ums.students Where id=?";

    public static final String deleteCourse="delete From ums.courses Where id=?";

    public static final String deleteTeacher="delete From ums.teachers Where id=?";

    public static final String viewTeachers = "select * from ums.viewTeachers";


    public static final String insertTeacherAttendances = "INSERT INTO ums.teachers_attendance (teacher_course_id, attendance_date, status) " +
            "VALUES (?, ?, ?)";


    //===============================================Reporting Queries===============================================

    //1: CourseWiseStudentCount

    public static final String CourseWiseStudentCount="select * from ums.CourseWiseStudentCount";

    // 2.	Count Total Courses Of Teacher

    public static final String CountCoursesOfTeacher="select * from ums.CountCoursesOfTeacher";

    //3.	Student Attendance Count

    public static final String StudentAttendanceCount="select * from ums.StudentAttendanceCount";

    //4.	Teacher Attendance Count

    public static final String TeacherAttendanceCount="select * from ums.TeacherAttendanceCount";

    // 5.	Students Attendance Percentage

    public static final String StudentsAttendancePercentage="select * from ums.StudentsAttendancePercentage";


}
