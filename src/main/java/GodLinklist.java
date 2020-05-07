import java.awt.*;
import java.util.LinkedList;

public class GodLinklist {
    public LinkedList<Gods> godsLinkedList = new LinkedList<>();

    public GodLinklist() {
        godsLinkedList.add(new Gods(0));
        godsLinkedList.add(new Gods(1));
        godsLinkedList.add(new Gods(2));
        godsLinkedList.add(new Gods(3));
        godsLinkedList.add(new Gods(4));
        godsLinkedList.add(new Gods(5));
        godsLinkedList.add(new Gods(6));
        godsLinkedList.add(new Gods(7));
        godsLinkedList.add(new Gods(8));
        godsLinkedList.add(new Gods(9));
        godsLinkedList.add(new Gods(10));
        godsLinkedList.add(new Gods(11));

    }

    public void drawMe(Graphics g){
        for (int i= 0;i<godsLinkedList.size();i++){
            godsLinkedList.get(i).drawMe(g);
        }

    }

    public void drawMe(Graphics g, int mapsX, int mapsY) {
        for (int i= 0;i<godsLinkedList.size();i++){
            godsLinkedList.get(i).drawMe(g,mapsX,mapsY);
        }
    }
}
