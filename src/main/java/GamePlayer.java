import java.awt.*;

public class GamePlayer {
    /**
     * 在医院时间和监狱时间
     */
    private int inHospital =0;
    private int inPrison = 0;

    public int getInHospital() {
        return inHospital;
    }

    public void setInHospital(int inHospital) {
        this.inHospital = inHospital;
    }

    public int getInPrison() {
        return inPrison;
    }

    public void setInPrison(int inPrison) {
        this.inPrison = inPrison;
    }

    public static final int PLAYERMOVE= 1;
    public static final int JUGEEVENT= 2;
    public static final int PLAYERBUYBUILD= 3;
    private String name ;
    private int money ;
    private int bankMoney;
    public int getBankMoney() {
        return bankMoney;
    }
    public void setBankMoney(int bankMoney) {
        this.bankMoney = bankMoney;
    }
    private String imagePath;
    private Color color;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Color getColor() {
        return color;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public GamePlayer(String name, int money, int x, int y,Color color, String imagePath) {
        this.name = name;
        this.money = money;
        this.x = x;
        this.y = y;
        this.color =color;
        this.imagePath= imagePath;
        new Thread(new PlayerDriver()).start();
    }

    public int map[][] = Global.map;
    static int N = 1;//北
    static int S = -1;//南
    static int W= -2;//西
    static int E =2;//东
    private int moveStep =0;
    protected int x, y;
    protected int nowDirection =E;
    protected int futureDirection =E;
    protected int tempposX;
    protected int tempposY;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private PlayerListener playerListener;
    public void addPlayerListener(PlayerListener playerListener){
        if (playerListener!=null)
            this.playerListener =playerListener;
    }
    public void setMoveStep(int moveStep) {
        this.moveStep = moveStep;
    }

    public int getMoveStep() {
        return moveStep;
    }

    /**
     * 自动寻路系统
     */
    public  void wayPoint(){
        do {
            do {
                do {
                    int t = (int) (Math.random()*4);
                    switch (t){
                        case 0:
                            futureDirection = N;
                            break;
                        case 1:
                            futureDirection = S;
                            break;
                        case 2:
                            futureDirection = W;
                            break;
                        case 3:
                            futureDirection = E;
                            break;
                    }
                }while (nowDirection+futureDirection==0);
                tempposX =x;
                tempposY =y;
                if (futureDirection==1){
                    tempposY-=1;
                }
                if (futureDirection==-1){
                    tempposY+=1;
                }if (futureDirection==2){
                    tempposX+=1;
                }
                if (futureDirection==-2){
                    tempposX-=1;
                }
            }while (tempposX<0||tempposX>=map.length||tempposY<0||tempposY>=map[map.length-1].length);
        }while (map[tempposX][tempposY]==0);
        y = tempposY;
        x = tempposX;
//        System.out.println("正posX:"+x + "正posY:"+y+"正map[posX][posY]:"+map[x][y]);
        nowDirection =futureDirection;
        face =getFace();
    }
    public int facesouth =0;
    public int facewest =1;
    public int faceeast =2;
    public int facenorth =3;
    public  int face ;
    private  int playermovestats=0;
    public void updatePlayerMoveStats() {
        playermovestats = (playermovestats + 1) % 4;
    }
    /**
     * 逆时针旋转90度才是地图上显示的真正方向
     * @return
     */
    public  int getFace(){

        switch (futureDirection){
            case 1:
                face= facewest;
                break;
            case -1:
                face = faceeast;
                break;
            case -2:
                face =facenorth;
                break;
            case 2:
                face =facesouth;
                break;
        }
        return face;
    }

    /**
     * 画出画家
     * @param g
     * @param maps
     */
    public void drawMe(Graphics g,Maps maps) {
        //g.setColor(color);
        //Image image =Toolkit.getDefaultToolkit().getImage("images/6061630.png");
        //g.fillOval(maps.mapsX+y*Global.CELL_SIZE,maps.mapsY+x*Global.CELL_SIZE,Global.CELL_SIZE,Global.CELL_SIZE);
        Image image = Toolkit.getDefaultToolkit().getImage(imagePath);
        image.getHeight(null);
        image.getWidth(null);
        g.drawImage(image,y*Global.CELL_SIZE+maps.mapsX,x*Global.CELL_SIZE+maps.mapsY,
                (y+1)*Global.CELL_SIZE+maps.mapsX,(x+1)*Global.CELL_SIZE+maps.mapsY,
                image.getWidth(null)/4*(playermovestats),image.getHeight(null)/4*(face),
                image.getWidth(null)/4*(playermovestats+1),image.getHeight(null)/4*(face+1),null);
    }

    /**
     * 玩家移动线程
     */
    private class PlayerDriver implements Runnable{
        @Override
        public void run() {
            System.out.println(getMoveStep());
            while (true){
                for (int i=getMoveStep();i>0;i--){
                    setMoveStep(getMoveStep()-1);
                    wayPoint();
                    updatePlayerMoveStats();
                    if (playerListener!=null){
                        playerListener.isPlayerMove(GamePlayer.this);
                    }
                    MyThread myThread =new MyThread();
                    myThread.run();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
