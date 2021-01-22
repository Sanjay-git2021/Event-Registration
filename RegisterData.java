import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.PreparedStatement; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
import java.sql.*;

// Import Database Connection Class file
 

public class RegisterData extends HttpServlet
{ 
	private static final long serialVersionUID = 1L; 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		try { 

			// Initialize the database 
			//DatabaseConnection dbc = new DatabaseConnection();
			String dbDriver = "com.mysql.cj.jdbc.Driver"; 
			String dbURL = "jdbc:mysql://localhost:3306/event"; 
			// Database name to access 
			String dbName = "event"; 
			String dbUsername = "root"; 
			String dbPassword = "Mysql@root2021"; 

			Class.forName(dbDriver); 
			Connection con = DriverManager.getConnection(dbURL,dbUsername,dbPassword); 
			//Connection con = dbc.initializeDatabase(); 

			// Create a SQL query to insert data into demo table 
			// demo table consists of two columns, so two '?' is used 
			PreparedStatement st = con.prepareStatement("insert into register values(?,?,?,?,?,?,?,?,?)"); 

			// For the first parameter, 
			// get the data using request object 
			// sets the data to st pointer 
			st.setString(1, request.getParameter("LN")); 

			// Same for second parameter 
			st.setString(2, request.getParameter("CLG"));
			st.setString(3, request.getParameter("MN"));
			st.setString(4, request.getParameter("Email"));
			st.setString(5, request.getParameter("CB1"));
			st.setString(6, request.getParameter("CB2"));
			st.setString(7, request.getParameter("CB3"));
			st.setString(8, request.getParameter("CB4"));
			st.setString(9, request.getParameter("CB5"));
			// Execute the insert command using executeUpdate() 
			// to make changes in database 
			int i = st.executeUpdate(); 
			// Get a writer pointer 
			// to display the successful result 
			PrintWriter out = response.getWriter(); 
			if(i>0)
			{
				out.println("<html><body><b>Successfully Inserted" + "</b></body></html>"); 
			}
			else
			{
				out.println("<html><body><b>insert fail" + "</b></body></html>"); 
			}
			// Close all the connections 
			st.close(); 
			con.close();
			out.close();
		} 
		catch (Exception e) { 
			e.printStackTrace();
		} 
	} 
} 
