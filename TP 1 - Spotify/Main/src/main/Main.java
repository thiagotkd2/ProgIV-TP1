/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.io.IOException;

/**
 *
 * @author Thiago
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        HelperClass.preencheLista();
        
        ListaEncadeada<Musica> listaMusica = new ListaEncadeada();
        listaMusica = HelperClass.preencheLista();
        
        for(Musica m:listaMusica){
            System.out.println(m.toString());
        }
        
        
        
    }
    
}
