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
            "FROM ums.enrollments e\n" +
            "JOIN ums.teacher_course tc on tc.id=e.id \n" +
            "join ums.courses c on c.id=tc.course_id\n" +
            "WHERE e.student_id = ?;";

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

    public static final String viewAllEnrollments="SELECT \n" +
            "st.id AS student_id,\n" +
            "st.name AS student_name,\n" +
            "c.title AS course_name\n" +
            "FROM ums.enrollments e\n" +
            "join ums.teacher_course tc on tc.id=e.id\n" +
            "JOIN ums.students st ON st.id = e.student_id\n" +
            "JOIN ums.courses c ON c.id = tc.course_id";


    public static final String deleteStudent="DELETE FROM ums.students Where id=?";

    public static final String deleteCourse="delete From ums.courses Where id=?";

    public static final String deleteTeacher="delete From ums.teachers Where id=?";

    public static final String viewTeachers =
            "select tc.id as teacher_enrollment_id ,t.name as teacher_name,c.title as course_name from ums.teacher_course tc \n" +
                    "join ums.courses c on c.id=tc.course_id " +
                    "join ums.teachers t on t.id=tc.teacher_id";


    public static final String insertTeacherAttendances = "INSERT INTO ums.teachers_attendance (teacher_course_id, attendance_date, status) " +
            "VALUES (?, ?, ?)";


    //===============================================Reporting Queries===============================================

    //1: CourseWiseStudentCount

    public static final String CourseWiseStudentCount="SELECT \n" +
            "    c.title AS course_name,\n" +
            "    COUNT(e.student_id) AS total_students\n" +
            "FROM ums.enrollments e\n" +
            "JOIN ums.teacher_course tc ON e.teacher_course_id = tc.id\n" +
            "JOIN ums.courses c ON tc.course_id = c.id\n" +
            "GROUP BY c.title\n" +
            "ORDER BY total_students DESC;";

    // 2.	Teacher-Course wise Student Count

    public static final String TeacherCourseWiseStudentCount="select t.name as teacher,\n" +
            "c.title as course,\n" +
            "count (student_id) as total_students\n" +
            "from ums.enrollments e\n" +
            "join ums.teacher_course tc on tc.id=e.teacher_course_id\n" +
            "join ums.teachers t on t.id=tc.teacher_id\n" +
            "join ums.courses c on c.id=tc.course_id\n" +
            "group by c.title ,t.name";

    // 3.	Count Total Courses Of Teacher

    public static final String CountCoursesOfTeacher="select t.name as teacher,\n" +
            "count (course_id) as total_courses_assigned\n" +
            "from ums.teacher_course tc\n" +
            "join ums.teachers t on t.id=tc.teacher_id\n" +
            "group by t.name ";


    //4.	Student Attendance Count

    public static final String StudentAttendanceCount="select s.name as student_name,\n" +
            "sum(case when sa.status='present' then 1 else 0 end) as present,\n" +
            "sum(case when sa.status='absent' then 1 else 0 end ) as absent,\n" +
            "sum(case when sa.status='late' then 1 else 0 end) as late\n" +
            "from ums.students_attendance sa\n" +
            "join ums.enrollments e on e.id=sa.enrollment_id\n" +
            "join ums.students s on s.id=e.student_id\n" +
            "group by s.name ";

    //5.	Teacher Attendance Count

    public static final String TeacherAttendanceCount="select t.name as teacher_name,\n" +
            "sum(case when ta.status='present' then 1 else 0 end) as present,\n" +
            "sum(case when ta.status='absent' then 1 else 0 end ) as absent,\n" +
            "sum(case when ta.status='late' then 1 else 0 end) as late\n" +
            "from ums.teachers_attendance ta\n" +
            "join ums.teacher_course tc on tc.id=ta.teacher_course_id\n" +
            "join ums.teachers t on t.id=tc.teacher_id\n" +
            "group by t.name ";

    // 6.	Students Attendance Percentage

    public static final String StudentsAttendancePercentage="SELECT \n" +
            "    st.id AS student_id,\n" +
            "    st.name AS student_name,\n" +
            "    COUNT(sa.id) AS total_classes,\n" +
            "    SUM(CASE WHEN sa.status = 'present' THEN 1 ELSE 0 END) AS total_present,\n" +
            "    (SUM(CASE WHEN sa.status = 'present' THEN 1 ELSE 0 END) * 100 / COUNT(sa.id)) AS present_percentage\n" +
            "FROM ums.students_attendance sa\n" +
            "JOIN ums.enrollments e ON sa.enrollment_id = e.id\n" +
            "JOIN ums.students st ON e.student_id = st.id\n" +
            "GROUP BY st.id, st.name\n" +
            "ORDER BY present_percentage ASC;";


}
