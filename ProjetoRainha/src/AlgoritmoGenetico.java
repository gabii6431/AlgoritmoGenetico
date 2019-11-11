

import java.util.ArrayList;
import java.util.Random;



public class AlgoritmoGenetico {
    
    private ArrayList<Individuo> populacao;
    private int probabilidadeMutacao;
    private int tamanhoTabuleiro;
    
    public AlgoritmoGenetico(int tamanhoPopulacao,int probabilidadeMutacao, int tamanhoTabuleiro) {
        populacao = new ArrayList<Individuo>();
        this.probabilidadeMutacao = probabilidadeMutacao;
        this.tamanhoTabuleiro = tamanhoTabuleiro;
        inicializarPopulacao(tamanhoPopulacao);
    }
    
    public ArrayList<Individuo> getPopulacao(){
        return populacao;
    }
    
    public void evoluir(int numGeracoes) {
        Operacoes op = new Operacoes();
        while(numGeracoes > 0) {
            //System.out.println("Geração ("+numGeracoes+")");
            ArrayList<Individuo> novaPopulacao = new ArrayList<Individuo>();
            while(novaPopulacao.size() < populacao.size()) {
                IndividuoTabuleiro i1 = (IndividuoTabuleiro) op.roleta(populacao);
                IndividuoTabuleiro i2 = (IndividuoTabuleiro) op.roleta(populacao);
                IndividuoTabuleiro filhos[] = op.crossover(i1, i2);
                op.mutacao(filhos[0], probabilidadeMutacao);
                op.mutacao(filhos[1], probabilidadeMutacao);
                novaPopulacao.add(filhos[0]);
                novaPopulacao.add(filhos[1]);
            }
            
            populacao = novaPopulacao;
            numGeracoes--;
        }
    }
    
    public void mostrarPopulacao() {
        for(Individuo i:populacao) {
            i.mostrarIndividuo();               
        }
    }
    
    public Tabuleiro pegaTabuleiro(){
        for (int i = 0; i < populacao.size(); i++) {
            if((int)populacao.get(i).getFenotipo() == 0)
                return populacao.get(i).mostraTabuleiro();
        }
        return populacao.get(0).mostraTabuleiro();
    }
    private void inicializarPopulacao(int tamanhoPopulacao) {
        Random r = new Random();
        
        for(int i = 0; i < tamanhoPopulacao; ++i) {
            Tabuleiro cromossomo = new Tabuleiro(this.tamanhoTabuleiro);
            int[] posicaoY = new int[this.tamanhoTabuleiro];
            
            //inicializa o vetor posicaoY (onde armazena a posicao da rainha no tabuleiro de acordo com a sua linha)
            for (int z = 0; z < posicaoY.length; z++) {
                posicaoY[z] = -1;
            }

            //inicializou a posicao das rainhas de forma aleatoria
            for (int t = 0; t < posicaoY.length; t++) {
                posicaoY[t] = r.nextInt(this.tamanhoTabuleiro);
                cromossomo.atualizaTabuleiro(posicaoY);
            }
            IndividuoTabuleiro individuo = new IndividuoTabuleiro(cromossomo, posicaoY);
            populacao.add(individuo);
        }
    }
    
}
