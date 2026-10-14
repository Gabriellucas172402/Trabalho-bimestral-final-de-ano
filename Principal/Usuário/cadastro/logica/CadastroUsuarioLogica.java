import java.util.Scanner;

public class CadastroUsuarioLogica {
    public static void main(String[] args) {
        Scanner leia = new Scanner(System.in);

        System.out.print("Digite seu nome completo: ");
        String nome = leia.nextLine();

        System.out.print("Digite seu cpf : ");
        int cpf = leia.nextInt();
      
        System.out.print("Digite uma senha : ");
        String senha = leia.nextInt();

      

    //fazer dois verificador o id do usuario sera feito por uma lógica usando os dados como cpf nome e data de cadastro cetnão quando o usuario desejar fazer login direto ele colocara
    //  o id e a senha e iremos usar um verificador para ver se o id faz sentido e se fizer sentido iremos verificar se este id esta cadastrado  
        
        leia.close();
    }
}

