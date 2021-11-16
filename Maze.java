public class Maze {

    // creates n-by-n grid, with all sites initially blocked
    public Maze(int n) {
        // your code goes here
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        // your code goes here
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        // your code goes here
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        // your code goes here
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        // your code goes here
    }

    // does the system percolate?
    public boolean isConnected() {
        // your code goes here
    }

    // test client
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int n = in.readInt();         // n-by-n maze system

        // read in pair of integers from standard input
        // if they are not yet connected, connect them
        Maze maze = new Maze(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            if (!maze.isConnected()) {
                maze.open(i, j);
            }
        }
    }
}
