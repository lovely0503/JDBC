package Legend_Animal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertLegendAnimal {
	 public static void main(String[] args) {
	        String url = "jdbc:oracle:thin:@localhost:1521:xe";
	        String user = "your_id";
	        String password = "your_pw";

	        String sql = "INSERT INTO LEGEND_ANIMAL (ANIMAL_ID, ANIMAL_NAME, ANIMAL_AGE, ANIMAL_TYPE) VALUES (?, ?, ?, ?)";

	        try (Connection conn = DriverManager.getConnection(url, user, password);
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {

	            pstmt.setInt(1, 1);
	            pstmt.setString(2, "호랑이");
	            pstmt.setString(3, "10");
	            pstmt.setString(4, "포유류");

	            int result = pstmt.executeUpdate();
	            System.out.println(result + "행 삽입 완료");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}
