package br.com.alura.consultafipe.util;

import java.util.List;

public interface ConversorDados {

    <T> T obterObjeto(String json, Class <T> classe);
    <T> List<T> obterLista(String json, Class <T> classe);

}
