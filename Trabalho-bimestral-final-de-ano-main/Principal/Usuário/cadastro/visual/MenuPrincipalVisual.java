package Principal.Usuário.cadastro.visual;

import Principal.frota.visual.CaminhaoVisual;
import Principal.carga.CargaEntrega;
import javax.swing.*;
import java.awt.*;

public class MenuPrincipalVisual extends JFrame {

    public MenuPrincipalVisual(String nomeUsuario) {
        setTitle("Sistema Frota Coca-Cola - Menu Principal (Gerente)");
        setSize(450, 440);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblBoasVindas = new JLabel("Bem-vindo(a), " + nomeUsuario + "!");
        lblBoasVindas.setBounds(30, 20, 380, 25);
        lblBoasVindas.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblBoasVindas);

        JLabel lblSub = new JLabel("Painel de Controle e Logística:");
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
            CargaEntrega cargaExemplo = new CargaEntrega("CARGA-2026-A");
            JOptionPane.showMessageDialog(this, 
                "Módulo de Cargas Conectado!\nStatus da Carga: " + cargaExemplo.getStatusCarga() + 
                "\nCódigo: " + cargaExemplo.getCodigoCarga(), 
                "Gestão de Cargas", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        add(btnCargas);

        JButton btnMotoristas = new JButton("Visualizar Funcionários / Motoristas");
        btnMotoristas.setBounds(50, 200, 330, 40);
        btnMotoristas.addActionListener(e -> {
            new GerenciarFuncionariosVisual().setVisible(true);
        });
        add(btnMotoristas);

        JButton btnManutencao = new JButton("Consultar Manutenções e Oficinas");
        btnManutencao.setBounds(50, 255, 330, 40);
        btnManutencao.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Módulo de Manutenção integrado ao sistema.");
        });
        add(btnManutencao);

        JButton btnSair = new JButton("Sair / Trocar de Conta");
        btnSair.setBounds(50, 325, 330, 35);
        btnSair.addActionListener(e -> {
            dispose();
            new LoginVisual().setVisible(true);
        });
        add(btnSair);
    }
}
