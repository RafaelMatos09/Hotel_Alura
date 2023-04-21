package dao;

import model.Hospedes;
import model.Reserva;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HospedeDAO {
    private final Connection connection;

    public HospedeDAO(Connection connection) {
        this.connection = connection;
    }

    public void salvar(Hospedes hospedes) {
        try {
            String sql = "INSERT INTO HOSPEDES (ID, NOME, SOBRENOME,DATA_NASCIMENTO,NACIONALIDADE,TELEFONE, ID_RESERVA) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                pstm.setInt(1, hospedes.getId());
                pstm.setString(2, hospedes.getNome());
                pstm.setString(3, hospedes.getSobrenome());
                pstm.setString(4, hospedes.getDataNascimento());
                pstm.setString(5, hospedes.getNacionalidade());
                pstm.setString(6, hospedes.getTelefone());
                pstm.setString(7, hospedes.getReserva());

                pstm.execute();

                try (ResultSet rst = pstm.getGeneratedKeys()) {
                    while (rst.next()) {
                        hospedes.setId(rst.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Hospedes> listar() {
        try {
            List<Hospedes> hospedes = new ArrayList<Hospedes>();
            String sql = "SELECT ID, NOME, SOBRENOME,DATA_NASCIMENTO,NACIONALIDADE,TELEFONE, ID_RESERVA FROM HOSPEDES";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.execute();

                transformarResultSetEmHospedes(hospedes, pstm);
            }
            return hospedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Hospedes> buscar(Reserva ct) {
        try {
            List<Hospedes> hospedes = new ArrayList<Hospedes>();
            String sql = "SELECT ID, NOME, SOBRENOME,DATA_NASCIMENTO,NACIONALIDADE,TELEFONE, ID_RESERVA FROM HOSPEDES WHERE ID_RESERVA = ?";

            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setInt(1, ct.getId());
                pstm.execute();

                transformarResultSetEmHospedes(hospedes, pstm);
            }
            return hospedes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletarHospede(Integer id) {
        try {
            try (PreparedStatement stm = connection.prepareStatement("DELETE FROM HOSPEDES WHERE ID = ?")) {
                stm.setInt(1, id);
                stm.execute();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterar(String nome, String sobrenome, String dataNascimento, String nacionalidade, String telefone, Integer id){
        try {
            try (PreparedStatement stm = connection.prepareStatement("UPDATE HOSPEDES H SET H.NOME = ?, H.SOBRENOME = ?, H.DATA_NASCIMENTO = ?, H.NACIONALIDADE = ?, H.TELEFONE = ? WHERE ID = ?")) {
                stm.setString(1, nome);
                stm.setString(2, sobrenome);
                stm.setString(3, dataNascimento);
                stm.setString(4, nacionalidade);
                stm.setString(5, telefone);
                stm.setInt(6, id);
                stm.execute();
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    private void transformarResultSetEmHospedes(List<Hospedes> hospede, PreparedStatement pstm) {
        try {
            try (ResultSet rst = pstm.getResultSet()) {
                while (rst.next()) {
                    Hospedes hospedes = new Hospedes(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));

                    hospede.add(hospedes);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
