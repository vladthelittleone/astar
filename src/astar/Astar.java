package astar;

import java.util.ArrayList;


public class Astar
{
    private ArrayList<Node> open;
    private ArrayList<Node> close;
    private ArrayList<Node> path;

    private Node endNode;
    private Node startNode;
    private Node closeNode;

    private Grid grid;

    private int straightCost = 1;
    private int diagCost = 2;

    public boolean findPath(Grid grid)
    {
        this.grid = grid;
        this.open = new ArrayList<Node>();
        this.close = new ArrayList<Node>();

        this.startNode = grid.getStartNode();
        this.endNode = grid.getEndNode();

        startNodeInit();

        return search();
    }

    public boolean search()
    {
        Node node = startNode;

        while (!node.equalsNodesPosition(endNode))
        {
            int startX = Math.max(0, node.x - 1);
            int startY = Math.max(0, node.y - 1);

            int endX = Math.min(grid.getNumCols() - 1, node.x + 1);
            int endY = Math.min(grid.getNumRows() - 1, node.y + 1);

            for (int i = startX; i <= endX; i++)
            {
                for (int j = startY; j <= endY; j++)
                {
                    Node test = grid.getNode(i, j);
                    if (test.equalsNodesPosition(node) ||
                            !test.walkable ||
                            !grid.getNode(node.x, test.y).walkable ||
                            !grid.getNode(test.x, node.y).walkable)
                    {
                        continue;
                    }

                    int cost = straightCost;

                    if (!((node.x == test.x) || (node.y == test.y)))
                    {
                        cost = diagCost;
                    }

                    int g = node.g + cost; // * test.costMultiplier;
                    int h = euclidean(test);
                    int f = g + h;

                    if (isOpen(test) || isClosed(test))
                    {
                        if (test.f > f)
                        {
                            test.f = f;
                            test.h = h;
                            test.g = g;
                            test.parent = node;
                        }
                    }
                    else
                    {
                        test.f = f;
                        test.h = h;
                        test.g = g;
                        test.parent = node;
                        open.add(test);
                    }
                }
            }

            close.add(node);

            if (open.size() == 0)
            {
                System.out.println("path not found");
                break;

            } else
            {
                node = sortArray(open);
                open.remove(node);
                closeNode = node;
            }
        }

        buildPath();

        return true;
    }

    public void buildPath()
    {
        path = new ArrayList<Node>();
        path.add(closeNode);

        while (!(closeNode.equalsNodesPosition(startNode)))
        {
            closeNode = closeNode.parent;
            path.add(closeNode);
        }
    }

    public boolean isOpen(Node node)
    {
        for (int i = 0; i <= open.size() - 1; i++)
        {
            if (node.equalsNodesPosition((open.get(i))))
            {
                return true;
            }
        }

        return false;
    }

    public boolean isClosed(Node node)
    {
        for (int i = 0; i <= close.size() - 1; i++)
        {
            if (node.equalsNodesPosition((close.get(i))))
            {
                return true;
            }
        }

        return false;
    }

    public Node sortArray(ArrayList<Node> array)
    {
        Node fMin = array.get(0);
        for (int i = 1; i <= array.size() - 1; i++)
        {
            Node node = array.get(i);
            if (node.f < fMin.f)
            {
                fMin = node;
            }
        }

        return fMin;
    }

    //------------------------- Strategies

    public int manhattan(Node node)
    {
        return (Math.abs(node.x - endNode.x) * straightCost + Math.abs(node.y - endNode.y) * straightCost);
    }

    public int euclidean(Node node)
    {
        int dx = node.x - endNode.x;
        int dy = node.y - endNode.y;
        return (int) Math.sqrt(dx * dx + dy * dy) * straightCost;
    }

    public int diagonal(Node node)
    {
        int dx = Math.abs(node.x - endNode.x);
        int dy = Math.abs(node.y - endNode.y);
        int diag = Math.min(dx, dy);
        int straight = dx + dy;
        return diagCost * diag + straightCost * (straight - 2 * diag);
    }

    //-------------------------

    public ArrayList<Node> getVisited()
    {
        ArrayList<Node> array = new ArrayList<Node>();
        array.addAll(open);
        array.addAll(close);
        return array;
    }

    public ArrayList<Node> getPath()
    {
        return path;
    }

    private void startNodeInit()
    {
        startNode.g = 0;
        startNode.h = euclidean(startNode);
        startNode.f = startNode.g + startNode.h;
    }
}
