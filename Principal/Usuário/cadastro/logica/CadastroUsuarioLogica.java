package Principal.Usuário.cadastro.logica;

import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroUsuarioLogica {
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite seu nome completo: ");
        String nome = scanner.nextLine();

        System.out.print("Digite seu CPF: ");
        String cpf = scanner.next(); 

        System.out.print("Digite uma senha: ");
        String senha = scanner.next();

        JFrame janela = new JFrame("Cadastro de Usuário");
        janela.setSize(350, 200);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);
        janela.setLocationRelativeTo(null); 

        JButton botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setBounds(100, 70, 140, 40);

 
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Cadastro realizado com sucesso para o usuário: " + nome);
            }
        });

        janela.add(botaoCadastrar);
        janela.setVisible(true);
        scanner.close();
    }
}
