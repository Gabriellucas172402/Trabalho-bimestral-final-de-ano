package Principal.manutencao;

import java.io.Serializable;
import java.time.LocalDate;

public class RegistroManutencao implements Serializable {
    private static final long serialVersionUID = 1L;

    private String idRegistro;
    private String placaVeiculo; 
    private Oficina oficina; 
    private LocalDate dataManutencao;
    private String tipoManutencao; 
    private String descricao;
    private double custo;
    private String status;

    public RegistroManutencao(String idRegistro, String placaVeiculo, Oficina oficina, 
                              LocalDate dataManutencao, String tipoManutencao, 
                              String descricao, double custo, String status) {
        this.idRegistro = idRegistro;
        this.placaVeiculo = placaVeiculo;
        this.oficina = oficina;
        this.dataManutencao = dataManutencao;
        this.tipoManutencao = tipoManutencao;
        this.descricao = descricao;
        this.custo = custo;
        this.status = status;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public Oficina getOficina() {
        return oficina;
    }

    public void setOficina(Oficina oficina) {
        this.oficina = oficina;
    }

    public LocalDate getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(LocalDate dataManutencao) {
        this.dataManutencao = dataManutencao;
    }

    public String getTipoManutencao() {
        return tipoManutencao;
    }


    public void setTipoManutencao(String tipoManutencao) {
        this.tipoManutencao = tipoManutencao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getCusto() {
        return custo;
    }

    public void setCusto(double custo) {
        this.custo = custo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
