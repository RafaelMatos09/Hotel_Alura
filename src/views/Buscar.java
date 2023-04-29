package views;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.List;

import model.Reserva;
import model.Hospedes;
import controller.ReservaController;
import controller.HospedesController;

@SuppressWarnings("serial")
public class Buscar extends JFrame {

    int xMouse, yMouse;
    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHospedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHospedes;
    private JLabel labelAtras;
    private JLabel labelExit;
    private HospedesController hospedesController;
    private ReservaController reservaController;

    /**
     * Create the frame.
     */
    public Buscar() {
        this.hospedesController = new HospedesController();
        this.reservaController = new ReservaController();
        setIconImage(Toolkit.getDefaultToolkit().getImage(Buscar.class.getResource("/imagenes/lOGO-50PX.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblTitulo = new JLabel("SISTEMA DE BUSCA");
        lblTitulo.setForeground(new Color(12, 138, 199));
        lblTitulo.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblTitulo.setBounds(331, 62, 280, 42);
        contentPane.add(lblTitulo);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Data Check In");
        modelo.addColumn("Data Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");

        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Buscar.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);
        preencherTabelaReserva();

        tbHospedes = new JTable();
        tbHospedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHospedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHospedes = (DefaultTableModel) tbHospedes.getModel();
        modeloHospedes.addColumn("Numero de Hóspede");
        modeloHospedes.addColumn("Nome");
        modeloHospedes.addColumn("Sobrenome");
        modeloHospedes.addColumn("Data de Nascimento");
        modeloHospedes.addColumn("Nacionalidade");
        modeloHospedes.addColumn("Telefone");
        modeloHospedes.addColumn("Numero de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHospedes);
        panel.addTab("Huéspedes", new ImageIcon(Buscar.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);
        preencherTabelaHospedes();

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Buscar.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { // Quando o usuário passa o mouse sobre o botão, ele muda de cor
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Quando o usuário remove o mouse do botão, ele retornará ao estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                alterarN(panel);
            }
        });
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));


        JPanel btnDeletar = new JPanel();
        btnDeletar.setLayout(null);
        btnDeletar.setBackground(new Color(12, 138, 199));
        btnDeletar.setBounds(767, 508, 122, 35);
        btnDeletar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnDeletar);

        JLabel lblExcluir = new JLabel("DELETAR");
        lblExcluir.setHorizontalAlignment(SwingConstants.CENTER);
        lblExcluir.setForeground(Color.WHITE);
        lblExcluir.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblExcluir.setBounds(0, 0, 122, 35);
        btnDeletar.add(lblExcluir);
        setResizable(false);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Buscar frame = new Buscar();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //Código que permite movimentar a janela pela tela seguindo a posição de "x" e "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    private void preencherTabelaReserva() {
        modelo.setRowCount(0);
        List<Reserva> reservas = reservaController.listar();
        try {
            for (Reserva reserva : reservas) {
                Object[] rowData = new Object[5];
                rowData[0] = reserva.getId();
                rowData[1] = reserva.getDataEntrada();
                rowData[2] = reserva.getDataSaida();
                rowData[3] = reserva.getValorStr();
                rowData[4] = reserva.getFormaPagamento();

                modelo.addRow(rowData);
            }
        } catch (Exception e) {
            throw e;
        }

    }

    private void preencherTabelaHospedes() {
        modeloHospedes.setRowCount(0); // Limpa as linhas da tabela
        List<Hospedes> hospedes = hospedesController.listar();
        try {
            for (Hospedes hospede : hospedes) {
                Object[] rowData = new Object[7]; // Cria um array para guardar os dados de uma linha da tabela
                rowData[0] = hospede.getId();
                rowData[1] = hospede.getNome();
                rowData[2] = hospede.getSobrenome();
                rowData[3] = hospede.getDataNascimento();
                rowData[4] = hospede.getNacionalidade();
                rowData[5] = hospede.getTelefone();
                rowData[6] = hospede.getReserva(); // Adiciona o número de reserva à última coluna da linha

                modeloHospedes.addRow(rowData);
            }
        } catch (Exception e) {
            throw e;
        }

    }

    private void alterarN(JTabbedPane panel) {

        int activeTabIndex = panel.getSelectedIndex();
        int tabReservas = 0;
        int tabHospedes = 1;

        if (activeTabIndex == tabReservas) {
            Object objetoDaLinha = (Object) modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn());
            if (objetoDaLinha instanceof Integer) {
                Integer id = (Integer) objetoDaLinha;
                String dataEntrada = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 1);
                String dataSaida = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 2);
                String valor = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 3);
                String formaPagamento = (String) modelo.getValueAt(tbReservas.getSelectedRow(), 4);
                this.reservaController.alterar(dataEntrada, dataSaida, valor, formaPagamento, id);
                JOptionPane.showMessageDialog(this, "Alterado com sucesso!");
            }
        } else if (activeTabIndex == tabHospedes) {
            Object objetoDaLinha = (Object) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), tbHospedes.getSelectedColumn());
            if (objetoDaLinha instanceof Integer) {
                Integer id = (Integer) objetoDaLinha;
                String nome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 1);
                String sobrenome = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 2);
                String dataNascimento = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 3);
                String nacionalidade = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 4);
                String telefone = (String) modeloHospedes.getValueAt(tbHospedes.getSelectedRow(), 5);
                this.hospedesController.alterar(nome, sobrenome, dataNascimento, nacionalidade, telefone, id);
                JOptionPane.showMessageDialog(this, "Alterado com sucesso!");

            } else {
                JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");

            }
        }
    }


    private List<Reserva> listarReserva() {
        return this.reservaController.listar();
    }

    private List<Hospedes> listarHospedes() {
        return this.hospedesController.listar();
    }
}
