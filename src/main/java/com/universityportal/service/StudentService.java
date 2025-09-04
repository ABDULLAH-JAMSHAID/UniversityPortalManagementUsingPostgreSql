package main.java.com.universityportal.service;

import main.java.com.universityportal.model.Student;
import main.java.com.universityportal.repository.StudentRepository;

import java.util.Scanner;

public class StudentService {


    private Scanner sc;

    public StudentService(){};

    public StudentService(Scanner sc){
        this.sc=sc;
    }

    private final StudentRepository studentRepository=new StudentRepository();


    // Methods

    public boolean registerStudent(String name,String email,String password){

        Student student=new Student(name,email,password);

        return studentRepository.registerStudent(student);

    }

    public boolean checkStudent(String email,String password){

        Student student=new Student(email,password);

        return studentRepository.checkStudent(student);
    }

    public void viewAllCourses(){

        studentRepository.viewAllCourses();
    }

    public void pickYourCourse(){




        studentRepository.pickYourCourse();
    }

    public void viewProfile(int studentId){

        studentRepository.viewProfile(studentId);
    }

    public void viewMyAttendance(int studentId){

        studentRepository.viewMyAttendance(studentId);
    }

    public void updateYourProfile(int studentId){


        viewProfile(studentId);

        while (true){

            System.out.println("1: Update Name  --  2: Update Email  -- 3: Exit");

            System.out.println("Enter Choice");

            int choice=sc.nextInt();
            sc.nextLine();

            switch (choice){

                case 1:
                    updateName(studentId);
                    break;
                case 2:
                    updateEmail(studentId);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Enter Valid Choice");
                    break;
            }
        }
    }

    public void updateName(int studentId){

        studentRepository.updateName(studentId);
    }

    public void viewMyPickedCourse(int studentId){
        studentRepository.viewMyPickedCourse(studentId);
    }

    public void updateEmail(int studentId){
        studentRepository.updateEmail(studentId);
    }
}
