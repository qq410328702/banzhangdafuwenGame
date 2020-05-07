import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Maps {
    private LinkedList<GamePlayer> gamePlayerLinkedList;
    private int dicecount;
    public int getDicecount() {
        return dicecount;
    }
    public Maps(LinkedList<GamePlayer> gamePlayerLinkedList) {
        this.gamePlayerLinkedList = gamePlayerLinkedList;
    }

    public void setDicecount(int dicecount) {
        this.dicecount = dicecount;
    }

    /**
     * 0代表 障碍物，1代表土地，2代表银行，3代表 医院，4 代表监狱
     */

    public int map[][] = Global.map;
    public int ownerBuildMap[][] = new int[map.length][map[0].length];
    public int buildingLvl[][] = new int[map.length][map[0].length];
    public int buildinghingerLvl[][] = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    /**
     * 得到医院坐标
     * @return
     */
    public Point hospitalPos (){
        Point hospitalpos = null;
        for (int i=0;i<map.length;i++){
            for (int j= 0;j<map[i].length;j++){
                if (map[i][j]==3){
                    hospitalpos = new Point(i,j);
                }
            }
        }
        return hospitalpos;
    }
    /**
     * 得到监狱坐标
     * @return
     */
    public Point prisonPos (){
        Point prisonPos = null;
        for (int i=0;i<map.length;i++){
            for (int j= 0;j<map[i].length;j++){
                if (map[i][j]==3){
                    prisonPos = new Point(i,j);
                }
            }
        }
        return prisonPos;
    }


    public int[][] getMap() {
        return map;
    }
    public  void shuiping(int x,int y){
        if (x>=0&&x<=map.length-1&&y>=0&&y<=map[0].length-1){
            if (x-1>=0&&x+1<=map.length-1){
                if ((map[x][y-1]==0&&map[x-1][y-1]==0&&map[x+1][y-1]==0)||
                        (map[x][y+1]==0&&map[x-1][y+1]==0&&map[x+1][y+1]==0)){
                    if (ownerBuildMap[x][y]==ownerBuildMap[x-1][y]){
                        buildinghingerLvl[x-1][y]=2;
                        System.out.println("buildinghingerLvl[x][y]"+buildinghingerLvl[x][y]);
                    }
                    if (ownerBuildMap[x][y]==ownerBuildMap[x+1][y]){
                        buildinghingerLvl[x][y]=2;
                        System.out.println("buildinghingerLvl[x][y]"+buildinghingerLvl[x][y]);
                    }
                    if (ownerBuildMap[x][y]==ownerBuildMap[x-1][y]&&ownerBuildMap[x][y]==ownerBuildMap[x+1][y]){
                        buildinghingerLvl[x-1][y]=3;
                        System.out.println("buildinghingerLvl[x][y]"+buildinghingerLvl[x][y]);
                    }
                }
                System.out.println("buildinghingerLvl[x][y]"+buildinghingerLvl[x][y]);
            }
        }
    }
    public  void chuizhi(int x,int y){
          if (x>=0&&x<=map.length-1&&y>0&&y<=10){
            if(x-1<0){
                if (map[x+1][y-1]==0&&map[x+1][y+1]==0&&map[x+1][y]==0){
                    if (ownerBuildMap[x][y]==ownerBuildMap[x][y-1]){
                        buildinghingerLvl[x][y-1]=2;
                        System.out.println("buildinghingerLvl[x][y-1]"+buildinghingerLvl[x][y-1]);
                    }else if (ownerBuildMap[x][y]==ownerBuildMap[x][y-1]&&ownerBuildMap[x][y]==ownerBuildMap[x][y+1]){
                        buildinghingerLvl[x][y-1]=3;
                        System.out.println("buildinghingerLvl[x][y-1]"+buildinghingerLvl[x][y-1]);
                    }else if (ownerBuildMap[x][y]==ownerBuildMap[x][y+1]){
                        buildinghingerLvl[x][y+1]=2;
                        System.out.println("buildinghingerLvl[x][y+1]"+buildinghingerLvl[x][y+1]);
                    }
                }
            }else if (x+1>map.length-1){
                if (map[x-1][y]==0&&map[x-1][y-1]==0&&map[x-1][y+1]==0){
                    if (ownerBuildMap[x][y]==ownerBuildMap[x][y-1]){
                        buildinghingerLvl[x][y-1]=2;
                        System.out.println("buildinghingerLvl[x][y-1]"+buildinghingerLvl[x][y-1]);
                    }else if (ownerBuildMap[x][y]==ownerBuildMap[x][y-1]&&ownerBuildMap[x][y]==ownerBuildMap[x][y+1]){
                        buildinghingerLvl[x][y-1]=3;
                        System.out.println("buildinghingerLvl[x][y-1]"+buildinghingerLvl[x][y-1]);
                    }else if (ownerBuildMap[x][y]==ownerBuildMap[x][y+1]){
                        buildinghingerLvl[x][y+1]=2;
                        System.out.println("buildinghingerLvl[x][y+1]"+buildinghingerLvl[x][y+1]);
                    }

                }
            }else if (x-1>=0&&x+1<=map.length-1){
                if ((map[x-1][y]==0&&map[x-1][y-1]==0&&map[x-1][y+1]==0)||
                        (map[x+1][y-1]==0&&map[x+1][y+1]==0&&map[x+1][y]==0)){
                    if (ownerBuildMap[x][y]==ownerBuildMap[x][y-1]){
                        buildinghingerLvl[x][y-1]=2;
                        System.out.println("buildinghingerLvl[x][y-1]"+buildinghingerLvl[x][y-1]);
                    }else if (ownerBuildMap[x][y]==ownerBuildMap[x][y-1]&&ownerBuildMap[x][y]==ownerBuildMap[x][y+1]){
                        buildinghingerLvl[x][y-1]=3;
                        System.out.println("buildinghingerLvl[x][y-1]"+buildinghingerLvl[x][y-1]);
                    }else if (ownerBuildMap[x][y]==ownerBuildMap[x][y+1]){
                        buildinghingerLvl[x][y+1]=2;
                        System.out.println("buildinghingerLvl[x][y+1]"+buildinghingerLvl[x][y+1]);
                    }

                }

            }
//            if (y-1>=0&&y+1<=10){
//                if ((map[x-1][y]==0&&map[x-1][y-1]==0&&map[x-1][y+1]==0)||
//                        (map[x+1][y-1]==0&&map[x+1][y+1]==0&&map[x+1][y]==0)){
//                    if (ownerBuildMap[x][y]==ownerBuildMap[x][y-1]){
//                        buildinghingerLvl[x][y-1]=2;
//                        System.out.println("buildinghingerLvl[x][y-1]"+buildinghingerLvl[x][y-1]);
//                    }else if (ownerBuildMap[x][y]==ownerBuildMap[x][y-1]&&ownerBuildMap[x][y]==ownerBuildMap[x][y+1]){
//                        buildinghingerLvl[x][y-1]=3;
//                        System.out.println("buildinghingerLvl[x][y-1]"+buildinghingerLvl[x][y-1]);
//                    }else if (ownerBuildMap[x][y]==ownerBuildMap[x][y+1]){
//                        buildinghingerLvl[x][y+1]=2;
//                        System.out.println("buildinghingerLvl[x][y+1]"+buildinghingerLvl[x][y+1]);
//                    }
//
//                }
//            }
        }
    }

    public int mapsWidth = map[0].length*Global.CELL_SIZE;
    public  int mapsHight = map.length*Global.CELL_SIZE+35;//+Global.CELL_SIZE;
    public int mapsX;
    public int mapsY;
    public  int stageWidth = Global.GAMEWIDTH;
    public  int stageHight = Global.GAMEHEIGHT;
    private int CURRENTPLAYER;
    private int CURRENTPLAYERSTATS;

    public int getCURRENTPLAYERSTATS() {
        return CURRENTPLAYERSTATS;
    }

    public void setCURRENTPLAYERSTATS(int CURRENTPLAYERSTATS) {
        this.CURRENTPLAYERSTATS = CURRENTPLAYERSTATS;
    }

    public int getCURRENTPLAYER() {
        return CURRENTPLAYER;
    }

    public void setCURRENTPLAYER(int CURRENTPLAYER) {
        this.CURRENTPLAYER = CURRENTPLAYER;
    }

    public void drawUI(Graphics g){
        if (getCURRENTPLAYERSTATS()==1){
            g.setColor(Color.ORANGE);
            g.fillRect(0,stageHight-Global.CELL_SIZE-30,stageWidth,Global.CELL_SIZE);
            Image image1 =Toolkit.getDefaultToolkit().getImage("images/大富翁素材/前进.png");
            Image image2 =Toolkit.getDefaultToolkit().getImage("images/大富翁素材/卡片.png");
            Image image3 =Toolkit.getDefaultToolkit().getImage("images/大富翁素材/查询.png");
            Image image4 =Toolkit.getDefaultToolkit().getImage("images/大富翁素材/股票.png");
            g.drawImage(image1,0*3*Global.CELL_SIZE,stageHight-Global.CELL_SIZE-30,3*Global.CELL_SIZE,Global.CELL_SIZE,null);
            g.drawImage(image2,1*3*Global.CELL_SIZE,stageHight-Global.CELL_SIZE-30,3*Global.CELL_SIZE,Global.CELL_SIZE,null);
            g.drawImage(image3,2*3*Global.CELL_SIZE,stageHight-Global.CELL_SIZE-30,3*Global.CELL_SIZE,Global.CELL_SIZE,null);
            g.drawImage(image4,3*3*Global.CELL_SIZE,stageHight-Global.CELL_SIZE-30,3*Global.CELL_SIZE,Global.CELL_SIZE,null);
        }else if (getCURRENTPLAYERSTATS()==2){

            g.setColor(Color.blue);
            g.fillRect(0,stageHight-Global.CELL_SIZE-30,stageWidth,Global.CELL_SIZE);
        }



    }


    public void drawMe(Graphics g) {

        /**
         * 背景颜色
         */
        g.setColor(Color.pink);
        g.fillRect(0,0,mapsWidth,mapsHight);
        if (gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()*Global.CELL_SIZE<=stageHight/2){
            mapsY=0;
        }else if (gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()*Global.CELL_SIZE>=mapsHight-stageHight/2){
            mapsY= stageHight-mapsHight;
        }else {
            mapsY= stageHight/2-gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()*Global.CELL_SIZE;
        }
        if (gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()*Global.CELL_SIZE<=stageWidth/2){
            mapsX=0;
        }else if (gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()*Global.CELL_SIZE>=mapsWidth-stageWidth/2){
            mapsX= stageWidth-mapsWidth;
        }else {
            mapsX= stageWidth/2-gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()*Global.CELL_SIZE;
        }

        Image image =Toolkit.getDefaultToolkit().getImage("images/building.png");
        Image image1 = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/一级房子.png");
        Image image2 = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/科技大楼1.png");
        Image image3 = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/城市高层建筑素材.png");
        Image image4 = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/城市高层素材.png");

        for (int i=0;i<map.length;i++){
            for (int j= 0;j<map[i].length;j++)
                if (map[i][j]==1){
                     if (ownerBuildMap[i][j]==0){
                        g.setColor(Color.WHITE);
                     }
                     for (int own =0;own<gamePlayerLinkedList.size();own++){
                         if (ownerBuildMap[i][j]==own+1){
                             g.setColor(gamePlayerLinkedList.get(own).getColor());
                         }
                     }
                    g.drawImage(image,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE*buildinghingerLvl[i][j],Global.CELL_SIZE*buildinghingerLvl[i][j],null);
                    g.fill3DRect(mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE*buildinghingerLvl[i][j],Global.CELL_SIZE*buildinghingerLvl[i][j],true);
                     if (buildingLvl[i][j]==1){
                         g.drawImage(image1,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE*buildinghingerLvl[i][j],Global.CELL_SIZE*buildinghingerLvl[i][j],null);
                     }
                    if (buildingLvl[i][j]==2){
                        g.drawImage(image2,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE*buildinghingerLvl[i][j],Global.CELL_SIZE*buildinghingerLvl[i][j],null);
                    }
                    if (buildingLvl[i][j]==3){
                        g.drawImage(image3,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE*buildinghingerLvl[i][j],Global.CELL_SIZE*buildinghingerLvl[i][j],null);
                    }
                    if (buildingLvl[i][j]==4){
                        g.drawImage(image4,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE*buildinghingerLvl[i][j],Global.CELL_SIZE*buildinghingerLvl[i][j],null);
                    }
                }else if (map[i][j]==2){
                    Image bank  = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/银行.png");

                    g.drawImage(bank,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,null);
                }
                else if (map[i][j]==3){
                    Image bank  = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/医院.png");

                    g.drawImage(bank,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,null);
                }
                else if (map[i][j]==4){
                    Image bank  = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/监牢.png");

                    g.drawImage(bank,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,null);
                }
                else if (map[i][j]==5){

                    int newsRandom = new Random().nextInt(5);
                    switch (newsRandom){
                        case 0:
                            Image bank01  = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/new01.png");
                            g.drawImage(bank01,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,null);
                            break;
                        case 1:
                            Image bank02  = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/new02.png");
                            g.drawImage(bank02,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,null);
                            break;
                        case 2:
                            Image bank03  = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/new03.png");
                            g.drawImage(bank03,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,null);
                            break;
                        case 3:
                            Image bank04  = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/new04.png");
                            g.drawImage(bank04,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,null);
                            break;
                        case 4:
                            Image bank05  = Toolkit.getDefaultToolkit().getImage("images/大富翁素材/new05.png");
                            g.drawImage(bank05,mapsX+j*Global.CELL_SIZE,mapsY+i*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE,null);
                            break;
                    }



                }
        }

        //drawUI(g);
    }

}
