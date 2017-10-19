package CCI;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Guibson Krause / Bianca
 */
public class Servidor
{

    private static final int port = 6789;

    //dinheiro
    public static void main(String[] args)
    {
        ArrayList<Double> listaCot = new ArrayList<>();
        ArrayList<Double> listaM3 = new ArrayList<>();
        ArrayList<Double> listaM5 = new ArrayList<>();
        AgenteArrojado agenteArrojado = new AgenteArrojado();
        AgenteModerado agenteModerado = new AgenteModerado();
        AgenteConservador agenteConservador = new AgenteConservador();

        int[] vet;
        int[] vetResultados;

        DatagramSocket aSocket = null;
        double valorArroja = 0;
        double valorModera = 0;
        double valorConser = 0;
        double[] aux;
        double valor = 0;
        double dinheiroArroj = agenteArrojado.getDinheiro();
        int acaoArroj = agenteArrojado.getAcao();
        double dinheiroMode = agenteModerado.getDinheiro();
        int acaoMode = agenteModerado.getAcao();
        double dinheiroConse = agenteConservador.getDinheiro();
        int acaoConse = agenteConservador.getAcao();

        int a = 0, x = 0, i = 0, b = 0, v = 0;

        try
        {
            aSocket = new DatagramSocket(port);
            byte[] buffer = new byte[100];

            while (true)
            {
                vet = new int[7];
                vetResultados = new int[4];
                i++;
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);

                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());

                System.out.println("Respondendo: " + new String(reply.getData()));

                if (i >= 5 && i < 20)
                {
                    a = 1;
                }
                if (i > 20)
                {
                    a = 2;
                }
                if (i < 5)
                {
                    a = 0;
                }

                reply.setLength(a);
                String message = new String(request.getData());
                double cotacao = Double.parseDouble(message);
                listaCot.add(x, cotacao);
                x++;
                if (listaCot.size() > 0 && listaCot.size() < 3)
                {
                    listaM3.add(0.0);

                }
                if (listaCot.size() > 0 && listaCot.size() < 5)
                {
                    listaM5.add(0.0);
                }
                if (listaCot.size() >= 3)
                {
                    listaM3.add((listaCot.get(listaCot.size() - 1) + listaCot.get(listaCot.size() - 2) + listaCot.get(listaCot.size() - 3)) / 3);
                }
                if (listaCot.size() >= 5)
                {

                    listaM5.add((listaCot.get(listaCot.size() - 1) + listaCot.get(listaCot.size() - 2) + listaCot.get(listaCot.size() - 3) + listaCot.get(listaCot.size() - 4) + listaCot.get(listaCot.size() - 5)) / 5);

                }
                if (i <= 20)
                {
                    if (listaCot.size() >= 6)
                    {
                        x = listaCot.size() - 1;
                        System.out.println("entrou");
                        if (listaM3.get(x) > listaM3.get(x - 1))
                        {
                            if (agenteArrojado.getDinheiro() > (cotacao * 5))
                            {
                                double porcentArro = agenteArrojado.getDinheiro() * 0.20;
                                porcentArro = porcentArro / cotacao;
                                int divArro = (int) porcentArro;
                                agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() - (divArro * cotacao));
                                agenteArrojado.setAcao(agenteArrojado.getAcao() + divArro);
                                System.out.println("entrou 1");
                            }
                            if (listaM3.get(x) > listaM5.get(x))
                            {
                                if (agenteModerado.getDinheiro() > (cotacao * 10))
                                {
                                    double porcentMod = agenteModerado.getDinheiro() * 0.10;
                                    porcentMod = porcentMod / cotacao;
                                    int divMod = (int) porcentMod;
                                    agenteModerado.setDinheiro(agenteModerado.getDinheiro() - (divMod * cotacao));
                                    agenteModerado.setAcao(agenteModerado.getAcao() + divMod);
                                    System.out.println("entrou 2");
                                }
                                if (cotacao > listaM3.get(x))
                                {
                                    if (agenteConservador.getDinheiro() > (cotacao * 20))
                                    {
                                        double porcentCons = agenteConservador.getDinheiro() * 0.05;
                                        porcentCons = porcentCons / cotacao;
                                        int divCons = (int) porcentCons;
                                        agenteConservador.setDinheiro(agenteConservador.getDinheiro() - (divCons * cotacao));
                                        agenteConservador.setAcao(agenteConservador.getAcao() + divCons);
                                        System.out.println("entrou 3");
                                    }

                                } else
                                {
                                    if (agenteArrojado.getAcao() > 5)
                                    {
                                        double percentArro = agenteArrojado.getAcao() * 0.20;
                                        int vendArro = (int) percentArro;
                                        agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() + (vendArro * cotacao));
                                        agenteArrojado.setAcao(agenteArrojado.getAcao() - vendArro);
                                        System.out.println("entrou 4");
                                    }
                                }
                            } else
                            {
                                if (agenteArrojado.getAcao() > 5)
                                {
                                    double percentArro = agenteArrojado.getAcao() * 0.20;
                                    int vendArro = (int) percentArro;
                                    agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() + (vendArro * cotacao));
                                    agenteArrojado.setAcao(agenteArrojado.getAcao() - vendArro);
                                }
                                if (agenteModerado.getAcao() > 10)
                                {
                                    double percentMod = agenteModerado.getAcao() * 0.20;
                                    int vendMod = (int) percentMod;
                                    agenteModerado.setDinheiro(agenteModerado.getDinheiro() + (vendMod * cotacao));
                                    agenteModerado.setAcao(agenteModerado.getAcao() - vendMod);
                                }
                                System.out.println("entrou 5");
                            }
                        } else
                        {
                            System.out.println("entrou 6");
                            if (agenteArrojado.getAcao() > 5)
                            {
                                double percentArro = agenteArrojado.getAcao() * 0.20;
                                int vendArro = (int) percentArro;
                                agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() + (vendArro * cotacao));
                                agenteArrojado.setAcao(agenteArrojado.getAcao() - vendArro);
                            }
                            if (agenteModerado.getAcao() > 10)
                            {
                                double percentMod = agenteModerado.getAcao() * 0.20;
                                int vendMod = (int) percentMod;
                                agenteModerado.setDinheiro(agenteModerado.getDinheiro() + (vendMod * cotacao));
                                agenteModerado.setAcao(agenteModerado.getAcao() - vendMod);
                            }
                            if (agenteConservador.getAcao() > 20)
                            {
                                double percentCons = agenteConservador.getAcao() * 0.20;
                                int vendCons = (int) percentCons;
                                agenteConservador.setDinheiro(agenteConservador.getDinheiro() + (vendCons * cotacao));
                                agenteConservador.setAcao(agenteConservador.getAcao() - vendCons);
                            }
                        }
                    }
                }
                valorArroja = agenteArrojado.getDinheiro() + (cotacao * agenteArrojado.getAcao());
                valorModera = agenteModerado.getDinheiro() + (cotacao * agenteModerado.getAcao());
                valorConser = agenteConservador.getDinheiro() + (cotacao * agenteConservador.getAcao());

                if (listaCot.size() >= 6)
                {
                    x = listaCot.size() - 1;
                    if (listaM3.get(x) > listaM3.get(x - 1))
                    {
                        vet[0] = 1;

                        if (listaM5.get(x) > listaM5.get(x - 1))
                        {
                            vet[1] = 1;

                        }
                        if (listaM3.get(x) > listaM5.get(x))
                        {
                            vet[2] = 1;
                            if (cotacao > listaM3.get(x))
                            {

                            } else
                            {
                                vet[3] = 1;
                            }
                        }
                    }
                    for (int j = 0; j < 4; j++)
                    {
                        vetResultados[j] = vet[j];
                    }

                    if (valorArroja > valorConser && valorArroja > valorModera)
                    {
                        vet[4] = 1;
                    } else if (valorModera > valorArroja && valorModera > valorConser)
                    {
                        vet[5] = 1;
                    } else if (valorConser > valorArroja && valorConser > valorModera)
                    {
                        vet[6] = 1;
                    }

                    if (vet[4] == 1)
                    {
                        for (int j = 0; j < vet.length; j++)
                        {
                            System.out.println(" " + vet[j]);
                        }
                        agenteArrojado.melhor(vetResultados);
                    } else if (vet[5] == 1)
                    {
                        for (int j = 0; i < vet.length; i++)
                        {
                            System.out.println("mod  " + vet[j]);
                        }
                        agenteModerado.melhor(vetResultados);

                    } else if (vet[6] == 1)
                    {
                        for (int j = 0; i < vet.length; i++)
                        {
                            System.out.println("cons  " + vet[j]);
                        }
                        agenteConservador.melhor(vetResultados);

                    }

                    if (i >= 20)
                    {

                        if (agenteArrojado.getResult() > agenteConservador.getResult() && agenteArrojado.getResult() > agenteModerado.getResult())
                        {
                            System.out.println("Arrojado é a melhor opção");
                            if (listaM3.get(x) > listaM3.get(x - 1))
                            {

                                if (agenteArrojado.getDinheiro() > (cotacao * 5))
                                {
                                    double porcentArro = agenteArrojado.getDinheiro() * 0.20;
                                    porcentArro = porcentArro / cotacao;
                                    int divArro = (int) porcentArro;
                                    agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() - (divArro * cotacao));
                                    agenteArrojado.setAcao(agenteArrojado.getAcao() + divArro);
                                }
                                if (agenteConservador.getDinheiro() > (cotacao * 5))
                                {
                                    double porcentArro = agenteConservador.getDinheiro() * 0.20;
                                    porcentArro = porcentArro / cotacao;
                                    int divArro = (int) porcentArro;
                                    agenteConservador.setDinheiro(agenteConservador.getDinheiro() - (divArro * cotacao));
                                    agenteConservador.setAcao(agenteConservador.getAcao() + divArro);
                                }
                                if (agenteModerado.getDinheiro() > (cotacao * 5))
                                {
                                    double porcentArro = agenteModerado.getDinheiro() * 0.20;
                                    porcentArro = porcentArro / cotacao;
                                    int divArro = (int) porcentArro;
                                    agenteModerado.setDinheiro(agenteModerado.getDinheiro() - (divArro * cotacao));
                                    agenteModerado.setAcao(agenteModerado.getAcao() + divArro);
                                }
                                if (listaM3.get(x) < listaM5.get(x))
                                {
                                    if (agenteArrojado.getAcao() > 5)
                                    {
                                        double percentArro = agenteArrojado.getAcao() * 0.20;
                                        int vendArro = (int) percentArro;
                                        agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() + (vendArro * cotacao));
                                        agenteArrojado.setAcao(agenteArrojado.getAcao() - vendArro);
                                    }
                                    if (agenteConservador.getAcao() > 5)
                                    {
                                        double percentArro = agenteConservador.getAcao() * 0.20;
                                        int vendArro = (int) percentArro;
                                        agenteConservador.setDinheiro(agenteConservador.getDinheiro() + (vendArro * cotacao));
                                        agenteConservador.setAcao(agenteConservador.getAcao() - vendArro);
                                    }
                                    if (agenteModerado.getAcao() > 5)
                                    {
                                        double percentArro = agenteModerado.getAcao() * 0.20;
                                        int vendArro = (int) percentArro;
                                        agenteModerado.setDinheiro(agenteModerado.getDinheiro() + (vendArro * cotacao));
                                        agenteModerado.setAcao(agenteModerado.getAcao() - vendArro);
                                    }
                                }
                                if (listaM3.get(x) > cotacao)
                                {
                                    if (agenteArrojado.getAcao() > 5)
                                    {
                                        double percentArro = agenteArrojado.getAcao() * 0.20;
                                        int vendArro = (int) percentArro;
                                        agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() + (vendArro * cotacao));
                                        agenteArrojado.setAcao(agenteArrojado.getAcao() - vendArro);
                                    }
                                    if (agenteConservador.getAcao() > 5)
                                    {
                                        double percentArro = agenteConservador.getAcao() * 0.20;
                                        int vendArro = (int) percentArro;
                                        agenteConservador.setDinheiro(agenteConservador.getDinheiro() + (vendArro * cotacao));
                                        agenteConservador.setAcao(agenteConservador.getAcao() - vendArro);
                                    }
                                    if (agenteModerado.getAcao() > 5)
                                    {
                                        double percentArro = agenteModerado.getAcao() * 0.20;
                                        int vendArro = (int) percentArro;
                                        agenteModerado.setDinheiro(agenteModerado.getDinheiro() + (vendArro * cotacao));
                                        agenteModerado.setAcao(agenteModerado.getAcao() - vendArro);
                                    }
                                }
                            } else
                            {
                                if (agenteArrojado.getAcao() > 5)
                                {
                                    double percentArro = agenteArrojado.getAcao() * 0.20;
                                    int vendArro = (int) percentArro;
                                    agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() + (vendArro * cotacao));
                                    agenteArrojado.setAcao(agenteArrojado.getAcao() - vendArro);
                                }
                                if (agenteConservador.getAcao() > 5)
                                {
                                    double percentArro = agenteConservador.getAcao() * 0.20;
                                    int vendArro = (int) percentArro;
                                    agenteConservador.setDinheiro(agenteConservador.getDinheiro() + (vendArro * cotacao));
                                    agenteConservador.setAcao(agenteConservador.getAcao() - vendArro);
                                }
                                if (agenteModerado.getAcao() > 5)
                                {
                                    double percentArro = agenteModerado.getAcao() * 0.20;
                                    int vendArro = (int) percentArro;
                                    agenteModerado.setDinheiro(agenteModerado.getDinheiro() + (vendArro * cotacao));
                                    agenteModerado.setAcao(agenteModerado.getAcao() - vendArro);
                                }
                            }
                        } else if (agenteModerado.getResult() > agenteArrojado.getResult() && agenteModerado.getResult() > agenteConservador.getResult())
                        {
                            System.out.println("Conservador é a melhor opção");
                            if (listaM3.get(x) > listaM3.get(x - 1))
                            {
                                if (listaM3.get(x) > listaM5.get(x))
                                {
                                    if (agenteModerado.getDinheiro() > (cotacao * 10))
                                    {
                                        double porcentMod = agenteModerado.getDinheiro() * 0.10;
                                        porcentMod = porcentMod / cotacao;
                                        int divMod = (int) porcentMod;
                                        agenteModerado.setDinheiro(agenteModerado.getDinheiro() - (divMod * cotacao));
                                        agenteModerado.setAcao(agenteModerado.getAcao() + divMod);

                                    }
                                    if (agenteArrojado.getDinheiro() > (cotacao * 10))
                                    {
                                        double porcentMod = agenteArrojado.getDinheiro() * 0.10;
                                        porcentMod = porcentMod / cotacao;
                                        int divMod = (int) porcentMod;
                                        agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() - (divMod * cotacao));
                                        agenteArrojado.setAcao(agenteArrojado.getAcao() + divMod);

                                    }
                                    if (agenteConservador.getDinheiro() > (cotacao * 10))
                                    {
                                        double porcentMod = agenteConservador.getDinheiro() * 0.10;
                                        porcentMod = porcentMod / cotacao;
                                        int divMod = (int) porcentMod;
                                        agenteConservador.setDinheiro(agenteConservador.getDinheiro() - (divMod * cotacao));
                                        agenteConservador.setAcao(agenteConservador.getAcao() + divMod);

                                    }
                                }
                                if (listaM3.get(x) < listaM5.get(x))
                                {
                                    if (agenteModerado.getAcao() > 10)
                                    {
                                        double percentMod = agenteModerado.getAcao() * 0.20;
                                        int vendMod = (int) percentMod;
                                        agenteModerado.setDinheiro(agenteModerado.getDinheiro() + (vendMod * cotacao));
                                        agenteModerado.setAcao(agenteModerado.getAcao() - vendMod);
                                    }
                                    if (agenteArrojado.getAcao() > 10)
                                    {
                                        double percentMod = agenteArrojado.getAcao() * 0.20;
                                        int vendMod = (int) percentMod;
                                        agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() + (vendMod * cotacao));
                                        agenteArrojado.setAcao(agenteArrojado.getAcao() - vendMod);
                                    }
                                    if (agenteConservador.getAcao() > 10)
                                    {
                                        double percentMod = agenteConservador.getAcao() * 0.20;
                                        int vendMod = (int) percentMod;
                                        agenteConservador.setDinheiro(agenteConservador.getDinheiro() + (vendMod * cotacao));
                                        agenteConservador.setAcao(agenteConservador.getAcao() - vendMod);
                                    }
                                }
                            } else
                            {
                                if (agenteModerado.getAcao() > 10)
                                {
                                    double percentMod = agenteModerado.getAcao() * 0.20;
                                    int vendMod = (int) percentMod;
                                    agenteModerado.setDinheiro(agenteModerado.getDinheiro() + (vendMod * cotacao));
                                    agenteModerado.setAcao(agenteModerado.getAcao() - vendMod);
                                }
                                if (agenteArrojado.getAcao() > 10)
                                {
                                    double percentMod = agenteArrojado.getAcao() * 0.20;
                                    int vendMod = (int) percentMod;
                                    agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() + (vendMod * cotacao));
                                    agenteArrojado.setAcao(agenteArrojado.getAcao() - vendMod);
                                }
                                if (agenteConservador.getAcao() > 10)
                                {
                                    double percentMod = agenteConservador.getAcao() * 0.20;
                                    int vendMod = (int) percentMod;
                                    agenteConservador.setDinheiro(agenteConservador.getDinheiro() + (vendMod * cotacao));
                                    agenteConservador.setAcao(agenteConservador.getAcao() - vendMod);
                                }
                            }

                        } else if (agenteConservador.getResult() > agenteArrojado.getResult() && agenteConservador.getResult() > agenteModerado.getResult())
                        {
                            System.out.println("Moderado é a melhor opção");
                            if (listaM3.get(x) > listaM3.get(x - 1))
                            {

                                if (cotacao > listaM3.get(x))
                                {
                                    if (agenteConservador.getDinheiro() > (cotacao * 20))
                                    {
                                        double porcentCons = agenteConservador.getDinheiro() * 0.05;
                                        porcentCons = porcentCons / cotacao;
                                        int divCons = (int) porcentCons;
                                        agenteConservador.setDinheiro(agenteConservador.getDinheiro() - (divCons * cotacao));
                                        agenteConservador.setAcao(agenteConservador.getAcao() + divCons);

                                    }
                                    if (agenteArrojado.getDinheiro() > (cotacao * 20))
                                    {
                                        double porcentCons = agenteArrojado.getDinheiro() * 0.05;
                                        porcentCons = porcentCons / cotacao;
                                        int divCons = (int) porcentCons;
                                        agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() - (divCons * cotacao));
                                        agenteArrojado.setAcao(agenteArrojado.getAcao() + divCons);

                                    }
                                    if (agenteModerado.getDinheiro() > (cotacao * 20))
                                    {
                                        double porcentCons = agenteModerado.getDinheiro() * 0.05;
                                        porcentCons = porcentCons / cotacao;
                                        int divCons = (int) porcentCons;
                                        agenteModerado.setDinheiro(agenteModerado.getDinheiro() - (divCons * cotacao));
                                        agenteModerado.setAcao(agenteModerado.getAcao() + divCons);

                                    }

                                }
                            }
                        } else
                        {
                            if (agenteConservador.getAcao() > 20)
                            {
                                double percentCons = agenteConservador.getAcao() * 0.20;
                                int vendCons = (int) percentCons;
                                agenteConservador.setDinheiro(agenteConservador.getDinheiro() + (vendCons * cotacao));
                                agenteConservador.setAcao(agenteConservador.getAcao() - vendCons);
                            }
                            if (agenteArrojado.getAcao() > 20)
                            {
                                double percentCons = agenteArrojado.getAcao() * 0.20;
                                int vendCons = (int) percentCons;
                                agenteArrojado.setDinheiro(agenteArrojado.getDinheiro() + (vendCons * cotacao));
                                agenteArrojado.setAcao(agenteArrojado.getAcao() - vendCons);
                            }
                            if (agenteModerado.getAcao() > 20)
                            {
                                double percentCons = agenteModerado.getAcao() * 0.20;
                                int vendCons = (int) percentCons;
                                agenteModerado.setDinheiro(agenteModerado.getDinheiro() + (vendCons * cotacao));
                                agenteModerado.setAcao(agenteModerado.getAcao() - vendCons);
                            }
                        }
                    }
                    valorArroja = agenteArrojado.getDinheiro() + (cotacao * agenteArrojado.getAcao());
                    valorModera = agenteModerado.getDinheiro() + (cotacao * agenteModerado.getAcao());
                    valorConser = agenteConservador.getDinheiro() + (cotacao * agenteConservador.getAcao());
                    System.out.println("Arro " + valorArroja);
                    System.out.println("Mode " + valorModera);
                    System.out.println("Conse " + valorConser);
                }
                aSocket.send(reply);

            }

        } catch (SocketException e)
        {
            System.out.println("Socket:" + e.getMessage());
        } catch (IOException e)
        {
            System.out.println("Input Output:" + e.getMessage());
        } finally
        {
            if (aSocket != null)
            {
                aSocket.close();
            }
        }

    }

}
