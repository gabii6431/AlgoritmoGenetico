package AlgoritmoGenetico;

public class Main 
{
    public static void main(String args[])
    {
        int qtdItens = 5;
        double [] peso = {5.0,4.0,3.0,2.0,1.0};
        double [] valor = {4.0,6.0,5.0,3.0,1.0};
        double capacidade = 10.0;
        
        AlgoritmoGenetico ag = new AlgoritmoGenetico(100, 5, peso, valor, capacidade, qtdItens);
        ag.mostrarPopulacao();
        System.out.println("*****************");
        ag.evoluir(200);
        ag.mostrarPopulacao();
    }
    
}
