package main.java.com.universityportal.service;

import main.java.com.universityportal.model.Teacher;
import main.java.com.universityportal.repository.TeacherRepository;

import java.util.Scanner;

public class TeacherService {

    Scanner sc=new Scanner(System.in);

    private final TeacherRepository teacherRepository=new TeacherRepository();

    private final StudentService studentService=new StudentService();

    public boolean registerTeacher(String name,String email,String password){

        Teacher teacher=new Teacher(name,email,password);

       return teacherRepository.registerTeacher(teacher);

    }

    public boolean checkTeacher(String email,String password){

        Teacher teacher=new Teacher(email,password);

        return teacherRepository.checkTeacher(teacher);
    }

    public void pickYourCourse(int teacherId){

        studentService.viewAllCourses();

        System.out.println("Enter Course ID");

        int courseId= sc.nextInt();
        sc.nextLine();

        teacherRepository.pickYourCourse(courseId);
    }

    public void viewMyPickedCourses(int teacherId){

        teacherRepository.viewMyPickedCourses(teacherId);
    }

    public void unselectCourse(int teacherId){
        teacherRepository.unselectCourse(teacherId);
    }

    public void viewStudentsInMySection(int teacherId){
        teacherRepository.viewStudentsInMySection(teacherId);
    }

    public void markStudentAttendance(int teacherId){

        teacherRepository.markStudentAttendance(teacherId);
    }

    public void viewMyAttendance(int teacherId){

        teacherRepository.viewMyAttendance(teacherId);
    }

    public void viewProfile(int teacherId){
        teacherRepository.viewProfile(teacherId);
    }

    public void updateYourProfile(int teacherId){


        viewProfile(teacherId);

        while (true){

            System.out.println("1: Update Name  --  2: Update Email  -- 3: Exit");

            System.out.println("Enter Choice");

            int choice=sc.nextInt();
            sc.nextLine();

            switch (choice){

                case 1:
                    updateName(teacherId);
                    break;
                case 2:
                    updateEmail(teacherId);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Enter Valid Choice");
                    break;
            }
        }
    }

    public void updateName(int teacherId){
        teacherRepository.updateName(teacherId);
    }

    public void updateEmail(int teacherId){
     teacherRepository.updateEmail(teacherId);
    }
}
