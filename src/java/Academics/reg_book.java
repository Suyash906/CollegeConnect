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

/**
 *
 * @author Owner
 */
public class reg_book extends HttpServlet {

    static Connection conn = null;
    Statement st = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    String bookid = null;
    String bookname = null;
    String publication = null;
    String author = null;
    String edition = null;
    String price = null;
    String other_meterial = null;
    String bdate = null;
    String bprice = null;
    String amount = null;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        bookid = request.getParameter("txtid");
        bookname = request.getParameter("name");
        publication = request.getParameter("publication");
        author = request.getParameter("author");
        edition = request.getParameter("edition");
        price = request.getParameter("price");
        other_meterial = request.getParameter("cmbo");
        bdate = request.getParameter("bdate");
        bprice = request.getParameter("bprice");
        amount = request.getParameter("amount");
        
        if (bookid != null) {
            try {
                conn = connection.getDBConnection.makeConn();
                String query = "Insert into book(bookid,bookname,publication,author,edition,price,other_meterial,bdate,bprice,amount)values(?,?,?,?,?,?,?,?,?,?)";

                pst = conn.prepareStatement(query);
                pst.setString(1, bookid);
                pst.setString(2, bookname);
                pst.setString(3, publication);
                pst.setString(4, author);
                pst.setString(5, edition);
                pst.setString(6, price);
                pst.setString(7, other_meterial);
                pst.setString(8, bdate);
                pst.setString(9, bprice);
                pst.setString(10, amount);
                
                int j = 0;
                j = pst.executeUpdate();
                if (j > 0) {

                    connection.AutoID.updateCampaignId("Book", bookid);
                    session.setAttribute("MSG", "New Book is Registered !!");
                    response.sendRedirect("Admin/book.jsp");
                } else {
                    session.setAttribute("MSG", "New Book Not Registered!!");
                    response.sendRedirect("Admin/Book.jsp");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
