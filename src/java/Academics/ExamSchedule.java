/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Academics;

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

public class ExamSchedule extends HttpServlet {

    static Connection conn = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String quesLimit = null;
    String subject = null;
    String date = null;
    String time1 = null;
    String time2 = null;
    String time = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        subject = request.getParameter("cmbsubject");
        quesLimit = request.getParameter("txtLimit");
        date = request.getParameter("txtdate");
        time1 = request.getParameter("combotext1");
        time2 = request.getParameter("combotext2");
        time = time1 + " to" + time2;

        if (subject != null) {
            try {
                conn = connection.getDBConnection.makeConn();
                String query = "insert into exam_schedule(subject,QuesLimit,date,time)values(?,?,?,?)";
                pst = conn.prepareStatement(query);
                pst.setString(1, subject);
                pst.setString(2, quesLimit);
                pst.setString(3, date);
                pst.setString(4, time);
                int j = 0;
                j = pst.executeUpdate();
                if (j > 0) {
                    session.setAttribute("MSG", "Exam Schedule is Successfully Added!!");
                    response.sendRedirect("Teacher/ExamSchedule.jsp");
                } else {
                    session.setAttribute("MSG", "Exam Schedule Not Added!!");
                    response.sendRedirect("Teacher/ExamSchedule.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
