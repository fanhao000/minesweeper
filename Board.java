import java.util.Random;

public class Board {
    private Spots[][] board;
    private Spots[][] playBoard;
    private int row;
    private int col;

    public Board(int c, int r, int diff) {
        this.row = r;
        this.col = c;
        board = new Spots[r][c];
        playBoard = new Spots[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                board[i][j] = new Spots('0');
                playBoard[i][j] = new Spots('*');
            }
        }
        bombs(diff, board);
        setup(board);
    }

    /**
     * uses the difficulty to create and set bombs within the board
     * pre: 0 <= difficulty <= 2
     * post: bombs have been set on the board, return number of bombs on board
     */
    private int bombs(int difficulty, Spots[][] board){
        Random x = new Random();
        if (difficulty == 2) {
            difficulty = (row * col) / 3;
        } else if (difficulty == 1) {
            difficulty = (row * col) / 10;
        } // if (difficulty == 0) -  do nothing

        for (int i = 0; i < difficulty; i++) {
            boolean placed = false;
            while(!placed) {
                int rowTemp = x.nextInt(board.length);
                int colTemp = x.nextInt(board[0].length);
                if (board[rowTemp][colTemp].getData() == '0') {
                    board[rowTemp][colTemp].setData('$');
                    placed = true;
                }
            }
        }
        return difficulty;
    }

    private boolean setup(Spots[][] board) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j].getData() != -1) {
                    board[i][j].setData((char) checkDirection(i, j));
                }
            }
        }
        return true;
    }

    public boolean leftClick(int row, int col) {
        if (playBoard[row][col].getData() != '*') {
            System.out.print("Already played this spot.\n");
            return false;
        } else {
            char cur = board[row][col].getData();
            playBoard[row][col].setData(cur);
            return cur == '$';
        }
    }

    public boolean rightClick (int row, int col) {
        if (playBoard[row][col].getData() != '*') {
            System.out.print("Already played this spot.\n");
            return false;
        } else {
            playBoard[row][col].setData('$');
            return true;
        }
    }

    public int checkDirection (int r, int c) {
        int total = 48;
        for (int chgx = -1; chgx <= 1; chgx++) {
            for (int chgy = -1; chgy <= 1; chgy++) {
                if (checkEdge(r + chgx) && checkEdge(c + chgy)) {    
                    Spots temp = board[r + chgx][c + chgy];
                    if (temp.getData() == '$') {
                        total++;
                    }
                }
            }
        }
        return total;
    }

    public String toString() {
        for (int i = 0; i < playBoard.length; i++) {
            for (int j = 0; j < playBoard[0].length; j++) {
                System.out.printf("%4s", playBoard[i][j].getData());
            }
            System.out.printf("%n");
        }
        return "";
    }

    private boolean checkEdge(int val) {
        if (val < 0) {
            return false;
        } else if (val >= row) {
            return false;
        } else if (val >= col) {
            return false;
        } else {
            return true;
        }
    }

    public boolean equals() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j].getData() != playBoard[i][j].getData()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkFilled() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (playBoard[i][j].getData() == '*') {
                    return false;
                }
            }
        }
        return true;
    }
}