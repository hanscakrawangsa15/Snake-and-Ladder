/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : 05
 * Members  :
 * 1. 5026231130 - Hans Christian Cakrawangsa  
 * 2. 5026231145 - Abrorus Shobah
 * 3. 50262311  Muhammad Daniel Alfarisi 
 * ------------------------------------------------------
 */

public class Snake{
    private int tailPosition;
    private int headPosition;

    public Snake(int t, int h){
        this.tailPosition = t;
        this.headPosition = h;
    }

    public void setTailPosition(int t){
        this.tailPosition = t;
    }

    public void setHeadPosition(int h){
        this.headPosition = h;
    }

    public int getTailPosition(){
        return tailPosition;
    }

    public int getHeadPosition(){
        return headPosition;
    }
}
