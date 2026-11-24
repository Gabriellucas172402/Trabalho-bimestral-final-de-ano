package Principal.Usuário.cadastro.logica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Properties;

public class LoginLogica {
    private static final String ARQUIVO_USUARIOS = "usuarios_frota_coca.properties";
    // O piso nacional de 2026 é apenas uma referência; a categoria pode ter piso maior.
    private static final double SALARIO_MINIMO_NACIONAL = 1621.00;

    public static boolean salvarUsuario(String nome, String cpf, char[] senha, String perfil)
            throws IOException {
        String cpfLimpo = limparCpf(cpf);
        if (nome == null || nome.trim().isEmpty()
                || cpfLimpo.length() != 11
                || senha == null || senha.length == 0
                || (!"Gerente".equals(perfil) && !"Funcionário".equals(perfil))) {
            return false;
        }

        Properties usuarios = carregarUsuarios();
        String chave = "usuario." + cpfLimpo + ".";
        if (usuarios.getProperty(chave + "nome") != null) {
            return false;
        }

        usuarios.setProperty(chave + "nome", nome.trim());
        usuarios.setProperty(chave + "perfil", perfil);
        usuarios.setProperty(chave + "senha", gerarHash(senha));

        try (FileOutputStream saida = new FileOutputStream(ARQUIVO_USUARIOS)) {
            usuarios.store(saida, "Cadastros do sistema");
        }
        return true;
    }

    public static String[] autenticar(String cpf, char[] senha) throws IOException {
        String cpfLimpo = limparCpf(cpf);
        if (cpfLimpo.length() != 11 || senha == null || senha.length == 0) {
            return null;
        }

        Properties usuarios = carregarUsuarios();
        String chave = "usuario." + cpfLimpo + ".";
        String senhaSalva = usuarios.getProperty(chave + "senha");
        if (senhaSalva == null || !senhaSalva.equals(gerarHash(senha))) {
            return null;
        }

        return new String[] {
            usuarios.getProperty(chave + "nome"),
            usuarios.getProperty(chave + "perfil")
        };
    }

    public static String[][] listarFuncionarios() throws IOException {
        Properties usuarios = carregarUsuarios();
        ArrayList<String[]> funcionarios = new ArrayList<>();

        for (String propriedade : usuarios.stringPropertyNames()) {
            if (propriedade.startsWith("usuario.")
                    && propriedade.endsWith(".perfil")
                    && "Funcionário".equals(usuarios.getProperty(propriedade))) {
                String cpf = propriedade.substring(
                    "usuario.".length(),
                    propriedade.length() - ".perfil".length()
                );
                String nome = usuarios.getProperty("usuario." + cpf + ".nome");
                if (nome != null) {
                    funcionarios.add(new String[] {nome, cpf});
                }
            }
        }

        return funcionarios.toArray(new String[funcionarios.size()][]);
    }

    public static String[] obterAtribuicao(String cpf) throws IOException {
        String chave = "usuario." + limparCpf(cpf) + ".";
        Properties usuarios = carregarUsuarios();
        return new String[] {
            usuarios.getProperty(chave + "funcao", "Ainda não definida"),
            usuarios.getProperty(chave + "salario", ""),
            usuarios.getProperty(chave + "cargaHoraria", ""),
            usuarios.getProperty(chave + "horaExtra", "Não informado"),
            usuarios.getProperty(chave + "turno", "Diurno (06:00 às 12:00)")
        };
    }

