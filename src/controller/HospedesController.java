package controller;

import dao.HospedeDAO;
import factory.ConnectionFactory;
import model.Hospedes;

import java.sql.Connection;
import java.util.List;

public class HospedesController {

    private HospedeDAO hospedeDAO;

    public HospedesController() {
        Connection connection = new ConnectionFactory().recuperaConexao();
        this.hospedeDAO = new HospedeDAO(connection);
    }

    public void deletar(Integer id) {
        this.hospedeDAO.deletarHospede(id);
    }

    public void salvar(Hospedes hospede) {
        this.hospedeDAO.salvar(hospede);
    }

    public List<Hospedes> listar() {
        return this.hospedeDAO.listar();
    }

    public void alterar(String nome, String sobrenome, String dataNascimento, String nacionalidade, String telefone, Integer id) {
        this.hospedeDAO.alterar(nome, sobrenome, dataNascimento, nacionalidade, telefone, id);
    }
}
