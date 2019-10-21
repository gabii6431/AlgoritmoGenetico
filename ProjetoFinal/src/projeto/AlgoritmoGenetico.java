package projeto;

import java.util.ArrayList;
import java.util.Random;



public class AlgoritmoGenetico {
    
    private ArrayList<Individuo> populacao;
    private int probabilidadeMutacao;
    
    public AlgoritmoGenetico(int tamanhoPopulacao,int probabilidadeMutacao) {
        populacao = new ArrayList<Individuo>();
        this.probabilidadeMutacao = probabilidadeMutacao;
        inicializarPopulacao(tamanhoPopulacao);
    }
    
    public void evoluir(int numGeracoes) {
        Operacoes op = new Operacoes();
        while(numGeracoes > 0) {
            System.out.println("Geração ("+numGeracoes+")");
            ArrayList<Individuo> novaPopulacao = new ArrayList<Individuo>();
            while(novaPopulacao.size() < populacao.size()) {
                IndividuoTabuleiro i1 = (IndividuoTabuleiro) op.roleta(populacao);
                IndividuoTabuleiro i2 = (IndividuoTabuleiro) op.roleta(populacao);
                System.out.println("sai");
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
    
    private void inicializarPopulacao(int tamanhoPopulacao) {
        Random r = new Random();
        
        for(int i = 0; i < tamanhoPopulacao; ++i) {
            Tabuleiro cromossomo = new Tabuleiro(8);
            int[] posicaoY = new int[8];
            
            //inicializa o vetor posicaoY (onde armazena a posicao da rainha no tabuleiro de acordo com a sua linha)
            for (int z = 0; z < posicaoY.length; z++) {
                posicaoY[z] = -1;
            }

            //inicializou a posicao das rainhas de forma aleatoria
            for (int t = 0; t < posicaoY.length; t++) {
                posicaoY[t] = gerarYAleatorioExclusivo(posicaoY);
                cromossomo.atualizaTabuleiro(posicaoY);
            }
            
            IndividuoTabuleiro individuo = new IndividuoTabuleiro(cromossomo, posicaoY);
            populacao.add(individuo);
        }
    }
    
    public int gerarYAleatorioExclusivo(int[] posicoesY) {
        int y;
        Random r;
        boolean encontrou;

        do {
            r = new Random();
            y = r.nextInt(8);
            encontrou = false;

            for (int i = 0; i < 8; i++) {
                if (posicoesY[i] == y) {
                    encontrou = true;
                    break;
                }
            }

        } while (encontrou);

        return y;
    }
    
}
