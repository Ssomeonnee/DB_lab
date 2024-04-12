package L5GUI;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PandaAction extends JFrame {

    protected String path;
    protected JLabel gif;
    protected Timer timer;

    public PandaAction(String path, String action)
    {
        super(action);
        this.path = path;
        windowInit();
        componentsInitAndLocate();

        this.setVisible(true);
    }
    private void windowInit()
    {
        this.setSize(450,320);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setResizable(false);
    }
    private void componentsInitAndLocate()
    {
        gif = new JLabel(new ImageIcon(path));
        this.add(gif);

        timer = new Timer(7000, e-> this.dispose());

       this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                timer.start();
            }
       });
    }
}
