import dao.HospedeDAO;
import factory.ConnectionFactory;
import model.Hospedes;

import java.sql.Connection;

public class TestaInsercakl
 {
     public static void main(String[] args) {
         ConnectionFactory factory = new ConnectionFactory();
         Connection connection = factory.recuperaConexao();

         Hospedes djmael = new Hospedes("eldj","djbydj", "1992-02-20","xuxeluf","22345676", "22" );

         HospedeDAO hospedeDAO = new HospedeDAO(connection);
         hospedeDAO.salvar(djmael);
     }
}
