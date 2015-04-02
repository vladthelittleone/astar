package astar;

public class Node
{
    public Node parent;

    public int f;
    public int g;
    public int h;
    public int x;
    public int y;

    public int costMultiplier = 1;

    public boolean walkable = true;

    public Node(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public boolean equalsNodesPosition(Node node)
    {
        return x == node.x && y == node.y;
    }
}
