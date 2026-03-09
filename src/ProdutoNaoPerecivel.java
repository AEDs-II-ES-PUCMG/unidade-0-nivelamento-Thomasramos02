import java.util.Locale;

public class ProdutoNaoPerecivel extends Produto {

    public ProdutoNaoPerecivel(String desc, double precoCusto, double margemLucro, int quantidadeProduto) {
        super(desc, precoCusto, margemLucro, quantidadeProduto);
    }

    public ProdutoNaoPerecivel(String desc, double precoCusto, int quantidadeProduto) {
        super(desc, precoCusto, quantidadeProduto);
    }

    @Override
    public String gerarDadosTexto() {
        return String.format("1;%s;%.2f;%.2f", getDescricao(), precoCusto, margemLucro);
    }

}
