import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class page extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            String name = request.getParameter("name");
            String age = request.getParameter("age");
            String course = request.getParameter("course");
            String abcid = request.getParameter("abcid");
            String mothername = request.getParameter("mothername");
            String fathername = request.getParameter("fathername");
            String applicantmarathi = request.getParameter("applicantmarathi");

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String host = "mysql-a7dcaeb-onlineokokok-2538.j.aivencloud.com";
                String port = "13350";
                String db   = "defaultdb";
                String user = "avnadmin";
                String pass = "AVNS_zsVzU151_FGO3iuRyzR";

                String url = "jdbc:mysql://" + host + ":" + port + "/" + db + "?sslMode=REQUIRED";

                Connection con = DriverManager.getConnection(url, user, pass);

                PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO student_registration(name, age, course, abcid, mother_name, father_name, applicant_full_name_marathi) VALUES (?, ?, ?, ?, ?, ?, ?)"
                );

                ps.setString(1, name);
                ps.setString(2, age);
                ps.setString(3, course);
                ps.setString(4, abcid);
                ps.setString(5, mothername);
                ps.setString(6, fathername);
                ps.setString(7, applicantmarathi);

                int x = ps.executeUpdate();

                if (x > 0) {
                    out.println("<html><body>");
                    out.println("<h2>Registration Successful</h2>");
                    out.println("<p>Name: " + name + "</p>");
                    out.println("<p>Age: " + age + "</p>");
                    out.println("<p>Course: " + course + "</p>");
                    out.println("<p>ABC ID: " + abcid + "</p>");
                    out.println("<p>Mother Name: " + mothername + "</p>");
                    out.println("<p>Father Name: " + fathername + "</p>");
                    out.println("<p>Applicant Full Name (Marathi): " + applicantmarathi + "</p>");
                    out.println("</body></html>");
                } else {
                    out.println("Registration failed");
                }

                ps.close();
                con.close();

            } catch (Exception e) {
                out.println("Error: " + e);
                e.printStackTrace(out);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}