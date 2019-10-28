package ProgramacaoDinamica;

public class Main 
{
     public static void main(String args[])
    {
        int qtdItens = 5;
        int [] peso = {5,4,3,2,1};
        int [] valor = {4,6,5,3,1};
        int capacidade = 10;
        
        int memorizacao[][] = new int[capacidade+1][qtdItens+1];
        
        for (int i = 0; i < capacidade; i++) {
            for (int j = 0; j < qtdItens; j++) {
                if(i==0 || j==0){
                    memorizacao[i][j] = 0;
                } else {
                    memorizacao[i][j] = memorizacao[i][j-1];
                    if(peso[j-1] <= i){
                        memorizacao[i][j] = maxNumero(memorizacao[i][j-1],memorizacao[i-peso[j-1]][j-1]+valor[j-1]);
                    }
                }
            }
        }
        
        System.out.println("Valor max: "+memorizacao[capacidade][qtdItens]);
        
    }
     
    public static int maxNumero(int a, int b){
        if(a>b){
            return a;
        } else{
            return b;
        }
    }
    
}
