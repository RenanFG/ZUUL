/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgcorp.zuul;

/**
 *
 * @author renan
 */
/**
 * Classe Ambiente - um ambiente em um jogo adventure.
 *
 * Esta classe eh parte da aplicacao "World of Zuul".
 * "World of Zuul" eh um jogo de aventura muito simples, baseado em texto.  
 *
 * Um "Ambiente" representa uma localizacao no cenario do jogo. Ele eh
 * conectado aos outros ambientes atraves de saidas. As saidas sao
 * nomeadas como norte, sul, leste e oeste. Para cada direcao, o ambiente
 * guarda uma referencia para o ambiente vizinho, ou null se nao ha
 * saida naquela direcao.
 * 
 * @author  Michael Kölling and David J. Barnes (traduzido por Julio Cesar Alves)
 * @version 2011.07.31 (2016.02.01)
 */
import java.util.HashMap;
import java.util.Map;

public class Ambiente 
{
    private String descricao;
    private  Map<String,Ambiente> mapjogo;
    private boolean tesouro;
    private boolean chave;
    /**
     * Cria um ambiente com a "descricao" passada. Inicialmente, ele
     * nao tem saidas. "descricao" eh algo como "uma cozinha" ou
     * "
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "um jardim aberto".
     * @param descricao A descricao do ambiente.
     */
    public Ambiente(String descricao) 
    {
        mapjogo = new HashMap<String,Ambiente>();
        this.descricao = descricao;
        tesouro=false;
        chave = false;
    }

    /**
     * Define as saidas do ambiente. Cada direcao ou leva a um
     * outro ambiente ou eh null (nenhuma saida para la).
     */
    public void ajustarSaidas(String d, Ambiente a) 
    {
        mapjogo.put(d, a);
    }

    public Ambiente getAmbiente(String dir){
        return mapjogo.get(dir);
    }
    /**
     * @return A descricao do ambiente.
     */
    public String getDescricao()
    {
        return descricao;
    }
    
    public void colocarTesouro(){
        tesouro=true;
    }

    public boolean checarTesouro(){
        return tesouro;
    }
    
    public boolean checarChave(){
        if (chave){
            chave=false;
            return true;
        }
        return false;
    }
    public void colocarChave(){
        chave=true;
    }
    
    public String getSaidas() {
        String ret = "";
        for (String direcao: mapjogo.keySet()) {
            ret = ret + direcao + " ";
        }
        return ret;
    }

}
