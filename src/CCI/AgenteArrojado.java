package CCI;

/**
 *
 * @author Guibson Krause / Bianca
 */
public class AgenteArrojado extends AgenteInvestidor
{

    private double dinheiro = 100000;
    private int acao = 0;
    private int[] n1 = new int[4];
    private int[] n2 = new int[4];
    private int patamar = 0;
    private int v1 = 0, v2 = 0, result;

    public int getPatamar()
    {
        return patamar;
    }

    public void setPatamar(int patamar)
    {
        this.patamar = patamar;
    }

    public int getV1()
    {
        return v1;
    }

    public void setV1(int v1)
    {
        this.v1 = v1;
    }

    public int getV2()
    {
        return v2;
    }

    public void setV2(int v2)
    {
        this.v2 = v2;
    }

    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }

    @Override
    public int[] getN1()
    {
        return n1;
    }

    @Override
    public void setN1(int[] n1)
    {
        this.n1 = n1;
    }

    @Override
    public int[] getN2()
    {
        return n2;
    }

    @Override
    public void setN2(int[] n2)
    {
        this.n2 = n2;
    }

    public AgenteArrojado()
    {

    }

    @Override
    public double getDinheiro()
    {
        return dinheiro;
    }

    @Override
    public void setDinheiro(double dinheiro)
    {
        this.dinheiro = dinheiro;
    }

    @Override
    public int getAcao()
    {
        return acao;
    }

    @Override
    public void setAcao(int acao)
    {
        this.acao = acao;
    }

    @Override
    public void melhor(int[] resultado)
    {
        if (resultado[1] == 1 && resultado[2] == 1)
        {
            this.n1[3] += 1;
            if (patamar < n1[3])
            {
                v1 = n1[3];
            } else
            {
                v1 = 0;
            }
        } else if (resultado[1] == 1 && resultado[2] == 0)
        {
            this.n1[2] += 1;
            if (patamar < n1[2])
            {
                v1 = n1[2];
            } else
            {
                v1 = 0;
            }
        } else if (resultado[1] == 0 && resultado[2] == 1)
        {
            this.n1[1] += 1;
            if (patamar < n1[1])
            {
                v1 = n1[1];
            } else
            {
                v1 = 0;
            }
        } else if (resultado[1] == 0 && resultado[2] == 0)
        {
            this.n1[0] += 1;
            if (patamar < n1[0])
            {
                v1 = n1[0];
            } else
            {
                v1 = 0;
            }
        }

        if (resultado[0] == 1 && resultado[3] == 1)
        {
            this.n2[3] += 1;
            if (patamar < n2[3])
            {
                v2 = n2[3];
            } else
            {
                v2 = 0;
            }
        } else if (resultado[0] == 1 && resultado[3] == 0)
        {
            this.n2[2] += 1;
            if (patamar < n2[2])
            {
                v2 = n2[2];
            } else
            {
                v2 = 0;
            }
        } else if (resultado[0] == 0 && resultado[3] == 1)
        {
            this.n2[1] += 1;
            if (patamar < n2[1])
            {
                v2 = n2[1];
            } else
            {
                v2 = 0;
            }
        } else if (resultado[0] == 0 && resultado[3] == 0)
        {
            this.n2[0] += 1;
            if (patamar < n2[0])
            {
                v2 = n2[0];
            } else
            {
                v2 = 0;
            }
        }

        result = v1 + v2;
    }
}
