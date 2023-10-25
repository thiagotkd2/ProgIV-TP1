/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.IOException;

/**
 *
 * @author thiago
 */
public class Musicas extends ListaEncadeada<Musica>{
    
    private ListaEncadeada<Musica> listaMusica;
    private int contadorInterno = 0;
    
    public Musicas() throws IOException{
        listaMusica = new ListaEncadeada();
        listaMusica = HelperClass.preencheLista();
    }
    
    public void imprimeLista(){
        
        for(Musica m:listaMusica){
            System.out.println(m);
        }
    }
    
    
    
    
    /*
    O algoritmo guarda o primeiro elemento de uma subcolecao imaginaria
    apos isso, busca pelo menor valor dentro dessa subcolecao
    se o menor valor não for igual ao primeiro elemento, estes nodos são trocados
    e o primeiro elemento passa para a segunda posicao da colecao original, até n-1
    isso é feito até o proximo elemento do primeiro elemento for nulo
    portanto, é uma ordenação por seleção
    
    
    
    */
    
    private void incrementaContador(){
        contadorInterno++;
    }

    public int getContadorInterno() {
        return contadorInterno;
    }
    
    
    
    public void selectionSort(int opcao){
        
        
        
        // o minimo considerado é a palavra que vem antes alfabeticamente
        Nodo<Musica> min = listaMusica.getCabeca();
        Nodo<Musica> antMin = null; // anterior do minimo
        
        Nodo<Musica> atualAnt = min; // anterior do atual 
        Nodo<Musica> atual = min.prox; // atual serve como iterador
        
        Nodo<Musica> antPrimeiro = min; // anterior do primeiro elemento da subcolecao
        Nodo<Musica> primeiroElemento = min; // primeiro elemento de uma subcolecao imaginaria
        
        Nodo<Musica> temp = min; // variavel temporaria
        
        String[] campoOrdenado = new String[2]; // determina a ordenacao por artista ou por musica
        
        
        while(primeiroElemento.prox!=null){
            do{ // procura o nodo minimo
                
                // atualiza string a ser comparada
                campoOrdenado[0] = opcao==1 ? atual.dado.getTrackName():atual.dado.getArtistsName();
                campoOrdenado[1] = opcao==1 ? min.dado.getTrackName():min.dado.getArtistsName();
                
                this.incrementaContador();
                
                if(comparaPalavras(campoOrdenado[0], campoOrdenado[1])){
                    
                    min = atual;
                    antMin = atualAnt;
                }
                
                atualAnt = atual;
                atual = atual.prox;
                
                
            }while(atual!=null);
            
            temp = primeiroElemento.prox;
            
            if (!primeiroElemento.equals(min)){ // se o primeiro elemento não for igual ao nodo-minimo
                
                if(primeiroElemento.equals(listaMusica.getCabeca())){ // trocar cabeca
                    trocarCabeca(min, antMin);
                }else if(primeiroElemento.equals(listaMusica.getUltimo())){ // trocar ultimo
                    trocarUltimo(min); // ERRO
                }else{ // trocar nodo qualquer
                    trocarNodo(primeiroElemento, min, antPrimeiro, antMin); // entao trocam as posicoes
                }
            }
            
            // atualiza os valores, para a proxima iteracao
            antPrimeiro = min;
            primeiroElemento = temp;
            antMin = min;
            min = temp;
            atualAnt = temp;
            atual = temp.prox;
        }
        
        
        
    }
    
