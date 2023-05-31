package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public String login(String user) throws SQLException {
        String sql = "SELECT senha FROM usuarios WHERE login = ?";
        String senha=null;
        try(PreparedStatement pstmt = connection.prepareStatement(sql)){
            try {
                pstmt.setString(1, user);

                ResultSet rst = pstmt.executeQuery();
                if (rst.next()) {
                    senha = rst.getString("senha");
                }
                pstmt.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return senha;
    }
}
