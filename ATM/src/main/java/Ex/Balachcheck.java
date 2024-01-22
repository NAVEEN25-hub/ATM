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
@WebServlet("/Balancehcheck")
public class Balachcheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accnum = request.getParameter("A");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		int temp = 0;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM","root","Naveen@123");
		java.sql.Statement st = con.createStatement();
		String query = ("select * from ATM where accnumber = '"+accnum+"'");
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			temp++;
			out.print("Your Current Balance Is : " + rs.getString(6));
		}
		if(temp == 0) {
			out.print("Invalid Account Number");
			out.print("<br><br><a href = 'Balancecheck.jsp'>BACK</a>");
		}
		}
		catch(Exception e){
			
		}
	}
}
