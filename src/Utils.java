public class Utils {
	// Local MySQL connecters
	public static String driver = "com.mysql.cj.jdbc.Driver";
	public static String connecter = "jdbc:mysql://localhost:3306/FinalProject?user=root&password=root";
	
	// Google Cloud connecters
	// Uncomment below and comment above when deploying
//	public static String driver = "com.mysql.jdbc.GoogleDriver";
//	public static String connecter = "jdbc:mysql:///FinalProject?cloudSqlInstance="
//			+ "java-310408:us-central1:sqlinstance&socketFactory=com.google.cloud.sql.mysql.SocketFactory&user=test";
}
