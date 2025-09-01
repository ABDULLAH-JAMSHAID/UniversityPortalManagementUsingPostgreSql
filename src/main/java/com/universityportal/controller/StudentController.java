package main.java.com.universityportal.controller;

import main.java.com.universityportal.service.StudentService;
import java.util.Scanner;

public class StudentController {

     Scanner sc=new Scanner(System.in);

    private  final StudentService studentService=new StudentService(sc);

    public void registerAsStudent(){

        System.out.println("Enter  Name");

        String name=sc.nextLine();

        System.out.println("Enter Email");

        String email=sc.nextLine();

        System.out.println("Enter Password");

        String password=sc.nextLine();

        if (!studentService.registerStudent(name,email,password)){
            System.out.println("Failed To Register.. Please Try Again");
            return;
        }

        System.out.println("Registration Successfully...Do You Want To Login Y/N");

        String choice =sc.nextLine();

        if (choice.equalsIgnoreCase("n")){
            return;
        }

        loginAsStudent();


    }

    public boolean checkStudent(String email,String password){

        return studentService.checkStudent(email,password);
    }

    public void loginAsStudent() {


        System.out.println("Enter Username");

        String username = sc.nextLine();

        System.out.println("Enter Password");

        String password = sc.nextLine();

        if (!checkStudent(username, password)) {
            System.out.println("Incorrect Username And Password");
            return;
        }
        int studentId = Session.CurrentStudentId;

        while (true) {

            System.out.println("1: View All Courses");
            System.out.println("2: Pick Your Course");
            System.out.println("3: View Your Profile");
            System.out.println("4: View My Attendance");
            System.out.println("5: Update Your Profile");
            System.out.println("6: View My Picked COurse");
            System.out.println("7: Exit");
            System.out.println("Enter Your Choice");

            int choice;

            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Enter Valid Choice");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    studentService.viewAllCourses();
                    break;
                case 2:
                    studentService.pickYourCourse();
                    break;
                case 3:
                    studentService.viewProfile(studentId);
                    break;
                case 4:
                    studentService.viewMyAttendance(studentId);
                    break;
                case 5:
                    studentService.updateYourProfile(studentId);
                    break;
                case 6:
                    studentService.viewMyPickedCourse(studentId);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Enter Valid Choice");
                    break;
            }

        }

    }



}

