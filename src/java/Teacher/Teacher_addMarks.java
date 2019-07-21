/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Teacher;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Niro Thakur
 */
public class Teacher_addMarks extends HttpServlet {

    static Connection conn = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String sem = null;
    String sessional = null;
    String sub1 = null;
    String sub2 = null;
    String sub3 = null;
    String sub4 = null;
    String sub5 = null;
    String sub6 = null;
    String rdate = null;
    String st_name=null;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession(true);
        sem = request.getParameter("sem");
        sessional = request.getParameter("sessional");
        sub1 = request.getParameter("Subject1");
        sub2 = request.getParameter("Subject2");
        sub3 = request.getParameter("Subject3");
        sub4 = request.getParameter("Subject4");
        sub5 = request.getParameter("Subject5");
        sub6 = request.getParameter("Subject6");
        rdate = request.getParameter("txtDate");
        st_name=(String) session.getAttribute("STUDENT");
        System.out.println(sem);
            try {
                conn = connection.getDBConnection.makeConn();
                String query = "Insert into marks(Semester,Student_ID,Sessional,Subject1,Subject2,Subject3,Subject4,Subject5,Subject6,rdate)values(?,?,?,?,?,?,?,?,?,?)";

                pst = conn.prepareStatement(query);
                pst.setString(1, sem);
                pst.setString(2, st_name);
                pst.setString(3, sessional);
                pst.setString(4, sub1);
                pst.setString(5, sub2);
                pst.setString(6, sub3);
                pst.setString(7, sub4);
                pst.setString(8, sub5);
                pst.setString(9, sub6);
                pst.setString(10, rdate);

                int j = 0;
                j = pst.executeUpdate();
                if (j > 0) {


                    session.setAttribute("MSG", "Marks Entered Successfully !!");
                    response.sendRedirect("Teacher/marks.jsp");
                } else {
                    session.setAttribute("MSG", "Marks Not Entered Successfully!!");
                    response.sendRedirect("Teacher/marks.jsp");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        }






   