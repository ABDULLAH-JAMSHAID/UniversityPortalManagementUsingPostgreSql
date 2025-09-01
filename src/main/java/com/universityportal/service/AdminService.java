package main.java.com.universityportal.service;

import main.java.com.universityportal.model.Admin;
import main.java.com.universityportal.repository.AdminRepository;
import main.java.com.universityportal.repository.StudentRepository;

public class AdminService {

    private final AdminRepository adminRepository=new AdminRepository();

    StudentRepository studentRepository=new StudentRepository();

    public boolean checkAdmin(String username,String password){

        Admin admin=new Admin(username,password);

        return adminRepository.checkAdmin(admin);

    }

    public void viewAllStudents(){

        adminRepository.viewAllStudents();
    }

    public void viewStudentsSectionWise(){

        adminRepository.viewStudentsSectionWise();
    }

    public void viewAllCourses(){
        studentRepository.viewAllCourses();
    }

    public void viewAllTeachers(){

        adminRepository.viewAllTeachers();
    }

    public void viewAllEnrollments(){
        adminRepository.viewAllEnrollments();
    }

    public void deleteStudent(){

        adminRepository.deleteStudent();
    }

    public void deleteCourse(){

        adminRepository.deleteCourse();

    }

    public void deleteTeacher(){

        adminRepository.deleteTeacher();
    }

    public void markTeachersAttendance(){

        adminRepository.markTeachersAttendance();
    }

    public void courseWiseStudentCount(){
        adminRepository.courseWiseStudentCount();
    }
    public void countCoursesEnrolledByTeacher(){
        adminRepository.countCoursesEnrolledByTeacher();
    }
    public void studentAttendanceCount(){
        adminRepository.studentAttendanceCount();
    }
    public void teacherAttendanceCount(){
        adminRepository.teacherAttendanceCount();
    }
    public void studentsAttendancePercentage(){
        adminRepository.studentsAttendancePercentage();
    }
}
