/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 *
 * @author thiago
 */
public class Musicas extends ListaEncadeada<Musica>{
    
    
    private int contadorInterno = 0;
    private int tamanhoLista =0;
    private Nodo<Musica>[] referenciaNodo;
    
    public Musicas() throws IOException{
        
        HelperClass.preencheLista(this);
        referenciaNodo = new Nodo[tamanhoLista];
        preencheReferencias();
    }
    
    
    private void preencheReferencias(){
        int i = 0;
        Nodo cabeca = this.getCabeca();
        while(cabeca!=null){
            referenciaNodo[i]=cabeca;
            cabeca = cabeca.prox;
            i++;
        }
        
        
    }
    
    
    public void imprimeLista(){
        
        for(Musica m:this){
            System.out.println(m);
        }
        System.out.println("Tamanho da lista: " + tamanhoLista);
        
        
    }
    
    public void imprimeRefArr(){
        for(Nodo<Musica> r:referenciaNodo){
            System.out.println(r.dado.toString());
        }
        System.out.println("Tamanho da lista: " + tamanhoLista);
    }
    
    
    public void incrementaContadorTamanhoLista(){
        tamanhoLista++;
    }
    
    
    
    private void incrementaContador(){
        contadorInterno++;
    }

    public int getContadorInterno() {
        return contadorInterno;
    }
    
    
    
    
    
    
    /*
    
    
    ALGORITMOS DE ORDENACAO
    
    */
    
    
    
    /*INICIO SELECTIONSORT*/
    
    
    
    /*
    O algoritmo guarda o primeiro elemento de uma subcolecao imaginaria
    apos isso, busca pelo menor valor dentro dessa subcolecao
    se o menor valor não for igual ao primeiro elemento, estes nodos são trocados
    e o primeiro elemento passa para a segunda posicao da colecao original, até n-1
    isso é feito até o proximo elemento do primeiro elemento for nulo
    portanto, é uma ordenação por seleção
    
    
    
    */
    
    
    
    
    
    public void selectionSort(int opcao){
        
        
        
        // o minimo considerado é a palavra que vem antes alfabeticamente
        Nodo<Musica> min = this.getCabeca();
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
                
                if(primeiroElemento.equals(this.getCabeca())){ // trocar cabeca
                    trocarCabeca(min, antMin);
                }/*else if(primeiroElemento.equals(this.getUltimo())){ // trocar ultimo
                    trocarUltimo(min); // ERRO
                }*/else{ // trocar nodo qualquer
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
    
    /*FIM SELECTIONSORT*/
    
    
    
    
    
    
    /*INICIO INSERTIONSORT*/
    
    public void insertionSort(int opcao){
        
        
        
        // o minimo considerado é a palavra que vem antes alfabeticamente
        Nodo<Musica> primeiroNodo = this.getCabeca();
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
            primeiroNodoAnt = this.buscarAnterior(primeiroNodo.dado);
            
            
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
                if(primeiroNodo.equals(this.getCabeca())){
                    trocarCabeca(segundoNodo, primeiroNodo);
                }else{
                    trocarNodo(primeiroNodo, segundoNodo, primeiroNodoAnt, primeiroNodo);
                }
                primeiroNodo = primeiroNodoAnt;
                
                nodoNulo = primeiroNodo==null ? true:false;
                if(!nodoNulo){
                    primeiroNodoAnt = this.buscarAnterior(primeiroNodo.dado);
                    campoOrdenado[1] = opcao==1 ? segundoNodo.dado.getTrackName():segundoNodo.dado.getArtistsName();
                    campoOrdenado[0] = opcao==1 ? primeiroNodo.dado.getTrackName():primeiroNodo.dado.getArtistsName();
                }
            }

            primeiroNodo = iterador;
            primeiroNodoAnt = iteradorAnt;
            
            segundoNodo = iterador.prox;
            
            


        }
            
            
        
        
        
        
    }
    
    
    /*FIM INSERTIONSORT*/
    
    
    
    
    
    
    /*INICIO BUBBLESORT*/
    
