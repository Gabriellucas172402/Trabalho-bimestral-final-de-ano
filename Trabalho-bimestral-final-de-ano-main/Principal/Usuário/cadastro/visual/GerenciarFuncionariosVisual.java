package Principal.Usuário.cadastro.visual;

import Principal.Usuário.cadastro.logica.LoginLogica;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Locale;

public class GerenciarFuncionariosVisual extends JFrame {
    private String[][] funcionarios = new String[0][];
    private final JComboBox<String> campoFuncionario = new JComboBox<>();
    private final JComboBox<String> campoFuncao = new JComboBox<>(new String[] {
        "Motorista",
        "Responsável por Manutenção",
        "Responsável por Carga",
        "Gestão da Frota"
    });
    private final JComboBox<String> campoTurno = new JComboBox<>(new String[] {
        "Diurno (06:00 às 12:00)",
        "Noturno (18:00 às 00:00)"
    });
    private final JTextField campoSalario = new JTextField();
    private final JTextField campoHorario = new JTextField();
    private final JLabel lblFaixaSalario = new JLabel();
    private final JLabel lblHoraExtra = new JLabel("Disponibilidade para hora extra: sem resposta");

    public GerenciarFuncionariosVisual() {
        setTitle("Gerenciar Funcionários");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel principal = new JPanel(new BorderLayout(12, 12));
        principal.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
        setContentPane(principal);

        JPanel cabecalho = new JPanel(new GridLayout(0, 1, 4, 4));
        JLabel titulo = new JLabel("Equipe de funcionários");
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        cabecalho.add(titulo);
        cabecalho.add(new JLabel(
            "Piso nacional de 2026 como referência; verifique eventual piso da categoria."
        ));
        cabecalho.add(new JLabel(
            "Faixas de exemplo; os máximos são internos, não tetos legais."
        ));
        principal.add(cabecalho, BorderLayout.NORTH);

        JPanel campos = new JPanel(new GridLayout(0, 2, 10, 9));
        campos.add(new JLabel("Funcionário:"));
        campos.add(campoFuncionario);
        campos.add(new JLabel("Função:"));
        campos.add(campoFuncao);
        campos.add(new JLabel("Salário mensal:"));

        JPanel painelSalario = new JPanel(new BorderLayout(5, 0));
        painelSalario.add(new JLabel("R$"), BorderLayout.WEST);
        painelSalario.add(campoSalario, BorderLayout.CENTER);
        campos.add(painelSalario);
        campos.add(new JLabel("Limites salariais:"));
        campos.add(lblFaixaSalario);
        campos.add(new JLabel("Turno:"));
        campos.add(campoTurno);
        campos.add(new JLabel("Horas por semana (máximo 44):"));
        campos.add(campoHorario);
        campos.add(new JLabel("Hora extra:"));
        campos.add(lblHoraExtra);
        principal.add(campos, BorderLayout.CENTER);

        JPanel botoes = new JPanel(new GridLayout(2, 1, 4, 4));
        JPanel acoesFuncionario = new JPanel(new FlowLayout(FlowLayout.CENTER, 7, 0));
        JButton botaoSalvar = new JButton("Salvar dados");
        botaoSalvar.addActionListener(e -> salvarDados(null));
        acoesFuncionario.add(botaoSalvar);

        JButton botaoPromover = new JButton("Promover");
        botaoPromover.addActionListener(e -> salvarDados("promover"));
        acoesFuncionario.add(botaoPromover);

        JButton botaoRebaixar = new JButton("Rebaixar");
        botaoRebaixar.addActionListener(e -> salvarDados("rebaixar"));
        acoesFuncionario.add(botaoRebaixar);

        JButton botaoDemitir = new JButton("Demitir");
        botaoDemitir.addActionListener(e -> demitirFuncionario());
        acoesFuncionario.add(botaoDemitir);
        botoes.add(acoesFuncionario);

        JPanel navegacao = new JPanel(new FlowLayout(FlowLayout.CENTER, 7, 0));
        JButton botaoAtualizar = new JButton("Atualizar lista");
        botaoAtualizar.addActionListener(e -> carregarFuncionarios());
        navegacao.add(botaoAtualizar);
        JButton botaoVoltar = new JButton("Voltar ao menu");
        botaoVoltar.addActionListener(e -> dispose());
        navegacao.add(botaoVoltar);
        botoes.add(navegacao);

        JPanel rodape = new JPanel(new BorderLayout(4, 6));
        rodape.add(botoes, BorderLayout.CENTER);
        rodape.add(new JLabel(
            "Turnos de exemplo; confira regras de intervalo e adicional noturno."
        ), BorderLayout.SOUTH);
        principal.add(rodape, BorderLayout.SOUTH);

        campoFuncionario.addActionListener(e -> preencherDadosFuncionario());
        campoFuncao.addActionListener(e -> atualizarFaixaSalario());
        carregarFuncionarios();

        setMinimumSize(new Dimension(620, 370));
        pack();
        setLocationRelativeTo(null);
    }

