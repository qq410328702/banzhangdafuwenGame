import java.awt.*;
import java.util.Random;

public class Gods {

    public Gods(int type) {
        this.type = type;
        setGodsPostion();
        System.out.println("postionX"+postionX);
        System.out.println("postionY"+postionY);
    }

    private int type ;
    private int postionX;
    private int postionY;
    private int[][] maps = Global.map;


    public Point  getRandomPostion(){
        Point randomPostion =null;
        int x;
        int y;
        do {
             x= new Random().nextInt(maps.length);
            y = new Random().nextInt(maps[0].length);
        }while (maps[x][y]==0);
        randomPostion = new Point(x,y);
        return randomPostion;
    }
    public void setGodsPostion(){
        Point newPostion = getRandomPostion();
        postionX = newPostion.x;
        postionY =newPostion.y;
    }
    Image godImage =null;
    public void drawMe(Graphics g){

        switch (type){
            case 0:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/土地公公.gif");
                break;
            case 1:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/大福神.gif");
                break;
            case 2:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/大穷神.gif");
                break;
            case 3:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/大财神.gif");
                break;
            case 4:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/天使.gif");
                break;
            case 5:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/恶魔.gif");
                break;
            case 6:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/糊涂神.gif");
                break;
            case 7:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/大衰神.gif");
                break;
        }

        g.drawImage(godImage,postionX*Global.CELL_SIZE,postionY*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,null);
    }

    public void drawMe(Graphics g, int mapsX, int mapsY) {

        switch (type){
            case 0:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/土地公公.gif");
                break;
            case 1:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/大福神.gif");
                break;
            case 2:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/大穷神.gif");
                break;
            case 3:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/大财神.gif");
                break;
            case 4:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/天使.gif");
                break;
            case 5:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/恶魔.gif");
                break;
            case 6:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/糊涂神.gif");
                break;
            case 7:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/大衰神.gif");
                break;
            case 8:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/小福神.gif");
                break;
            case 9:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/小穷神.gif");
                break;
            case 10:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/小衰神.gif");
                break;
            case 11:
                godImage =Toolkit.getDefaultToolkit().getImage("images/神/小财神.gif");
                break;
        }

        g.drawImage(godImage,mapsX+postionY*Global.CELL_SIZE,mapsY+postionX*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,null);
    }
}
