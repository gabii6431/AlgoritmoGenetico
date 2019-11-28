package AlgoritmoGenetico;

public class Individuo 
{
    private int [] cromossomo;
    private int [] peso;
    private int [] valor;
    private int capacidade;

    
    public Individuo(int[] cromossomo, int[] peso, int[] valor, int capacidadeTotal)
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
    
    public String resultadoIndividuo(){
        double somatoria = 0.0;
        for (int i = 0; i < valor.length; i++) 
        {
            somatoria += cromossomo[i] * valor[i];
        }
        
        String resultado = "Peso: " +this.getPeso() + " Valor: ";
        resultado += String.valueOf(somatoria);
        resultado += " Itens: ";
        for(int i = 0; i < cromossomo.length; ++i){
            resultado+= String.valueOf(cromossomo[i]);
            resultado+= " ";
//            if(cromossomo[i] == 1){
//                resultado += String.valueOf(i+1);
//                if(i != cromossomo.length-1){
//                    resultado += " - ";
//                }
//            }
        }
        return resultado;
    }
}
