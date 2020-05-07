import javax.swing.*;
import java.awt.*;

public class JFrameGame extends JFrame {
    public JFrameGame(){
        this.setLayout(null);
        this.setTitle("班长大富翁游戏");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(0,0,Global.GAMEWIDTH,Global.GAMEHEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setBackground(Color.gray);
        this.setFont(new Font("黑体", Font.BOLD, 40));
        /**
         * 更改标题的图标
         */
        Image image = new  ImageIcon("images/6061630.png").getImage();
        this.setIconImage(image);
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        }
//        catch (Exception e)
//        {
//
//        }
    }

}
