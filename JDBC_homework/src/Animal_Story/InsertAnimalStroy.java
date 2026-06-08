package Animal_Story;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertAnimalStroy {
	public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "your_id";
        String password = "your_pw";

        String sql = "INSERT INTO ANIMAL_STORY " +
                     "(ANIMAL_HELL, ANIMAL_PIECE, ANIMAL_FIY, ANIMAL_EAT, ANIMAL_PURE, ANIMAL_ID) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "전설");
            pstmt.setString(2, "이야기");
            pstmt.setString(3, "날다");
            pstmt.setString(4, "고기");
            pstmt.setString(5, "순수");
            pstmt.setInt(6, 1); // FK

            int result = pstmt.executeUpdate();
            System.out.println(result + "행 삽입 완료");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
