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



    //fazer dois verificador o id do usuario sera feito por uma lógica usando os dados como cpf nome e data de cadastro cetnão quando o usuario desejar fazer login direto ele colocara
    //  o id e a senha e iremos usar um verificador para ver se o id faz sentido e se fizer sentido iremos verificar se este id esta cadastrado  
        
        leia.close();
    }
}

