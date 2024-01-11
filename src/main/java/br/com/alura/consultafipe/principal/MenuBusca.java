package br.com.alura.consultafipe.principal;

import br.com.alura.consultafipe.model.*;
import br.com.alura.consultafipe.util.ConsumoAPI;
import br.com.alura.consultafipe.util.Conversor;

import java.util.ArrayList;
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

        atualizarDadosWeb(URL + tipoVeiculo + "/marcas");

        List<Marca> listaMarcas = obterMarcasVeiculos();

        listaMarcas.forEach(System.out::println);

        obterCodigoMarcaVeiculo();

        atualizarDadosWeb("/" + codigoMarcaVeiculo + "/modelos");

        List<Modelo> listaModelos = obterModelosVeiculos();

        listaModelos.forEach(System.out::println);

        String veiculoBuscado = obterVeiculoBuscado();

        List<Modelo> listaModelosFiltrados = obterListaResultadoBuscaVeiculos(listaModelos, veiculoBuscado);

        listaModelosFiltrados.forEach(System.out::println);

        String codigoVeiculoDesejado = obterVeiculoDesejado();

        atualizarDadosWeb("/" + codigoVeiculoDesejado + "/anos");

        List<DadosBase> anosDisponiveis = obterListaAnosDisponiveis();

        List<Veiculo> listaVeiculos = obterListaVeiculosResultado(anosDisponiveis);

        listaVeiculos.forEach(System.out::println);

        leituraInput.close();

    }

    private List<Veiculo> obterListaVeiculosResultado(List<DadosBase> anosDisponiveis) {
        List<DadosVeiculo> veiculos = obterListaVeiculosResultadoWeb(anosDisponiveis);
        return veiculos.stream()
                .map(Veiculo::new)
                .sorted(Comparator.comparing(Veiculo::getAnoModelo))
                .collect(Collectors.toList());
    }

    private List<DadosVeiculo> obterListaVeiculosResultadoWeb(List<DadosBase> anosDisponiveis) {
        List<DadosVeiculo> veiculos = new ArrayList<>();
        for (DadosBase anosDisponivei : anosDisponiveis) {
            json = consumoAPI.obterDadosAPI(enderecoWeb + "/" + anosDisponivei.codigo());
            DadosVeiculo veiculo = conversor.obterObjeto(json, DadosVeiculo.class);
            veiculos.add(veiculo);
        }
        return veiculos;
    }

    private List<DadosBase> obterListaAnosDisponiveis() {
        return conversor.obterLista(json, DadosBase.class);
    }

    private String obterVeiculoDesejado() {
        System.out.println("\nDigite o código do veículo desejado:");
        return leituraInput.nextLine();
    }

    private void atualizarDadosWeb(String enderecoWebAdicional) {
        atualizarEderecoWeb(enderecoWebAdicional);

        atualizarJson();
    }

    private static List<Modelo> obterListaResultadoBuscaVeiculos(List<Modelo> listaModelos, String veiculoBuscado) {
        return listaModelos.stream()
                        .filter(m -> m.getNome().toLowerCase().contains(veiculoBuscado.toLowerCase()))
                                .collect(Collectors.toList());
    }

    private String obterVeiculoBuscado() {
        System.out.println("\nDigite o nome do veículo que deseja procurar:");
        return leituraInput.nextLine();
    }

    private List<Modelo> obterModelosVeiculos() {
        DadosModelo modelos = conversor.obterObjeto(json, DadosModelo.class);
        return modelos.listaModelos().stream()
                .map(Modelo::new)
                .sorted(Comparator.comparing(Modelo::getNome))
                .collect(Collectors.toList());
    }

    private void obterCodigoMarcaVeiculo() {
        System.out.println("\nSelecione o código da marca desejado: ");
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
