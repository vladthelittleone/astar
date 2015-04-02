import astar.Astar;
import astar.Grid;
import astar.Node;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AstarManager
{
    private final Grid grid;
    private final Astar astar;

    private final int rowsCount;
    private final int colsCount;
    private final int nodeSize;

    private int mouseX;
    private int mouseY;

    private Node startNode;
    private Node endNode;

    private DrawManager drawManager;

    public AstarManager(Node startNode, Node endNod, int colsCount, int rowsCount, int nodeSize)
    {
        this.rowsCount = rowsCount;
        this.colsCount = colsCount;
        this.nodeSize = nodeSize;

        this.startNode = startNode;
        this.endNode = endNod;

        grid = new Grid(colsCount, rowsCount);
        astar = new Astar();

        createDrawManager();
        renderScene();
    }

    private void createDrawManager()
    {
        drawManager = new DrawManager();
        drawManager.addMouseListener(new MyMouseAdapter());
    }

    public void buildAndDrawPath()
    {
        grid.setStartNode(startNode.x, startNode.y);
        grid.setEndNode(endNode.x, endNode.y);

        if (astar.findPath(grid))
        {
            drawManager.drawPath(astar.getPath());
            drawManager.drawWalcableNodes(grid, colsCount, rowsCount);
        }
    }

    public void initWalkableNode()
    {
        int dx = (800 * mouseX / drawManager.getScreenWidth()) / 10;
        int dy = (600 * mouseY / drawManager.getScreenHeight()) / 10;

        Node node = grid.getNode(dx, dy);

        if (!node.equalsNodesPosition(startNode) &&
                !node.equalsNodesPosition(endNode))
        {
            boolean value = true;

            if (node.walkable)
            {
                value = false;
            }

            grid.setWalkable(dx, dy, value);
        }
    }

    class MyMouseAdapter extends MouseAdapter
    {
        public void mousePressed(MouseEvent me)
        {
            mouseX = me.getX();
            mouseY = me.getY();

            initWalkableNode();

            renderScene();
        }
    }

    private void renderScene()
    {
        drawManager.beginRendering();

        drawManager.drawGrid(nodeSize, colsCount, rowsCount);
        buildAndDrawPath();

        drawManager.endRendering();
    }
}
