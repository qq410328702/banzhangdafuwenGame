import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Dice extends JLabel {
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Dice() {
        this.setBounds(Global.CELL_SIZE,Global.CELL_SIZE,3*Global.CELL_SIZE,Global.CELL_SIZE);
        Font font = new Font("宋体",Font.BOLD,60);
        this.setFont(font);
        this.setBackground(Color.PINK);

    }

    public void drawMe(Graphics g){
        //g.setColor(Color.ORANGE);
        //g.fill3DRect(Global.CELL_SIZE,2*Global.CELL_SIZE,180,Global.CELL_SIZE,true);
       this.setText("点数"+Integer.toString(getNumber()));

//        g.setColor(Color.red);
//        g.setFont(font);
//        g.drawString("点数"+Integer.toString(getNumber()),Global.CELL_SIZE+10,2*Global.CELL_SIZE-10);
    }
}
