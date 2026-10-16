package Principal.Usuário.cadastro.logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginLogica {
    private static final String ARQUIVO_USUARIOS = "usuarios_frota_coca.properties";
    public static boolean salvarUsuario(String usuario, String senha) {
        if (usuario == null || usuario.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            return false;
        }

        Properties props = new Properties();
        File file = new File(ARQUIVO_USUARIOS);

        try {
       
            if (file.exists()) {
                try (FileInputStream in = new FileInputStream(file)) {
                    props.load(in);
                }
            }
            props.setProperty(usuario.trim(), senha);
            try (FileOutputStream out = new FileOutputStream(file)) {
                props.store(out, "Usuarios Autorizados - Frota Coca-Cola");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean autenticar(String usuario, String senha) {
        if (usuario == null || senha == null) {
            return false;
        }

        Properties props = new Properties();
        File file = new File(ARQUIVO_USUARIOS);

        if (!file.exists()) {
            return false;
        }

        try (FileInputStream in = new FileInputStream(file)) {
            props.load(in);
            String senhaSalva = props.getProperty(usuario.trim());
            return senhaSalva != null && senhaSalva.equals(senha);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
