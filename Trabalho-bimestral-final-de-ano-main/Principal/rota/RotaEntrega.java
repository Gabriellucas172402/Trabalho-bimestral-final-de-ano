package Principal.rota;

import java.io.Serializable;

public class RotaEntrega implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigoRota;
    private String origem;
    private String destino;
    private String matriculaMotorista; 
    private String placaVeiculo;      
    private double distanciaKm;
    private String statusRota;       

    public RotaEntrega(String codigoRota, String origem, String destino, 
                       String matriculaMotorista, String placaVeiculo, double distanciaKm, String statusRota) {
        this.codigoRota = codigoRota;
        this.origem = origem;
        this.destino = destino;
        this.matriculaMotorista = matriculaMotorista;
        this.placaVeiculo = placaVeiculo;
        this.distanciaKm = distanciaKm;
        this.statusRota = statusRota;
    }

    public String getCodigoRota() {
        return codigoRota;
    }

    public void setCodigoRota(String codigoRota) {
        this.codigoRota = codigoRota;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMatriculaMotorista() {
        return matriculaMotorista;
    }

    public void setMatriculaMotorista(String matriculaMotorista) {
        this.matriculaMotorista = matriculaMotorista;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public String getStatusRota() {
        return statusRota;
    }

    public void setStatusRota(String statusRota) {
        this.statusRota = statusRota;
    }

    @Override
    public String toString() {
        return String.format("Rota [%s] de %s para %s | Distância: %.1f km | Veículo: %s | Motorista: %s | Status: %s",
                codigoRota, origem, destino, distanciaKm, placaVeiculo, matriculaMotorista, statusRota);
    }
}
