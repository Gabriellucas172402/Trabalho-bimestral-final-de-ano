import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroUsuarioLogica {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);

        System.out.print("Digite seu nome completo: ");
        String nome = leia.nextLine();

        System.out.print("Digite seu cpf : ");
        int cpf = leia.nextInt();
      
        System.out.print("Digite uma senha : ");
        String senha = leia.nextInt();




    
        JFrame janela = new JFrame("Cadastro");
        janela.setSize(300, 200);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(null);

       
        JButton botao = new JButton("Cadastrar");
        botao.setBounds(80, 60, 140, 40); 

        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("O botão foi clicado!");
            }
        });

      
        janela.add(botao);
        janela.setVisible(true);
}




        
        leia.close();
    }
}
