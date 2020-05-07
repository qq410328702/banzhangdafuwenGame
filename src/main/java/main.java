

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

public class main {
    public static void main(String[] args){
        JFrameGame jFrameGame= new JFrameGame();
        LinkedList<GamePlayer> gamePlayerLinkedList = new LinkedList<GamePlayer>();
        Maps maps = new Maps(gamePlayerLinkedList);
        gamePlayerLinkedList.add(new GamePlayer("阿土仔",300000,3,0,Color.red,"images/玩家/r1.png"));
        gamePlayerLinkedList.add(new GamePlayer("钱夫人",300000,5,0,Color.blue,"images/玩家/r2.png"));
        gamePlayerLinkedList.add(new GamePlayer("大老千",300000,0,5,Color.ORANGE,"images/玩家/r3.png"));
        gamePlayerLinkedList.add(new GamePlayer("孙小美",300000,0,1,Color.GREEN,"images/玩家/npc4.png"));
        CharacterInfoJPanel characterInfoJPanel= new CharacterInfoJPanel(gamePlayerLinkedList);
        CharacterInfoJFrame characterInfoJFrame = new CharacterInfoJFrame(gamePlayerLinkedList,characterInfoJPanel);
        JPanelGame jPanelGame= new JPanelGame(maps,gamePlayerLinkedList);
        Container container= jFrameGame.getContentPane();
        container.add(jPanelGame);
        Controller controller =new Controller(jPanelGame,maps,gamePlayerLinkedList,characterInfoJPanel);
        characterInfoJFrame.jButton.addMouseListener(controller);
    }
}
