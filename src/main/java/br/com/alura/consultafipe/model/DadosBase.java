package br.com.alura.consultafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosBase(@JsonAlias("codigo") String codigo,
                        @JsonAlias("nome") String nome) {
}
