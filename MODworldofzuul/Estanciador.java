/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpgcorp.zuul;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author renan
 */
public class Estanciador {
    List<Ambiente> listaRNG;
    
    public Estanciador(){
        listaRNG= new ArrayList<>();
    }
    public void add(Ambiente e){
        listaRNG.add(e);
    }
    public void plantarTesouro(){
        Collections.shuffle(listaRNG);
        Ambiente amb= listaRNG.get(0);
        amb.colocarTesouro();
    }
     public void plantarChave(){
        Collections.shuffle(listaRNG);
        Ambiente amb= listaRNG.get(0);
        amb.colocarChave();
    }
}


