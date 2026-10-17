package Principal.manutencao;

import java.io.Serializable;

public class Oficina implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cnpj;
    private String nome;
    private String telefone;
    private String endereco;
    private String especialidade; 

    public Oficina(String cnpj, String nome, String telefone, String endereco, String especialidade) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.especialidade = especialidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return String.format("%s (CNPJ: %s) - Esp: %s - Tel: %s", nome, cnpj, especialidade, telefone);
    }
}
