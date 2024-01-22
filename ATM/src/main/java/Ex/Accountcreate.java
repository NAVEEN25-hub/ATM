package Ex;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/Accountcreate")
public class Accountcreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int number = 34050001;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String IFSC = "SBI00003405";
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String name = request.getParameter("A");
		String mob = request.getParameter("B");
		String address = request.getParameter("C");
		String amount = request.getParameter("D");
		if(name.length()>=1) {
			if(mob.length() == 10) {
				if(address.length()>=1) {
					try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ATM","root","Naveen@123");
					java.sql.Statement st = con.createStatement();
					String query = ("insert into ATM values('"+name+"','"+mob+"','"+address+"','"+number+"','"+IFSC+"','"+amount+"')");
					int rs = st.executeUpdate(query);
					if(rs>0){
						out.print("Acoount Creation SuccessFully");
						out.print("<br>");
						out.print("Your Acoount Number Is : " + number);
						out.print("<br>");
						out.print("Your IFSC Code Is : " + IFSC);	
						out.print("<br>");
						out.print("<br>");
						out.print("<a href = 'FrondPage.html'>Back</a>");
						number++;
						}
					else{
						out.print("Data n't Inserted");
						}
					}
					catch(Exception e) {
						
					}					
				}
				else {
					out.print("Enter Correct Address");
					out.print("<br>");
					out.print("<a href = 'Accountcreate.jsp'>Back</a>");
				}
				
			}
			else {
				out.print("Invalid Mobile Number");
				out.print("<br>");
				out.print("<a href = 'Accountcreate.jsp'>Back</a>");
			}
		}
		else {
			out.print("Invalid Name");
			out.print("<br>");
			out.print("<a href = 'Accountcreate.jsp'>Back</a>");
			
		}
	}
}
