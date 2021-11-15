package com;
import com.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        try {
            int choice = 0;
            SingletonStdInfo s = new SingletonStdInfo();
            do {
                System.out.println("Select the number \n 1- Department \n 2- Oldest student \n 3- Exit");
                Scanner choicein = new Scanner(System.in);
                System.out.println("Select the number : ");
                choice = choicein.nextInt();
                switch (choice) {
                    case 1:
                        s.view_Dept();
                        break;
                    case 2:
                        s.view_Oldest();
                        break;
                    default:
                        System.out.println("Thank you   ");
                }
             } while (choice != 3);
            System.out.println("Thanks You!");
        }
        catch (Exception e){
            System.out.println(e);
        }

    }

}
