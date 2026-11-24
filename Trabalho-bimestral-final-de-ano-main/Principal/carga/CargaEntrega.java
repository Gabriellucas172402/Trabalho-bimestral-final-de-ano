package Principal.carga;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CargaEntrega implements Serializable {
    private static final long serialVersionUID = 1L;

    private String codigoCarga;
    private List<Produto> itens;
    private double pesoTotalEstimado;
    private String statusCarga;

    public CargaEntrega(String codigoCarga) {
        this.codigoCarga = codigoCarga;
        this.itens = new ArrayList<>();
        this.pesoTotalEstimado = 0.0;
        this.statusCarga = "Montada";
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        if (produto != null && quantidade > 0) {
          
            for (int i = 0; i < quantidade; i++) {
                this.itens.add(produto);
            }
            recalcularPeso();
        }
    }

    public void removerProduto(Produto produto) {
        this.itens.removeIf(p -> p.getCodigo().equals(produto.getCodigo()));
        recalcularPeso();
    }

    private void recalcularPeso() {
        this.pesoTotalEstimado = 0.0;
        for (Produto p : itens) {
            this.pesoTotalEstimado += p.getPesoUnitario();
        }
    }

  
    public String getCodigoCarga() {
        return codigoCarga;
    }

    public void setCodigoCarga(String codigoCarga) {
        this.codigoCarga = codigoCarga;
    }

    public List<Produto> getItens() {
        return itens;
    }

    public double getPesoTotalEstimado() {
        return pesoTotalEstimado;
    }

    public String getStatusCarga() {
        return statusCarga;
    }

    public void setStatusCarga(String statusCarga) {
        this.statusCarga = statusCarga;
    }

    @Override
    public String toString() {
        return String.format("Carga [%s] - Total de Itens: %d | Peso: %.2f kg | Status: %s",
                codigoCarga, itens.size(), pesoTotalEstimado, statusCarga);
    }
}
