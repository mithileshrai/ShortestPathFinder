import java.util.ArrayList;
import java.util.List;

class PathFinder {
    // N x M matrix
    private static final int N = 10;
    private static final int M = 20;
    private static List<Node> SHORTEST_PATH = new ArrayList<>();

    // Below arrays details all 4 possible movements from a cell
    private static final int row[] = {-1, 0, 0, 1};
    private static final int col[] = {0, -1, 1, 0};

    // Function to check if it is possible to go to position pt
    // from current position. The function returns false if pt is
    // not a valid position or it is already visited
    private static boolean isValid(List<Node> path, Node pt) {
        return pt.first >= 0 && pt.first < N &&
                pt.second >= 0 && pt.second < M &&
                !path.contains(pt);
    }

    // Check if it is possible to go to (x, y) from current position. The
    // function returns false if the cell has value 0 i.e W
    private static boolean isSafe(int mat[][], Node pt) {
        return !(mat[pt.first][pt.second] == 0);
    }

    // Function to print the complete path from source to destination
    private static void printPath(List<Node> path) {
        for (Node cell : path) {
            System.out.print("(" + cell.first + ", " + cell.second + ") ");
        }
    }

    // Find route in a matrix mat from source cell (0, 0) to
    // destination cell (N-1, M-1)
    public boolean findPath(int mat[][], List<Node> path, Node curr, Node destination) {
        // include current vertex in the path
        path.add(curr);

        // if destination is found, return true
        if (curr.first == destination.first - 1 && curr.second == destination.second - 1)
            return true;

        // value of current cell
        int n = mat[curr.first][curr.second];

        // check all 4 possible movements from current cell
        // and recurse for each valid movement
        for (int i = 0; i < 4; i++) {
            // get next position using value of current cell
            int x = curr.first + row[i] * n;
            int y = curr.second + col[i] * n;

            Node next = new Node(x, y);

            // check if it is possible to go to position (x, y)
            // from current position
            if (isValid(path, next) && isSafe(mat, next) && findPath(mat, path, next, destination)) {
                SHORTEST_PATH.add(next);
                return true;
            }
        }

        // Backtrack - exclude current vertex in the path
        //path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        PathFinder shortestPath = new PathFinder();
        int mat[][] =
                {
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                        {0, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
                        {1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1},
                        {0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
                };

        List<Node> path = new ArrayList<>();
        Node source = new Node(4, 0); // Starting point S
        Node destination = new Node(4, 19); // Destination E

        if (shortestPath.findPath(mat, path, source, destination))
            printPath(SHORTEST_PATH);
    }
}