package br.com.alura.consultafipe.model;

public class Veiculo {

    private String valor;
    private String marca;
    private String modelo;
    private String anoModelo;
    private String combustivel;

    public Veiculo(DadosVeiculo dadosVeiculo) {
        this.valor = dadosVeiculo.valor();
        this.marca = dadosVeiculo.marca();
        this.modelo = dadosVeiculo.modelo();
        this.anoModelo = dadosVeiculo.ano();
        this.combustivel = dadosVeiculo.combustivel();
    }

    @Override
    public String toString() {
        return "Modelo: " + this.modelo + ", Marca: " + this.marca +
                ", Valor: " + this.valor + ", Ano: " + this.anoModelo +
                ", Combustivel: " + this.combustivel;
    }

    public String getValor() {
        return valor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getAnoModelo() {
        return anoModelo;
    }

    public String getCombustivel() {
        return combustivel;
    }
}
