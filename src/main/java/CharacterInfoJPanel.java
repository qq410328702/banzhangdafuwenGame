import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class CharacterInfoJPanel extends JPanel{
    private LinkedList<GamePlayer> gamePlayerLinkedList;

    public void display(){
        repaint();

    }

    public CharacterInfoJPanel(LinkedList<GamePlayer> gamePlayerLinkedList) {
        this.setBackground(Color.gray);
        this.gamePlayerLinkedList = gamePlayerLinkedList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i=0;i<gamePlayerLinkedList.size();i++){
            Font font = new Font("宋体",Font.BOLD,30);
            g.setColor(gamePlayerLinkedList.get(i).getColor());
            g.setFont(font);
            g.drawString(gamePlayerLinkedList.get(i).getName()+"金钱："+gamePlayerLinkedList.get(i).getMoney(),0,(i+1)*Global.CELL_SIZE-40);
        }
    }
}
