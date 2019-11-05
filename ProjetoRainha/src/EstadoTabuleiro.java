

import java.util.Random;

public class EstadoTabuleiro {
    
    private int casas[][];
    private int numRainhas;
    private int heuristica[][];
    private int posRainhas[];
    
    public EstadoTabuleiro(int dimensao) {
        this.numRainhas = dimensao;
        this.casas = new int[dimensao][dimensao];
        this.heuristica = new int[dimensao][dimensao];
        this.posRainhas = new int[dimensao];
    }
    
    public void inicializarCasasComRainha() {
        Random r = new Random();
        for(int i = 0; i < numRainhas; ++i) {
            int pos = r.nextInt(numRainhas-1);
            casas[i][pos] = 1;
            posRainhas[i] = pos;
        }
    }
    
    public void construirHeuristica() {
        int linha;
        int coluna;
        for(linha = 0; linha < numRainhas; ++linha) {
            int posI = posRainhas[linha];
            casas[linha][posI] = 0;
            for(coluna = 0; coluna < numRainhas; ++coluna) {
                casas[linha][coluna] = 1; 
                heuristica[linha][coluna] = contarConflitos()/2;
                casas[linha][coluna] = 0; 
            }
            casas[linha][posI] = 1;
            heuristica[linha][posI] = Integer.MAX_VALUE;
        }
    }
    
    public EstadoTabuleiro clonar() {
        EstadoTabuleiro clone = new EstadoTabuleiro(numRainhas);
        for(int i = 0; i < casas.length; ++i) {
            for(int j = 0; j < casas.length; ++j) {
                clone.casas[i][j] = casas[i][j];
                clone.heuristica[i][j] = heuristica[i][j];
            }
            clone.posRainhas[i] = posRainhas[i];
        }
        return clone;
    }
    
    public int[][] getHeuristica() {
        return this.heuristica;
    }
    
    public int contarConflitos() {
        int conflitos = 0;
        for(int i = 0; i < numRainhas; ++i) {
            for(int j = 0; j < numRainhas; ++j) {
                if(casas[i][j] == 1) {
                    int cc = j;
                    int ll = i+1;
                    while(ll < numRainhas) {
                        if(casas[ll][cc] == 1) {
                            conflitos++;
                        }
                        ll++;
                    }
                    cc = j;
                    ll = i-1;
                    while(ll >= 0) {
                        if(casas[ll][cc] == 1) {
                            conflitos++;
                        }
                        ll--;
                    }
                    
                    cc = j + 1;
                    ll = i + 1;
                    while(cc < numRainhas && ll < numRainhas) {
                        if(casas[ll][cc] == 1) {
                            conflitos++;
                        }
                        cc++;
                        ll++;
                    }
                    
                    
                    cc = j - 1;
                    ll = i - 1;
                    while(cc >= 0&& ll >= 0) {
                        if(casas[ll][cc] == 1) {
                            conflitos++;
                        }
                        cc--;
                        ll--;
                    }
                    
                    cc = j + 1;
                    ll = i - 1;
                    while(cc < numRainhas && ll >= 0) {
                        if(casas[ll][cc] == 1) {
                            conflitos++;
                        }
                        cc++;
                        ll--;
                    }
                    
                    
                    cc = j - 1;
                    ll = i + 1;
                    while(cc >= 0&& ll < numRainhas) {
                        if(casas[ll][cc] == 1) {
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
    
    public int[][] getCasas() {
        return this.casas;
    }
    
    public int getDimensaoTabuleiro() {
        return casas.length;
    }
    
    public void mostrarEstado() {
        for(int i = 0; i < casas.length; ++i) {
            for(int j = 0; j < casas.length; ++j) {
                System.out.print(casas[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public boolean compararCom(EstadoTabuleiro estado) {
        for(int i = 0; i < numRainhas; ++i) {
            for(int j = 0; j < numRainhas; ++j ) {
                if(casas[i][j] != estado.casas[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public EstadoTabuleiro moverRainha(int idRainha, int pos) {
        EstadoTabuleiro novoEstado = clonar();
        int aux = posRainhas[idRainha];
        novoEstado.casas[idRainha][aux] = 0;
        novoEstado.casas[idRainha][pos] = 1;
        novoEstado.posRainhas[idRainha] = pos;
        return novoEstado;
    }
    
    public void mostrarEstadoHeuristica() {
        for(int i = 0; i < casas.length; ++i) {
            for(int j = 0; j < casas.length; ++j) {
                System.out.print(heuristica[i][j]+" ");
            }
            System.out.println();
        }
    }     
    
}