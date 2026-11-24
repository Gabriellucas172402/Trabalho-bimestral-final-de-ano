package Principal.Usuário.cadastro.visual;

import Principal.Usuário.cadastro.logica.CadastroUsuarioLogica;
import Principal.Usuário.cadastro.logica.LoginLogica;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoginVisual extends JFrame {

    public LoginVisual() {
        setTitle("Sistema Logística Coca-Cola - Login");
        setSize(380, 310);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Acesso ao Sistema");
        lblTitulo.setBounds(115, 20, 180, 25);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblTitulo);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(40, 65, 100, 25);
        add(lblCpf);

        JTextField campoCpf = new JTextField();
        campoCpf.setBounds(140, 65, 200, 25);
        add(campoCpf);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(40, 110, 100, 25);
        add(lblSenha);

        JPasswordField campoSenha = new JPasswordField();
        campoSenha.setBounds(140, 110, 200, 25);
        add(campoSenha);

        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(120, 160, 140, 35);
        btnEntrar.addActionListener(e -> {
            try {
                String[] usuario = LoginLogica.autenticar(
                    campoCpf.getText(),
                    campoSenha.getPassword()
                );

                if (usuario == null) {
                    JOptionPane.showMessageDialog(
                        this,
                        "CPF ou senha incorretos.",
                        "Não foi possível entrar",
                        JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }

                if ("Gerente".equals(usuario[1])) {
                    new MenuPrincipalVisual(usuario[0]).setVisible(true);
                } else if ("Funcionário".equals(usuario[1])) {
                    new FuncionarioVisual(usuario[0], campoCpf.getText()).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(
                        this,
                        "O perfil deste cadastro não é válido.",
                        "Erro",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                dispose();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                    this,
                    "Não foi possível ler os cadastros: " + ex.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
        add(btnEntrar);

        JButton btnCadastrar = new JButton("Cadastrar usuário");
        btnCadastrar.setBounds(95, 210, 190, 35);
        btnCadastrar.addActionListener(e -> {
            CadastroUsuarioLogica.abrirCadastro();
            dispose();
        });
        add(btnCadastrar);
    }

    public static void main(String[] args) {
        new LoginVisual().setVisible(true);
    }
}
