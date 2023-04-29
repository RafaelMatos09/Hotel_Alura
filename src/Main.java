import dao.HospedeDAO;
import factory.ConnectionFactory;
import model.Hospedes;
import views.Login;
import views.MenuPrincipal;

import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MenuPrincipal frame = new MenuPrincipal();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
