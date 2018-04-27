import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Test extends JPanel
{
    private String ret;
    public Test()
    {
        ret = "";
        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("keyPressed="+KeyEvent.getKeyText(e.getKeyCode()));
                ret = KeyEvent.getKeyText(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("keyReleased="+KeyEvent.getKeyText(e.getKeyCode()));
            }
        };
        addKeyListener(listener);
        setFocusable(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mini Tennis");
        Test keyboardExample = new Test();
        frame.add(keyboardExample);
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public String retString()
    {
        return ret;
    }
}