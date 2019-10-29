package ProgramacaoDinamica;

public class Main 
{
     public static void main(String args[])
    {
        int quantidadeItens = 5;
        int [] peso = {5,4,3,2,1};
        int [] valor = {4,6,5,3,1};
        int capacidadeMochila = 10;
        
        int[][] mochila = new int[capacidadeMochila+1][quantidadeItens+1];
        
        for (int i = 0; i <= capacidadeMochila; ++i)
        {
            for (int j = 0; j <= quantidadeItens; ++j)
            {
                if(j == 0 || i == 0)
                {
                    mochila[i][j] = 0;
                }
                else 
                {
                    mochila[i][j] = mochila[i][j-1];
                    if(peso[j-1] <= i)
                    {
                        mochila[i][j] = maxNumero(mochila[i][j-1],mochila[i-peso[j-1]][j-1]+valor[j-1]);
                    }
                }
            }
        }
        
        System.out.println("Valor max: "+mochila[capacidadeMochila][quantidadeItens]);     
    }
     
    public static int maxNumero(int a, int b)
    {
        if(a>b)
        {
            return a;
        }
        else
        {
            return b;
        }
    }
    
}
