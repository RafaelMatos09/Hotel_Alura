package controller;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import model.Reserva;

import java.sql.Connection;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        Connection connection = new ConnectionFactory().recuperaConexao();
        this.reservaDAO = new ReservaDAO(connection);
    }

    public void salvar(Reserva reserva) {
        this.reservaDAO.salvar(reserva);
    }

    public List<Reserva> listar() {
        return this.reservaDAO.listar();
    }
}
