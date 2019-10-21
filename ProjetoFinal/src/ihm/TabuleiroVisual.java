package ihm;

import dominio.Tabuleiro;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import operacao.Algoritmo;

public class TabuleiroVisual extends JPanel {

    private int tamanhoQuadrado = 50;
    private Tabuleiro tabuleiro;
    private int widthComponent;

    public TabuleiroVisual(Tabuleiro tabuleiro, int w) {
        this.tabuleiro = tabuleiro;
        this.widthComponent = w;
        tamanhoQuadrado = (int) widthComponent / Algoritmo.getN();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cont = 0;
        for (int y = 0; y < Algoritmo.getN(); y++) {
            cont++;
            for (int x = 0; x < Algoritmo.getN(); x++) {
                if (cont % 2 == 0) {
                    g.setColor(Color.BLACK);
                } else {
                    g.setColor(Color.WHITE);
                }
                cont++;
                g.fillRect(tamanhoQuadrado * x, tamanhoQuadrado * y, tamanhoQuadrado, tamanhoQuadrado);
            }
        }

        for (int y = 0; y < Algoritmo.getN(); y++) {
            for (int x = 0; x < Algoritmo.getN(); x++) {
                if (tabuleiro.getTabuleiro()[x][y]) {
                    g.setColor(Color.red);
                    g.fillOval(tamanhoQuadrado * x, tamanhoQuadrado * y, tamanhoQuadrado, tamanhoQuadrado);
                }
            }
        }
    }

    public void setTabuleiro(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;
        tamanhoQuadrado = (int) widthComponent / Algoritmo.getN();
        paintComponent(super.getGraphics());
    }
}