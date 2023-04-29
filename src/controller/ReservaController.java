package controller;

import dao.ReservaDAO;
import factory.ConnectionFactory;
import model.Hospedes;
import model.Reserva;

import java.sql.Connection;
import java.util.List;

public class ReservaController {
    private ReservaDAO reservaDAO;

    public ReservaController() {
        Connection connection = new ConnectionFactory().recuperaConexao();
        this.reservaDAO = new ReservaDAO(connection);
    }

    public void deletar(Integer id) {
        this.reservaDAO.deletar(id);
    }

    public void salvar(Reserva reserva) {
        this.reservaDAO.salvar(reserva);
    }

    public List<Reserva> listar() {
        return this.reservaDAO.listar();
    }

    public void alterar(String dataEntrada, String dataSaida, String valor, String formaPagamento, Integer id) {
        this.reservaDAO.alterar(dataEntrada, dataSaida, valor, formaPagamento, id);
    }

    public List<Reserva> buscar(Integer id) {
        return this.reservaDAO.buscarPorReserva(id);
    }

}
