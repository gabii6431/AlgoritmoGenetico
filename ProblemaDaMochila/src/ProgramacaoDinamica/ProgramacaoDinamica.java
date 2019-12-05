package ProgramacaoDinamica;

public class ProgramacaoDinamica {
    int quantidadeItens;
    int [] peso;
    int [] valor;
    int capacidadeMochila;    
    int[][] mochila;
    int[] vetorSolucao;

    public ProgramacaoDinamica(int[] peso, int[] valor, int capacidade, int qtdItens) {
        this.quantidadeItens = qtdItens;
        this.peso = peso;
        this.valor = valor;
        this.capacidadeMochila = capacidade;
        this.mochila = new int[quantidadeItens+1][capacidadeMochila+1];
        this.vetorSolucao = new int[quantidadeItens+1];
    }
    
    public void executaAlgoritmo(){
        for (int i = 0; i <= quantidadeItens; ++i){
            for (int j = 0; j <= capacidadeMochila; ++j){
                if(j == 0 || i == 0){
                    mochila[i][j] = 0;
                } else {
                    mochila[i][j] = mochila[i-1][j];
                    if(peso[i-1] <= j){
                        mochila[i][j] = maxNumero(mochila[i-1][j-peso[i-1]]+valor[i-1],mochila[i-1][j]);
                    }
                }
            }
        }
        this.preencheVetorSolucao();
    }
     
    public String getItensSolucao(){
        String resultado = "";
        resultado += "Itens: ";
        
        for (int i = 1; i < vetorSolucao.length; i++) {
            resultado += String.valueOf(vetorSolucao[i]);
            resultado += " ";
        } 
        return resultado;
    }
    
    public String getValorSolucao(){
        return String.valueOf(mochila[quantidadeItens][capacidadeMochila]);
    }
    
    public String getPesoSolucao(){
        int pesoAcumulado = 0;
        for (int i = 1; i < vetorSolucao.length; i++) {
            pesoAcumulado += vetorSolucao[i] * peso[i-1];
        }
        return String.valueOf(pesoAcumulado);
    }
    
    private int maxNumero(int a, int b){
        if(a>b){
            return a;
        } else {
            return b;
        }
    }
    
    private void preencheVetorSolucao(){
        int[] x = new int[quantidadeItens+1];
        this.vetorSolucao = solucao(x, quantidadeItens, capacidadeMochila); 
    }
    
    private int[] solucao(int x[], int i, int p){
        if(i != 0){
            if(mochila[i][p] == mochila[i-1][p]){
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
