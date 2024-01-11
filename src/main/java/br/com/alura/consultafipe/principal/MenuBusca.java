package br.com.alura.consultafipe.principal;

import br.com.alura.consultafipe.util.ConsumoAPI;

import java.util.Scanner;

public class MenuBusca {

    private final static String URL = "https://parallelum.com.br/fipe/api/v1/";

    private String enderecoWeb;
    private String json;
    private String tipoVeiculo;

    private Scanner leituraInput = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();

    public void exibirMenu() {

        obterTipoVeiculo();

        enderecoWeb = URL + tipoVeiculo + "/marcas";

        json = consumoAPI.obterDadosAPI(enderecoWeb);

        System.out.println(json);

        leituraInput.close();

    }

    private void obterTipoVeiculo() {
        exibirOpcoesTipoVeiculo();
        System.out.println("Digite uma opção:");
        tipoVeiculo =  leituraInput.nextLine().toLowerCase();
    }

    private static void exibirOpcoesTipoVeiculo() {
        System.out.println("""
                *** Selecione o tipo de veículo ***
                carros
                motos
                caminhoes
                """);
    }

}
