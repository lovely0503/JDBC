package Join_Example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectJoinExample {
	public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "your_id";
        String password = "your_pw";

        String sql = "SELECT L.ANIMAL_NAME, S.ANIMAL_HELL, S.ANIMAL_EAT " +
                     "FROM LEGEND_ANIMAL L " +
                     "JOIN ANIMAL_STORY S ON L.ANIMAL_ID = S.ANIMAL_ID";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("ANIMAL_NAME");
                String hell = rs.getString("ANIMAL_HELL");
                String eat = rs.getString("ANIMAL_EAT");

                System.out.println(name + " | " + hell + " | " + eat);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
