package AlgoritmoGenetico;

public class Individuo 
{
    private int [] cromossomo;
    private double [] peso;
    private double [] valor;
    private double capacidade;

    
    public Individuo(int[] cromossomo, double[] peso, double[] valor, double capacidadeTotal)
    {
        this.cromossomo = cromossomo;
        this.peso = peso;
        this.valor = valor;
        this.capacidade = capacidadeTotal;
    }
    
    public double getFenotipo()
    {
        double fenotipo = 0.0;
        double pesoAtual = getPeso();
        
        if(pesoAtual > capacidade)
        {
            return 0.0;
        }
        for (int i = 0; i < valor.length; i++) 
        {
            fenotipo += cromossomo[i] * valor[i];
        }
        return fenotipo;
    }
    
    public double getAptidao()
    {
        return getFenotipo();
    }
    
    public double getPeso()
    {
        double pesoAtual = 0.0;
        for (int i = 0; i < peso.length; i++) 
        {
            pesoAtual += cromossomo[i] * peso[i];
        }
        return pesoAtual;
    }
    
    public int getTamanhoCromossomo() 
    {
        return cromossomo.length;
    }
    
    // Método para clonar um indivíduo.
    public Individuo clonar() {
        int cromossomoClonado[] = new int[cromossomo.length];
        for(int i = 0; i < cromossomo.length; ++i) {
            cromossomoClonado[i] = cromossomo[i];
        }
        Individuo clone = new Individuo(cromossomoClonado, peso, valor, capacidade);
        return clone;
    }
    
    // Método para modificar o valor de um gene no cromossomo.
    public void setGene(int pos, int valor) {
        cromossomo[pos] = valor;
    }
    
    // Retorna o valor do gene em uma determinada posição
    public int getGene(int pos) {
        return cromossomo[pos];
    }
    
    public void mostrarIndividuo() {
        System.out.print("I = ");
        for(int i = 0; i < cromossomo.length; ++i) 
        {
            System.out.print(" "+cromossomo[i]);
        }
        System.out.printf(" Peso = " +getPeso());
        System.out.printf(" Fenótipo = " +getFenotipo());
        
        System.out.println();
    }        
}
