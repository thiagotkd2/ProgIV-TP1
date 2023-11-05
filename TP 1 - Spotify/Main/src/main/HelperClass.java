/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Thiago
 */
public class HelperClass {
    
    public static void preencheLista(Musicas ms) throws IOException{
        
        
        
        
        
        
        /*
        Leitura de CSV de javatpoint:
        
        */
        //parsing a CSV file into BufferedReader class constructor
        String line = "";
        
        // regex que filtra vírgula, excluindo aquelas entre aspas 
        String regex = "(,(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$))";  // gerado por perplexity.ai
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/test/spotify-2023.csv"));
        line = br.readLine();
        while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
            String[] dadosMusica = (line.split(regex));    // usando regex
            for (int i = 0; i<dadosMusica.length; i++){
                if (dadosMusica[i].isEmpty()){
                    dadosMusica[i] = "-";}
//                }else{
//                    dadosMusica[i]=dadosMusica[i].replace("\"",""); // retira aspas internas
//                }
            }
            
            /*
            Adaptado de:
            https://www.javatpoint.com/how-to-read-csv-file-in-java
            */
            
            Musica m = new Musica();
            // track_name,artist(s)_name,artist_count,released_year,released_month,released_day,in_spotify_playlists,in_spotify_charts,streams,in_apple_playlists,in_apple_charts,
            // in_deezer_playlists,in_deezer_charts,in_shazam_charts,bpm,key,mode,danceability_%,valence_%,energy_%,acousticness_%,instrumentalness_%,liveness_%,speechiness_%
            
            
            // alguns campos vazios impedem a conversão para double
            // guardar todos em strings, por ora
            m.setTrackName(dadosMusica[0]);
            m.setArtistsName(dadosMusica[1]);
            m.setArtistCount(dadosMusica[2]);
            m.setReleasedYear((dadosMusica[3]));
            m.setReleasedMonth((dadosMusica[4]));
            m.setReleasedDay((dadosMusica[5]));
            m.setInSpotifyPlaylist((dadosMusica[6].replace(",", ""))); // retirar ,' de números da ordem de milhares
            m.setInSpotifyCharts((dadosMusica[7].replace(",", ""))); 
            m.setStreams((dadosMusica[8].replace(",", "")));        
            m.setInApplePlaylists((dadosMusica[9].replace(",", "")));
            m.setInAppleCharts((dadosMusica[10].replace(",", "")));
            m.setInDeezerPlaylists((dadosMusica[11].replace(",", "")));
            m.setInDeezerCharts((dadosMusica[12].replace(",", "")));
            m.setInShazamCharts((dadosMusica[13].replace(",", "")));
            m.setBpm((dadosMusica[14]));
            m.setKey(dadosMusica[15]);
            m.setMode(dadosMusica[16]);
            m.setDanceability((dadosMusica[17]));
            m.setValence((dadosMusica[18]));
            m.setEnergy((dadosMusica[19]));
            m.setAcousticness((dadosMusica[20]));
            m.setInstrumentalness((dadosMusica[21]));
            m.setLiveness((dadosMusica[22]));
            m.setSpeechiness((dadosMusica[23]));
            
            ms.inserir(m);
            ms.incrementaContadorTamanhoLista();
            
            
        }
        
        
        
        
    }
    
    /*
    SelectionSort: 1
    InsertionSort: 2
    
    
    */
    
    
    
    public static void escreveEstatisticaOrdenacao(double tempo, int operacoes, int metodo, String nomeMetodo){
        
    try{
        File f = new File("test/estatisticas.txt");
        PrintWriter writer =null;
        
        if(f.exists() && !f.isDirectory()){
            writer = new PrintWriter(new FileOutputStream(new File("test/estatisticas.txt"), true));
        }else{
            writer = new PrintWriter("test/estatisticas.txt");
        }
        
        
        writer.println("-----------------------------------------------------");
        writer.println(nomeMetodo);
        writer.println("Tempo de execução: " + tempo + " ms");
        writer.println("Operações: " + operacoes);
        if(metodo==1 || metodo==2 || metodo==3 || metodo ==4){writer.println("O(n^2)");}
        if(metodo==5){writer.println("Conjectura ao número de comparação para a sequência de  Knuth\n1-C(n) = O(n^1,25)\n2-C(n) = O(n(ln n)^2)");}
        if(metodo==6 || metodo==7){writer.println("O(n log n)");}
        
        writer.close();
    }catch(FileNotFoundException e){
        System.out.println("Erro ao criar arquivo.");
    }
        
        
    }
    
    
}
