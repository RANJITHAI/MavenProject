package com;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.*;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.Scanner;

class SingletonStdInfo {
    private static Connection con = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assessments", "root", "7358034980MySQLroot");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection getCon() {
        return con;
    }
    //Searching data

    public void view_Dept() throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getCon();
            Statement stmt = con.createStatement();

            Scanner input = new Scanner(System.in);
            System.out.println("Enter the searching department : ");
            String dept = input.nextLine();

            String sql = "select * from  student where StdDept='" + dept + "';";
            rs = stmt.executeQuery(sql);

            int count = 0;
            while (rs.next()) {
                System.out.println(rs.getInt("StdID") + "\t" + rs.getString("StdNAME") +
                        "\t" + rs.getDate("StdDOB") + "\t" + rs.getString("StdDept"));
                count++;

            }
            System.out.println("Number of student in " + dept + " department : " + count);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        finally {
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

    public void view_Oldest()   throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getCon();

            ArrayList<Integer> stdsId = new ArrayList<Integer>();
            ArrayList<String> stdsName = new ArrayList<String>();
            ArrayList<Date> stdsDob = new ArrayList<>();
            ArrayList<String> stdsDept = new ArrayList<String>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/assessments", "root", "7358034980MySQLroot");
            Statement stmt = con.createStatement();
            System.out.println("      Oldest Student :");
            String sql = "select * from  student";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                stdsId.add(rs.getInt("StdID"));
                stdsName.add(rs.getString("StdNAME"));
                stdsDob.add(rs.getDate("StdDOB"));
                stdsDept.add(rs.getString("StdDept"));
            }

            int i = stdsDob.indexOf(Collections.min(stdsDob));
            System.out.println("Oldest Student : ");
            System.out.println(stdsId.get(i) + "\t" + stdsName.get(i) + "\t" + stdsDob.get(i) + "\t" + stdsDept.get(i));
        }
         catch (Exception ex) {
            System.out.println(ex);
        }
        finally {
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