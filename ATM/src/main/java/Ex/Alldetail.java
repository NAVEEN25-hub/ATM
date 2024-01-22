package Ex;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Alldetail")
public class Alldetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       		String id = request.getParameter("A");
       		String pass = request.getParameter("B");
    		PrintWriter out = response.getWriter();
    		response.setContentType("text/html");
       		if(id.equals("2545") && pass.equals("123"))	{
       			try {
       				Class.forName("com.mysql.jdbc.Driver");
       				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM","root","Naveen@123");
       				java.sql.Statement st = con.createStatement();
       				String query = ("select * from ATM");
       				ResultSet rs = st.executeQuery(query);
       				out.print("<table border = '2'>");
       				out.print("<tr><td>NAME</td><td>MOB NO</td><td>ADDRESS</td><td>ACC NUMBER</td><td>IFSC<td>Balance</td>");
       				while(rs.next()){
       					String fullname = rs.getString(1);
       					String mob = rs.getString(2);
       					String address = rs.getString(3);
       					String accnumber = rs.getString(4);
       					String IFSC = rs.getString(5);
       					String Balance = rs.getString(6);
       					out.print("<tr><td>" + fullname + "</td><td>" + mob + "</td><td>" + address + "</td><td>" + accnumber + "</td><td>" + IFSC + "</td><td>" + Balance + "</td></tr>" );
       				}
   					out.print("</table");
       			}
       			catch(Exception e) {
       				

       			}
       		}
       		else {
       			out.print("Invalid Password");
       			out.print("<br>");
    			out.print("<a href = 'Alldetail.jsp'>Back</a>");
    			
       		}
			out.print("<br><br><a href = 'FrondPage.html'>Back</a>");
      }
}
