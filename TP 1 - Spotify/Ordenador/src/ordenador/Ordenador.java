/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ordenador;

/**
 *
 * @author thiago
 */
public class Ordenador {
    
    public static ListaEncadeada<Musica> ordenacaoSelecao(ListaEncadeada<Musica> lista){
        //lista  -> selecionar menor valor
        // colocá-lo na posicao correta
        
        ListaEncadeada<Musica> listaOrdenada = new ListaEncadeada();
        
        // atual, ant, prox, temp, antTemp
        // if m1.nomeMusica < m2.nomeMusica (alfbt)
        
        // atual > prox : trocar(atual,prox)?passar para o prox ::: recursão
        
        return listaOrdenada;
    }
    
    // verifica se a primeira palavra vem primeiro alfabeticamente, em relação ao comparador
    public static boolean comparaPalavras(String palavra, String comparador){ 
        
        //todo:tolower e tirar acento/caracteres especiais
        
        // converter para lowercase, retirar caracteres especiais
        String palavraFiltrada = palavra.toLowerCase();
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
