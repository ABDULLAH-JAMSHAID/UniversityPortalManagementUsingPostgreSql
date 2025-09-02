package main.java.com.universityportal.repository;
import main.java.com.universityportal.controller.Session;
import main.java.com.universityportal.model.StudentAttendance;
import main.java.com.universityportal.model.Teacher;
import main.java.com.universityportal.util.DBConnection;
import main.java.com.universityportal.util.Sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TeacherRepository {

    Scanner sc=new Scanner(System.in);

    public boolean registerTeacher(Teacher teacher){

        try{
            Connection connection= DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.registerTeacher);

            preparedStatement.setString(1,teacher.getName());
            preparedStatement.setString(2,teacher.getEmail());
            preparedStatement.setString(3,teacher.getPassword());

           int rows = preparedStatement.executeUpdate();

           if (rows>0){

               return true;
           }
           else return false;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public boolean checkTeacher(Teacher teacher){

        try{

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.loginTeacher);

            preparedStatement.setString(1, teacher.getEmail());

            preparedStatement.setString(2,teacher.getPassword());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                Session.CurrentTeacherId= rs.getInt("id");

                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void pickYourCourse(int courseId){

        try{

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.enrollingTeacher);

            preparedStatement.setInt(1,Session.CurrentTeacherId);
            preparedStatement.setInt(2,courseId);

            int rows=preparedStatement.executeUpdate();

            if (rows>0){
                System.out.println("Picked Successfully");
            }
            else System.out.println("Failed To Pick Course");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewMyPickedCourses(int teacherId){

        try{
            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewPickedCourses);

            preparedStatement.setInt(1,teacherId);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                int teacher_course_id=rs.getInt("teacher_course_id");

                String course_name=rs.getString("course_title");

                System.out.println("ID : "+teacher_course_id+" Course : "+course_name);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void unselectCourse(int teacherId){

        try{

            viewMyPickedCourses(teacherId);

            System.out.println("Enter ID");

            int course_id=sc.nextInt();

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.unselectCourse);

            preparedStatement.setInt(1,course_id);

            int rows=preparedStatement.executeUpdate();

            if (rows>0){
                System.out.println("Unselected Successfully");
            }else System.out.println("Unselection Failed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewStudentsInMySection(int teacherId){

        try{

            viewMyPickedCourses(teacherId);

            int courseId=sc.nextInt();
            sc.nextLine();

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewStudentsInMySection);

            preparedStatement.setInt(1,courseId);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                int studentId=rs.getInt("student_id");

                String student_name=rs.getString("student_name");

                System.out.println("ID : "+studentId+" Student Name : "+student_name);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void markStudentAttendance(int teacherId){

        try{

            viewMyPickedCourses(teacherId);

            System.out.println("Enter Course Id In which You Want To Mark Attendance");

            int courseId=sc.nextInt();
            sc.nextLine();

            System.out.println("Enter date (yyyy-mm-dd): ");

            String date=sc.nextLine();

            java.sql.Date sqlDate=java.sql.Date.valueOf(date);

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewStudentsInMySection);

            preparedStatement.setInt(1,courseId);

            ResultSet rs=preparedStatement.executeQuery();

            List<StudentAttendance> studentAttendances=new ArrayList<>();

            while (rs.next()){

                int studentId=rs.getInt("student_id");

                String studentName=rs.getString("student_name");

                System.out.println("Enter Attendance for -->> Student ID : "+studentId+" Student Name : "+studentName);

                System.out.println("present/absent/late");

                String status=sc.nextLine();

                StudentAttendance studentAttendance=new StudentAttendance(studentId,status,sqlDate);

                studentAttendances.add(studentAttendance);

            }

            PreparedStatement ps = connection.prepareStatement(Sql.insertStudentAttendances);
                for (StudentAttendance studentAttendance: studentAttendances) {
                    ps.setInt(1, studentAttendance.getEnrollmentId());
                    ps.setDate(2, studentAttendance.getAttendanceDate());
                    ps.setString(3, studentAttendance.getStatus());
                    ps.addBatch();
                }
                ps.executeBatch();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void viewMyAttendance(int teacherId){

        try{

            viewMyPickedCourses(teacherId);

            System.out.println("Enter Id Of Course In Which You Want TO View Attendance");

            int courseId=sc.nextInt();
            sc.nextLine();

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewTeacherAttendance);

            preparedStatement.setInt(1,courseId);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                String attendanceDate=rs.getString("attendance_date");

                String Status=rs.getString("status");

                System.out.println("Date : "+attendanceDate+" Status : "+Status);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewProfile(int teacherId){

        try{

            Connection connection=DBConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewTeacherProfile);
            preparedStatement.setInt(1,teacherId);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                int teacher_id=rs.getInt("id");

                String teacher_name=rs.getString("name");

                String teacher_email=rs.getString("email");

                String password=rs.getString("password");

                System.out.println("ID : "+teacher_id+" Name : "+teacher_email+" Email : "+teacher_email+" Password : "+password);

            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateName(int teacherId){

        try{
            System.out.println("Enter Name");

            String teacherName=sc.nextLine();

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.updateTeacherName);
            preparedStatement.setString(1,teacherName);
            preparedStatement.setInt(2,teacherId);

            int rows=preparedStatement.executeUpdate();

            if (rows>0){
                System.out.println("Updated Successfully");
            }else System.out.println("Failed To Update");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateEmail(int teacherId){

        try{
            System.out.println("Enter Email");

            String teacherEmail=sc.nextLine();

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.updateTeacherEmail);
            preparedStatement.setString(1,teacherEmail);
            preparedStatement.setInt(2,teacherId);

            int rows=preparedStatement.executeUpdate();

            if (rows>0){
                System.out.println("Updated Successfully");
            }else System.out.println("Failed To Update");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

   public void viewStudentsInMyCourse(int teacherId){

       try{

           viewMyPickedCourses(teacherId);

           System.out.println("Enter Id");

           int teacher_course_id=sc.nextInt();
           sc.nextLine();

           System.out.println("Enter Attendance Date");
           String date=sc.nextLine();

           java.sql.Date sqlDate=java.sql.Date.valueOf(date);


           Connection connection=DBConnection.getConnection();

           PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewStudentsInMyCourse);

           preparedStatement.setDate(1,sqlDate);
           preparedStatement.setInt(2,teacher_course_id);

           ResultSet rs=preparedStatement.executeQuery();

           while (rs.next()){

               int students_attendance_id=rs.getInt("sa_id");

               String student_name=rs.getString("student_name");

               String attendance_date=rs.getString("attendance_date");

               String attendance_status=rs.getString("attendance_status");

               System.out.println("Enrollment ID : "+students_attendance_id+" Student Name : "+student_name+" Date : "+attendance_date+" Status : "+attendance_status);
           }


       } catch (SQLException e) {
           e.printStackTrace();
       }

   }

    public void viewStudentsAttendance(int teacherId ){
        viewStudentsInMyCourse(teacherId);

        System.out.println("Enter Id Of Which You Want To Update Attendance");

        int attendance_id=sc.nextInt();
        sc.nextLine();

        System.out.println("Enter Status --->> present/absent/late");

        String status=sc.nextLine();

        try{
            Connection connection=DBConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Sql.UpdateStudentAttendance);

            preparedStatement.setString(1,status);

            preparedStatement.setInt(2,attendance_id);
           int rows= preparedStatement.executeUpdate();

           if (rows>0){
               System.out.println("Updated Successfully");
           }else System.out.println("Failed To Update");



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editStudentAttendance(int teacherId){

        viewStudentsAttendance(teacherId);

    }

}
