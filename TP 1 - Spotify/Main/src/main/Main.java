/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.io.IOException;
import java.util.Scanner;

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
        
        Scanner in = new Scanner(System.in);
        int opcao = 0;
        while(opcao!=1 && opcao!=2){
            System.out.println("Deseja ordenar por nome da música[digite 1] ou por nome de artista[digite 2]");
            opcao =  Integer.parseInt(in.nextLine());
        }
        Musicas ms = new Musicas();
        
        
        
        int opcaoOrdenacao=0;
        while(opcaoOrdenacao!=1 && opcaoOrdenacao!=2 && opcaoOrdenacao!=3){
            
            System.out.println("Deseja ordenar por selection sort[digite 1], por insertion sort[digite 2], por bubble sort[digite 3]");
            opcaoOrdenacao =  Integer.parseInt(in.nextLine());
        }

        if(opcaoOrdenacao==1){
            long startTime = System.nanoTime();
            ms.selectionSort(opcao);
            long endTime = System.nanoTime();

            double duration = (endTime - startTime)  /(double)1000000;  //divide by 1000000 to get milliseconds.
            
            // fonte: https://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
            
            ms.imprimeLista();
           

            System.out.println(duration + " " + ms.getContadorInterno());
            HelperClass.escreveEstatisticaOrdenacao(duration, ms.getContadorInterno(), opcaoOrdenacao, "SelectionSort");
            
        }else if(opcaoOrdenacao==2){
            long startTime = System.nanoTime();
            ms.insertionSort(opcao);
            long endTime = System.nanoTime();

            double duration = (endTime - startTime)  /(double)1000000;  //divide by 1000000 to get milliseconds.
            
            // fonte: https://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
            
            ms.imprimeLista();

            System.out.println(duration + " " + ms.getContadorInterno());
            HelperClass.escreveEstatisticaOrdenacao(duration, ms.getContadorInterno(), opcaoOrdenacao, "insertionSort");
        }else if(opcaoOrdenacao ==3){
            long startTime = System.nanoTime();
            ms.bubbleSort(opcao);
            long endTime = System.nanoTime();

            double duration = (endTime - startTime)  /(double)1000000;  //divide by 1000000 to get milliseconds.
            
            // fonte: https://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
            
            ms.imprimeLista();

            System.out.println(duration + " " + ms.getContadorInterno());
            HelperClass.escreveEstatisticaOrdenacao(duration, ms.getContadorInterno(), opcaoOrdenacao, "bubbleSort");
        }
        
        
        
    }
    
}
