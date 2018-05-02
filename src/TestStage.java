import mayflower.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;
public class TestStage extends Stage
{


    public TestStage()
    {
        Character c = new Character(50,50);
        addActor(c,50,50);
        addActor(c.retGun(),50,50);
    }
    public void update()
    {

    }
}
