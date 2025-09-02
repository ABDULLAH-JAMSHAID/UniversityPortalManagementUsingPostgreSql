package main.java.com.universityportal.controller;

import main.java.com.universityportal.service.StudentService;
import main.java.com.universityportal.service.TeacherService;
import java.util.Scanner;

public class TeacherController {


    Scanner sc=new Scanner(System.in);

    private final TeacherService teacherService=new TeacherService();

    private final StudentService studentService=new StudentService();

    public TeacherController(Scanner sc){
        this.sc = sc;
    }

    public TeacherController(){

    }

    public boolean checkTeacher(String email,String password){

        return teacherService.checkTeacher(email,password);
    }

    public void registerAsTeacher(){

        System.out.println("Enter Name");

        String name=sc.nextLine();

        System.out.println("Enter Email");

        String email=sc.nextLine();

        System.out.println("Enter Password");

        String password=sc.nextLine();

        if (!teacherService.registerTeacher(name,email,password)){
            System.out.println("Failed To Register.. Please Try Again");
            return;
        }

        System.out.println("Registration Successfully...Do You Want To Login Y/N");

        String choice =sc.nextLine();

        if (choice.equalsIgnoreCase("n")){
            return;
        }

        loginAsTeacher();


    }

    public void loginAsTeacher(){

        System.out.println("Enter email");

        String email=sc.nextLine();

        System.out.println("Enter Password");

        String password=sc.nextLine();

        if (!teacherService.checkTeacher(email,password)){
            System.out.println("Incorrect Email And Password");
            return;
        }
        int teacherId= Session.CurrentTeacherId;

        while (true){

            System.out.println("1: View All Courses");
            System.out.println("2: Pick Your Course");
            System.out.println("3: View My Picked Courses");
            System.out.println("4: Unselect Course");
            System.out.println("5: View Students In My Course");
            System.out.println("6: Mark Attendance Of Students");
            System.out.println("7: View My Attendance");
            System.out.println("8: View Your Profile");
            System.out.println("9: Update Your Profile");
            System.out.println("10: Edit Attendance");
            System.out.println("11: Exit");
            System.out.println("Enter Your Choice");


            int choice;

            try{
                choice=sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Enter Valid Choice");
                sc.nextLine();
                continue;
            }

            switch (choice){
                case 1:
                    studentService.viewAllCourses();
                    break;
                case 2:
                    teacherService.pickYourCourse(teacherId);
                    break;
                case 3:
                    teacherService.viewMyPickedCourses(teacherId);
                    break;
                case 4:
                    teacherService.unselectCourse(teacherId);
                    break;
                case 5:
                    teacherService.viewStudentsInMySection(teacherId);
                    break;
                case 6:
                    teacherService.markStudentAttendance(teacherId);
                    break;
                case 7:
                    teacherService.viewMyAttendance(teacherId);
                    break;
                case 8:
                    teacherService.viewProfile(teacherId);
                    break;
                case 9:
                    teacherService.updateYourProfile(teacherId);
                    break;
                case 10:
                    teacherService.editStudentAttendance(teacherId);
                    break;
                case 11:
                    return;
                default:
                    System.out.println("Enter Valid Choice");
                    break;
            }

        }

    }


    }