    private void carregarFuncionarios() {
        try {
            funcionarios = LoginLogica.listarFuncionarios();
            campoFuncionario.removeAllItems();
            for (String[] funcionario : funcionarios) {
                campoFuncionario.addItem(funcionario[0] + " - " + funcionario[1]);
            }
            preencherDadosFuncionario();
            if (funcionarios.length == 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Ainda não há funcionários cadastrados.",
                    "Lista de funcionários",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Não foi possível carregar os funcionários: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void preencherDadosFuncionario() {
        int indice = campoFuncionario.getSelectedIndex();
        if (indice < 0 || indice >= funcionarios.length) {
            campoSalario.setText("");
            campoHorario.setText("");
            lblHoraExtra.setText("Disponibilidade para hora extra: sem resposta");
            atualizarFaixaSalario();
            return;
        }

        try {
            String[] atribuicao = LoginLogica.obterAtribuicao(funcionarios[indice][1]);
            campoFuncao.setSelectedItem(atribuicao[0]);
            campoSalario.setText(atribuicao[1]);
            campoHorario.setText(atribuicao[2]);
            campoTurno.setSelectedItem(atribuicao[4]);
            lblHoraExtra.setText("Disponibilidade para hora extra: " + atribuicao[3]);
            atualizarFaixaSalario();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Não foi possível carregar os dados do funcionário: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void atualizarFaixaSalario() {
        String funcao = (String) campoFuncao.getSelectedItem();
        double minimo = LoginLogica.obterSalarioMinimo();
        double maximo = LoginLogica.obterSalarioMaximo(funcao);
        if (maximo == 0) {
            lblFaixaSalario.setText("Escolha uma função");
            return;
        }

        lblFaixaSalario.setText(
            String.format(new Locale("pt", "BR"), "R$ %.2f a R$ %.2f", minimo, maximo)
        );
        if (campoSalario.getText().trim().isEmpty()) {
            campoSalario.setText(String.format(Locale.US, "%.2f", minimo));
        }
    }

    private void salvarDados(String acao) {
        int indice = campoFuncionario.getSelectedIndex();
        if (indice < 0 || indice >= funcionarios.length) {
            JOptionPane.showMessageDialog(
                this,
                "Selecione um funcionário.",
                "Dados incompletos",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String funcao = (String) campoFuncao.getSelectedItem();
        double salario;
        int horas;
        try {
            salario = Double.parseDouble(campoSalario.getText().trim().replace(",", "."));
            horas = Integer.parseInt(campoHorario.getText().trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Informe o salário em reais e as horas por semana como números.",
                "Dados inválidos",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        double minimo = LoginLogica.obterSalarioMinimo();
        double maximo = LoginLogica.obterSalarioMaximo(funcao);
        if (Double.isNaN(salario) || Double.isInfinite(salario)
                || salario < minimo || salario > maximo) {
            JOptionPane.showMessageDialog(
                this,
                String.format(
                    new Locale("pt", "BR"),
                    "O salário para essa função deve estar entre R$ %.2f e R$ %.2f.",
                    minimo,
                    maximo
                ),
                "Salário fora da faixa",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        if (horas <= 0 || horas > 44) {
            JOptionPane.showMessageDialog(
                this,
                "Informe uma carga horária semanal entre 1 e 44 horas.",
                "Carga horária inválida",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (acao != null) {
            try {
                String[] atribuicaoAtual = LoginLogica.obterAtribuicao(funcionarios[indice][1]);
                if ("Ainda não definida".equals(atribuicaoAtual[0])) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Defina primeiro a função atual usando Salvar dados.",
                        "Não foi possível " + acao,
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                if (funcao.equals(atribuicaoAtual[0])) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Escolha uma função diferente para " + acao + " o funcionário.",
                        "Função não alterada",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                if (atribuicaoAtual[1].isEmpty()) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Defina primeiro o salário atual usando Salvar dados.",
                        "Não foi possível " + acao,
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                double salarioAtual = Double.parseDouble(atribuicaoAtual[1]);
                if ("promover".equals(acao) && salario <= salarioAtual) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Para promover, informe um salário maior que o atual.",
                        "Salário não aumentado",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                if ("rebaixar".equals(acao) && salario >= salarioAtual) {
                    JOptionPane.showMessageDialog(
                        this,
                        "Para rebaixar, informe um salário menor que o atual.",
                        "Salário não reduzido",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                int resposta = JOptionPane.showConfirmDialog(
                    this,
                    "Confirma " + acao + " " + funcionarios[indice][0]
                        + " para a função " + funcao + " com o salário informado?"
                        + ("rebaixar".equals(acao)
                            ? "\nConfira antes se a alteração salarial é permitida."
                            : ""),
                    "Confirmar alteração",
                    JOptionPane.YES_NO_OPTION
                );
                if (resposta != JOptionPane.YES_OPTION) {
                    return;
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível ler os dados atuais: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }
        }

        try {
            boolean salvo = LoginLogica.salvarAtribuicao(
                funcionarios[indice][1],
                funcao,
                String.format(Locale.US, "%.2f", salario),
                String.valueOf(horas),
                (String) campoTurno.getSelectedItem()
            );
            if (salvo) {
                preencherDadosFuncionario();
                JOptionPane.showMessageDialog(
                    this,
                    acao == null ? "Dados do funcionário salvos."
                        : "Função e dados atualizados para " + funcionarios[indice][0] + "."
                );
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível salvar. Atualize a lista e tente novamente.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Não foi possível salvar os dados: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void demitirFuncionario() {
        int indice = campoFuncionario.getSelectedIndex();
        if (indice < 0 || indice >= funcionarios.length) {
            JOptionPane.showMessageDialog(
                this,
                "Selecione um funcionário.",
                "Nenhum funcionário selecionado",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(
            this,
            "Demitir " + funcionarios[indice][0]
                + " e apagar permanentemente a conta e os dados cadastrados?",
            "Confirmar demissão",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        if (resposta != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (LoginLogica.demitirFuncionario(funcionarios[indice][1])) {
                JOptionPane.showMessageDialog(this, "Conta e dados do funcionário apagados.");
                carregarFuncionarios();
            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível demitir o funcionário. Atualize a lista e tente novamente.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Não foi possível apagar os dados: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
