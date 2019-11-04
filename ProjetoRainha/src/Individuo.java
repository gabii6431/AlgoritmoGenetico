
// Classe para representar um indivíduo no AG (Algoritmo Genético)
public abstract class Individuo<T,E> {
    protected T cromossomo;
    protected int[] posicoesY;
    
    public Individuo(T cromossomo, int[] posicaoY) {
        this.cromossomo = cromossomo;
        this.posicoesY = posicaoY;
    }
    
    // Para retornar o fenótipo (característica) do indivíduo.
    public abstract E getFenotipo();
    public abstract double getAptidao();
    public abstract void mostrarIndividuo();
}
