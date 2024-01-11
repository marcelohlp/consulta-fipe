package br.com.alura.consultafipe.model;

public class Marca {

    private Integer codigo;
    private String nome;

    public Marca(DadosBase dadosMarcas) {
        try {
            this.codigo = Integer.parseInt(dadosMarcas.codigo());
        } catch (NumberFormatException e) {
            this.codigo = null;
        }
        this.nome = dadosMarcas.nome();
    }

    @Override
    public String toString() {
        return "Codigo: " + this.codigo + ", Nome: " + this.nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

}
