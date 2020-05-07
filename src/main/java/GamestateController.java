import javax.swing.*;
import java.util.LinkedList;
import java.util.Random;

public class GamestateController {
    LinkedList<GamePlayer> gamePlayerLinkedList;
    private Maps maps;
    private JPanelGame jPanelGame;
    private int CURRENTPLAYER;
    private int CURRENTPLAYERSTATS;
    private int ALLPLAYERSTATS=3;

    public int getCURRENTPLAYERSTATS() {
        return CURRENTPLAYERSTATS;
    }

    public void setCURRENTPLAYERSTATS(int CURRENTPLAYERSTATS) {
        this.CURRENTPLAYERSTATS = CURRENTPLAYERSTATS;
    }

    public int getALLPLAYERSTATS() {
        return ALLPLAYERSTATS;
    }

    public void setALLPLAYERSTATS(int ALLPLAYERSTATS) {
        this.ALLPLAYERSTATS = ALLPLAYERSTATS;
    }

    public int getCURRENTPLAYER() {
        return CURRENTPLAYER;
    }

    public void setCURRENTPLAYER(int CURRENTPLAYER) {
        this.CURRENTPLAYER = CURRENTPLAYER;
    }

    public GamestateController(JPanelGame jPanelGame, Maps maps, LinkedList<GamePlayer> gamePlayerLinkedList) {
        this.gamePlayerLinkedList=gamePlayerLinkedList;
        this.jPanelGame = jPanelGame;
        this.maps =maps;
    }
    private boolean isCanDice(){
        int number =0;
        for (int i=0;i<gamePlayerLinkedList.size();i++){
            number += gamePlayerLinkedList.get(i).getMoveStep();
        }
        if (number==0) {
            return true;
        }
        return  false;
    }



