
package Principal.frota.visual;

import Principal.frota.Veiculo;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CaminhaoVisual extends JFrame {
    private List<Veiculo> frota = new ArrayList<>();
    private JTable tabela;
    private DefaultTableModel modeloTabela;
    private JTextField txtPlaca, txtModelo, txtCapacidade, txtFiltroStatus;
    private final String ARQUIVO_DADOS = "frota_coca.dat";

    public CaminhaoVisual() {
        setTitle("Controle de Frota - Caminhões Coca-Cola");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        carregarDados();

        JPanel painelForm = new JPanel(new GridLayout(4, 2, 5, 5));
        painelForm.setBorder(BorderFactory.createTitledBorder("Cadastro de Caminhão"));
        
        painelForm.add(new JLabel("Placa:"));
        txtPlaca = new JTextField();
        painelForm.add(txtPlaca);

        painelForm.add(new JLabel("Modelo (ex: Volvo FH):"));
        txtModelo = new JTextField();
        painelForm.add(txtModelo);

        painelForm.add(new JLabel("Capacidade (Ton):"));
        txtCapacidade = new JTextField();
        painelForm.add(txtCapacidade);

        JButton btnSalvar = new JButton("Salvar / Cadastrar");
        painelForm.add(btnSalvar);
        
        JButton btnExcluir = new JButton("Excluir Selecionado");
        painelForm.add(btnExcluir);

        add(painelForm, BorderLayout.NORTH);

        modeloTabela = new DefaultTableModel(new String[]{"Placa", "Modelo", "Capacidade (t)", "Status"}, 0);
        tabela = new JTable(modeloTabela);
        atualizarTabela(frota);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        // Painel de Relatório com Filtro
        JPanel painelFiltro = new JPanel(new FlowLayout());
        painelFiltro.setBorder(BorderFactory.createTitledBorder("Relatório com Filtro por Status"));
        painelFiltro.add(new JLabel("Status (ex: Disponível):"));
        txtFiltroStatus = new JTextField(15);
        painelFiltro.add(txtFiltroStatus);
        
        JButton btnFiltrar = new JButton("Filtrar Relatório");
        painelFiltro.add(btnFiltrar);
        
        JButton btnLimparFiltro = new JButton("Mostrar Todos");
        painelFiltro.add(btnLimparFiltro);

        add(painelFiltro, BorderLayout.SOUTH);

        btnSalvar.e -> {
            try {
                String placa = txtPlaca.getText();
                String modelo = txtModelo.getText();
                double cap = Double.parseDouble(txtCapacidade.getText());
                frota.add(new Veiculo(placa, modelo, cap, "Disponível"));
                salvarDados();
                atualizarTabela(frota);
                JOptionPane.showMessageDialog(this, "Caminhão cadastrado com sucesso na frota!");
                txtPlaca.setText("");
                txtModelo.setText("");
                txtCapacidade.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro nos dados informados!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnExcluir.addActionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                frota.remove(linha);
                salvarDados();
                atualizarTabela(frota);
                JOptionPane.showMessageDialog(this, "Veículo removido!");
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um veículo na tabela.");
            }
        });

        btnFiltrar.addActionListener(e -> {
            String statusFiltro = txtFiltroStatus.getText().trim();
            List<Veiculo> filtrados = frota.stream()
                .filter(v -> v.getStatus().equalsIgnoreCase(statusFiltro))
                .collect(Collectors.toList());
            atualizarTabela(filtrados);
        });

        btnLimparFiltro.addActionListener(e -> atualizarTabela(frota));
    }

    private void atualizarTabela(List<Veiculo> lista) {
        modeloTabela.setRowCount(0);
        for (Veiculo v : lista) {
            modeloTabela.addRow(new Object[]{v.getPlaca(), v.getModelo(), v.getCapacidadeCarga(), v.getStatus()});
        }
    }

    private void salvarDados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_DADOS))) {
            oos.writeObject(frota);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void carregarDados() {
        File f = new File(ARQUIVO_DADOS);
        if (f.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                frota = (List<Veiculo>) ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
