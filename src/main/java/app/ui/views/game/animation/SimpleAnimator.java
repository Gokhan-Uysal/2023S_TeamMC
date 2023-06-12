package app.ui.views.game.animation;


import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class SimpleAnimator extends JFrame {

    Line line = new Line(20, 20, 450, 450);

    public SimpleAnimator() {
        Container con = this.getContentPane();
        con.add(line);
    }

    public void paint(Graphics g) {
        super.paint(g);
        try {
            Thread.sleep(50);
            repaint();
        } catch(InterruptedException e) {
        }
    }


    @SuppressWarnings("deprecation")
    public static void main(String args[]) {
        SimpleAnimator sa = new SimpleAnimator();
        sa.setSize(500,500);
        sa.show();
        sa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for(int i=0; i<1000; i++) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