    public  void  nextGamestate(){
        ImageIcon showTipsBlankIcon = new ImageIcon("images/event/bg.png");
        jPanelGame.isShowTips=false;
        jPanelGame.showTipsJLabel.setText("");
        jPanelGame.showtipsPicJLabel.setIcon(showTipsBlankIcon);

        //System.out.println("getCURRENTPLAYER()"+getCURRENTPLAYER());
        //System.out.println("getCURRENTPLAYERSTATS()"+getCURRENTPLAYERSTATS());
            if (isCanDice()) {
                if (getCURRENTPLAYERSTATS()==getALLPLAYERSTATS()){
                    setCURRENTPLAYER((getCURRENTPLAYER()+1)%gamePlayerLinkedList.size());
                    setCURRENTPLAYERSTATS(1);
                }else {
                setCURRENTPLAYERSTATS(getCURRENTPLAYERSTATS()+1);
            }
                maps.setCURRENTPLAYER(getCURRENTPLAYER());
                maps.setCURRENTPLAYERSTATS(getCURRENTPLAYERSTATS());
//            if (gamePlayerLinkedList.get(getCURRENTPLAYER()).getInHospital()>0){
//                gamePlayerLinkedList.get(getCURRENTPLAYER()).setInHospital(gamePlayerLinkedList.get(getCURRENTPLAYER()).getInHospital()-1);
//                setCURRENTPLAYERSTATS(getCURRENTPLAYERSTATS()+1);
//                return;
//            }
//
//            if (gamePlayerLinkedList.get(getCURRENTPLAYER()).getInPrison()>0){
//                gamePlayerLinkedList.get(getCURRENTPLAYER()).setInPrison(gamePlayerLinkedList.get(getCURRENTPLAYER()).getInPrison()-1);
//                setCURRENTPLAYERSTATS(getCURRENTPLAYERSTATS()+1);
//                return;
//            }


//            if (gamePlayerLinkedList.get(getCURRENTPLAYER()).getNOWGAMESTATES() + 1 == gamePlayerLinkedList.get(getCURRENTPLAYER()).getGAMEALLSTEP()) {
//                gamePlayerLinkedList.get((getCURRENTPLAYER() + 1) % gamePlayerLinkedList.size()).setNOWGAMESTATES(1);
//            } else {
//                gamePlayerLinkedList.get(getCURRENTPLAYER()).setNOWGAMESTATES(gamePlayerLinkedList.get(getCURRENTPLAYER()).getNOWGAMESTATES() + 1);
//            }
//            System.out.println("gamePlayerLinkedList.get(getCURRENTPLAYER()).getNOWGAMESTATES()"+gamePlayerLinkedList.get(getCURRENTPLAYER()).getNOWGAMESTATES());
            Random random = new Random();
            int tax;
            switch (getCURRENTPLAYERSTATS()) {
                case GamePlayer.PLAYERMOVE:
                    if (gamePlayerLinkedList.get(getCURRENTPLAYER()).getInHospital()>0){
                        gamePlayerLinkedList.get(getCURRENTPLAYER()).setInHospital(gamePlayerLinkedList.get(getCURRENTPLAYER()).getInHospital()-1);

                        gamePlayerLinkedList.get(getCURRENTPLAYER()).setX(maps.hospitalPos().x);
                        gamePlayerLinkedList.get(getCURRENTPLAYER()).setY(maps.hospitalPos().y);

                        return;
                    }

                    if (gamePlayerLinkedList.get(getCURRENTPLAYER()).getInPrison()>0){
                        gamePlayerLinkedList.get(getCURRENTPLAYER()).setInPrison(gamePlayerLinkedList.get(getCURRENTPLAYER()).getInPrison()-1);
                        gamePlayerLinkedList.get(getCURRENTPLAYER()).setX(maps.prisonPos().x);
                        gamePlayerLinkedList.get(getCURRENTPLAYER()).setY(maps.prisonPos().y);

                        return;
                    }

                    jPanelGame.isCanBuy =false;
                    gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoveStep(random.nextInt(12)+1);
                    maps.setDicecount(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoveStep());
                    System.out.println(maps.getDicecount());
                    break;

                case GamePlayer.JUGEEVENT:
                    if (maps.map[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()]
                            [gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()] == 2) {
                        jPanelGame.isBankVisible =true;

                    }
                    if (maps.map[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()]
                            [gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()] == 1&&
                            maps.ownerBuildMap[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()]
                                    [gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()]==0) {
                        jPanelGame.isCanBuy = true;
                    }
                    if (maps.map[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()]
                            [gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()] == 3) {

                        jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+"住院中还有"+
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).getInHospital()+"天");
                        jPanelGame.isShowTips =true;

                    }
                    if (maps.map[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()]
                            [gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()] == 4) {
                        jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+"坐牢中还有"+
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).getInPrison()+"天");
                        jPanelGame.isShowTips =true;

                    }

                    if (maps.map[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()]
                            [gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()] == 5) {
                        int newsRandom = new Random().nextInt(13);
                        switch (newsRandom){
                            case 0:
                                ImageIcon newsRandom01 = new ImageIcon("images/event/bad_hospital_3.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom01);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setInHospital(3);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":住院3天!");
                                break;
                            case 1:
                                ImageIcon newsRandom02 = new ImageIcon("images/event/bad_lose300.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom02);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()-300);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":罚款300");
                                break;
                            case 2:
                                ImageIcon newsRandom03 = new ImageIcon("images/event/bad_lose300_01.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom03);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()-300);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":罚款300!");
                                break;
                            case 3:
                                ImageIcon newsRandom04 = new ImageIcon("images/event/bad_lose400.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom04);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()-400);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":丢失400!");
                                break;
                            case 4:
                                ImageIcon newsRandom05 = new ImageIcon("images/event/bad_lose500.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom05);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()-500);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":丢失500！");
                                break;
                            case 5:
                                ImageIcon newsRandom06 = new ImageIcon("images/event/bad_lose1000_01.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom06);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()-1000);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":丢失1000！");
                                break;
                            case 6:
                                ImageIcon newsRandom07 = new ImageIcon("images/event/bad_lose1500.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom07);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()-1500);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":丢失1500！");
                                break;
                            case 7:
                                ImageIcon newsRandom08 = new ImageIcon("images/event/bad_lose2000.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom08);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()-2000);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":丢失2000！");
                                break;
                            case 8:
                                ImageIcon newsRandom09 = new ImageIcon("images/event/bad_lose3000.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom09);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()-3000);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":丢失3000！");
                                break;
                            case 9:
                                ImageIcon newsRandom10 = new ImageIcon("images/event/luck_gain1000.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom10);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()+1000);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":获得1000！");
                                break;
                            case 10:
                                ImageIcon newsRandom11 = new ImageIcon("images/event/luck_gain2000.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom11);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()+2000);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":获得2000！");
                                break;
                            case 11:
                                ImageIcon newsRandom12 = new ImageIcon("images/event/luck_gain2000_01.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom12);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()+2000);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":获得2000！");
                                break;
                            case 12:
                                ImageIcon newsRandom13 = new ImageIcon("images/event/luck_gain3999_100.jpg");
                                jPanelGame.showtipsPicJLabel.setIcon(newsRandom13);
                                gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()+3999);
                                jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+":获得3999！");
                                break;
                        }

                        jPanelGame.isShowTips =true;
                    }

                    break;

                case GamePlayer.PLAYERBUYBUILD:
                    if (maps.map[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()]
                            [gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()] == 5) {
                        jPanelGame.isShowTips =false;
                        return;
                    }
                    if (maps.map[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()]
                            [gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()] == 3||
                            maps.map[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()]
                                    [gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()] == 4) {
                        jPanelGame.isShowTips =false;
                        return;
                    }
                    if (jPanelGame.isBankVisible==true){
                        jPanelGame.isBankVisible=false;
                    }
                    if (jPanelGame.isCanBuy =true){
                        jPanelGame.isCanBuy =false;
                    }
                    if (maps.ownerBuildMap[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()][gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()]==0){
                        if (jPanelGame.aBoolean ==false){
                            return;
                        }

                        if (maps.map[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()]
                                [gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()] == 1){
                            jPanelGame.isShowTips =true;
                            maps.ownerBuildMap[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()][gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()]=getCURRENTPLAYER()+1;
                            jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+"花费2000购买土地");
                            gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()-2000);
                            maps.buildingLvl[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()][gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()]=1;


                        }

                        }
                        if (maps.ownerBuildMap[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()][gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()]!=0&&
                                maps.ownerBuildMap[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()][gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()]!=getCURRENTPLAYER()+1){
                            tax =random.nextInt(1000)*maps.buildingLvl[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()][gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()];
                            //JOptionPane.showMessageDialog(jPanelGame,gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+"向"+gamePlayerLinkedList.get(maps.ownerBuildMap[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()][gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()]-1).getName()+"付了"+tax+"住宿费");
                            jPanelGame.isShowTips =true;
                            jPanelGame.showTipsJLabel.setText(gamePlayerLinkedList.get(getCURRENTPLAYER()).getName()+"向"+gamePlayerLinkedList.get(maps.ownerBuildMap[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()][gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()]-1).getName()+"付了"+tax+"住宿费");
                            gamePlayerLinkedList.get(getCURRENTPLAYER()).setMoney(gamePlayerLinkedList.get(getCURRENTPLAYER()).getMoney()-tax);
                            gamePlayerLinkedList.get(maps.ownerBuildMap[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()][gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()]-1).setMoney(
                                    gamePlayerLinkedList.get(maps.ownerBuildMap[gamePlayerLinkedList.get(getCURRENTPLAYER()).getX()][gamePlayerLinkedList.get(getCURRENTPLAYER()).getY()]-1).getMoney()+tax);
                        }


            }
        }
    }
}
