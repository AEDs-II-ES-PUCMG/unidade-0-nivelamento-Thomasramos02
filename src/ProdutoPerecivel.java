import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ProdutoPerecivel extends Produto {

    private static double DESCONTO = 0.25;
    private static int PRAZO_DESCONTO = 7;
    private LocalDate dataDeValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate dataDeValidade) {
        super(desc, precoCusto, margemLucro);
        if (dataDeValidade.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Data de validade não pode ser anterior a data atual.");
        }
        this.dataDeValidade = dataDeValidade;
    }

    @Override
    public String toString() {
        return "ProdutoPerecivel [precoCusto=" + precoCusto + ", dataDeValidade=" + dataDeValidade + ", margemLucro="
                + margemLucro + ", valorDeVenda()=" + valorDeVenda() + ", toString()=" + super.toString() + "]";
    }

    public double valorVenda() {
        LocalDate hoje = LocalDate.now();
        if (dataDeValidade.isBefore(hoje)) {
            throw new IllegalStateException("Produto vencido. Não pode ser vendido.");
        }
        double valorNormal = super.valorDeVenda();

        long diasParaVencer = java.time.temporal.ChronoUnit.DAYS.between(hoje, dataDeValidade); // variavel para
                                                                                                // calcular vencimento a
                                                                                                // partir de hoje

        if (diasParaVencer <= PRAZO_DESCONTO) {
            return valorNormal * (1 - DESCONTO);
        }

        return valorNormal;
    }

    @Override
    public String gerarDadosTexto() {
        DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("2;%s;%.2f;%.2f;%s", getDescricao(), precoCusto, margemLucro,
                dataDeValidade.format(dataFormatada));
    }
}
