
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
 
public class Interface2048 extends JFrame{
     
    private int[][] g;
    private Graphic p;
    private int bordure = 5, cell = 100, marge = 5;
    private KeyEvent ke;
     
    private class KEDispatcher implements KeyEventDispatcher {
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_PAUSE) ke = e;
            }
            return false;
        }
    }
      
    public Interface2048(int l, int h){
        super("2048");
        this.g = new int[l][h];
        this.p = new Graphic();
        this.p.setPreferredSize(new Dimension(2*this.bordure+this.g.length*this.cell, 2*this.bordure+this.g[0].length*this.cell));
        this.getContentPane().add(this.p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KEDispatcher());
    }
     
    public int toucheTapee(){
        if(this.ke == null) return 0;
        else{
            int result = 0;
            if(this.ke.getKeyCode() == KeyEvent.VK_RIGHT) result = 2;
            if(this.ke.getKeyCode() == KeyEvent.VK_LEFT) result = 1;
            if(this.ke.getKeyCode() == KeyEvent.VK_DOWN) result = 3;
            if(this.ke.getKeyCode() == KeyEvent.VK_UP) result = 4;
            //if(this.ke.getKeyCode() == KeyEvent.VK_PAUSE) result = 5;
            this.ke = null;
            return result;
        }
    }
         
    public void modifieCase(int x, int y, int val){
        if(x>-1 && x<this.g.length && y>-1 && y<this.g[0].length) g[x][y] = val;
        this.p.repaint();
    }
     
    private class Graphic extends JPanel{
                 
        public void paint(Graphics gr){
            int w = this.getWidth();
            int h = this.getHeight();
            gr.setColor(new Color(187,173,160));
            gr.fillRect(0,0,this.getWidth(),this.getHeight());
            for(int i=0;i<g.length;i++){
                for(int j=0;j<g[i].length;j++){
                    switch(g[i][j]){
                        case 0 : gr.setColor(new Color(205,192,180)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        case 2 : gr.setColor(new Color(238,228,218)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        case 4 : gr.setColor(new Color(237,224,200)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        case 8 : gr.setColor(new Color(242,177,121)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        case 16 : gr.setColor(new Color(245,149,99)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        case 32 : gr.setColor(new Color(246,124,95)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        case 64 : gr.setColor(new Color(246,94,59)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        case 128 : gr.setColor(new Color(237,207,114)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        case 256 : gr.setColor(new Color(237,204,97)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        case 512 : gr.setColor(new Color(237,200,80)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        case 1024 : gr.setColor(new Color(237,197,63)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        case 2048 : gr.setColor(new Color(237,194,46)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                        default : gr.setColor(new Color(187,173,160)); gr.fillRect(bordure+cell*i+marge,bordure+cell*j+marge,cell-2*marge,cell-2*marge); break;
                    }
                    if(g[i][j]!=0){
                        float coef = 2.0f;
                        if(g[i][j]>9){
                            if(g[i][j]>99)
                                if(g[i][j]>999) coef = 3.0f;
                                else coef = 2.8f;
                            else coef = 2.0f;
                        }
                        gr.setFont(gr.getFont().deriveFont(Font.BOLD,(float) ((h-2*bordure)/(g.length*coef))));
                        Rectangle2D rect = gr.getFont().getStringBounds(g[i][j]+"",((Graphics2D) gr).getFontRenderContext());
                        double wChar = rect.getWidth();
                        double hChar = rect.getHeight();
                        if(g[i][j]<8) gr.setColor(new Color(119,110,101));
                        else gr.setColor(Color.WHITE);
                        //Graphics2D gr2D = (Graphics2D) gr;7
                        //gr2D.setStroke(new BasicStroke(2.0));
                        gr.drawString(g[i][j]+"",(int) (bordure+cell*i+marge+(cell-2*marge-wChar)/2),(int) (bordure+cell*j+marge+hChar+(cell-2*marge-hChar)/2-hChar*0.15));
                    }
                }
            }
        }
         
    }
     
    /** Affiche le message m dans une boite de dialogue. */
    public void afficheMessage(String m){
        JOptionPane.showMessageDialog(this,m);
    }
}