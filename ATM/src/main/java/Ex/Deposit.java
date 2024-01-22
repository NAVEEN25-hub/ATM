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
@WebServlet("/Deposit")
public class Deposit extends HttpServlet {
	static String acc;
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accnum = request.getParameter("A");
		String amount = request.getParameter("B");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM","root","Naveen@123");
			java.sql.Statement st1 = con1.createStatement();
			String query = ("select *from ATM where accnumber = '"+accnum+"'");
			ResultSet rs = st1.executeQuery(query);
			while(rs.next()) {
			 acc = rs.getString(6);
			}
			int temp = Integer.parseInt(acc) + Integer.parseInt(amount);
			if(Integer.parseInt(amount) %100 == 0) {
			String query1 = ("update ATM set Balance = '"+temp+"' where accnumber = '"+accnum+"'");
			int rs1 = st1.executeUpdate(query1);
			if(rs1>0) {
				out.print("Deposit SuccessFully");
				out.print("<br>Available Balance Is : " + temp);
				out.print("<br><br><a href = 'FrondPage.html'>BACK</a>");
				
			}
			else {
				out.print("Invalid Details");
				out.print("<br><br><a href = 'Withdrawl.jsp'>BACK</a>");
			}
		}
			else {
				out.print("You Can Deposit Only 100's ");
				out.print("<br><br><a href = 'Withdrawl.jsp'>BACK</a>");
			}
		}
		catch(Exception e) {
			
		}
	}
}
