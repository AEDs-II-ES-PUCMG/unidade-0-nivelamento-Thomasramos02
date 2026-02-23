import java.time.LocalDate;

public class ProdutoPerecivel  extends Produto {

    private static double DESCONTO = 0.25;
    private static int PRAZO_DESCONTO = 7;
    private LocalDate dataDeValidade;
    
    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate dataDeValidade) {
        super(desc, precoCusto, margemLucro);
        if(dataDeValidade.isBefore(LocalDate.now())) {
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
        if (dataDeValidade.isBefore(hoje)){
            throw new IllegalStateException("Produto vencido. Não pode ser vendido.");
        }
        if(dataDeValidade.isBefore(hoje.plusDays(PRAZO_DESCONTO))){
            return super.valorDeVenda() * DESCONTO;
        }
        
        return super.valorDeVenda();
    }
}