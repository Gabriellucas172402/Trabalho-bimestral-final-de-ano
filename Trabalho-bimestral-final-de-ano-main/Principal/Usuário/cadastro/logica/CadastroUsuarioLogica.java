package Principal.Usuário.cadastro.logica;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CadastroUsuarioLogica {
    public static void main(String[] args) {
        abrirCadastro();
    }

    public static void abrirCadastro() {
        JFrame janela = new JFrame("Cadastro de Usuário");
        janela.setSize(400, 350);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);
        janela.setLocationRelativeTo(null);

        JLabel textoNome = new JLabel("Nome completo:");
        textoNome.setBounds(30, 25, 120, 25);
        janela.add(textoNome);

        JTextField campoNome = new JTextField();
        campoNome.setBounds(150, 25, 200, 25);
        janela.add(campoNome);

        JLabel textoCpf = new JLabel("CPF:");
        textoCpf.setBounds(30, 70, 120, 25);
        janela.add(textoCpf);

        JTextField campoCpf = new JTextField();
        campoCpf.setBounds(150, 70, 200, 25);
        janela.add(campoCpf);

        JLabel textoSenha = new JLabel("Senha:");
        textoSenha.setBounds(30, 115, 120, 25);
        janela.add(textoSenha);

        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(150, 115, 200, 25);
        janela.add(campoSenha);

        JLabel textoPerfil = new JLabel("Tipo de acesso:");
        textoPerfil.setBounds(30, 160, 120, 25);
        janela.add(textoPerfil);

        String[] perfis = {"Funcionário", "Gerente"};
        JComboBox<String> campoPerfil = new JComboBox<>(perfis);
        campoPerfil.setBounds(150, 160, 200, 25);
        janela.add(campoPerfil);

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(45, 220, 140, 35);

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText().trim();
                String cpf = campoCpf.getText().trim();
                char[] senha = campoSenha.getPassword();
                String perfil = (String) campoPerfil.getSelectedItem();

                if (nome.isEmpty() || cpf.replaceAll("[^0-9]", "").length() != 11
                        || senha.length == 0) {
                    JOptionPane.showMessageDialog(
                        janela,
                        "Preencha o nome, informe um CPF com 11 números e digite uma senha.",
                        "Campos obrigatórios",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                try {
                    boolean salvo = LoginLogica.salvarUsuario(nome, cpf, senha, perfil);
                    if (!salvo) {
                        JOptionPane.showMessageDialog(
                            janela,
                            "Esse CPF já está cadastrado.",
                            "Cadastro não realizado",
                            JOptionPane.WARNING_MESSAGE
                        );
                        return;
                    }

                    JOptionPane.showMessageDialog(janela, "Cadastro realizado com sucesso.");
                    janela.dispose();
                    new Principal.Usuário.cadastro.visual.LoginVisual().setVisible(true);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(
                        janela,
                        "Não foi possível salvar o cadastro: " + ex.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        janela.add(botaoCadastrar);

        JButton botaoVoltar = new JButton("Voltar ao Login");
        botaoVoltar.setBounds(205, 220, 140, 35);
        botaoVoltar.addActionListener(e -> {
            janela.dispose();
            new Principal.Usuário.cadastro.visual.LoginVisual().setVisible(true);
        });
        janela.add(botaoVoltar);

        janela.setVisible(true);
    }
}
