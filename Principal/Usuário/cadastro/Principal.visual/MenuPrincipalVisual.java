package Principal.visual;

import Principal.frota.visual.CaminhaoVisual;
import javax.swing.*;
import java.awt.*;

public class MenuPrincipalVisual extends JFrame {

    public MenuPrincipalVisual(String nomeUsuario) {
        setTitle("Sistema Frota Coca-Cola - Menu Principal");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblBoasVindas = new JLabel("Bem-vindo(a), " + nomeUsuario + "!");
        lblBoasVindas.setBounds(30, 20, 380, 25);
        lblBoasVindas.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblBoasVindas);

        JLabel lblSub = new JLabel("Escolha o módulo que deseja gerenciar:");
        lblSub.setBounds(30, 50, 380, 20);
        add(lblSub);

        JButton btnFrota = new JButton("Gerenciar Frota de Caminhões");
        btnFrota.setBounds(50, 90, 330, 40);
        btnFrota.addActionListener(e -> {
           
            new CaminhaoVisual().setVisible(true);
        });
        add(btnFrota);

        JButton btnCargas = new JButton("Gerenciar Cargas e Produtos");
        btnCargas.setBounds(50, 145, 330, 40);
        btnCargas.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Módulo de Cargas em breve!");
        });
        add(btnCargas);

        JButton btnMotoristas = new JButton("Gerenciar Motoristas");
        btnMotoristas.setBounds(50, 200, 330, 40);
        btnMotoristas.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Módulo de Motoristas em breve!");
        });
        add(btnMotoristas);

        JButton btnSair = new JButton("Sair / Trocar de Conta");
        btnSair.setBounds(50, 275, 330, 35);
        btnSair.addActionListener(e -> {
            dispose(); 
            JOptionPane.showMessageDialog(this, "Sessão encerrada.");
        });
        add(btnSair);
    }
}
