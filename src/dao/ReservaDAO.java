package dao;

import model.Hospedes;
import model.Reserva;

import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private Connection connection;

    public ReservaDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Reserva reservas) {

        try {
            String sql = "INSERT INTO RESERVAS (DATA_ENTRADA, DATA_SAIDA, VALOR, FORMA_PAGAMENTO) VALUES ( ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setString(1, reservas.getDataEntrada());
                pstm.setString(2, reservas.getDataSaida());
                pstm.setFloat(3, reservas.getValor());
                pstm.setString(4, reservas.getFormaPagamento());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        reservas.setId(rst.getInt(1));

                    }

                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> listar() {
        try {
            List<Reserva> reservas = new ArrayList<Reserva>();
            String sql = "SELECT ID, DATA_ENTRADA, DATA_SAIDA, VALOR, FORMA_PAGAMENTO FROM RESERVAS";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();
                transformarResultSetEmReservas(reservas, pstm);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Reserva> buscar(Reserva ct) {
        try {
            List<Reserva> reservas = new ArrayList<>();
            String sql = "SELECT DATA_ENTRADA, DATA_SAIDA, VALOR, FORMA_PAGAMENTO WHERE ID = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, ct.getId());
                pstm.execute();

                transformarResultSetEmReservas(reservas, pstm);
            }
            return reservas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(Integer id) {
        try {
            try (PreparedStatement stm = connection.prepareStatement("DELETE FROM RESERVAS WHERE ID = ?")) {
                stm.setInt(1, id);
                stm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void alterar(String dataEntrada, String dataSaida, String valor, String formaPagamento, Integer id) {

        try {
            try (PreparedStatement stm = connection.prepareStatement("UPDATE RESERVAS P SET P.DATA_ENTRADA = ?, P.DATA_SAIDA = ?, P.VALOR = ? , P.FORMA_PAGAMENTO = ? WHERE ID = ?")) {
                stm.setString(1, dataEntrada);
                stm.setString(2, dataSaida);
                stm.setString(3, valor);
                stm.setString(4, formaPagamento);
                stm.setInt(5, id);
                stm.execute();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void transformarResultSetEmReservas(List<Reserva> reserva, PreparedStatement pstm) {
        try {
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Reserva reservas = new Reserva(rst.getInt(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5));
                    reserva.add(reservas);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
