
import java.util.ArrayList;


public class No implements Comparable<No>{

    private EstadoTabuleiro estado;
    private No pai;
    private ArrayList<No> filhos;
    private int custoHeuristica; // h(n).
    private int custoCaminho;    // g(n)
    private int f;               // f(n) = g(n) + h(n)
    private int estrategia;
    
    public No(EstadoTabuleiro estado) {
        this.estado = estado;
        this.pai = null;
        this.custoHeuristica = 0;
        this.custoCaminho = 0;
        this.filhos = new ArrayList();
        this.estrategia = 0;
    }
    
    public No(EstadoTabuleiro estado, int estrategia) {
        this.estado = estado;
        this.pai = null;
        this.custoHeuristica = 0;
        this.custoCaminho = 0;
        this.filhos = new ArrayList();
        this.estrategia = estrategia;
    }
    
    public void setCustoHeuristica(int custo) {
        this.custoHeuristica = custo;
    }
    
    public int getCustoHeuristica() {
        if(estrategia == 1) {
            return f;
        }    else {
            return custoHeuristica;
        }
    }
    
    public int getCustoCaminho() {
        return custoCaminho;
    }
    
    public void setCustoCaminho(int custo) {
        custoCaminho = custo;
    }
    
    public boolean addFilho(No filho) {
        No pai = this;
        while(pai!=null) {
            if(pai.estado.compararCom(filho.estado)) {
                return false;
            }
            pai = pai.pai;
        }
        filho.pai = this;
        filho.custoCaminho = custoCaminho + 1;
        filho.f = filho.custoCaminho + filho.custoHeuristica;
        filhos.add(filho);
        return true;
    }
    
    public No getPai() {
        return pai;
    }
    
    public EstadoTabuleiro getEstadoTabuleiro() {
        return estado;
    }
    
    public ArrayList<No> getFilhos(){
        return filhos;
    }

    @Override
    public int compareTo(No t) {
        if(t.getCustoHeuristica() < getCustoHeuristica()) {
            return 1;
        } 
        if(t.getCustoHeuristica() > getCustoHeuristica()) {
            return -1;
        }
        return 0;
    }
    
}