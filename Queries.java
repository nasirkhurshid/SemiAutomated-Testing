import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queries {
	public static void executeQueriesFromFile(String inputFile, String outputFile) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:ucanaccess://C://Users//Nasir Khurshid//Downloads//eclipse//Queries//database.accdb");
			Statement stmt = null;
			
			BufferedReader inputReader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter outputWriter = new BufferedWriter(new FileWriter(outputFile));
			String query;
			while ((query = inputReader.readLine()) != null) {
				stmt = conn.createStatement();
				stmt.executeUpdate(query);

				String selectQuery = "";
				if (query.startsWith("INSERT")) {
					selectQuery = "SELECT * FROM users ORDER BY id DESC LIMIT 1";
				} else if (query.startsWith("UPDATE")) {
					String[] queryParts = query.split(" ");
					String columnName = queryParts[queryParts.length - 3];
					String columnValue = queryParts[queryParts.length - 1];
					selectQuery = "SELECT * FROM users WHERE " + columnName + " = " + columnValue;
				}
				
				ResultSet resultSet = stmt.executeQuery(selectQuery);
				while (resultSet.next()) {
					String name = resultSet.getString("name");
					String email = resultSet.getString("email");
					int age = resultSet.getInt("age");
					outputWriter.write("Name: " + name + ", Email: " + email + ", Age: " + age + "\n");
					break;
				}
				stmt.close();
			}
			inputReader.close();
			outputWriter.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
