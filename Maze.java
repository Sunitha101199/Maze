public class Maze {
    
    private boolean[][] grid;
    private final QuickFindUF wqfGrid;
    private final QuickFindUF wqfFull;
    private final int gridSize;
    private final int virtualTop;
    private final int virtualBottom;
    private int openSites;

    // creates n-by-n grid, with all sites initially blocked
    public Maze(int n) {
        if (n <= 0) throw new IllegalArgumentException("N must be > 0");
        int gridSquared = n * n;
        gridSize = n;
        grid = new boolean[gridSize][gridSize];
        wqfGrid = new QuickFindUF(gridSquared + 2);
        wqfFull = new QuickFindUF(gridSquared + 1);
        virtualBottom = gridSquared + 1;
        virtualTop = gridSquared;
        openSites = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateSite(row, col);

        int shiftRow = row - 1;
        int shiftCol = col - 1;
        int flatIndex = flattenGrid(row, col) - 1;

        // If already open, stop
        if (isOpen(row, col)) {
            return;
        }

        // Open Site

        grid[shiftRow][shiftCol] = true;
        openSites++;

        if (row == 1) {  // Top Row
            wqfGrid.union(virtualTop, flatIndex);
            wqfFull.union(virtualTop, flatIndex);
        }

        if (row == gridSize) {  // Bottom Row
            wqfGrid.union(virtualBottom, flatIndex);
        }

        // Check and Open Left
        if (isOnGrid(row, col - 1) && isOpen(row, col - 1)) {
            wqfGrid.union(flatIndex, flattenGrid(row, col - 1) - 1);
            wqfFull.union(flatIndex, flattenGrid(row, col - 1) - 1);
        }

        // Check and Open Right
        if (isOnGrid(row, col + 1) && isOpen(row, col + 1)) {
            wqfGrid.union(flatIndex, flattenGrid(row, col + 1) - 1);
            wqfFull.union(flatIndex, flattenGrid(row, col + 1) - 1);
        }

        // Check and Open Up
        if (isOnGrid(row - 1, col) && isOpen(row - 1, col)) {
            wqfGrid.union(flatIndex, flattenGrid(row - 1, col) - 1);
            wqfFull.union(flatIndex, flattenGrid(row - 1, col) - 1);
        }

        // Check and Open Down
        if (isOnGrid(row + 1, col) && isOpen(row + 1, col)) {
            wqfGrid.union(flatIndex, flattenGrid(row + 1, col) - 1);
            wqfFull.union(flatIndex, flattenGrid(row + 1, col) - 1);
        }
    }

    public boolean isOpen(int row, int col) {
        validateSite(row, col);
        return grid[row - 1][col - 1];

    }

    public boolean isFull(int row, int col) {
        validateSite(row, col);
        return wqfFull.find(virtualTop) == wqfFull.find(flattenGrid(row, col) - 1);
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean isConnected() {
        return wqfGrid.find(virtualTop) == wqfGrid.find(virtualBottom);
    }

    private int flattenGrid(int row, int col) {
        return gridSize * (row - 1) + col;
    }

    private void validateSite(int row, int col) {
        if (!isOnGrid(row, col)) {
            throw new IllegalArgumentException("Index is out of bounds" + row + " " + col);
        }
    }

    private boolean isOnGrid(int row, int col) {
        int shiftRow = row - 1;
        int shiftCol = col - 1;
        return (shiftRow >= 0 && shiftCol >= 0 && shiftRow < gridSize && shiftCol < gridSize);
    }

    // test client
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int n = in.readInt();         // n-by-n maze system

        // read in pair of integers from standard input
        // if they are not yet connected, connect them
        Stopwatch timer = new Stopwatch();
        Maze maze = new Maze(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            if (!maze.isConnected()) {
                maze.open(i, j);
            }
        }
        double elapsedTime = timer.elapsedTime();
        System.out.println(args[0]);
        System.out.printf("%.2f seconds\n", elapsedTime);
    }
}
