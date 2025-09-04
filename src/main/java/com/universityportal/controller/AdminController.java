package main.java.com.universityportal.controller;

import main.java.com.universityportal.service.AdminService;

import java.util.Scanner;

public class AdminController {

    private final Scanner sc;

    private  final AdminService adminService=new AdminService();

    private final TeacherController teacherController=new TeacherController();

    private final StudentController studentController=new StudentController();

    public AdminController(Scanner sc){
        this.sc=sc;
    }



    public void registerMenu(int choice){

        while (true){
            System.out.println("1 : Register AS Teacher");
            System.out.println("2 : Register AS Student");
            System.out.println("3 : Exit");

            System.out.println("Enter Your Choice");

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
                    teacherController.registerAsTeacher();
                    break;
                case 2:
                    studentController.registerAsStudent();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Enter Valid Choice");
                    break;

            }
        }
    }

    public void loginMenu(int choice){

        while (true){
            System.out.println("1 : Login AS Teacher");
            System.out.println("2 : Login AS Student");
            System.out.println("3 : Exit");

            System.out.println("Enter Your Choice");

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
                    teacherController.loginAsTeacher();
                    break;
                case 2:
                    studentController.loginAsStudent();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Enter Valid Choice");
                    break;
            }
        }
    }

    public void adminLogin(){

        System.out.println("Enter Username");

        String adminUsername=sc.nextLine();

        System.out.println("Enter Password");

        String adminPassword=sc.nextLine();

        if (!checkAdmin(adminUsername,adminPassword)){
            System.out.println("Incorrect Username or Password");
            return;
        }

        while(true){

            System.out.println("1:  View All University Students");
            System.out.println("2:  View Students in A Course");
            System.out.println("3:  View All Courses");
            System.out.println("4:  View All Teachers");
            System.out.println("5:  View All Enrollments");
            System.out.println("6:  Delete Student");
            System.out.println("7:  Delete Course");
            System.out.println("8:  Delete Teacher");
            System.out.println("9:  Mark Attendance");
            System.out.println("10: Course Wise Student Count");
            System.out.println("11: Count Courses Enrolled By Teacher");
            System.out.println("12: Student Attendance Count");
            System.out.println("13: Teacher Attendance Count");
            System.out.println("14: Students Attendance Percentage");
            System.out.println("15: Exit");

            System.out.println("Enter Choice");

            int choice=sc.nextInt();
            sc.nextLine();

            switch (choice){

                case 1:
                    adminService.viewAllStudents();
                    break;
                case 2:
                    adminService.viewStudentsSectionWise();
                    break;
                case 3:
                    adminService.viewAllCourses();
                    break;
                case 4:
                    adminService.viewAllTeachers();
                    break;
                case 5:
                    adminService.viewAllEnrollments();
                    break;
                case 6:
                    adminService.deleteStudent();
                    break;
                case 7:
                    adminService.deleteCourse();
                    break;
                case 8:
                    adminService.deleteTeacher();
                    break;
                case 9:
                    adminService.markTeachersAttendance();
                    break;
                case 10:
                   adminService.courseWiseStudentCount();
                    break;
                case 11:
                    adminService.countCoursesEnrolledByTeacher();
                    break;
                case 12:
                    adminService.studentAttendanceCount();
                    break;
                case 13:
                    adminService.teacherAttendanceCount();
                    break;
                case 14:
                    adminService.studentsAttendancePercentage();
                case 15:
                    return;
                default:
                    System.out.println("Please Enter Valid Choice");
                    break;
            }
        }
    }

    public boolean checkAdmin(String username, String password){

        return adminService.checkAdmin(username, password);

    }

}