    public void bubbleSort(int opcao){
        
        

        
        Nodo<Musica> atual = this.getCabeca();
        Nodo<Musica> nodoFim = null;
        
        
        String[] campoOrdenado = new String[2]; // determina a ordenacao por artista ou por musica
        
        
        
        while(nodoFim!=this.getCabeca()){ // procura o nodo minimo
            while(atual.prox!=nodoFim){
                this.incrementaContador();
                // define campo a ser comparado
                campoOrdenado[0] = opcao==1 ? atual.dado.getTrackName():atual.dado.getArtistsName();
                campoOrdenado[1] = opcao==1 ? atual.prox.dado.getTrackName():atual.prox.dado.getArtistsName();
                if(comparaPalavras(campoOrdenado[1], campoOrdenado[0])){
                    if(atual == this.getCabeca()){
                        trocarCabeca(atual.prox, atual);
                    }else{
                        trocarNodo(atual, atual.prox, atual.ant, atual);
                    }
                    
                }else{
                    atual = atual.prox; // caso nao haja troca entre atual e atual prox, atual deve ser "passado para frente"
                }
                
            }

            nodoFim = atual;
            atual = this.getCabeca();

        }  
    }
    
    
    /*FIM BUBBLESORT*/
    
    
    
    
    
    
    
    
    
    /*INICIO QUICKSORT*/
    
    public void quickSort(int opcao) throws NoSuchFieldException{
        
        
        
        try{
            quickSort(0, referenciaNodo.length-1,opcao);
        }catch(IllegalAccessException e){
            System.out.println(e);
        }
        
    }
    
    
    private void quickSort(int esq, int dir, int opcao) throws IllegalAccessException, NoSuchFieldException{
        
        Nodo pivot = referenciaNodo[(dir+esq)/2];
        int i = esq;
        int j = dir;
        Nodo temp;
        String[] campoOrdenado = new String[3];
        
        
        
        
        
        campoOrdenado[0] = escolherCategoriaOrdenar(referenciaNodo[i].dado, opcao);
        campoOrdenado[2] = escolherCategoriaOrdenar(referenciaNodo[j].dado, opcao);
        campoOrdenado[1] = escolherCategoriaOrdenar((Musica)pivot.dado, opcao);
        

        this.incrementaContador();

        // particao
        while(j>=i){
            while(comparaPalavras(campoOrdenado[0], campoOrdenado[1]) && !(campoOrdenado[0].equals(campoOrdenado[1]))){
                i++;
                if(opcao==1 && i<referenciaNodo.length){
                    campoOrdenado[0] = escolherCategoriaOrdenar(referenciaNodo[i].dado, opcao);
                }else if(i<referenciaNodo.length && opcao==2){
                    campoOrdenado[0] = escolherCategoriaOrdenar(referenciaNodo[i].dado, opcao);
                }
                this.incrementaContador();
            }
            while(comparaPalavras(campoOrdenado[1], campoOrdenado[2]) && !(campoOrdenado[2].equals(campoOrdenado[1]))){
                j--;
                if(opcao==1 && j>-1){
                    campoOrdenado[2] = escolherCategoriaOrdenar(referenciaNodo[j].dado,opcao);
                }else if(j>-1 && opcao==2){
                    campoOrdenado[2] = escolherCategoriaOrdenar(referenciaNodo[j].dado,opcao);
                }
                this.incrementaContador();
                
            }
            if(j>=i){
                temp = referenciaNodo[j];
                referenciaNodo[j] = referenciaNodo[i];
                referenciaNodo[i]=temp;
                
                i++;
                j--;
                
                if(opcao==1 && i<referenciaNodo.length){
                    campoOrdenado[0] = escolherCategoriaOrdenar(referenciaNodo[i].dado,opcao);
                }else if(i<referenciaNodo.length && opcao==2){
                    campoOrdenado[0] = escolherCategoriaOrdenar(referenciaNodo[i].dado,opcao);
                }
                if(opcao==1 && j>-1){
                    campoOrdenado[2] = escolherCategoriaOrdenar(referenciaNodo[j].dado,opcao);
                }else if(j>-1 && opcao==2){
                    campoOrdenado[2] = escolherCategoriaOrdenar(referenciaNodo[j].dado,opcao);
                }
                
            }
        }


        //recursao
        if(esq<j){
            quickSort(esq,j,opcao);
        }
        if(dir>i){
            quickSort(i,dir,opcao);
        }
        
        
    }
    
    /*FIM QUICKSORT*/
    
    
    
    
    
    
    /*INICIO SHELLSORT*/
    
    
    
    public void shellSort(int opcao) throws NoSuchFieldException{
        try{
            shellSort(opcao, 1);
        }catch(IllegalAccessException e){
            System.out.println(e);
        }
    }
    
