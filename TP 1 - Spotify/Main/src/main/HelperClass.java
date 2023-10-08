/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Thiago
 */
public class HelperClass {
    
    public static ListaEncadeada<Musica> preencheLista() throws IOException{
        
        ListaEncadeada<Musica> listaMusica = new ListaEncadeada();
        
        /*
        Leitura de CSV de javatpoint:
        
        */
        //parsing a CSV file into BufferedReader class constructor
        String line = "";
        
        // regex que filtra vírgula, excluindo aquelas entre aspas 
        String regex = "(,(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$))";  // gerado por perplexity.ai
        BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/../spotify-2023.csv"));
        line = br.readLine();
        while ((line = br.readLine()) != null)   //returns a Boolean value  
        {  
            String[] dadosMusica = (line.split(regex));    // usando regex
            for (int i = 0; i<dadosMusica.length; i++){
                if (dadosMusica[i].isEmpty()){
                    dadosMusica[i] = "-";
                }else{
                    dadosMusica[i]=dadosMusica[i].replace("\"","");
                }
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
            m.setInSpotifyPlaylist((dadosMusica[6].replace(",", ""))); // retirar , de números da ordem de milhares
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
            
            listaMusica.inserir(m);
            
            
        }
        
        
        
        return listaMusica;
    }
    
    
    
    
}
