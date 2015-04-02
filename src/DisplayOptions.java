import javax.swing.*;
import java.awt.*;


public class DisplayOptions
{
    private int screenWidth;

    private int screenHeight;

    private JFrame frame;

    public DisplayOptions()
    {
        GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice graphDev = graphEnv.getDefaultScreenDevice();

        if (!graphDev.isFullScreenSupported())
            MainWindow.error("Full screen is not supported.");

        DisplayMode displayMode = graphDev.getDisplayMode();

        screenWidth = displayMode.getWidth();
        screenHeight = displayMode.getHeight();

        frame = createFrame();
        graphDev.setFullScreenWindow(frame);

        if (!graphDev.isDisplayChangeSupported())
            MainWindow.error("Display change is not supported.");

        graphDev.setDisplayMode(displayMode);
    }

    public void add(DrawManager p)
    {
        frame.add(p);
    }

    public void setVisible(boolean visible)
    {
        frame.setVisible(visible);
    };

    private JFrame createFrame()
    {
        JFrame frame = new JFrame();
        frame.dispose();
        frame.setIgnoreRepaint(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        return frame;
    }

    public int getScreenWidth()
    {
        return screenWidth;
    }

    public int getScreenHeight()
    {
        return screenHeight;
    }
}
