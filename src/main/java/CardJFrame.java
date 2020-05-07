import javax.swing.*;
import java.awt.*;

public class CardJFrame extends JFrame {
    public boolean isVisible =true;
    public CardJFrame() {
        this.setLayout(null);
        this.setTitle("卡片系统");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBounds(0,0,Global.GAMEWIDTH,Global.GAMEHEIGHT);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setBackground(Color.gray);
        this.setFont(new Font("黑体", Font.BOLD, 40));
    }
}
