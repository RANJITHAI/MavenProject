package com.Myprogram1.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

class StudentInfo {
    private static StudentInfo dbcon;

    private StudentInfo() {

    }

    public static StudentInfo getInstance() {
        if (dbcon == null) {
            dbcon = new StudentInfo();
        }
        return dbcon;
    }

    //Connection

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection con = null;
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assessments", "root",
                "7358034980MySQLroot");
        return con;
    }

    //Searching data

    public void view_Dept() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = this.getConnection();
            Scanner input = new Scanner(System.in);
            System.out.println("Enter the name of searching : ");
            String dept = input.nextLine();

            String sql = "select * from  student where StdDept='" + dept + "'";
            ps = con.prepareStatement(sql);
            //ps.setString(2,name);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("StdID") + "\t" + rs.getString("StdNAME") +
                        "\t" + rs.getDate("StdDOB") + "\t" + rs.getString("StdDept"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void view_Oldest() throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = this.getConnection();
            System.out.println("      Oldest Student :");
            String sql = "select * from  student where StdDOB=(select min(StdDOB) from student)";
            ps = con.prepareStatement(sql);
            //ps.setString(2,StdNAME);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt("StdID") + "\t" + rs.getString("StdNAME") +
                        "\t" + rs.getDate("StdDOB") + "\t" + rs.getString("StdDept"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}

public class App
{
        public static void main (String[]args) throws IOException, SQLException {
            System.out.println("welcome to maven");
            try {
                int choice = 0;
                StudentInfo s = StudentInfo.getInstance();
                do {
                    System.out.println("Select the number \n 1- Department \n 2- Oldest student \n 3- Exit");
                    Scanner choicein = new Scanner(System.in);
                    System.out.println("Enter the number : ");
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
                //System.out.println("Thanks You!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

