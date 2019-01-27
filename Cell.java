import java.awt.Graphics;
import java.awt.Color;

public class Cell {
    private boolean hasAnt;
    private String antD;
    private boolean colored;
    private int x, y;
    
    public Cell(boolean colored, int x, int y) {        
        this.colored = colored;
        this.x = x;
        this.y = y;
        
        hasAnt = false;
        antD = "";
    }
    
    public boolean getHasAnt() {return hasAnt;}
    public void setHasAnt(boolean hasAnt) {this.hasAnt = hasAnt;}
    
    public String getAntD() {return antD;}
    public void setAntD(String direction) {antD = direction;} 
    
    public boolean getColored() {return colored;}
    public void setColored(boolean colored) {this.colored = colored;}
    
    public void changeState(boolean cellState) {
        if(cellState) colored = false;
        else colored = true;
    }
    
    public void paintCell(Graphics g) {
        if (hasAnt) g.setColor(Color.RED);
        else if (colored) g.setColor(Color.GRAY); //or DARK_GRAY
        else g.setColor(Color.WHITE);
        
        g.fillRect(x, y, 40, 40);
    }
}