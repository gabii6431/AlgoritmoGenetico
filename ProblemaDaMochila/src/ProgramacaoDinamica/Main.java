package ProgramacaoDinamica;

public class Main 
{
    static int quantidadeItens = 4;
    static int [] peso = {2,1,6,5};
    static int [] valor = {10,7,25,24};
    static int capacidadeMochila = 7;    
    static int[][] mochila = new int[quantidadeItens+1][capacidadeMochila+1];
    
    public static void main(String args[])
    {
    
         for (int i = 0; i <= quantidadeItens; ++i)
        {
            for (int j = 0; j <= capacidadeMochila; ++j)
            {
                if(j == 0 || i == 0)
                {
                    mochila[i][j] = 0;
                }
                else 
                {
                    mochila[i][j] = mochila[i-1][j];
                    if(peso[i-1] <= j)
                    {
                        mochila[i][j] = maxNumero(mochila[i-1][j-peso[i-1]]+valor[i-1],mochila[i-1][j]);
                    }
                }
            }
        }
        //imprimeMatriz(mochila);
        int[] x = new int[quantidadeItens+1];
        int[] itensMochila = solucao(x, quantidadeItens, capacidadeMochila);
        
        for (int i = 0; i < x.length; i++) 
        {
            System.out.print(itensMochila[i] + " ");
        }
        System.out.println("");
   
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
    
    public static void imprimeMatriz(int mochila[][])
    {
        for (int i = 0; i < mochila.length; i++) 
         {
             for (int j = 0; j < mochila[i].length; j++) 
             {
                 System.out.print(mochila[i][j] + " " );
             }
             System.out.println("");
        }
    }
    
    public static int[] solucao(int x[], int i, int p)
    {
        if(i != 0)
        {
            if(mochila[i][p] == mochila[i-1][p])
            {
               x[i] = 0;
               solucao(x, i-1, p);
            }else
            {
                x[i] = 1;
                solucao(x, i-1, p-peso[i-1]);
            }
        }   
        return x;    
    }
    
    
}
