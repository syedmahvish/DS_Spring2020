package Maze;

public class PairInt {
    private int x;
    private int y;

    /**
     * Constructor to initialize co-ordinate of maze.
     * @param x The x-coordinate of point
     * @param y The y-coordinate of  point
     */
    public PairInt(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * To access value of x-coordinate of point.
     * @return x-coordinate of point.
     */
    public int getX(){
        return x;
    }

    /**
     * To access value of y-coordinate of point.
     * @return y-coordinate of point.
     */
    public int getY(){
        return y;
    }

    /**
     * Set value of x-coordinate of point.
     * @param x x-coordinate of point.
     * @throws NumberFormatException if value of x is invalid throws NumberFormatException
     */
    public void setX(int x) throws NumberFormatException {
        if(x >= 0 && x <= Integer.MAX_VALUE){
            this.x = x;
        }
        throw new NumberFormatException();
    }

    /**
     * Set value of y-coordinate of point.
     * @param y y-coordinate of point.
     * @throws NumberFormatException if value of y is invalid throws NumberFormatException
     */
    public void setY(int y) throws NumberFormatException  {
        if(y >= 0 && y <= Integer.MAX_VALUE){
            this.y = y;
        }
        throw new NumberFormatException();
    }

    /**
     * Compares objects of PairInt
     * @param p object of PairInt to be compare.
     * @return true if same else false.
     */
    public boolean equals(Object p) {
        if (this == p) return true;
        if (!(p instanceof PairInt)) return false;
        PairInt pairInt = (PairInt) p;
        return getX() == pairInt.getX() &&
                getY() == pairInt.getY();
    }

    /***
     * Represent string format of points coordinate.
     * @return String representation of points.
     */
    public String toString() {
        return  "(" + x + ", " + y + ")";

    }

    /***
     * Create and return new copy of PairInt.
     * @return new copy of PairInt
     */
    public PairInt copy() {
        return new PairInt(x,y);
    }


}
