package projeto;

import java.util.Locale;
import java.util.Random;

public class IndividuoTabuleiro extends Individuo<Tabuleiro,Integer> {
    
    private double intervalo[];
    private Tabuleiro tabuleiro; //novo Individuo
    
    public IndividuoTabuleiro(Tabuleiro cromossomo, int[] posicaoY) {
        super(cromossomo,posicaoY);
    }
    
    public Integer getFenotipo() {
        int numRainhas = 8;
        int conflitos = 0;
        for(int i = 0; i < numRainhas; ++i) {
            for(int j = 0; j < numRainhas; ++j) {
                if(cromossomo.getTabuleiro()[i][j] == true) {
                    int cc = j;
                    int ll = i+1;
                    while(ll < numRainhas) {
                        if(cromossomo.getTabuleiro()[ll][cc] == true) {
                            conflitos++;
                        }
                        ll++;
                    }
                    cc = j;
                    ll = i-1;
                    while(ll >= 0) {
                        if(cromossomo.getTabuleiro()[ll][cc] == true) {
                            conflitos++;
                        }
                        ll--;
                    }
                    
                    cc = j + 1;
                    ll = i + 1;
                    while(cc < numRainhas && ll < numRainhas) {
                        if(cromossomo.getTabuleiro()[ll][cc] == true) {
                            conflitos++;
                        }
                        cc++;
                        ll++;
                    }
                    
                    
                    cc = j - 1;
                    ll = i - 1;
                    while(cc >= 0&& ll >= 0) {
                        if(cromossomo.getTabuleiro()[ll][cc] == true) {
                            conflitos++;
                        }
                        cc--;
                        ll--;
                    }
                    
                    cc = j + 1;
                    ll = i - 1;
                    while(cc < numRainhas && ll >= 0) {
                        if(cromossomo.getTabuleiro()[ll][cc] == true) {
                            conflitos++;
                        }
                        cc++;
                        ll--;
                    }
                    
                    
                    cc = j - 1;
                    ll = i + 1;
                    while(cc >= 0&& ll < numRainhas) {
                        if(cromossomo.getTabuleiro()[ll][cc] == true) {
                            conflitos++;
                        }
                        cc--;
                        ll++;
                    }
                }
                
            }
        }
            
        return conflitos;
    }
    
    public double getAptidao() {
        return getFenotipo();
    }
    
    // Método para retornar o tamanho do cromossomo.
    public int getTamanhoCromossomo() {
        return cromossomo.getTamanho();
    }
    
    // Método para clonar um indivíduo.
    public IndividuoTabuleiro clonar() {
        Tabuleiro cromossomoClonado = new Tabuleiro(8);
        cromossomoClonado = cromossomo;
        
        int[] posicaoYClonada = new int[posicoesY.length];
        
        for(int i = 0; i < posicoesY.length; ++i) {
            posicaoYClonada[i] = posicoesY[i];
        }
        IndividuoTabuleiro clone = new IndividuoTabuleiro(cromossomoClonado,posicaoYClonada);
        return clone;
    }
    
    // Método para modificar o valor de um gene no cromossomo.
    public void setGene(int pos, int valor) {
        posicoesY[pos] = valor;
        cromossomo.atualizaTabuleiro(posicoesY);
    }
    
    // Retorna o valor do gene em uma determinada posição
    public int[] getGene() {
        return posicoesY;
    }
    
    public int gerarYAleatorioExclusivo() {
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
    
    public void mostrarIndividuo() {
        System.out.println(cromossomo.toString());
        System.out.printf(" Fenótipo = " + getFenotipo());
        
        System.out.printf(" Aptidão = " + getAptidao());
        
        System.out.println();
    }
}
