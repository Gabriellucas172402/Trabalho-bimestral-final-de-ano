package Principal.visual;

import javax.swing.*;
import java.awt.*;

public class FuncionarioVisual extends JFrame {

    public FuncionarioVisual() {
        setTitle("Painel do Funcionário - Coca-Cola");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Área do Funcionário");
        lblTitulo.setBounds(120, 20, 180, 25);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblTitulo);

        JLabel lblCargo = new JLabel("Selecione seu Setor/Cargo:");
        lblCargo.setBounds(30, 60, 200, 20);
        add(lblCargo);

        String[] cargos = {"Motorista", "Responsável por Manutenção", "Responsável por Carga", "Gestão da Frota"};
        JComboBox<String> cbCargos = new JComboBox<>(cargos);
        cbCargos.setBounds(30, 85, 325, 30);
        add(cbCargos);

        JButton btnVerDados = new JButton("Ver Meus Dados (Salário e Prazos)");
        btnVerDados.setBounds(30, 140, 325, 40);
        btnVerDados.addActionListener(e -> {
            String cargoSelecionado = (String) cbCargos.getSelectedItem();
            double salario = 0;
            String prazo = "N/A";

            switch (cargoSelecionado) {
                case "Motorista":
                    salario = 3200.00;
                    prazo = "Entrega rota SP-04 às 16:00";
                    break;
                case "Responsável por Manutenção":
                    salario = 3500.00;
                    prazo = "Revisar caminhão placa ABC-1234 até amanhã";
                    break;
                case "Responsável por Carga":
                    salario = 2800.00;
                    prazo = "Montar carga de bebidas leves (Lote B)";
                    break;
                case "Gestão da Frota":
                    salario = 4500.00;
                    prazo = "Atualizar status de 5 veículos da base";
                    break;
            }

            JOptionPane.showMessageDialog(this, 
                "Cargo: " + cargoSelecionado + 
                "\nSalário Mensal: R$ " + String.format("%.2f", salario) + 
                "\nPrazo / Tarefa Atual: " + prazo, 
                "Meus Dados & Prazos", 
                JOptionPane.INFORMATION_MESSAGE);
        });
        add(btnVerDados);

        JButton btnVoltar = new JButton("Voltar ao Login");
        btnVoltar.setBounds(30, 210, 325, 35);
        btnVoltar.addActionListener(e -> {
            dispose();
            new LoginVisual().setVisible(true);
        });
        add(btnVoltar);
    }
}
