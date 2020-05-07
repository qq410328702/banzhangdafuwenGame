import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CardController implements MouseListener {
    private  CardJFrame cardJFrame;

    public CardController() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (cardJFrame==null){
            cardJFrame = new CardJFrame();
        }
        if (cardJFrame.isVisible ==true){
            cardJFrame.setVisible(false);
            cardJFrame.isVisible =false;
        }else if (cardJFrame.isVisible ==false){
            cardJFrame.setVisible(true);
            cardJFrame.isVisible =true;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
