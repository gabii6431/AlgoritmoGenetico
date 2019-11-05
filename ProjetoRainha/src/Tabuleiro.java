

//Define um tabuleiro de dimensão n
public class Tabuleiro {

    private boolean[][] tabuleiro;
    private int tamanho;

    public Tabuleiro(int tamanho) {
        this.tamanho = tamanho;
        tabuleiro = new boolean[tamanho][tamanho];
        zeraTabuleiro();
    }

    public int getTamanho() {
        return tamanho;
    }
    public void zeraTabuleiro() {
        for (int y = 0; y < tamanho; y++) {
            for (int x = 0; x < tamanho; x++) {
                tabuleiro[x][y] = false;
            }
        }
    }

    public void atualizaTabuleiro(int posicoesY[]) {
        zeraTabuleiro();
        for (int i = 0; i < tamanho; i++) {
            if (posicoesY[i] != -1) {
                tabuleiro[i][posicoesY[i]] = true;
            }
        }
    }

    public boolean[][] getTabuleiro() {
        return tabuleiro;
    }

    public boolean estaLivre(int x, int y) {
        boolean livre = true;
        if (tabuleiro[x][y]) {
            livre = false;
        }
        return livre;
    }
    
    public Tabuleiro clonar(){
        Tabuleiro tabuleiroClonado = new Tabuleiro(8);
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiroClonado.tabuleiro[i][j] = tabuleiro[i][j];
            }  
        }
        
        return tabuleiroClonado;
    }

    @Override
    public String toString() {
        String r = "";
        for (int y = 0; y < tamanho; y++) {
            for (int x = 0; x < tamanho; x++) {
                if (tabuleiro[y][x]) {
                    r += " x";
                } else {
                    r += " o";
                }
            }
            r += "\n";
        }
        return r;
    }
}
