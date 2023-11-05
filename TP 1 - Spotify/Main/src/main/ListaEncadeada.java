/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Thiago
 * @param <T>
 */


public class ListaEncadeada<T> implements Iterable<T>{
    
    private Nodo<T> cabeca;
    private Nodo<T> ultimo;

    public ListaEncadeada(){
        cabeca = null;
        ultimo = null;
    }

    public boolean contem(T dado){
        return buscar(dado) != null;
    }
    
    
    protected Nodo<T> buscarAnterior(T dado){
        Nodo<T> aux = cabeca;               // c1
        while(aux.prox != null){                 // c2.n
            if(aux.prox.dado.equals(dado)){  // c3. (n-1)
                return aux;                 // c4 . 0
            }
            aux = aux.prox;                 // c5
        }
        
        
        return null;                        // c6
    }                                       // c1+c2*n+(c3*(n-1)) + c5 = 2 + n
                                            // Tempo Linear
    
    
    protected Nodo<T> buscar(T dado){
        Nodo<T> aux = cabeca;               // c1
        while(aux != null){                 // c2.n
            if(aux.dado.equals(dado)){  // c3. (n-1)
                return aux;                 // c4 . 0
            }
            aux = aux.prox;                 // c5
        }
        
        
        return null;                        // c6
    }                                       // c1+c2*n+(c3*(n-1)) + c5 = 2 + n
                                            // Tempo Linear
    public Nodo<T> getCabeca() {
        return cabeca;
    }

    protected void setCabeca(Nodo<T> cabeca) {
        this.cabeca = cabeca;
    }

    public Nodo<T> getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo<T> ultimo) {
        this.ultimo = ultimo;
    }
    
    
    
    public void inserir(T dado){
        Nodo<T> nodo = new Nodo<>(dado);
        inserir(nodo);
    }
    
    private void inserir(Nodo nodo){
        if (cabeca == null){ //c1
            Nodo cabecaAtual = cabeca;   // c2
            cabeca = nodo;              // c3
            nodo.prox = cabecaAtual;    // c4 -> c1+c2+c3+c4 = A
            nodo.ant = null;
            ultimo = nodo;
        }else{                          //c5
            Nodo ultimoAtual = ultimo; // X = an + b
            ultimoAtual.prox = nodo; // c6
            nodo.ant = ultimoAtual;
            nodo.prox = null;
            this.setUltimo(nodo);           // c7 -> c4+c5+c6+c7 = B
        }                            // no total: A*0+B+X,
    }                                // Tempo linear
    
    public Nodo<T> acharUltimo(){
        Nodo ultimo = cabeca;           // c1
        while(ultimo.prox != null){     // c2*n
            ultimo = ultimo.prox;       // c3*(n-1)
        }
        return ultimo;                  // c4           Tempo Linear : 2n + 1
    }
    
    public void remover(T dado){
        Nodo<T> nodo = buscar(dado);
        remover(nodo);

    }

    private void remover(Nodo nodo){
        if(cabeca == null) throw new IllegalStateException("underflow");
        
        if (nodo == cabeca){            // c1 * 0
            cabeca = cabeca.prox;       // c2 * 0
            cabeca.ant = null;
            return;                     // c3 * 0
        }
        
        Nodo ant = nodo.ant;
        
        if(nodo == ultimo){
            ant.prox = null; 
            nodo.prox = null;                

            nodo.ant = null;
            ultimo = ant;
            return;
            
        }
        
        
        


        if (nodo==null) throw new IllegalStateException("nodo nao existe"); // 0

        ant.prox = nodo.prox; 
        nodo.prox = null;                
        
        nodo.ant = null;
        ant.prox.ant = ant;
                                                
    }

    class Nodo<T> {
        T dado;
        Nodo<T> prox;
        Nodo<T> ant;

        public Nodo (T dado){
            this.dado = dado;
            this.prox = null;
            this.ant = null;
        }
        
        public T getDado() {
            return dado;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new Iterator<T>(){
            Nodo<T> atual = cabeca;

            @Override
            public boolean hasNext(){
                return atual != null;
            }

            @Override
            public T next(){
                if(!hasNext()) throw new NoSuchElementException();
                T result = atual.dado;
                atual = atual.prox;
                return result;
            }
        };
    }
}
