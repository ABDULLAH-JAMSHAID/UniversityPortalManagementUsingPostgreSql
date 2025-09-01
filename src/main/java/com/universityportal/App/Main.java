package main.java.com.universityportal.App;

import main.java.com.universityportal.controller.AdminController;

import java.util.Scanner;

public class Main{

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        AdminController adminControl=new AdminController(sc);

        while(true){

            System.out.println("1: ADMIN");
            System.out.println("2: REGISTER");
            System.out.println("3: LOGIN");
            System.out.println("4: Exit");

            System.out.println("Enter Choice ");

            int choice;

            try
            {
                choice=sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid Input Please Enter Valid Choice");
                sc.nextLine();
                continue;
            }

            switch (choice){

                case 1 :
                    adminControl.adminLogin();
                    break;
                case 2 :
                    adminControl.registerMenu(choice);
                    break;
                case  3 :
                    adminControl.loginMenu(choice);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Please Enter Valid Choice");
                    break;
            }

        }
    }
}
