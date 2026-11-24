package Principal.motorista;

import Principal.util.GeradorMatricula;
import Principal.util.ValidadorCPF;
import java.io.Serializable;

public class Motorista implements Serializable {
    private static final long serialVersionUID = 1L;

    private String matricula; 
    private String nome;
    private String cpf;
    private String cnh;
    private String categoriaCNH; 
    private boolean ativo;

    public Motorista(String nome, String cpf, String cnh, String categoriaCNH) throws IllegalArgumentException {
        if (!ValidadorCPF.validar(cpf)) {
            throw new IllegalArgumentException("CPF informado é inválido!");
        }
        this.matricula = GeradorMatricula.gerar();
        this.nome = nome;
        this.cpf = cpf;
        this.cnh = cnh;
        this.categoriaCNH = categoriaCNH;
        this.ativo = true;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getCategoriaCNH() {
        return categoriaCNH;
    }

    public void setCategoriaCNH(String categoriaCNH) {
        this.categoriaCNH = categoriaCNH;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - CNH: %s (%s)", matricula, nome, cnh, categoriaCNH);
    }
}
