
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class TabuleiroInterface extends JFrame {
    
    private JButton casas[][];
    
    private EstadoTabuleiro estado;
    private int dimensao;
    private Tabuleiro tabuleiroGenetico;
    
    public TabuleiroInterface(int dimensao,int tipo) {
        this.tabuleiroGenetico = new Tabuleiro(dimensao);
        this.estado = new EstadoTabuleiro(dimensao); //Cria o EstadoTabuleiro Inicial
        this.estado.inicializarCasasComRainha(); //Coloca as rainhas em posições aleatórias
        this.estado.construirHeuristica(); //Conta a quantidade de conflitos para cada posição do tabuleiro
        this.dimensao = dimensao;
        inicializarTabuleiro(tipo); //Cria o Jframe para o tabuleiro
    }
    
//    public TabuleiroInterface(EstadoTabuleiro estado) {;
//        this.dimensao = estado.getDimensaoTabuleiro();
//        this.estado = estado;
//        this.estado.construirHeuristica();
//        inicializarTabuleiro();
//    }
    
    //mostra a quantidade de conflito de cada posição do tabuleiro (Tabuleiro A*)
    private void exibirHeuristica() {
        for(int i = 0; i < dimensao; ++i) {
            for(int j = 0; j< dimensao; ++j) {
                casas[i][j].setText(estado.getHeuristica()[i][j]+""); 
            }
        }
    }
    
    //Cria JFrame para exibir o tabuleiro
    private void inicializarTabuleiro(int tipo) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500,500);
        setLayout(new GridLayout(dimensao,dimensao));
        if(tipo == 1){
            desenharCasas();
        }else{
            desenharCasasGenetica();
        }
        
    }
    
    //Cria os botões representando cada posição do tabuleiro A* e pinta de azul
    private void desenharCasas() {
        casas = new JButton[dimensao][dimensao];
        for(int i = 0; i < dimensao; ++i) {
            for(int j = 0; j < dimensao; ++j) {
                casas[i][j] = new JButton();
                add(casas[i][j]);
                casas[i][j].setBackground(Color.BLUE);
            }
        }
        validate();
        desenharRainha();
    }
    
    
    //Cria botões representando cada posição do tabuleiro Genético e pinta de azul
    private void desenharCasasGenetica() {
        casas = new JButton[dimensao][dimensao];
        for(int i = 0; i < dimensao; ++i) {
            for(int j = 0; j < dimensao; ++j) {
                casas[i][j] = new JButton();
                add(casas[i][j]);
                casas[i][j].setBackground(Color.BLUE);
            }
        }
        validate();
        desenharRainhaGenetico();
    }
    
    //Coloca as rainhas e a quantidade de conflitos de cada posição
    private void desenharRainha() {
        exibirHeuristica();
        for(int i = 0; i < dimensao; ++i) {
            for(int j = 0; j < dimensao; ++j) {
                casas[i][j].setBackground(Color.blue);
                casas[i][j].setIcon(null);
                addEvento(i,j,1);
                if(estado.getCasas()[i][j] == 1) {
                    JButton casa = casas[i][j];
                    try {
                        File imgFile = new File(getClass().getResource("img/rainha.png").getFile());
                        Image img = new ImageIcon(Files.readAllBytes(imgFile.toPath())).getImage();
                        img = img.getScaledInstance(casa.getWidth(), casa.getWidth(), java.awt.Image.SCALE_SMOOTH);
                        casa.setIcon(new ImageIcon(img));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                casas[i][j].repaint();
                casas[i][j].validate();
            }
        }
        
    }
    
    private void desenharRainhaGenetico() {
        for(int i = 0; i < dimensao; ++i) {
            for(int j = 0; j < dimensao; ++j) {
                casas[i][j].setBackground(Color.blue);
                casas[i][j].setIcon(null);
                addEvento(i,j,2);
                if(tabuleiroGenetico.getTabuleiro()[i][j]) {
                    JButton casa = casas[i][j];
                    try {
                        File imgFile = new File(getClass().getResource("img/rainha.png").getFile());
                        Image img = new ImageIcon(Files.readAllBytes(imgFile.toPath())).getImage();
                        img = img.getScaledInstance(casa.getWidth(), casa.getWidth(), java.awt.Image.SCALE_SMOOTH);
                        casa.setIcon(new ImageIcon(img));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                casas[i][j].repaint();
                casas[i][j].validate();
            }
        }
        
    }
    
    private void pintarBotao(int i, int j, Color cor, boolean pintarAmeacas, int tipo) {
        for(int k = 0; k < dimensao; ++k) {
            casas[i][k].setBackground(cor);
            if(tipo == 1){
                if(estado.getCasas()[i][k] == 1 && pintarAmeacas) {
                    casas[i][k].setBackground(Color.red);
                }
            }
            else{
                if(tabuleiroGenetico.getTabuleiro()[i][k] && pintarAmeacas){
                    casas[i][k].setBackground(Color.red);
                }
            }
            
            
        }
        for(int k = 0; k < dimensao; ++k) {
            casas[k][j].setBackground(cor);
            if(tipo == 1){
               if(estado.getCasas()[k][j] == 1 && pintarAmeacas) {
                    casas[k][j].setBackground(Color.red);
                } 
            }
            else{
                if(tabuleiroGenetico.getTabuleiro()[k][j] && pintarAmeacas){
                    casas[k][j].setBackground(Color.red);
                }
            }
            
        }
        int l = i-1;
        int c = j-1;
        while(c >= 0 && l >= 0) {
            casas[l][c].setBackground(cor);
            if(tipo == 1){
                if(estado.getCasas()[l][c] == 1 && pintarAmeacas) {
                    casas[l][c].setBackground(Color.red);
                }  
            }
            else{
                if(tabuleiroGenetico.getTabuleiro()[l][c] && pintarAmeacas){
                    casas[l][c].setBackground(Color.red);
                }
            }
            c--;
            l--;
        }

        l = i+1;
        c = j+1;
        while(c < dimensao && l < dimensao) {
            casas[l][c].setBackground(cor);
            if(tipo == 1){
               if(estado.getCasas()[l][c] == 1 && pintarAmeacas) {
                    casas[l][c].setBackground(Color.red);
                } 
            }
            else{
               if(tabuleiroGenetico.getTabuleiro()[l][c] && pintarAmeacas){
                    casas[l][c].setBackground(Color.red);
                } 
            }
            c++;
            l++;
        }

        l = i-1;
        c = j+1;
        while(c < dimensao && l >= 0) {
            casas[l][c].setBackground(cor);
            if(tipo == 1){
                if(estado.getCasas()[l][c] == 1 && pintarAmeacas) {
                    casas[l][c].setBackground(Color.red);
                }
            }
            else{
               if(tabuleiroGenetico.getTabuleiro()[l][c] && pintarAmeacas){
                    casas[l][c].setBackground(Color.red);
                } 
            }
            c++;
            l--;
        }

        l = i+1;
        c = j-1;
        while(c >= 0 && l < dimensao) {
            casas[l][c].setBackground(cor);
            if(tipo == 1){
                if(estado.getCasas()[l][c] == 1 && pintarAmeacas) {
                    casas[l][c].setBackground(Color.red);
                }
            }
            else{
               if(tabuleiroGenetico.getTabuleiro()[l][c] && pintarAmeacas){
                    casas[l][c].setBackground(Color.red);
                } 
            }
            c--;
            l++;
        }
    }
    
    private void addEvento(int i, int j, int tipo) {
        JButton casa = casas[i][j];
        casa.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if(tipo == 1){
                        if(estado.getCasas()[i][j] == 1){
                        pintarBotao(i, j, Color.YELLOW, true, tipo);
                    }
                }
                else{
                        if(tabuleiroGenetico.getTabuleiro()[i][j]){
                        pintarBotao(i, j, Color.YELLOW, true, tipo);
                    }
                }
                
            }

            @Override
            public void mouseExited(MouseEvent me) {
                pintarBotao(i, j, Color.BLUE, false,tipo);
            }
        });
    }
    
    public void mostrarSolucao(Stack<EstadoTabuleiro> passos) {
        for(int i = 0; i < dimensao; ++i) {
            for(int j = 0; j < dimensao; ++j) {
                casas[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        if(!passos.empty()) {
                            estado = passos.pop();
                            desenharRainha();
                            validate();
                        }
                    }
                });
            }
        }
    }
    
    public void mostrarSolucaoAlgoritmoGenetico(Tabuleiro tabuleiro) {
        System.out.println(tabuleiro.toString());
        for(int i = 0; i < dimensao; ++i) 
        {
            for(int j = 0; j < dimensao; ++j) 
            {
                tabuleiroGenetico = tabuleiro;
                desenharRainhaGenetico();
                validate();
            }
        }
    }
    
    public No buscaAEstrela() {
        No raiz = new No(estado, 1);
        No noAtual = raiz;
        
        ArrayList<No> nos = new ArrayList();
        
        
        while(true) {
            
            EstadoTabuleiro estadoAtual = noAtual.getEstadoTabuleiro();
            int d = estadoAtual.getDimensaoTabuleiro();
            int h[][] = estadoAtual.getHeuristica();
            for(int i = 0; i < d; ++i) {
                for(int j = 0; j < d; ++j) {
                    EstadoTabuleiro e = estadoAtual.moverRainha(i, j);
                    No filho = new No(e, 1);
                    e.construirHeuristica();
                    filho.setCustoHeuristica(h[i][j]);
                    if(noAtual.addFilho(filho)) {
                        nos.add(filho);
                    }
                }
            }

            Collections.sort(nos);

            int maisRaso = nos.get(0).getCustoCaminho();
            int fNo = nos.get(0).getCustoHeuristica();
            int idNo = 0;
            for(int i = 1; i < nos.size(); ++i) {
                if(nos.get(i).getCustoCaminho() < maisRaso && 
                        fNo == nos.get(i).getCustoHeuristica()) {
                    maisRaso = nos.get(i).getCustoCaminho();
                    idNo = i;
                }
            }
            No filho = nos.remove(idNo);
            if(filho.getEstadoTabuleiro().contarConflitos() == 0) {
                return filho;
            }
            noAtual = filho;
        }
        
    }
    
    public static void main(String args[]) {
        
        TabuleiroInterface tabuleiroEstrela = new TabuleiroInterface(8,1);
        tabuleiroEstrela.setTitle("A*");
        No solucaoAEstrela = tabuleiroEstrela.buscaAEstrela();
        
        Stack<EstadoTabuleiro> passosAEstrela = new Stack();
        while(solucaoAEstrela != null) {
            passosAEstrela.push(solucaoAEstrela.getEstadoTabuleiro());
            solucaoAEstrela = solucaoAEstrela.getPai();
        }
        
        tabuleiroEstrela.mostrarSolucao(passosAEstrela);
        
        //Tabuleiro Algoritmo Genetico
        TabuleiroInterface tabuleiroGenetico1 = new TabuleiroInterface(8,2);
        tabuleiroGenetico1.setTitle("Algoritmo Genetico");
        
        AlgoritmoGenetico ag = new AlgoritmoGenetico(100, 7);
        tabuleiroGenetico1.mostrarSolucaoAlgoritmoGenetico(ag.pegaTabuleiro());
        ag.evoluir(2000);
        tabuleiroGenetico1.mostrarSolucaoAlgoritmoGenetico(ag.pegaTabuleiro());
    }
    
}