   private void shellSort(int opcao, int h) throws IllegalAccessException, NoSuchFieldException{
       
        
        while(h<referenciaNodo.length){
            h= (3*h) +1;
            this.incrementaContador();
        }
        
        
        
        String campoOrdenado;
        
        
        
        
        while(h>0){
            h = (h)/3;
            for(int i = h;i< referenciaNodo.length; i++){
                
                int j = i;
                String chave;
                Nodo chaveNodo;
                chaveNodo = referenciaNodo[i];
                
                if(opcao==1){
                    chave = escolherCategoriaOrdenar(referenciaNodo[i].dado, opcao);
                    campoOrdenado = escolherCategoriaOrdenar(referenciaNodo[j-h].dado, opcao);
                }else{
                    chave = (String) escolherCategoriaOrdenar(referenciaNodo[i].dado, opcao);
                    
                    campoOrdenado = escolherCategoriaOrdenar(referenciaNodo[j-h].dado, opcao);
                }
                
                
                while(comparaPalavras(chave, campoOrdenado) && !(chave.equals(campoOrdenado))){
                    referenciaNodo[j] = referenciaNodo[j-h];
                    this.incrementaContador();
                    j-=h;
                    if(j<h)break;
                    if(opcao==1){
                        
                        campoOrdenado = escolherCategoriaOrdenar(referenciaNodo[j-h].dado, opcao);
                    }else{
                        
                        campoOrdenado = escolherCategoriaOrdenar(referenciaNodo[j-h].dado, opcao);
                    }
                    
                }
                referenciaNodo[j] = chaveNodo;
                
            }
            this.incrementaContador();
        }
        
   }
   
   /*FIM SHELLSORT*/
   
   
   
   
   
   
   
   
   /*INICIO MERGESORT*/
   public void mergeSort(int opcao) throws NoSuchFieldException{
       
       try{
            mergeSort(opcao, 0, referenciaNodo.length, referenciaNodo);
        }catch(IllegalAccessException e){
            System.out.println(e);
        }
       
   }
   
   private void mergeSort(int opcao, int p, int r, Nodo[] n) throws IllegalAccessException, NoSuchFieldException{
       this.incrementaContador();
       if(p<r-1){
            int q = (p+r)/2;
            mergeSort(opcao,p,q,n);
            mergeSort(opcao,q,r,n);
            intercalar(opcao,p,q,r,n);
        }
       
   }
   
   
   // ordenar de v1[p,...,q-1] e v2[q,...,r-1]
   // https://www.ime.usp.br/~pf/algoritmos/aulas/mrgsrt.html
  
   private void intercalar(int opcao, int p, int q, int r, Nodo[] n) throws IllegalAccessException, NoSuchFieldException{
        String[] campoOrdenado = new String[2];
        
        
        Nodo[] vetorNodoAux = new Nodo[r-p];
        int i = p, j =q, k =0;

        while(i<q && j<r){
            if (opcao==1){
                campoOrdenado[0] = escolherCategoriaOrdenar((Musica)n[i].dado, opcao);
                campoOrdenado[1] = escolherCategoriaOrdenar((Musica)n[j].dado, opcao);
            }else{
                campoOrdenado[0] = escolherCategoriaOrdenar((Musica)n[i].dado, opcao);
                campoOrdenado[1] = escolherCategoriaOrdenar((Musica)n[j].dado, opcao);
            }
            if(comparaPalavras(campoOrdenado[0], campoOrdenado[1])){
                vetorNodoAux[k++] = n[i++];
            }else{
                vetorNodoAux[k++] = n[j++];
            }
            this.incrementaContador();
        }
        while(i<q){
            vetorNodoAux[k++] = n[i++];
            this.incrementaContador();
        }
        while(j<r){
            vetorNodoAux[k++] =n[j++];
            this.incrementaContador();
        }

        for(i = p; i<r; ++i){
            n[i] = vetorNodoAux[i-p];
            this.incrementaContador();
        }
       
       
   }
   
   /*FIM MERGESORT*/
   
   
   
   
   
   
   
   
   
   /*INICIO HEAPSORT*/
   
