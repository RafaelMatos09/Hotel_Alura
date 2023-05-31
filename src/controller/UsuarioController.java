package controller;

import dao.ReservaDAO;
import dao.UsuarioDAO;
import factory.ConnectionFactory;
import model.Reserva;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UsuarioController {
    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        Connection connection = new ConnectionFactory().recuperaConexao();
        this.usuarioDAO = new UsuarioDAO(connection);
    }

    public String login(String user) throws SQLException, IOException {
        Connection connection = new ConnectionFactory().recuperaConexao();
        return this.usuarioDAO.login(user);
    }
}
