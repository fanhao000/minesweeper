import java.util.*;
public class Main {
    final static private String LEFT = "l";
    final static private String RIGHT = "r";
    private static Board b;
    public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Select difficulty: 0 for easy, 1 for medium, 2 for difficult\n");
        int diff = scan.nextInt();
        System.out.print("How many rows and columns?\n");
        int len = scan.nextInt();
        b = new Board(len, len, diff);
        playGame(b);
    }

    public static void playGame(Board b) {
        Scanner scan = new Scanner(System.in);
        boolean curStat = true;
        boolean noBomb = true;
        while(curStat && noBomb) {
            b.toString();
            System.out.print("L to left click and R to right click: \n");
            String move = scan.next();
            move = move.toLowerCase();
            System.out.print("type in the row number (0 for top row and counting down)\n");
            int row = scan.nextInt();
            System.out.print("type in the col number (0 for left col and counting right)\n");
            int col = scan.nextInt();
            if (move.equals(LEFT)) {
                noBomb = status(b.leftClick(row, col));
            } else if (move.equals(RIGHT)) {
                b.rightClick(row, col);
            }
            curStat = status(false);
        }
    }

    public static boolean status(boolean lost) {
        if (lost) {
            System.out.print("THAT'S A BOMB! YOU LOST!\n");
            b.toString();
            return false;
        } else if (b.equals()) {
            System.out.print("CONGRATS! YOU WON!\n");
            b.toString();
            return false;
        } else if (b.checkFilled()) {
            System.out.print("SORRY! YOU LOST!\n");
            b.toString();
            return false;
        } else {
            return true;
        }
    }
}