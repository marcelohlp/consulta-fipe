package br.com.alura.consultafipe.model;

import br.com.alura.consultafipe.util.ModificarDado;

public class Modelo {

    private Integer codigo;
    private String nome;

    public Modelo(DadosBase dadosBase) {
        this.codigo = ModificarDado.stringParaInteger(dadosBase.codigo());
        this.nome = dadosBase.nome();

    }

    @Override
    public String toString() {
        return "Nome: " + this.nome + ", Codigo: " + this.codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

}
