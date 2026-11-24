package Principal.frota;

import java.io.Serializable;

public class Veiculo implements Serializable {
    private String placa;
    private String modelo;
    private double capacidadeCarga;
    private String status; 

    public Veiculo(String placa, String modelo, double capacidadeCarga, String status) {
        this.placa = placa;
        this.modelo = modelo;
        this.capacidadeCarga = capacidadeCarga;
        this.status = status;
    }

    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public double getCapacidadeCarga() { return capacidadeCarga; }
    public String getStatus() { return status; }
    
    public void setStatus(String status) { this.status = status; }
}
