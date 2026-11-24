package Principal.util;

public class ValidadorCPF {
    public static boolean validar(String cpf) {
        cpf = cpf.replaceAll("\\D", "");
        if (cpf.length() != 11) return false;

        return !cpf.matches("(\\d)\\1{10}");
    }
}