    private void trocarCabeca(Nodo segundoNodo, Nodo segundoNodoAnt) throws NullPointerException{ // anteriores não podem ser nulos
    
        Nodo temp;
        Nodo tempProx;
        
        if (segundoNodoAnt == listaMusica.getCabeca()){
            temp = listaMusica.getCabeca();
            temp.ant=segundoNodo;
            temp.prox = segundoNodo.prox;
            
            segundoNodo.prox = temp;
            segundoNodo.ant = null;
            
            listaMusica.setCabeca(segundoNodo);
            
        }else{
            temp = listaMusica.getCabeca();
            tempProx = temp.prox;
            
            temp.prox = segundoNodo.prox;
            temp.ant = segundoNodo.ant;
            
            segundoNodo.prox = tempProx;
            segundoNodo.ant = null;
            
            segundoNodoAnt.prox = temp;
            
            listaMusica.setCabeca(segundoNodo);
            
        }
        
    }
    private void trocarUltimo(Nodo nodo) throws NullPointerException{ // troca nodo com Ultimo
    
        Nodo temp= listaMusica.getUltimo();
        
        if(nodo==temp){return;}
        
        Nodo antTemp = temp.ant;
        
        if(nodo.prox.equals(temp)){
            nodo.prox = null;
            temp.ant = nodo.ant;
            temp.ant.prox = temp;
            temp.prox = nodo;
            
            nodo.ant = temp;
            
            listaMusica.setUltimo(nodo);
            
        }else if(!(nodo.prox.equals(temp))){
            temp.prox = nodo.prox;
            temp.ant = nodo.ant;
            temp.ant.prox = temp;
            temp.prox.ant = temp;
            
            antTemp.prox = nodo;
            nodo.prox = null;
            nodo.ant = antTemp;
            
            listaMusica.setUltimo(nodo);
            
        }
        
        
        
        
        
     }
        
    
    private void trocarNodo(Nodo primeiroNodo, Nodo segundoNodo, Nodo primeiroNodoAnt, Nodo segundoNodoAnt) throws NullPointerException{ // anteriores não podem ser nulos
        
        
        
        Nodo temp;
        // caso os nodos estejam em sequencia
        if(primeiroNodo.equals(segundoNodoAnt)){
            // trocar prox
            
            primeiroNodo.prox = segundoNodo.prox;
            if (segundoNodo.prox!=null)
                segundoNodo.prox.ant = primeiroNodo;
            
            segundoNodo.ant = primeiroNodo.ant;
            primeiroNodo.ant = segundoNodo;
            
            segundoNodo.prox = primeiroNodo;
            
            
            
            

            // trocar anterior
            primeiroNodoAnt.prox = segundoNodo;
            
            
            
            
        }else if(segundoNodo.equals(primeiroNodoAnt)){
            // trocar prox
            segundoNodo.prox=primeiroNodo.prox;
            if (segundoNodo.prox!=null)
                primeiroNodo.prox.ant = segundoNodo;
            primeiroNodo.ant = segundoNodo.ant;
            primeiroNodo.prox = segundoNodo;

            
            

            // trocar anterior
            segundoNodoAnt.prox = primeiroNodo;
            
            
            
        }else if(!(segundoNodo.equals(primeiroNodoAnt)|| primeiroNodo.equals(segundoNodoAnt))){
            // trocar prox
            temp = primeiroNodo.prox;
            primeiroNodo.prox = segundoNodo.prox;
            if (segundoNodo.prox!=null)
                segundoNodo.prox.ant = primeiroNodo;
            segundoNodo.prox = temp;
            temp.ant = segundoNodo;
            
            
            

            
            // trocar anterior
            if(primeiroNodoAnt!=null){
                primeiroNodoAnt.prox =  segundoNodo;
                segundoNodo.ant = primeiroNodoAnt;
            }
            if(segundoNodoAnt!=null){
                segundoNodoAnt.prox = primeiroNodo;
                primeiroNodo.ant = segundoNodoAnt;
            }
            
        }
        
        
    }
    
    
    
