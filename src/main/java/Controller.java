

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Controller extends MouseAdapter implements PlayerListener{
    private Maps maps;
    private JPanelGame jPanelGame;
    private GamestateController gamestateController;
    private LinkedList<GamePlayer> gamePlayerLinkedList;
    private CharacterInfoJPanel characterInfoJPanel;


    public Controller(JPanelGame jPanelGame,Maps maps,LinkedList<GamePlayer> gamePlayerLinkedList,CharacterInfoJPanel characterInfoJPanel) {
        this.jPanelGame = jPanelGame;
        this.maps =maps;
        this.characterInfoJPanel =characterInfoJPanel;
        this.gamePlayerLinkedList= gamePlayerLinkedList;
        for (int i=0;i<gamePlayerLinkedList.size();i++){
            gamePlayerLinkedList.get(i).addPlayerListener(this);
        }

        gamestateController =new GamestateController(jPanelGame,maps,gamePlayerLinkedList);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        gamestateController.nextGamestate();
    }

    @Override
    public void isPlayerMove(GamePlayer gamePlayer) {
        jPanelGame.disPlay();
        characterInfoJPanel.display();
    }

}
