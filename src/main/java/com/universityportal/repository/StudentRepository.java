package main.java.com.universityportal.repository;


import main.java.com.universityportal.controller.Session;
import main.java.com.universityportal.model.Student;
import main.java.com.universityportal.util.DBConnection;
import main.java.com.universityportal.util.Sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class StudentRepository {

    Scanner sc=new Scanner(System.in);

    public boolean registerStudent(Student student){

        try{
            Connection connection= DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.registerStudent);

            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.setString(3,student.getPassword());

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

    public boolean checkStudent(Student student){

        try{

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.loginStudent);

            preparedStatement.setString(1, student.getEmail());

            preparedStatement.setString(2,student.getPassword());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                Session.CurrentStudentId= rs.getInt("id");

                return true;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void viewAllCourses(){

        try{
            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewAllCourses);

            ResultSet rs= preparedStatement.executeQuery();

            while (rs.next()){

                int id=rs.getInt("id");

                String title=rs.getString("title");

                int credit_hours=rs.getInt("credit_hours");

                System.out.println("ID : "+id +" Title: "+title +" Credit Hours : "+credit_hours);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void pickYourCourse(int courseId){

        try{

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewRelevantTeachers);

            preparedStatement.setInt(1,courseId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){

                int teacher_course_id=rs.getInt("teacher_id");

                String teacher_name=rs.getString("teacher_name");


                System.out.println("Id : "+teacher_course_id+" Teacher Name : "+teacher_name);
            }

            System.out.println("Enter Id Of Teacher From Which You Want To Study");

            int teacher_course_id=sc.nextInt();
            sc.nextLine();

            preparedStatement=connection.prepareStatement(Sql.enrollingStudent);

            preparedStatement.setInt(1,Session.CurrentStudentId);
            preparedStatement.setInt(2,teacher_course_id);

            int rows=preparedStatement.executeUpdate();

            if (rows>0){
                System.out.println("Enrolled Successfully");

            }
            else System.out.println("Failed To Enroll");



        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void viewProfile(int studentId){

        try{

            Connection connection=DBConnection.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewProfile);
            preparedStatement.setInt(1,studentId);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                int student_id=rs.getInt("id");

                String student_name=rs.getString("name");

                String student_email=rs.getString("email");

                System.out.println("ID : "+student_id+" Name : "+student_name+" Email : "+student_email);

            }


        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewMyAttendance(int studentId){

        try{

            int enrollmentId=getEnrollmentId(studentId);

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewStudentAttendance);

            preparedStatement.setInt(1,enrollmentId);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()) {

                LocalDate attendanceDate = rs.getObject("attendance_date", LocalDate.class);


                String status = rs.getString("status");

                System.out.println("Date: " + attendanceDate + ", Status: " + status);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getEnrollmentId(int studentId){

        try{
            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.getEnrollmentId);

            preparedStatement.setInt(1,studentId);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){
                int enrollmentId=rs.getInt("enrollment_id");
                return enrollmentId;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void updateName(int studentId){

        try{
            System.out.println("Enter Name");

            String studentName=sc.nextLine();

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.updateStudentName);
            preparedStatement.setString(1,studentName);
            preparedStatement.setInt(2,studentId);

            int rows=preparedStatement.executeUpdate();

            if (rows>0){
                System.out.println("Updated Successfully");
            }else System.out.println("Failed To Update");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void updateEmail(int studentId){

        try{
            System.out.println("Enter Email");

            String studentEmail=sc.nextLine();

            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.updateStudentEmail);
            preparedStatement.setString(1,studentEmail);
            preparedStatement.setInt(2,studentId);

            int rows=preparedStatement.executeUpdate();

            if (rows>0){
                System.out.println("Updated Successfully");
            }else System.out.println("Failed To Update");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewMyPickedCourse(int studentId){

        try{
            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(Sql.viewStudentPickedCourse);

            preparedStatement.setInt(1,studentId);

            ResultSet rs=preparedStatement.executeQuery();

            while (rs.next()){

                String courseName=rs.getString("course_title");

                System.out.println("Course Name "+courseName);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