    public void insertionSort(int opcao){
        
        
        
        // o minimo considerado é a palavra que vem antes alfabeticamente
        Nodo<Musica> primeiroNodo = listaMusica.getCabeca();
        Nodo<Musica> segundoNodo = primeiroNodo.prox; // anterior do minimo
        
        Nodo<Musica> primeiroNodoAnt = null; // anterior do minimo
        Nodo<Musica> iterador = primeiroNodo; // anterior do minimo
        Nodo<Musica> iteradorAnt = null; // anterior do minimo
        Nodo<Musica> min = primeiroNodo; // anterior do minimo
        
        
        
        String[] campoOrdenado = new String[2]; // determina a ordenacao por artista ou por musica
        
        
        
        while(iterador.prox!=null){ // procura o nodo minimo

            
            // atualiza string a ser comparada
            campoOrdenado[0] = opcao==1 ? primeiroNodo.dado.getTrackName():primeiroNodo.dado.getArtistsName();
            campoOrdenado[1] = opcao==1 ? segundoNodo.dado.getTrackName():segundoNodo.dado.getArtistsName();
            boolean nodoNulo = primeiroNodo==null ? true:false;
            primeiroNodoAnt = listaMusica.buscarAnterior(primeiroNodo.dado);
            
            
            if(comparaPalavras(campoOrdenado[1], campoOrdenado[0]) && !nodoNulo){
                iterador = primeiroNodo;
                iteradorAnt = primeiroNodoAnt;
                
            }else{
                iteradorAnt = iterador;
                iterador = iterador.prox;
                this.incrementaContador();
            }
            
            while(comparaPalavras(campoOrdenado[1], campoOrdenado[0]) && !nodoNulo){
                this.incrementaContador();
                if(primeiroNodo.equals(listaMusica.getCabeca())){
                    trocarCabeca(segundoNodo, primeiroNodo);
                }else{
                    trocarNodo(primeiroNodo, segundoNodo, primeiroNodoAnt, primeiroNodo);
                }
                primeiroNodo = primeiroNodoAnt;
                
                nodoNulo = primeiroNodo==null ? true:false;
                if(!nodoNulo){
                    primeiroNodoAnt = listaMusica.buscarAnterior(primeiroNodo.dado);
                    campoOrdenado[1] = opcao==1 ? segundoNodo.dado.getTrackName():segundoNodo.dado.getArtistsName();
                    campoOrdenado[0] = opcao==1 ? primeiroNodo.dado.getTrackName():primeiroNodo.dado.getArtistsName();
                }
            }

            primeiroNodo = iterador;
            primeiroNodoAnt = iteradorAnt;
            
            segundoNodo = iterador.prox;
            
            


        }
            
            
        
        
        
        
    }
    
    
    public void bubbleSort(int opcao){
        
        

        
        Nodo<Musica> atual = listaMusica.getCabeca();
        Nodo<Musica> nodoFim = null;
        
        
        String[] campoOrdenado = new String[2]; // determina a ordenacao por artista ou por musica
        
        
        
        while(nodoFim!=listaMusica.getCabeca()){ // procura o nodo minimo

            
            
            
            
            

            
            while(atual.prox!=nodoFim){
                this.incrementaContador();
                // define campo a ser comparado
                campoOrdenado[0] = opcao==1 ? atual.dado.getTrackName():atual.dado.getArtistsName();
                campoOrdenado[1] = opcao==1 ? atual.prox.dado.getTrackName():atual.prox.dado.getArtistsName();
                if(comparaPalavras(campoOrdenado[1], campoOrdenado[0])){
                    if(atual == listaMusica.getCabeca()){
                        trocarCabeca(atual.prox, atual);
                    }else{
                        trocarNodo(atual, atual.prox, atual.ant, atual);
                    }
                    
                }else{
                    atual = atual.prox; // caso nao haja troca entre atual e atual prox, atual deve ser "passado para frente"
                }
                
            }

            nodoFim = atual;
            atual = listaMusica.getCabeca();

        }  
    }
    
    
    public void quickSort(int opcao){
        
        

        
        Nodo<Musica> atual = listaMusica.getCabeca();
        Nodo<Musica> nodoFim = null;
        
        
        String[] campoOrdenado = new String[2]; // determina a ordenacao por artista ou por musica
        
        // define campo a ser comparado
        campoOrdenado[0] = opcao==1 ? atual.dado.getTrackName():atual.dado.getArtistsName();
        campoOrdenado[1] = opcao==1 ? atual.prox.dado.getTrackName():atual.prox.dado.getArtistsName();
        
        
        
        while(nodoFim!=listaMusica.getCabeca()){ // procura o nodo minimo
            while(atual.prox!=nodoFim){
               
                
            }

        }  
    }
    
    
   
    
    // verifica se a primeira palavra vem primeiro alfabeticamente, em relação ao comparador
    private boolean comparaPalavras(String palavra, String comparador){ 
        
        
        //todo:tolower e tirar acento/caracteres especiais
        
        // converter para lowercase, retirar caracteres especiais
        String palavraFiltrada = palavra.toLowerCase();
         // incrementa o numero de operacoes feitas 
        String comparadorFiltrado = comparador.toLowerCase();
        
        
        char[] palavraChar = palavraFiltrada.toCharArray();
        
        char[] comparadorChar = comparadorFiltrado.toCharArray();
        
        String menorPalavra = palavra.length() >= comparador.length() ? comparadorFiltrado:palavraFiltrada;
        
        
        // determina tamanho do loop de comparação
        int tamanhoLoop = palavra.length() >= comparador.length() ? comparador.length():palavra.length();
        
        
        // verifica se a palavra precede alfabeticamente o comparador
        boolean palavraPrecede = false; // por default false
        
        boolean validacaoDefault = false; // verifica se o boolean acima é valido
        
        /*
        Alternativa:
        Boolean palavraPrecede = null;
        // Usar (B)oolean (tipo por referência, permite usar null, false e true)
        https://docs.oracle.com/javase%2F7%2Fdocs%2Fapi%2F%2F/java/lang/Boolean.html
        */
        
        // compara o valor ASCII de cada caractere, das duas palavras
        for(int i = 0; i<tamanhoLoop;i++){
            if(palavraChar[i]!=comparadorChar[i]){
                palavraPrecede = palavraChar[i]<comparadorChar[i];
                
                validacaoDefault = true; // garante que o boolean acima nao tenha seu valor default
                break;
            }
        }
        
        // caso palavraPrecede nao esteja em seu valor default, é retornada
        // senao, retorna a menor palavra
        return validacaoDefault ? palavraPrecede:(menorPalavra.equals(palavraFiltrada));
    }    
    
}
