package astar;


public class Grid
{

    private Node startNode;
    private Node endNode;

    private Node nodes[][];

    private int numRows;
    private int numCols;

    public Grid(int numCols, int numRows)
    {
        this.numRows = numRows;
        this.numCols = numCols;

        nodes = new Node[numCols][numRows];

        for (int i = 0; i <= numCols - 1; i++)
        {
            for (int j = 0; j <= numRows - 1; j++)
            {
                nodes[i][j] = new Node(i, j);
            }
        }
    }

    public void setStartNode(int x, int y)
    {
        startNode = new Node(x, y);
    }

    public void setEndNode(int x, int y)
    {
        endNode = new Node(x, y);
    }

    public void setWalkable(int x, int y, boolean value)
    {
        nodes[x][y].walkable = value;
    }

    public Node getNode(int x, int y)
    {
        return nodes[x][y];
    }

    public Node getEndNode()
    {
        return endNode;
    }

    public Node getStartNode()
    {
        return startNode;
    }

    public int getNumRows()
    {
        return numRows;
    }

    public int getNumCols()
    {
        return numCols;
    }


}
