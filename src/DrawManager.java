import astar.Grid;
import astar.Node;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * @author Skurishin Vladislav
 * @since 02.04.15
 */
class DrawManager extends Canvas
{
    private static final long serialVersionUID = 1L;

    private BufferStrategy bufferStrategy;
    private DisplayOptions displayOptions;
    private Graphics2D g2d;

    public DrawManager()
    {
        createDisplayOptions();
        setVisible();
    }

    private void setVisible()
    {
        requestFocus();
        displayOptions.setVisible(true);
    }

    private void createDisplayOptions()
    {
        displayOptions = new DisplayOptions();
        displayOptions.add(this);

        createBufferStrategy();
    }

    private void createBufferStrategy()
    {
        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
    }

    public void beginRendering()
    {
        g2d = createGraphics2D();
    }

    public void endRendering()
    {
        if (!bufferStrategy.contentsLost())
        {
            bufferStrategy.show();
        }

        g2d.dispose();
    }

    public void drawGrid(int nodeSize, int rowsCount, int colsCount)
    {
        int width =  colsCount * nodeSize;
        int height = rowsCount * nodeSize;

        for (int i = 0; i <= width; i = i + 10)
        {
            g2d.setColor(new Color(210, 210, 210));
            g2d.drawLine(i, 0, i, height);

            if (i <= 600)
            {
                g2d.drawLine(0, i, width, i);
            }
        }
    }

    public void drawWalcableNodes(Grid grid, int colsCount, int rowsCount)
    {
        for (int i = 0; i <= colsCount - 1; i++)
        {
            for (int j = 0; j <= rowsCount - 1; j++)
            {
                Node node = grid.getNode(i, j);

                if (!node.walkable)
                {
                    g2d.setColor(new Color(210, 110, 110));
                    g2d.fillRect(node.x * 10, node.y * 10, 10, 10);
                }
            }
        }
    }

    public void drawPath(ArrayList<Node> path)
    {
        for (int i = 0; i <= path.size() - 1; i++)
        {
            Node node = path.get(i);

            g2d.setColor(new Color(110, 210, 110));

            System.out.println(node.x * 10 + " " + node.y * 10);
            g2d.fillRect(node.x * 10, node.y * 10, 10, 10);

        }
    }

    private Graphics2D createGraphics2D()
    {
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

        g2d.setColor(Color.white);

        g2d.fillRect(0, 0, getScreenWidth(), getScreenHeight());

        g2d.setColor(Color.green);

        g2d.scale(getScreenWidth() / 800., getScreenHeight() / 600.);

        g2d.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON));

        return g2d;
    }

    public int getScreenWidth()
    {
        return displayOptions.getScreenWidth();
    }

    public int getScreenHeight()
    {
        return displayOptions.getScreenHeight();
    }
}






