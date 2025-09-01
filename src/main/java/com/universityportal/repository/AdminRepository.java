package main.java.com.universityportal.repository;

import main.java.com.universityportal.model.Admin;
import main.java.com.universityportal.util.DBConnection;
import main.java.com.universityportal.util.Sql;


import java.sql.*;
import java.util.Scanner;

public class AdminRepository {

    Scanner sc=new Scanner(System.in);

    TeacherRepository teacherRepository=new TeacherRepository();

    StudentRepository studentRepository=new StudentRepository();

    public boolean checkAdmin(Admin admin){

        try {

            Connection connection = DBConnection.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(Sql.checkAdmin);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                String username=rs.getString("username");

                String password=rs.getString("password");

                 if (admin.getUsername().equals(username) && admin.getPassword().equals(password)){

                     return true;
                 }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

    public void viewAllStudents(){

        try{

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewAllUniversityStudents);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                int studentId=rs.getInt("id");

                String studentName=rs.getString("name");

                String studentEmail=rs.getString("email");

                String courseName=rs.getString("course_name");

                System.out.println("ID : "+studentId+" Name : "+studentName+" Email : "+studentEmail+"Course Name : "+courseName);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewStudentsSectionWise(){

        try{

            Connection connection=DBConnection.getConnection();

            studentRepository.viewAllCourses();

            System.out.println("Enter Course id In Which You Want To View Students");

            int courseId=sc.nextInt();
            sc.nextLine();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewStudentsInMySection);

            preparedStatement.setInt(1,courseId);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                int student_id=rs.getInt("student_id");

                String student_name=rs.getString("student_name");

                System.out.println("ID : "+student_id +" Student Name :"+student_name);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewAllTeachers(){

        try{

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewAllTeachers);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                int teacher_id=rs.getInt("id");

                String teacher_name=rs.getString("name");

                String teacher_email=rs.getString("email");

                System.out.println("Id : "+teacher_id+" Name : "+teacher_name+" Email : "+teacher_email);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewAllEnrollments(){

        try{

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewAllEnrollments);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                int student_id=rs.getInt("student_id");

                String student_name=rs.getString("student_name");

                String course_name=rs.getString("course_name");

                System.out.println("ID : "+student_id+" Student : "+student_name+" Course : "+course_name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(){

        try{

            Connection connection=DBConnection.getConnection();

            viewAllEnrollments();

            System.out.println("Enter Id");
            int studentId=sc.nextInt();
            sc.nextLine();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.deleteStudent);

            preparedStatement.setInt(1,studentId);

            int rows=preparedStatement.executeUpdate();

            if (rows>0){
                System.out.println("Student Deleted Successfully");

            }
            else System.out.println("Failed To Delete Student");


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteCourse(){

        try{

            studentRepository.viewAllCourses();

            System.out.println("Enter Course ID you Want To Delete");

            int courseId=sc.nextInt();
            sc.nextLine();

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.deleteCourse);

            preparedStatement.setInt(1,courseId);

            int rows=preparedStatement.executeUpdate();

            if (rows>0){
                System.out.println("Course Deleted Successfully");

            }else System.out.println("Failed To Delete Course");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteTeacher(){

        try{
             Connection connection=DBConnection.getConnection();

             viewAllTeachers();

            System.out.println("Enter Teacher ID");
            int teacherId=sc.nextInt();
            sc.nextLine();

             PreparedStatement preparedStatement=connection.prepareStatement(Sql.deleteTeacher);

             preparedStatement.setInt(1,teacherId);

             int rows=preparedStatement.executeUpdate();

             if (rows>0){
                 System.out.println("Teacher Deleted Successfully");
             }else System.out.println("Failed To Delete Teacher");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void markTeachersAttendance(){

        System.out.println("Enter Date yyyy-mm--dd");

        String date=sc.nextLine();

        java.sql.Date sqlDate=java.sql.Date.valueOf(date);

        viewTeachers();

        System.out.println(" Enter Teacher ID  Status ONLY 'present ,absent,late'");
        int teacherId=sc.nextInt();
        sc.nextLine();

        String status=sc.nextLine();


        try{

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.insertTeacherAttendances);

            preparedStatement.setInt(1,teacherId);
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setObject(3,status, Types.OTHER);

            int rows=preparedStatement.executeUpdate();

            if (rows>0){
                System.out.println("Updated Successfully");
            }
            else System.out.println("Failed to Update Attendance");


        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void viewTeachers(){
        try{

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewTeachers);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                int teacher_id=rs.getInt("teacher_enrollment_id");
                String teacher_name=rs.getString("teacher_name");
                String course_name=rs.getString("course_name");

                System.out.println("ID : "+teacher_id+" Teacher Name : "+teacher_name+" Course Name :"+course_name);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void courseWiseStudentCount(){

    }

    public void countCoursesEnrolledByTeacher(){

    }

    public void studentAttendanceCount(){

    }
    public void teacherAttendanceCount(){

    }
    public void studentsAttendancePercentage(){

    }
}
