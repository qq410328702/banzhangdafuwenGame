import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class CharacterInfoJFrame extends JFrame implements Runnable, KeyListener {
    private LinkedList<GamePlayer> gamePlayerLinkedList;
    private CharacterInfoJPanel characterInfoJPanel;
    private  Thread thread = new Thread(this);
    private LinkedList<JLabel> jLabelLinkedList = new LinkedList<JLabel>();
    private LinkedList<JLabel> bankjLabelLinkedList = new LinkedList<JLabel>();
    public  JButton jButton = new JButton();
    public CharacterInfoJFrame(LinkedList<GamePlayer> gamePlayerLinkedList,CharacterInfoJPanel characterInfoJPanel) {

        this.addKeyListener(this);
        this.setLayout(null);

        this.gamePlayerLinkedList = gamePlayerLinkedList;
        this.characterInfoJPanel =characterInfoJPanel;
        this.setTitle("人物信息");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(0,0, 3*Global.CELL_SIZE, Global.GAMEHEIGHT);
 //       this.setLocationRelativeTo(null);
        this.setVisible(true);
        for (int i=0;i<gamePlayerLinkedList.size();i++) {
//            System.out.println("!!!!!!!!!!!!!!!!!!!!!");
            JButton jButton = new JButton();
            jButton.setBounds(0, i * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE);
            jButton.setBackground(gamePlayerLinkedList.get(i).getColor());
            Font font = new Font("宋体", Font.BOLD, 20);
            JLabel jLabel = new JLabel();
            jLabel.setFont(font);
            jLabel.setText(gamePlayerLinkedList.get(i).getName() + "金钱：" + gamePlayerLinkedList.get(i).getMoney());
            jLabel.setBounds(Global.CELL_SIZE, i * Global.CELL_SIZE, 3 * Global.CELL_SIZE, Global.CELL_SIZE/2);
            jLabel.setBackground(gamePlayerLinkedList.get(i).getColor());

            JLabel bankJLabel = new JLabel();
            bankJLabel.setFont(font);
            bankJLabel.setText("银行存款金：" + gamePlayerLinkedList.get(i).getBankMoney());
            bankJLabel.setBounds(Global.CELL_SIZE, i * Global.CELL_SIZE+Global.CELL_SIZE/2, 3 * Global.CELL_SIZE, Global.CELL_SIZE/2);
            bankJLabel.setBackground(gamePlayerLinkedList.get(i).getColor());
            this.add(jButton);
            this.add(jLabel);
            this.add(bankJLabel);
            jLabelLinkedList.add(jLabel);
            bankjLabelLinkedList.add(bankJLabel);
            //  this.add(characterInfoJPanel);

        }

        jButton.setBounds(0,4*Global.CELL_SIZE,3*Global.CELL_SIZE,Global.CELL_SIZE);
        ImageIcon imageIcon = new ImageIcon("images/大富翁素材/前进.png");
        jButton.setBackground(Color.ORANGE);
        jButton.setIcon(imageIcon);
        this.add(jButton);


        JButton cardBtn = new JButton();
        cardBtn.setBounds(0,5*Global.CELL_SIZE,3*Global.CELL_SIZE,Global.CELL_SIZE);
        ImageIcon cardImage = new ImageIcon("images/大富翁素材/卡片.png");
        cardBtn.setBackground(Color.ORANGE);
        cardBtn.setIcon(cardImage);
        this.add(cardBtn);
        CardController cardController = new CardController();
        cardBtn.addMouseListener(cardController);
        thread.start();
    }

    JTextArea jTextArea = new JTextArea(10,1);
    {

        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jScrollPane.setBounds(0,6*Global.CELL_SIZE,3*Global.CELL_SIZE,Global.CELL_SIZE);
        this.add(jScrollPane);
    }

    @Override
    public void run(){
        while (true){
            drawUI();
            try {
                Thread.sleep(1000);
//                jTextArea.append("111+/n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public  void  drawUI(){

        for (int i=0;i<gamePlayerLinkedList.size();i++){
 //           System.out.println("!!!!!!!!!!!!!!!!!!!!!");
            jLabelLinkedList.get(i).setText(gamePlayerLinkedList.get(i).getName()+"金钱："+gamePlayerLinkedList.get(i).getMoney());
            bankjLabelLinkedList.get(i).setText("银行存款金：" + gamePlayerLinkedList.get(i).getBankMoney());

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!");
        if (e.getKeyCode() == KeyEvent.VK_D){
            this.setVisible(false);

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
