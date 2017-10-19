package CDP;

/**
 *
 * @author Guibson Krause / Bianca
 */
public class Cotacao
{

    private String cotacao;
    private String inicial;
    private String treinamento;
    private String producao;
    private String avaliacao;

    public Cotacao(String cotacao, String inicial, String treinamento, String producao, String avaliacao)
    {
        this.cotacao = cotacao;
        this.avaliacao = avaliacao;
        this.inicial = inicial;
        this.producao = producao;
        this.treinamento = treinamento;

    }

    public Cotacao()
    {

    }

    public String getCotacao()
    {
        return cotacao;
    }

    public void setCotacao(String cotacao)
    {
        this.cotacao = cotacao;
    }

    public String getInicial()
    {
        return inicial;
    }

    public void setInicial(String inicial)
    {
        this.inicial = inicial;
    }

    public String getTreinamento()
    {
        return treinamento;
    }

    public void setTreinamento(String treinamento)
    {
        this.treinamento = treinamento;
    }

    public String getProducao()
    {
        return producao;
    }

    public void setProducao(String producao)
    {
        this.producao = producao;
    }

    public String getAvaliacao()
    {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao)
    {
        this.avaliacao = avaliacao;
    }

}
