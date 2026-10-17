package Principal.carga;

import java.io.Serializable;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigo;
    private String nome;
    private String categoria; 
    private double pesoUnitario; 
    private double precoUnitario;

    public Produto(String codigo, String nome, String categoria, double pesoUnitario, double precoUnitario) {
        this.codigo = codigo;
        this.nome = nome;
        this.categoria = categoria;
        this.pesoUnitario = pesoUnitario;
        this.precoUnitario = precoUnitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPesoUnitario() {
        return pesoUnitario;
    }

    public void setPesoUnitario(double pesoUnitario) {
        this.pesoUnitario = pesoUnitario;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%s) - %.2f kg - R$ %.2f", codigo, nome, categoria, pesoUnitario, precoUnitario);
    }
}
