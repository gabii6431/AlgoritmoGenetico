package AlgoritmoGenetico;

import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGenetico 
{
    private ArrayList<Individuo> populacao;
    private int probabilidadeMutacao;

    public AlgoritmoGenetico(int tamanhoPopulacao,int probabilidadeMutacao, int[] peso, int[] valor, int capacidade, int qtdItens) 
    {
        populacao = new ArrayList<Individuo>();
        this.probabilidadeMutacao = probabilidadeMutacao;
        inicializarPopulacao(tamanhoPopulacao, peso,valor,capacidade, qtdItens);
    }
    
    public void evoluir(int numGeracoes) 
    {
        while(numGeracoes > 0) 
        {
            ArrayList<Individuo> novaPopulacao = new ArrayList<Individuo>();
            while(novaPopulacao.size() < populacao.size()) {
                Individuo i1 = (Individuo) this.roleta(populacao);
                Individuo i2 = (Individuo) this.roleta(populacao);
                Individuo filhos[] = this.crossover(i1, i2);
                this.mutacao(filhos[0], probabilidadeMutacao);
                this.mutacao(filhos[1], probabilidadeMutacao);
                novaPopulacao.add(filhos[0]);
                novaPopulacao.add(filhos[1]);
            }
            populacao = novaPopulacao;
            numGeracoes--;
        }
    }
    
    
    
    public String[] mostraResultado(){
        String[] resultado = new String[populacao.size()];
        for (int i = 0; i < populacao.size(); i++) {
            resultado[i] = populacao.get(i).resultadoIndividuo();
        }
        return resultado;
    }
    private void inicializarPopulacao(int tamanhoPopulacao, int[] peso, int[] valor, int capacidade, int qtdItens) 
    {
        Random r = new Random();
        int tamCromossomo = qtdItens;
        
        for(int i = 0; i < tamanhoPopulacao; ++i) {
            int cromossomo[] = new int[tamCromossomo];
            for(int j = 0; j < tamCromossomo; ++j) 
            {
                cromossomo[j] = r.nextInt(2);
            }
            Individuo individuo = new Individuo(cromossomo, peso, valor, capacidade);
            populacao.add(individuo);
        }
    }
      // Efetua o crossover entre dois indivíduos
    private Individuo[] crossover(Individuo i1,Individuo i2) 
    {
        int tam = i1.getTamanhoCromossomo();
        
        Random r = new Random();
        int pontoCorte = r.nextInt(tam);
        
        Individuo ni1 = i1.clonar();
        Individuo ni2 = i2.clonar();
        
        for(int i = pontoCorte; i < tam; ++i) 
        {
            ni1.setGene(i, i2.getGene(i));
            ni2.setGene(i, i1.getGene(i));
        }
        
        return new Individuo[]{ni1, ni2};
    }
    
    // Método para efetuar a mutação sobre um indivíduo
    // o parâmetro probabilidade assume um valor [0,100]
    private void mutacao(Individuo individuo,int probabilidade)
    {    
        Random r = new Random();
        
        int valorAleatorio = r.nextInt(101);
        if(probabilidade >= valorAleatorio) {
            int tam = individuo.getTamanhoCromossomo();
            int pos = r.nextInt(tam);
            int gene = individuo.getGene(pos);
            if(gene == 1) 
            {
                individuo.setGene(pos, 0);
            }
            else 
            {
                individuo.setGene(pos, 1);
            }
        } 
    }
    
    // Método da Roleta para determinar os indivíduos mais aptos
    public Individuo roleta(ArrayList<Individuo> populacao) {
        double aptidaoTotal = 0.0;
        // Soma todas as aptidões...
        for(Individuo i:populacao) 
        {
            aptidaoTotal += i.getAptidao();
        }
        
        Random r = new Random();
        
        double valorAleatorio = r.nextDouble()*aptidaoTotal;
        double aptidaoAcumulada = 0.0;
        for(Individuo i:populacao) 
        {
            aptidaoAcumulada += i.getAptidao();
            if(valorAleatorio <= aptidaoAcumulada) {
                return i;
            }
        }
        return null; 
    }
}
