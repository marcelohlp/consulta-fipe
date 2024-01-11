package br.com.alura.consultafipe.principal;

import br.com.alura.consultafipe.model.DadosBase;
import br.com.alura.consultafipe.model.DadosModelo;
import br.com.alura.consultafipe.model.Marca;
import br.com.alura.consultafipe.model.Modelo;
import br.com.alura.consultafipe.util.ConsumoAPI;
import br.com.alura.consultafipe.util.Conversor;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuBusca {

    private final String URL = "https://parallelum.com.br/fipe/api/v1/";

    private String enderecoWeb = "";
    private String json;
    private String tipoVeiculo;
    private String codigoMarcaVeiculo;

    private Scanner leituraInput = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private Conversor conversor = new Conversor();

    public void consultarVeiculo() {

        obterTipoVeiculo();

        atualizarEderecoWeb(URL + tipoVeiculo + "/marcas");

        atualizarJson();

        List<Marca> listaMarcas = obterMarcasVeiculos();

        listaMarcas.forEach(System.out::println);

        obterCodigoMarcaVeiculo();

        atualizarEderecoWeb("/" + codigoMarcaVeiculo + "/modelos");

        atualizarJson();

        List<Modelo> listaModelos = obterModelosVeiculos();

        listaModelos.forEach(System.out::println);

        leituraInput.close();

    }

    private List<Modelo> obterModelosVeiculos() {
        DadosModelo modelos = conversor.obterObjeto(json, DadosModelo.class);
        return modelos.listaModelos().stream()
                .map(Modelo::new)
                .sorted(Comparator.comparing(Modelo::getNome))
                .collect(Collectors.toList());
    }

    private void obterCodigoMarcaVeiculo() {
        System.out.println("\nSelecione o código do modelo desejado: ");
        codigoMarcaVeiculo = leituraInput.nextLine();
    }

    private List<Marca> obterMarcasVeiculos() {
        List<DadosBase> lista = conversor.obterLista(json, DadosBase.class);
        return lista.stream()
                .map(Marca::new)
                .sorted(Comparator.comparing(Marca::getNome))
                .collect(Collectors.toList());
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

    private void atualizarJson() {
        json = consumoAPI.obterDadosAPI(enderecoWeb);
    }

    private void atualizarEderecoWeb(String parteEndereco) {
        enderecoWeb = enderecoWeb + parteEndereco;
    }

}
