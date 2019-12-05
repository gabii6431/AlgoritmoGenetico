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
    
    public int getFenotipo()
    {
        int fenotipo = 0;
        int pesoAtual = getPeso();
        
        if(pesoAtual > capacidade)
        {
            return 0;
            
        }
        for (int i = 0; i < valor.length; i++) 
        {
            fenotipo += cromossomo[i] * valor[i];
        }
        return fenotipo;
    }
    
    public int getAptidao()
    {
        return getFenotipo();
    }
    
    public int getPeso()
    {
        int pesoAcumulado = 0;
        for (int i = 0; i < peso.length; i++) 
        {
            pesoAcumulado += cromossomo[i] * peso[i];
        }
        return pesoAcumulado;
    }
    
    public int getValor(){
        int valorAcumulado = 0;
        for (int i = 0; i < valor.length; i++) 
        {
            valorAcumulado += cromossomo[i] * valor[i];
        }
        return valorAcumulado;
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
    
    public String getItensMochila(){
         String resultado = "";
        resultado += "Itens: ";
        
        for(int i = 0; i < cromossomo.length; ++i){
            resultado+= String.valueOf(cromossomo[i]);
            resultado+= " ";
        }
        return resultado;
    }
    
    public String resultadoIndividuo(){ 
        int valor = this.getValor(); 
        
        String resultado = "Peso: " +this.getPeso() + " Valor: "; 
        resultado += String.valueOf(valor); 
        resultado += " Itens: "; 
        for(int i = 0; i < cromossomo.length; ++i){ 
            resultado+= String.valueOf(cromossomo[i]); 
            resultado+= " "; 
        } 
        return resultado; 
    } 
    

}
