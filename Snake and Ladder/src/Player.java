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
 * 3. 5026231161 - Muhammad Daniel Alfarisi 
 * ------------------------------------------------------
 */
import java.io.File;
import javax.sound.sampled.*;
public class Player {
    private String name;
    private int position;

    Player(String name){
        this.name=name;
        this.position=0;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPosition(int position){
        this.position = position;
    }
    public String getName(){
        return this.name;
    }
    public int getPosition(){
        return this.position;
    }

    public int rollDice() {
        return (int)(Math.random()*6+1);

    }

    public void moveAround(int y, int boardSize) {
        if(this.position + y>boardSize) {
            this.position = boardSize-(this.position + y)%boardSize;
        } else {
            this.position = this.position + y;
        }



    }
}
