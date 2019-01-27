import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class MainPanel extends JPanel implements MouseListener, Runnable {
    private static final int N = 800; //frame size window
    private static final int D = 200;
    private static final int B = N/D;
    
    private Cell[][] cells = new Cell[200][200];
    
    private boolean antAlive;   
    
    private boolean run;
    
    public MainPanel () {
        super.setPreferredSize(new Dimension(N,N));
        run = false;
        addMouseListener(this);
        initCells();
        antAlive = false;
    }
    
    public boolean getRun() {return run;}
    public void setRun(boolean run) {this.run = run;}
    
    public void initCells() {
        for (int x = 0; x < D; x++) {
            for (int y = 0; y < D; y++) {
                cells[x][y] = new Cell(false, x*B, y*B);
            }
        }
    }
    
    public void paint(Graphics g) {
        paintComponents(g);
        
        //Background
        g.setColor(Color.WHITE);
        g.fillRect(0,0,N,N);
        
        //Cell
        for (int x = 0; x < D; x++) {
            for (int y = 0; y < D; y++) {
                cells[x][y].paintCell(g);
            }
        }
        
        //Grid
        g.setColor(Color.BLACK);
        for (int x = 0; x < D; x++) {
            g.drawLine(B*x, 0, B*x, N);
        }
        for (int y = 0; y < D; y++) {
            g.drawLine(0, B*y, N, B*y);            
        }
    }
        
    public void mousePressed(MouseEvent e) {
        int x = e.getX()/B;
        int y = e.getY()/B;
        if (e.getButton() == 1) {
            cells[x][y].changeState(cells[x][y].getColored());
        } else if (!antAlive && e.getButton() == 3) {
            cells[x][y].setHasAnt(true);
            cells[x][y].setAntD("N");
            antAlive = true;
        }
    }
    
    /*Un-used methods of implemented Objects*/
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    
    /** 
     * LANGTON ANT ALGORITHM
     */
    public void nextStep() {
        Main.step++;
        Main.counterValue.setText(Integer.toString(Main.step));
        for (int x = 1; x < D-1; x++) {
            for (int y = 1; y < D-1; y++) {
                if (cells[x][y].getHasAnt()){
                    String direction = cells[x][y].getAntD();
                    if (cells[x][y].getColored()) {
                        if (direction.equals("N")) {
                            cells[x-1][y].setHasAnt(true);
                            cells[x-1][y].setAntD("W");
                        } else if (direction.equals("E")) {
                            cells[x][y-1].setHasAnt(true);
                            cells[x][y-1].setAntD("N");
                        } else if (direction.equals("S")) {
                            cells[x+1][y].setHasAnt(true);
                            cells[x+1][y].setAntD("E");
                        } else if (direction.equals("W")) {
                            cells[x][y+1].setHasAnt(true);
                            cells[x][y+1].setAntD("S");
                        }
                    }
                    else {
                        if (direction.equals("N")) {
                            cells[x+1][y].setHasAnt(true);
                            cells[x+1][y].setAntD("E");
                        } else if (direction.equals("E")) {
                            cells[x][y+1].setHasAnt(true);
                            cells[x][y+1].setAntD("S");
                        } else if (direction.equals("S")) {
                            cells[x-1][y].setHasAnt(true);
                            cells[x-1][y].setAntD("W");
                        } else if (direction.equals("W")) {
                            cells[x][y-1].setHasAnt(true);
                            cells[x][y-1].setAntD("N");
                        }
                    }
                    
                    cells[x][y].setHasAnt(false);
                    cells[x][y].setAntD(null);
                    cells[x][y].changeState(cells[x][y].getColored());
                    return;
                }                
            }
        }
    }
    
    public void run() {
        while(true) {
            if (run && antAlive)  nextStep();
            repaint();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("ANT");
        frame.setVisible(true);
        frame.setSize(N+10, N+30);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainPanel main = new MainPanel();
        
        frame.add(main);
        
        Thread t = new Thread(main);
        t.start();
    }
}