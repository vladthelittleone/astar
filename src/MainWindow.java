import astar.Node;

public class MainWindow
{
    public static void main(String[] args)
    {
        Node startNode = new Node(2, 2);
        Node endNode = new Node(15, 15);
        new AstarManager(startNode, endNode, 60, 80, 10);
    }

    public static void error(String s)
    {
        System.err.print(s);
    }

    public static void error(Exception e)
    {
        e.printStackTrace();
    }

}
