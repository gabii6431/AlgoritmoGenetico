package projeto;

public class Main {
    
    public static void main(String args[]) {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(10, 7);
        ag.mostrarPopulacao();
        ag.evoluir(50);
        ag.mostrarPopulacao();
        
    }
    
}
