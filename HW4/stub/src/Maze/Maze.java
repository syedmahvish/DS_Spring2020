package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        //check boundary condition and if not visited.
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || (!maze.getColor(x, y).equals(NON_BACKGROUND)))
            return false;
        //check if reach destination and return.
        else if (x == maze.getNCols()-1 && y == maze.getNRows()-1) {
            maze.recolor(x, y, PATH);
            return true;
        } else {
            maze.recolor(x, y, PATH);
            if (findMazePath((x-1), y)
                    || findMazePath(x + 1, y)
                    || findMazePath(x, y-1)
                    || findMazePath(x, y + 1 ) ) {
                return true;
            } else {
                maze.recolor(x, y, TEMPORARY);
                return false;
            }
        }
    }

    /** Wrapper method. */
    public ArrayList <ArrayList< PairInt >> findAllMazePaths (int x, int y) {
         ArrayList<ArrayList< PairInt >> result = new ArrayList<>();
         Stack<PairInt> trace = new Stack < >();
         findMazePathStackBased(0, 0, result, trace);
         return result;
    }

    /**
     * Attempts to find a all paths through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color. While backtracking it set to NON_BACKGROUND color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @param result store all paths between source and destination.
     * @param trace Stack to store path while finding path from source to destination.
     */

   public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace){
        //check boundary condition and if not visited.
       if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1 || (!maze.getColor(x, y).equals(NON_BACKGROUND))){
           return;
       } else // check if reach destination. Store stack in list and set node as not visited.
           if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
           trace.push(new PairInt(x, y)); // exit point added to trace
           ArrayList<PairInt> cur = new ArrayList<>(trace);
           result.add(cur);
           trace.pop();
           maze.recolor(x, y, NON_BACKGROUND);
           return;
       } else {
               // push node that is visited and change color to path i.e visited.
           trace.push(new PairInt(x, y));
           maze.recolor(x, y, PATH);
           findMazePathStackBased(x, y + 1, result, trace); // go down
           findMazePathStackBased(x, y - 1, result, trace); // go up
           findMazePathStackBased(x + 1, y, result, trace); // go right
           findMazePathStackBased(x - 1, y, result, trace); // go left
           maze.recolor(x, y, NON_BACKGROUND); // once done backtrack and mark as not visited
           trace.pop();
           return;
       }

   }

    /***
     * Find the minimum path between source and destination.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return  array list of min path between source and destination.
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y) {
        ArrayList <ArrayList< PairInt >> allPaths = findAllMazePaths(x, y);

        // if no path from source to destination return null.
        if(allPaths.isEmpty()) {
            return null;
        }

        int size = allPaths.get(0).size();
        int minIndex = 0;

        // compare min path using size of each path.
        // store size of minPath and index for further comparison.
        for(int i = 1 ; i < allPaths.size() ; i++) {
            if(allPaths.get(i).size() < size) {
                size = allPaths.get(i).size();
                minIndex = i;
            }
        }

        // return min path using minIndex.
        return allPaths.get(minIndex);

    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
