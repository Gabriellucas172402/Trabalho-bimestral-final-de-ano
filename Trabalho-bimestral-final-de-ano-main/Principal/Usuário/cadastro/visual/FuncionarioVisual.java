package Principal.Usuário.cadastro.visual;

import Principal.Usuário.cadastro.logica.LoginLogica;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;

public class FuncionarioVisual extends JFrame {
    private final String cpf;
    private final JLabel lblFuncao = new JLabel();
    private final JLabel lblSalario = new JLabel();
    private final JLabel lblHorario = new JLabel();
    private final JLabel lblTurno = new JLabel();
    private final JRadioButton opcaoHoraExtraSim = new JRadioButton("Sim");
    private final JRadioButton opcaoHoraExtraNao = new JRadioButton("Não");
    private final JButton botaoSalvar = new JButton("Salvar resposta");

    public FuncionarioVisual() {
        this("Funcionário", "");
    }

    public FuncionarioVisual(String nome) {
        this(nome, "");
    }

    public FuncionarioVisual(String nome, String cpf) {
        this.cpf = cpf;
        setTitle("Painel do Funcionário - Coca-Cola");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel principal = new JPanel(new BorderLayout(12, 12));
        principal.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));
        setContentPane(principal);

        JLabel titulo = new JLabel("Olá, " + nome);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        principal.add(titulo, BorderLayout.NORTH);

        JPanel dados = new JPanel(new GridLayout(0, 2, 12, 12));
        dados.setBorder(BorderFactory.createTitledBorder("Seus dados de trabalho"));
        adicionarLinha(dados, "Função:", lblFuncao);
        adicionarLinha(dados, "Salário mensal:", lblSalario);
        adicionarLinha(dados, "Carga horária semanal:", lblHorario);
        adicionarLinha(dados, "Turno:", lblTurno);

        ButtonGroup grupoHoraExtra = new ButtonGroup();
        grupoHoraExtra.add(opcaoHoraExtraSim);
        grupoHoraExtra.add(opcaoHoraExtraNao);
        JPanel opcoesHoraExtra = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        opcoesHoraExtra.add(opcaoHoraExtraSim);
        opcoesHoraExtra.add(opcaoHoraExtraNao);
        adicionarLinha(dados, "Pode fazer hora extra?", opcoesHoraExtra);
        principal.add(dados, BorderLayout.CENTER);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        botaoSalvar.addActionListener(e -> salvarRespostaHoraExtra());
        botoes.add(botaoSalvar);

        JButton btnVoltar = new JButton("Voltar ao Login");
        btnVoltar.addActionListener(e -> {
            dispose();
            new LoginVisual().setVisible(true);
        });
        botoes.add(btnVoltar);
        principal.add(botoes, BorderLayout.SOUTH);

        carregarDados();
        setMinimumSize(new Dimension(520, 320));
        pack();
        setLocationRelativeTo(null);
    }

    private void adicionarLinha(JPanel painel, String texto, Component componente) {
        painel.add(new JLabel(texto));
        painel.add(componente);
    }

    private void carregarDados() {
        if (cpf.isEmpty()) {
            mostrarSemAtribuicao();
            return;
        }

        try {
            String[] atribuicao = LoginLogica.obterAtribuicao(cpf);
            lblFuncao.setText(atribuicao[0]);
            if (atribuicao[1].isEmpty()) {
                lblSalario.setText("Não definido");
            } else {
                double salario = Double.parseDouble(atribuicao[1]);
                lblSalario.setText(String.format(new Locale("pt", "BR"), "R$ %.2f", salario));
            }
            lblHorario.setText(atribuicao[2].isEmpty()
                ? "Não definida"
                : atribuicao[2] + " horas por semana");
            lblTurno.setText(atribuicao[4]);

            boolean temAtribuicao = !atribuicao[1].isEmpty() && !atribuicao[2].isEmpty();
            opcaoHoraExtraSim.setEnabled(temAtribuicao);
            opcaoHoraExtraNao.setEnabled(temAtribuicao);
            botaoSalvar.setEnabled(temAtribuicao);

            if ("Sim".equals(atribuicao[3])) {
                opcaoHoraExtraSim.setSelected(true);
            } else if ("Não".equals(atribuicao[3])) {
                opcaoHoraExtraNao.setSelected(true);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Não foi possível carregar seus dados: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
            mostrarSemAtribuicao();
        }
    }

    private void mostrarSemAtribuicao() {
        lblFuncao.setText("Ainda não definida");
        lblSalario.setText("Não definido");
        lblHorario.setText("Não definida");
        lblTurno.setText("Não definido");
        opcaoHoraExtraSim.setEnabled(false);
        opcaoHoraExtraNao.setEnabled(false);
        botaoSalvar.setEnabled(false);
    }

    private void salvarRespostaHoraExtra() {
        if (!opcaoHoraExtraSim.isSelected() && !opcaoHoraExtraNao.isSelected()) {
            JOptionPane.showMessageDialog(
                this,
                "Escolha Sim ou Não para responder sobre hora extra.",
                "Resposta necessária",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            LoginLogica.salvarRespostaHoraExtra(cpf, opcaoHoraExtraSim.isSelected());
            JOptionPane.showMessageDialog(this, "Sua resposta foi salva.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Não foi possível salvar sua resposta: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
