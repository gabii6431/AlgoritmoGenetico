package ProgramacaoDinamica;

public class ProgramacaoDinamica {
    int quantidadeItens;
    int [] peso;
    int [] valor;
    int capacidadeMochila;    
    int[][] mochila;

    public ProgramacaoDinamica(int[] peso, int[] valor, int capacidade, int qtdItens) {
        this.quantidadeItens = qtdItens;
        this.peso = peso;
        this.valor = valor;
        this.capacidadeMochila = capacidade;
        this.mochila = new int[quantidadeItens+1][capacidadeMochila+1];
    }
    
    
    
    public void executaAlgoritmo(){
        for (int i = 0; i <= quantidadeItens; ++i)
        {
            for (int j = 0; j <= capacidadeMochila; ++j)
            {
                if(j == 0 || i == 0)
                {
                    mochila[i][j] = 0;
                } else {
                    mochila[i][j] = mochila[i-1][j];
                    if(peso[i-1] <= j)
                    {
                        mochila[i][j] = maxNumero(mochila[i-1][j-peso[i-1]]+valor[i-1],mochila[i-1][j]);
                    }
                }
            }
        }
    }
     
    public String mostraResultado(){
        String resultado = "Valor: ";
        resultado += String.valueOf(mochila[quantidadeItens][capacidadeMochila]);
        resultado += " Itens: ";
        
        int[] x = new int[quantidadeItens+1];
        int[] itensMochila = solucao(x, quantidadeItens, capacidadeMochila);
        
        for (int i = 1; i < x.length; i++) {
            resultado += String.valueOf(x[i]);
            resultado += " ";
//            if(x[i] == 1){
//                resultado += String.valueOf(i);
//                if(i != x.length-1){
//                    resultado += " - ";
//                }
//                
//            }
        }
                
        return resultado;
    }
    
    public static int maxNumero(int a, int b){
        if(a>b){
            return a;
        } else {
            return b;
        }
    }
    
    public void imprimeMatriz(int mochila[][]) {
        for (int i = 0; i < mochila.length; i++) {
            for (int j = 0; j < mochila[i].length; j++){
                System.out.print(mochila[i][j] + " " );
            }
            System.out.println("");
        }
    }
    
    public int[] solucao(int x[], int i, int p)
    {
        if(i != 0)
        {
            if(mochila[i][p] == mochila[i-1][p])
            {
               x[i] = 0;
               solucao(x, i-1, p);
            } else {
                x[i] = 1;
                solucao(x, i-1, p-peso[i-1]);
            }
        }   
        return x;    
    }
    
    
}
