package Principal.visual;

import Principal.frota.visual.CaminhaoVisual;
import javax.swing.*;
import java.awt.*;

public class LoginVisual extends JFrame {

    public LoginVisual() {
        setTitle("Sistema Logística Coca-Cola - Login");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Acesso ao Sistema");
        lblTitulo.setBounds(100, 20, 150, 25);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblTitulo);

        JButton btnGerente = new JButton("Entrar como Gerente");
        btnGerente.setBounds(60, 70, 220, 35);
        btnGerente.addActionListener(e -> {
            dispose();
           
            new MenuPrincipalVisual("Gerente Geral").setVisible(true);
        });
        add(btnGerente);

        JButton btnFuncionario = new JButton("Entrar como Funcionário");
        btnFuncionario.setBounds(60, 120, 220, 35);
        btnFuncionario.addActionListener(e -> {
            dispose();
           
            JOptionPane.showMessageDialog(this, "Abrindo painel do Funcionário...");
        });
        add(btnFuncionario);
    }

    public static void main(String[] args) {
        new LoginVisual().setVisible(true);
    }
}
