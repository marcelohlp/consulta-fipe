package br.com.alura.consultafipe.util;

public class ModificarDado {

    public static Integer stringParaInteger(String numeroInteiro) {
        try {
            return Integer.parseInt(numeroInteiro);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
