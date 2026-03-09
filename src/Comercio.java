import java.io.File;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Comercio {
    /** Para inclusão de novos produtos no vetor */
    static final int MAX_NOVOS_PRODUTOS = 10;
    /**
     * Nome do arquivo de dados. O arquivo deve estar localizado na raiz do projeto
     */
    static String nomeArquivoDados;
    /** Scanner para leitura do teclado */
    static Scanner teclado;
    /**
     * Vetor de produtos cadastrados. Sempre terá espaço para 10 novos produtos a
     * cada execução
     */
    static Produto[] produtosCadastrados;
    /** Quantidade produtos cadastrados atualmente no vetor */
    static int quantosProdutos;

    /** Gera um efeito de pausa na CLI. Espera por um enter para continuar */
    static void pausa() {
        System.out.println("Digite enter para continuar...");
        teclado.nextLine();
    }

    /** Cabeçalho principal da CLI do sistema */
    static void cabecalho() {
        System.out.println("AEDII COMÉRCIO DE COISINHAS");
        System.out.println("===========================");
    }

    /**
     * Imprime o menu principal, lê a opção do usuário e a retorna (int).
     * Perceba que poderia haver uma melhor modularização com a criação de uma
     * classe Menu.
     * 
     * @return Um inteiro com a opção do usuário.
     */
    static int menu() {
        cabecalho();
        System.out.println("1 - Listar todos os produtos");
        System.out.println("2 - Procurar e listar um produto");
        System.out.println("3 - Cadastrar novo produto");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        return Integer.parseInt(teclado.nextLine());
    }

    /**
     * Lê os dados de um arquivo texto e retorna um vetor de produtos. Arquivo no
     * formato
     * N (quantiade de produtos) <br/>
     * tipo; descrição;preçoDeCusto;margemDeLucro;[dataDeValidade] <br/>
     * Deve haver uma linha para cada um dos produtos. Retorna um vetor vazio em
     * caso de problemas com o arquivo.
     * 
     * @param nomeArquivoDados Nome do arquivo de dados a ser aberto.
     * @return Um vetor com os produtos carregados, ou vazio em caso de problemas de
     *         leitura.
     */
    static Produto[] lerProdutos(String nomeArquivoDados) {
        Produto[] vetorProdutos = null;
        /*
         * Ler a primeira linha do arquivoDados contendo a quantidade de produtos
         * armazenados no arquivo.
         * Instanciar o vetorProdutos com o tamanho necessário para acomodar todos os
         * produtos do arquivo + o
         * espaço reserva MAX_NOVOS_PRODUTOS. Após isso, ler uma após a outra o restante
         * das linhas do arquivo,
         * convertendo, a cada leitura de linha, seus dados em objetos do tipo Produto
         * (utilizar o método
         * criarDoTexto()). Cada objeto Produto instanciado será armazenado no
         * vetorProdutos.
         */
        try (Scanner arq = new Scanner(new File(nomeArquivoDados), "UTF-8")) {
            int numeroProdutos = Integer.parseInt(arq.nextLine().trim());
            Produto[] vetor = new Produto[numeroProdutos + MAX_NOVOS_PRODUTOS];
            for (int i = 0; i < numeroProdutos && arq.hasNextLine(); i++) {
                vetor[i] = Produto.criarDoTexto(arq.nextLine());
            }
              zquantosProdutos = numeroProdutos;
                return vetor;
        } catch (Exception e) {
            quantosProdutos = 0;
            return new Produto[MAX_NOVOS_PRODUTOS];
        }
        return vetorProdutos;

    }

    /** Lista todos os produtos cadastrados, numerados, um por linha */
    static void listarTodosOsProdutos() {
        /*
         * Percorrer o vetor de produtosCadastrados, escrevendo na tela os dados de cada
         * um (utilizar o método
         * toString() já implementado).
         */

        if (quantosProdutos == 0) {
        System.out.println("Nenhum produto cadastrado.");
        return;
    }

    for (int i = 0; i < quantosProdutos; i++) {
        System.out.println((i + 1) + " - " + produtosCadastrados[i]);
    }
    }

    /**
     * Localiza um produto no vetor de cadastrados, a partir do nome (descrição), e
     * imprime seus dados.
     * A busca não é sensível ao caso. Em caso de não encontrar o produto, imprime
     * mensagem padrão
     */
    static void localizarProdutos() {
        /*
         * Ler do teclado a descrição (nome) do produto que o usuário deseja localizar,
         * procurar no vetor de
         * produtosCadastrados o produto em questão (utilizar o método equals() já
         * implementado na classe Produto)
         * e imprimir na tela seus dados.
         */

         System.out.print("Digite o nome do produto: ");
         String nome = teclado.nextLine();

         boolean encontrado = false;

    for (int i = 0; i < quantosProdutos; i++) {

        if (produtosCadastrados[i].getDescricao().equalsIgnoreCase(nome)) {
            System.out.println(produtosCadastrados[i]);
            encontrado = true;
            break;
        }
    }

    if (!encontrado) {
        System.out.println("Produto não encontrado.");
    }

    }

    /**
     * Rotina de cadastro de um novo produto: pergunta ao usuário o tipo do produto,
     * lê os dados correspondentes,
     * cria o objeto adequado de acordo com o tipo, inclui no vetor. Este método
     * pode ser feito com um nível muito
     * melhor de modularização. As diversas fases da lógica poderiam ser
     * encapsuladas em outros métodos.
     * Uma sugestão de melhoria mais significativa poderia ser o uso de padrão
     * Factory Method para criação dos
     * objetos.
     */
    static void cadastrarProduto() {
        /*
         * Implementar a sub-rotina de exibir o novo menu para cadastro de novo produto,
         * ler os dados necessários
         * conforme o tipo desejado, criar o objeto correspondente, salvando-o no vetor
         * de produtosCadastrados e
         * incrementando a variável de controle da quantidade de produtos.
         */
        if (quantosProdutos >= produtosCadastrados.length) {
        System.out.println("Limite de produtos atingido.");
        return;
    }

    System.out.println("Tipo de produto:");
    System.out.println("1 - Não Perecível");
    System.out.println("2 - Perecível");

    int tipo = Integer.parseInt(teclado.nextLine());

    System.out.print("Descrição: ");
    String descricao = teclado.nextLine();

    System.out.print("Preço de custo: ");
    double preco = Double.parseDouble(teclado.nextLine());

    System.out.print("Margem de lucro: ");
    double margem = Double.parseDouble(teclado.nextLine());

    Produto novoProduto = null;

    try {

        if (tipo == 1) {

            novoProduto = new ProdutoNaoPerecivel(descricao, preco, margem);

        } else if (tipo == 2) {

            System.out.print("Data de validade (dd/MM/yyyy): ");
            String data = teclado.nextLine();

            java.time.format.DateTimeFormatter formatter =
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");

            java.time.LocalDate validade =
                    java.time.LocalDate.parse(data, formatter);

            novoProduto = new ProdutoPerecivel(descricao, preco, margem, validade);
        }

        produtosCadastrados[quantosProdutos] = novoProduto;
        quantosProdutos++;

        System.out.println("Produto cadastrado com sucesso!");

    } catch (Exception e) {
        System.out.println("Erro ao cadastrar produto: " + e.getMessage());
    }
    }

    /**
     * Salva os dados dos produtos cadastrados no arquivo csv informado. Sobrescreve
     * todo o conteúdo do arquivo.
     * 
     * @param nomeArquivo Nome do arquivo a ser gravado.
     */
    public static void salvarProdutos(String nomeArquivo) {
        /*
         * Você deve implementar aqui a lógica que abrirá um arquivo para escrita com o
         * nome informado no
         * parâmetro, percorrerá um por um todos os produtos existentes no vetor de
         * produtosCadastrados, gerando
         * uma linha de texto com os dados de cada objeto Produto, escrevendo-a no
         * arquivo.
         */
         try (java.io.PrintWriter writer = new java.io.PrintWriter(nomeArquivo)) {

        writer.println(quantosProdutos);

        for (int i = 0; i < quantosProdutos; i++) {
            writer.println(produtosCadastrados[i].gerarDadosTexto());
        }

    } catch (Exception e) {
        System.out.println("Erro ao salvar produtos: " + e.getMessage());
    }
    }

    public static void main(String[] args) throws Exception {
        teclado = new Scanner(System.in, Charset.forName("ISO-8859-2"));
        nomeArquivoDados = "dadosProdutos.csv";
        produtosCadastrados = lerProdutos(nomeArquivoDados);
        int opcao = -1;
        do {
            opcao = menu();
            switch (opcao) {
                case 1 -> listarTodosOsProdutos();
                case 2 -> localizarProdutos();
                case 3 -> cadastrarProduto();
            }
            pausa();
        } while (opcao != 0);
        salvarProdutos(nomeArquivoDados);
        teclado.close();
    }
}