    public static boolean salvarAtribuicao(
            String cpf, String funcao, String salario, String cargaHoraria, String turno)
            throws IOException {
        String cpfLimpo = limparCpf(cpf);
        if (cpfLimpo.length() != 11 || funcao == null || funcao.trim().isEmpty()
                || salario == null || salario.trim().isEmpty()
                || cargaHoraria == null || cargaHoraria.trim().isEmpty()
                || turno == null || turno.trim().isEmpty()) {
            return false;
        }

        double valorSalario;
        try {
            valorSalario = Double.parseDouble(salario.trim());
        } catch (NumberFormatException ex) {
            return false;
        }
        double salarioMaximo = obterSalarioMaximo(funcao);
        int horas;
        try {
            horas = Integer.parseInt(cargaHoraria.trim());
        } catch (NumberFormatException ex) {
            return false;
        }
        if (salarioMaximo == 0 || Double.isNaN(valorSalario) || Double.isInfinite(valorSalario)
                || valorSalario < SALARIO_MINIMO_NACIONAL || valorSalario > salarioMaximo
                || horas <= 0 || horas > 44
                || (!"Diurno (06:00 às 12:00)".equals(turno)
                    && !"Noturno (18:00 às 00:00)".equals(turno))) {
            return false;
        }

        Properties usuarios = carregarUsuarios();
        String chave = "usuario." + cpfLimpo + ".";
        if (!"Funcionário".equals(usuarios.getProperty(chave + "perfil"))) {
            return false;
        }

        usuarios.setProperty(chave + "funcao", funcao.trim());
        usuarios.setProperty(chave + "salario", String.valueOf(valorSalario));
        usuarios.setProperty(chave + "cargaHoraria", cargaHoraria.trim());
        usuarios.setProperty(chave + "turno", turno.trim());
        try (FileOutputStream saida = new FileOutputStream(ARQUIVO_USUARIOS)) {
            usuarios.store(saida, "Cadastros e dados dos funcionarios");
        }
        return true;
    }

    public static double obterSalarioMinimo() {
        return SALARIO_MINIMO_NACIONAL;
    }

    public static double obterSalarioMaximo(String funcao) {
        if ("Motorista".equals(funcao)) {
            return 5000.00;
        }
        if ("Responsável por Manutenção".equals(funcao)) {
            return 5500.00;
        }
        if ("Responsável por Carga".equals(funcao)) {
            return 4800.00;
        }
        if ("Gestão da Frota".equals(funcao)) {
            return 6500.00;
        }
        return 0;
    }

    public static boolean demitirFuncionario(String cpf) throws IOException {
        String cpfLimpo = limparCpf(cpf);
        if (cpfLimpo.length() != 11) {
            return false;
        }

        Properties usuarios = carregarUsuarios();
        String prefixo = "usuario." + cpfLimpo + ".";
        if (!"Funcionário".equals(usuarios.getProperty(prefixo + "perfil"))) {
            return false;
        }

        ArrayList<String> propriedades = new ArrayList<>();
        for (Object chave : usuarios.keySet()) {
            String nomePropriedade = chave.toString();
            if (nomePropriedade.startsWith(prefixo)) {
                propriedades.add(nomePropriedade);
            }
        }
        for (String propriedade : propriedades) {
            usuarios.remove(propriedade);
        }

        try (FileOutputStream saida = new FileOutputStream(ARQUIVO_USUARIOS)) {
            usuarios.store(saida, "Cadastros e dados dos funcionarios");
        }
        return true;
    }

    public static void salvarRespostaHoraExtra(String cpf, boolean aceita) throws IOException {
        String cpfLimpo = limparCpf(cpf);
        Properties usuarios = carregarUsuarios();
        String chave = "usuario." + cpfLimpo + ".";
        if (!"Funcionário".equals(usuarios.getProperty(chave + "perfil"))) {
            throw new IOException("Funcionário não encontrado.");
        }

        usuarios.setProperty(chave + "horaExtra", aceita ? "Sim" : "Não");
        try (FileOutputStream saida = new FileOutputStream(ARQUIVO_USUARIOS)) {
            usuarios.store(saida, "Cadastros e dados dos funcionarios");
        }
    }

    private static Properties carregarUsuarios() throws IOException {
        Properties usuarios = new Properties();
        File arquivo = new File(ARQUIVO_USUARIOS);
        if (arquivo.exists()) {
            try (FileInputStream entrada = new FileInputStream(arquivo)) {
                usuarios.load(entrada);
            }
        }
        return usuarios;
    }

    private static String limparCpf(String cpf) {
        if (cpf == null) {
            return "";
        }
        return cpf.replaceAll("[^0-9]", "");
    }

    private static String gerarHash(char[] senha) {
        try {
            byte[] resultado = MessageDigest.getInstance("SHA-256")
                .digest(new String(senha).getBytes(StandardCharsets.UTF_8));
            StringBuilder hash = new StringBuilder();
            for (byte valor : resultado) {
                hash.append(String.format("%02x", valor & 0xff));
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 não está disponível.", e);
        }
    }
}
