/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Owner
 */
public class getDBConnection {

    static Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    int p;

    /*Creates a new instance of ConnectionBean */
    public static Connection makeConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/CollegeConnect", "root", "root");


        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static int saveAttendence(String id, String status) {
        con = connection.getDBConnection.makeConn();
        int i = 0;

        try {
            PreparedStatement pst = null;
            String query = "Insert into attendence(Student_ID,status,adate,atime)values(?,?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, status);
            pst.setString(3, getDate());
            pst.setString(4, getTime());
            i = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(getDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }


        return i;
    }
    public static int saveReview(String id, String status) {
        con = connection.getDBConnection.makeConn();
        int i = 0;

        try {
            PreparedStatement pst = null;
            String query = "Insert into review(Student_ID,review,rdate)values(?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, status);
            pst.setString(3, getDate());

            i = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(getDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }


        return i;
    }
    public static int saveExtra(String id, String status) {
        con = connection.getDBConnection.makeConn();
        int i = 0;

        try {
            PreparedStatement pst = null;
            String query = "Insert into extra_curri(Student_ID,review,rdate)values(?,?,?)";
            pst = con.prepareStatement(query);
            pst.setString(1, id);
            pst.setString(2, status);
            pst.setString(3, getDate());

            i = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(getDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }


        return i;
    }

    public static int updateAttendence(String id, String status) {
        con = connection.getDBConnection.makeConn();
        int i = 0;

        try {
            PreparedStatement pst = null;
            String query = "update attendence set status=?,adate=?,atime=? where Student_ID='"+id+"'";
            pst = con.prepareStatement(query);
          
            pst.setString(1, status);
            pst.setString(2, getDate());
            pst.setString(3, getTime());
            i = pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(getDBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }


        return i;
    }

    public static String getDate() {
        DateFormat dateFormat = new SimpleDateFormat(
                "yyyy/MM/dd");

        Calendar cal = Calendar.getInstance();

        return dateFormat.format(cal.getTime());// "11/03/14 12:33:43";
    }

    public static String getTime() {
        DateFormat dateFormat1 = new SimpleDateFormat(
                "HH:mm:ss");

        Calendar cal = Calendar.getInstance();

        return dateFormat1.format(cal.getTime());// "11/03/14 12:33:43";
    }

    public static void closeConn() throws Exception {
        if (con != null) {
            con.close();
        }
    }
}
