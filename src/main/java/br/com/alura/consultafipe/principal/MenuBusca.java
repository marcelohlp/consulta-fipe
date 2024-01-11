package br.com.alura.consultafipe.principal;

import br.com.alura.consultafipe.model.DadosBase;
import br.com.alura.consultafipe.model.Marca;
import br.com.alura.consultafipe.util.ConsumoAPI;
import br.com.alura.consultafipe.util.Conversor;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuBusca {

    private final String URL = "https://parallelum.com.br/fipe/api/v1/";

    private String enderecoWeb;
    private String json;
    private String tipoVeiculo;

    private Scanner leituraInput = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private Conversor conversor = new Conversor();

    public void exibirMenu() {

        obterTipoVeiculo();

        enderecoWeb = URL + tipoVeiculo + "/marcas";

        json = consumoAPI.obterDadosAPI(enderecoWeb);

        List<Marca> listaMarcas = obterMarcasVeiculos();

        listaMarcas.forEach(System.out::println);

        leituraInput.close();

    }

    private List<Marca> obterMarcasVeiculos() {
        List<DadosBase> lista = conversor.obterLista(json, DadosBase.class);
        return lista.stream()
                .map(Marca::new)
                .sorted(Comparator.comparing(Marca::getCodigo))
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

}
