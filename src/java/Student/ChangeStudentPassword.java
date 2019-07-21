package Student;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpSession;

public class ChangeStudentPassword extends HttpServlet {

    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String old = request.getParameter("txtoldpwd");
        String newpass = request.getParameter("txtnewpwd");

        if (old != null) {
            try {
                con = connection.getDBConnection.makeConn();
                String sql = "Update student set pwd='" + newpass + "'where  pwd='" + old + "'";
                System.out.println(newpass);
                pst = con.prepareStatement(sql);
                int j = pst.executeUpdate();
                if (j > 0) {
                    response.sendRedirect("/Student/ChnagePassword.jsp");

                } else {
                    response.sendRedirect("/Student/ChnagePassword.jsp");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
