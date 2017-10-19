package CCI;

/**
 *
 * @author Guibson Krause / Bianca
 */
public class AgenteInvestidor
{

    private double dinheiro;
    private int acao;
    private int[] n1 = new int[4];
    private int[] n2 = new int[4];

    public int[] getN1()
    {
        return n1;
    }

    public void setN1(int[] n1)
    {
        this.n1 = n1;
    }

    public int[] getN2()
    {
        return n2;
    }

    public void setN2(int[] n2)
    {
        this.n2 = n2;
    }

    public AgenteInvestidor()
    {
        this.dinheiro = 100000;
        this.acao = 0;

    }

    public double getDinheiro()
    {
        return dinheiro;
    }

    public void setDinheiro(double dinheiro)
    {
        this.dinheiro = dinheiro;
    }

    public int getAcao()
    {
        return acao;
    }

    public void setAcao(int acao)
    {
        this.acao = acao;
    }

    public void melhor(int[] resultado)
    {

    }

}
