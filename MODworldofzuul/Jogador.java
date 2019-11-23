/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgcorp.zuul;

import java.util.Random;

/**
 *
 * @author renan
 */
public class Jogador {
    private int ntentativas;
    private int chaveMestra;
    private boolean bomba;
    private Random gerador; // criação do rngesus
   
    public Jogador(){
    gerador = new Random();
    this.ntentativas = gerador.nextInt(30)+20;
    this.chaveMestra = 0;
    bomba=true;
    }
    public boolean checarChave(){
        if(chaveMestra > 0){
            return true;
        } 
    return false;
    }
    public void achouChave(){
        chaveMestra = gerador.nextInt(12)+1;
    }
    public void abrirPorta(){
        ntentativas--;
    }
    public void explodirBomba(){
        bomba=false;
    }
    public boolean checarBomba(){
        return bomba;
    }
    public int get_ntentativas(){
        return ntentativas;
    }
    public int get_chaveMestra(){
        return chaveMestra;
    }
}
