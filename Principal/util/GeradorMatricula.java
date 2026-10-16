package Principal.util;

import java.util.Random;

public class GeradorMatricula {
    public static String gerar() {
        Random random = new Random();
        int numero = 1000 + random.nextInt(9000);
        return "COCA-" + numero;
    }
}
