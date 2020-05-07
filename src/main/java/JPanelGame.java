import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class JPanelGame extends JPanel implements Runnable{

    private Thread thread = new Thread(this);
    private Maps maps;
    LinkedList<GamePlayer> gamePlayerLinkedList;
    private Dice dice =new Dice();
    public Boolean aBoolean = true;
    public boolean isCanBuy =false;
    public boolean isBankVisible =false;
    public boolean isShowTips =false;
    public JPanelGame(Maps maps,LinkedList<GamePlayer> gamePlayerLinkedList) {
        this.setLayout(null);
        this.maps=maps;
        this.gamePlayerLinkedList = gamePlayerLinkedList;
        this.setBackground(Color.gray);
        this.setBounds(0,0,maps.mapsWidth,maps.mapsHight);
        this.add(dice);
        thread.start();
    }
    JPanel selectJpanel = new JPanel();
    JRadioButton radioOne = new JRadioButton();
    JRadioButton radioTwo = new JRadioButton();
    JButton jButton = new JButton();
    JLabel jLabel = new JLabel();

    /**
     * 消息提示界面
     */
    JPanel showTipsJPane = new JPanel();
    public JLabel showTipsJLabel = new JLabel();
    public JLabel showtipsPicJLabel = new JLabel();
    {
        showTipsJPane.setLayout(null);
        showTipsJPane.setBackground(Color.orange);
        showTipsJPane.setForeground(Color.blue);

        showTipsJPane.setBounds(5*Global.CELL_SIZE,1*Global.CELL_SIZE,4*Global.CELL_SIZE,5*Global.CELL_SIZE);

        Font font = new Font("宋体",Font.BOLD,25);
        showTipsJLabel.setFont(font);
        showTipsJLabel.setBounds(0*Global.CELL_SIZE,4*Global.CELL_SIZE,5*Global.CELL_SIZE,Global.CELL_SIZE);
        showTipsJLabel.setAlignmentX(CENTER_ALIGNMENT);
        showtipsPicJLabel.setAlignmentX(CENTER_ALIGNMENT);
        showtipsPicJLabel.setBounds(0,0,4*Global.CELL_SIZE,4*Global.CELL_SIZE);
        showTipsJPane.add(showTipsJLabel);
        showTipsJPane.add(showtipsPicJLabel);
        this.add(showTipsJPane);
    }
    //静态代码块
    {


        ButtonGroup button = new ButtonGroup();
        selectJpanel.setLayout(null);
        selectJpanel.setBackground(Color.MAGENTA);
        selectJpanel.setBounds(5*Global.CELL_SIZE,3*Global.CELL_SIZE,5*Global.CELL_SIZE,Global.CELL_SIZE);
        Font font = new Font("宋体",Font.BOLD,40);

        radioOne.setEnabled(true);
        radioOne.setSelected(true);

        radioTwo.setEnabled(true);
        jLabel.setText("是否要购买土地");
        jLabel.setFont(font);
        button.add(radioOne);
        button.add(radioTwo);
        jLabel.setBackground(Color.ORANGE);
        jLabel.setBounds(0,0,3*Global.CELL_SIZE,Global.CELL_SIZE);
        radioTwo.setBounds(4*Global.CELL_SIZE,0,Global.CELL_SIZE,Global.CELL_SIZE);
        radioOne.setBounds(3*Global.CELL_SIZE,0,Global.CELL_SIZE,Global.CELL_SIZE);
        jButton.setBounds(5*Global.CELL_SIZE,0,Global.CELL_SIZE,Global.CELL_SIZE);
        Font font1 = new Font("宋体",Font.BOLD,50);
        ImageIcon imageIcon1 = new ImageIcon("images/大富翁素材/是.png");
        //radioOne.setIcon(imageIcon1);
        radioOne.setBackground(Color.ORANGE);
        radioOne.setFont(font1);
        ImageIcon imageIcon2 = new ImageIcon("images/大富翁素材/否.png");
        //radioTwo.setIcon(imageIcon2);
        radioTwo.setBackground(Color.ORANGE);
        radioTwo.setFont(font1);
        radioOne.setText("是");
        radioTwo.setText("否");
        System.out.print("静态代码块！-->");
        selectJpanel.add(jLabel);
        selectJpanel.add(radioOne);
        selectJpanel.add(radioTwo);
        this.add(selectJpanel);
        //this.add(jButton);
    }
    //银行界面
    JPanel bankJPanel = new JPanel();
    JLabel jLabel1 =new JLabel();

    JSlider jSlider = new JSlider(0,10000);
    {

        bankJPanel.setLayout(null);
        bankJPanel.setBackground(Color.MAGENTA);
        bankJPanel.setBounds(5*Global.CELL_SIZE,3*Global.CELL_SIZE,5*Global.CELL_SIZE,Global.CELL_SIZE);

        jSlider.setBounds(0,0,5*Global.CELL_SIZE,Global.CELL_SIZE/2);


        jLabel1.setBounds(0,Global.CELL_SIZE/2,5*Global.CELL_SIZE,Global.CELL_SIZE/2);
        jLabel1.setText("现金："+String.valueOf(jSlider.getValue())+"    存款："+String.valueOf(jSlider.getMaximum()-jSlider.getValue()));
        bankJPanel.add(jSlider);
        bankJPanel.add(jLabel1);



        this.add(bankJPanel);

    }
    public void disPlay(){
        if (isBankVisible==false){
            bankJPanel.setVisible(false);
        }else  if (isBankVisible==true){
            bankJPanel.setVisible(true);
            int maxValue = gamePlayerLinkedList.get(maps.getCURRENTPLAYER()).getMoney()+gamePlayerLinkedList.get(maps.getCURRENTPLAYER()).getBankMoney();
            jSlider.setMaximum(maxValue);
            jLabel1.setText("现金："+String.valueOf(jSlider.getValue())+"    存款："+String.valueOf(jSlider.getMaximum()-jSlider.getValue()));
            gamePlayerLinkedList.get(maps.getCURRENTPLAYER()).setMoney(jSlider.getValue());
            gamePlayerLinkedList.get(maps.getCURRENTPLAYER()).setBankMoney(maxValue-jSlider.getValue());

        }
        if (isShowTips){
            showTipsJPane.setVisible(true);
        }else {
            showTipsJPane.setVisible(false);
        }

        if (isCanBuy==false){
            selectJpanel.setVisible(false);
        }else {
            selectJpanel.setVisible(true);
        }
        aBoolean = radioOne.isSelected();

        repaint();
    }

    /**
     * 各种神的绘制
     * @param g
     */
    public GodLinklist godLinklist = new GodLinklist();

    @Override
    protected void paintComponent(Graphics g) {

        //g.setColor(Color.red);
        maps.getCURRENTPLAYER();

        maps.drawMe(g);
        godLinklist.drawMe(g,maps.mapsX,maps.mapsY);
        for (int i=0;i<gamePlayerLinkedList.size();i++){
            gamePlayerLinkedList.get(i).drawMe(g,maps);
        }
        //dice.setNumber(maps.getDicecount());
        dice.setNumber(gamePlayerLinkedList.get(maps.getCURRENTPLAYER()).getMoveStep());
        dice.drawMe(g);

    }

    @Override
    public void run() {
        while (true){
            disPlay();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