   private void constroiHeap (int m, Nodo N[], int opcao) throws IllegalAccessException, NoSuchFieldException{
       String campoOrdenado[]= new String[2];
       Nodo temp;
       this.incrementaContador();
        for (int k = 1; k < m-1; ++k) {   
            this.incrementaContador();
            int f = k+1;
            campoOrdenado[0] = escolherCategoriaOrdenar((Musica)N[f/2].dado, opcao);
            campoOrdenado[1] = escolherCategoriaOrdenar((Musica)N[f].dado, opcao);
                while (f > 0 && comparaPalavras(campoOrdenado[0], campoOrdenado[1]) && !(campoOrdenado[0].equals(campoOrdenado[1]))) {  // N[f/2]<N[f]
                    
                    temp = N[f/2];
                    N[f/2] = N[f];
                    N[f] = temp;
                    
                    f /= 2;
                    campoOrdenado[0] = escolherCategoriaOrdenar((Musica)N[f/2].dado, opcao);
                    campoOrdenado[1] = escolherCategoriaOrdenar((Musica)N[f].dado, opcao);
                    this.incrementaContador();
                }
        }
    }
    
    
    private void peneira (int m, Nodo N[], int opcao) throws IllegalAccessException, NoSuchFieldException{ 
        int p = 0, f = 1;
        String campoOrdenado[]= new String[3];
        Nodo x = N[0];
        campoOrdenado[2] = escolherCategoriaOrdenar((Musica)x.dado, opcao);
        this.incrementaContador();
        while (f <= m) {
           campoOrdenado[0] = escolherCategoriaOrdenar((Musica)N[f].dado, opcao);
           campoOrdenado[1] = escolherCategoriaOrdenar((Musica)N[f+1].dado, opcao);
           if (f < m && comparaPalavras(campoOrdenado[0], campoOrdenado[1]) && !(campoOrdenado[0].equals(campoOrdenado[1]))) ++f; //N[f]<N[f+1]
           
           campoOrdenado[0] = escolherCategoriaOrdenar((Musica)N[f].dado, opcao);
           if (comparaPalavras(campoOrdenado[0], campoOrdenado[2])) break;
           N[p] = N[f];
           p = f;
           f = 2*p;
           this.incrementaContador();
        }
        N[p] = x;
    }

    private void heapSort(int n, Nodo[] N, int opcao) throws IllegalAccessException, NoSuchFieldException{
        constroiHeap(n, N, opcao);
        Nodo temp;
        for (int m = n; m >= 2; --m) {
            this.incrementaContador();
            temp = N[0];
            N[0] = N[m-1];
            N[m-1] = temp;
            
            peneira (m-2, N, opcao);
        }
    } 
    
    
    public void heapSort(int opcao) throws NoSuchFieldException{
        try{
            heapSort(referenciaNodo.length, referenciaNodo, opcao);
        }catch(IllegalAccessException e){
            System.out.println(e);
        }
    }
    
    
    /*FIM HEAPSORT*/
    
    /*FIM ALGORITMOS DE ORDENACAO*/
    
    
    
    
    
    
    
    
    
    
    
    
    /* METODOS UTEIS */
   
    
    private String escolherCategoriaOrdenar(Musica m, int opcao) throws IllegalAccessException, NoSuchFieldException{
        
        Field trackNameField = this.getCabeca().dado.getClass().getDeclaredField("trackName");
        Field artistNameField =  this.getCabeca().dado.getClass().getDeclaredField("artistsName");
        trackNameField.setAccessible(true);
        artistNameField.setAccessible(true);
        String campoOrdenado = "";
        
        if(opcao==1){
            campoOrdenado = (String) trackNameField.get(m);
        }else{
            campoOrdenado = (String) artistNameField.get(m);
        }
        
        return campoOrdenado;
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
    
    
    
    private void trocarCabeca(Nodo segundoNodo, Nodo segundoNodoAnt) throws NullPointerException{ // anteriores não podem ser nulos
    
        Nodo temp;
        Nodo tempProx;
        
        if (segundoNodoAnt == this.getCabeca()){
            temp = this.getCabeca();
            temp.ant=segundoNodo;
            temp.prox = segundoNodo.prox;
            
            segundoNodo.prox = temp;
            segundoNodo.ant = null;
            
            this.setCabeca(segundoNodo);
            
        }else{
            temp = this.getCabeca();
            tempProx = temp.prox;
            
            temp.prox = segundoNodo.prox;
            temp.ant = segundoNodo.ant;
            
            segundoNodo.prox = tempProx;
            segundoNodo.ant = null;
            
            segundoNodoAnt.prox = temp;
            
            this.setCabeca(segundoNodo);
            
        }
        
    }
    
    
    /*
    private void trocarUltimo(Nodo nodo) throws NullPointerException{ // troca nodo com Ultimo
    
        Nodo temp= this.getUltimo();
        
        if(nodo==temp){return;}
        
        Nodo antTemp = temp.ant;
        
        if(nodo.prox.equals(temp)){
            nodo.prox = null;
            temp.ant = nodo.ant;
            temp.ant.prox = temp;
            temp.prox = nodo;
            
            nodo.ant = temp;
            
            this.setUltimo(nodo);
            
        }else if(!(nodo.prox.equals(temp))){
            temp.prox = nodo.prox;
            temp.ant = nodo.ant;
            temp.ant.prox = temp;
            temp.prox.ant = temp;
            
            antTemp.prox = nodo;
            nodo.prox = null;
            nodo.ant = antTemp;
            
            this.setUltimo(nodo);
            
        }
        
        
        
        
        
     }
        
    */
    
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
    
    
    
}
