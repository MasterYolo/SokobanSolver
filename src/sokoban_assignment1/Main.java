package sokoban_assignment1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

/**
 * The main class.
 * @author Micke L, Calle Bergmark.
 */
public class Main {

    static boolean checkblock[][]; // To check if the block is already checked.
    static Vector<String> board = new Vector<>(); //The board.
    static int ll = 0; //Line length.
    static boolean win = false; //Win/no path variable.

    public static void main(String[] args) throws IOException {
        FileInputStream fileinput = new FileInputStream("E:/Program Files (x86)/Netbeans/NetBeans 8.0/proj/Sokoban_assignment1/src/sokoban_assignment1/test.txt");
        BufferedReader br = new BufferedReader(
                new InputStreamReader(fileinput));

        String line;
        String output = "";
        char p;
        while (br.ready()) {
            line = br.readLine();
            if (ll < line.length()) {
                ll = line.length();
            }

            board.add(line);
        }

        //Initisiering av checkblock.
        checkblock = new boolean[board.size()][ll];

        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).length(); j++) {
                p = board.get(i).charAt(j);
                if (p == '@') {
                    output = findPath(i, j);
                    if (output == "") {
                        output = "no path";
                    }
                } else if (p == '+') { //Sign for player on goal.
                    output = "";
                }
            }
        }

        System.out.println(output);

    }

    /**
     * Method to calculate the right path.
     * @param row The row to search.
     * @param col The col search.
     * @return The calculated path as sting.
     */
    public static String findPath(int row, int col) {
        String RP = "";
        // Markera Block som använts
        checkblock[row][col] = true;
        
        if (board.get(row).charAt(col) == '.') {
            win = true;
            return RP;

        }
        if (row > 0 && win == false) {
            if (board.get(row - 1).charAt(col) == ' ') {//Freespace.
                if (checkblock[row - 1][col] != true) {
                    RP = "U " + findPath(row - 1, col);
                }
            }
            if (board.get(row - 1).charAt(col) == '.') {//Goal.
                if (checkblock[row - 1][col] != true) {
                    RP = "U " + findPath(row - 1, col);
                }
            }
        }
        
        if (col > 0 && win == false) {
            if (board.get(row).charAt(col - 1) == ' ') {//Freespace.
                if (checkblock[row][col - 1] != true) {
                    RP = "L " + findPath(row, col - 1);
                }
            }
            if (board.get(row).charAt(col - 1) == '.') {//Goal.
                if (checkblock[row][col - 1] != true) {
                    RP = "L " + findPath(row, col - 1);
                }
            }
        }

        if (row < board.size() - 1 && win == false) {
            if (board.get(row + 1).charAt(col) == ' ') {//Freespace.
                if (checkblock[row + 1][col] != true) {
                    RP = "D " + findPath(row + 1, col);
                }
            }
            if (board.get(row + 1).charAt(col) == '.') {//Goal.
                if (checkblock[row + 1][col] != true) {
                    RP = "D " + findPath(row + 1, col);
                }
            }
        }
        
        if (col < ll && win == false) {
            if (board.get(row).charAt(col + 1) == ' ') {//Freespace.
                if (checkblock[row][col + 1] != true) {
                    RP = "R " + findPath(row, col + 1);
                }
            }
            if (board.get(row).charAt(col + 1) == '.') {//Goal.
                if (checkblock[row][col + 1] != true) {
                    RP = "R " + findPath(row, col + 1);
                }
            }
        }
        if (win == false) {
            return "";
        }
        return RP;
    }
}
