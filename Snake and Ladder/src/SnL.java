/**
 * -----------------------------------------------------
 * ES234211 - Programming Fundamental
 * Genap - 2023/2024
 * Group Capstone Project: Snake and Ladder Game
 * -----------------------------------------------------
 * Class    : D
 * Group    : XX
 * Members  :
 * 1. Student ID - Full Name
 * 2. Student ID - Full Name
 * 3. Student ID - Full Name
 * ------------------------------------------------------
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.sound.sampled.*;
import java.util.HashSet;

public class SnL{
    private ArrayList<Player> players;
    private ArrayList<Snake> snakes;
    private ArrayList<Ladder> ladders;
    private int boardSize;
    private int gameStatus;
    private int nowPlaying;
    private boolean extraRoll;
    private String gameMode;
    private HashSet<Integer> bombPositions;

    //Memberikan warna pada pemberitahuan saat terkena tangga dan terkena ular
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m"; // ladders notification
    public static final String ANSI_YELLOW = "\u001B[33m"; // snakes notification
    public static final String ANSI_RED = "\u001B[31m";  // bomb notification


    public SnL(int s){
        this.boardSize = s;
        this.players = new ArrayList<Player>();
        this.snakes = new ArrayList<Snake>();
        this.ladders = new ArrayList<Ladder>();
        this.gameStatus = 0;
        this.extraRoll = false; //default
        this.gameMode = "normal"; //default
        this.bombPositions = new HashSet<>(); //initialize bomb position
    }
    public void setBoardSize(int s){
        this.boardSize = s;
    }
    public void setGameStatus(int s){
        this.gameStatus = s;
    }
    public int getGameStatus(){
        return this.gameStatus;
    }
    public void setGameMode(String mode){
        this.gameMode = mode;
    }
    public String getGameMode(){
        return this.gameMode;
    }
    public void addRollDiceSoundEffect(String soundFileName){
        try{
            File soundFile = new File(soundFileName);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }catch(UnsupportedAudioFileException e){
            System.out.println("UnsupportedAudioFileException" + e.getMessage());
        }catch(IOException e){
            System.out.println("IOException" + e.getMessage());
        }catch(LineUnavailableException e){
            System.out.println("LineUnavailableException" + e.getMessage());
        }
    }

    public void play(){
        Player playerInTurn;
        Scanner read=new Scanner(System.in);
        System.out.println("Please enter Player 1: ");
        String player1 = read.nextLine();
        System.out.println("Please enter Player 2: ");
        String player2 = read.nextLine();

        //Selecting game Mode
        System.out.println("Choose your game mode (easy/normal/unique): ");
        String mode = read.nextLine().trim().toLowerCase();
        if (mode.equals("easy") || mode.equals("normal") || mode.equals("unique")) {
            setGameMode(mode);
        } else {
            System.out.println("Invalid mode selected. Auto Selecting to normal mode.");
            setGameMode("normal");
            System.out.println("");
        }
        System.out.println("Your Game Mode is: " + getGameMode() + ", Enjoy Playing!");
        System.out.println("");
//        SoundPlayer.playSound("src//start.wav");


        //object instantiation
        Player p1 = new Player(player1);
        Player p2 = new Player(player2);
        initiateGame();
        addPlayer(p1);
        addPlayer(p2);

        if (getGameMode().equals("unique")) {
            Random rand = new Random();
            while (bombPositions.size() < 10){
                int bombPosition = rand.nextInt(boardSize - 1) + 1; //Supaya bomb tidak di angka 1 dan 100
                bombPositions.add(bombPosition);
            }
            System.out.println("A BOMB HAS BEEN PLACED! RUN FROM IT OR DIE WITH IT!");
            System.out.println("");
        }
        do {
            playerInTurn =getWhoseTurn();
            System.out.println("Now Playing "+ playerInTurn.getName());
            do{
                extraRoll = false;
                System.out.println(playerInTurn.getName() + " please press enter to roll the dice");
                String enter = read.nextLine();
                int x = 0;
                if (enter.isEmpty()) {
                    x = playerInTurn.rollDice();
                    SoundPlayer.playSound("src//rolldice.wav");
                }
                System.out.println("Dice Number : "+ x);
                movePlayerAround(playerInTurn, x);
                System.out.println("New Position:  "+ playerInTurn.getPosition());
                System.out.println("==============================================");
            } while (extraRoll);
        }
        while (getGameStatus()!=2);
        SoundPlayer.playSound("src//gameover.wav");
        System.out.println("the  winner is:"+ playerInTurn.getName());
        if (bombPositions.isEmpty()) {
            System.out.println("The bomb was at position: " + bombPositions);
            SoundPlayer.playSound("src//gameover.wav");
        }

    }
    public void addPlayer(Player s){
        this.players.add(s);
    }
    public ArrayList<Player> getPlayers(Player s){
        return this.players;
    }
    public void addSnake(Snake s){
        this.snakes.add(s);
    }

    public void addSnakes(int [][] s){
        for (int r = 0; r < s.length; r++){
            Snake snake = new Snake(s[r][0], s[r][1]);
            this.snakes.add(snake);
        }
    }


    public void addLadder(Ladder l){
        this.ladders.add(l);
    }

    public void addLadders(int [][] l){
        for (int r = 0; r < l.length; r++){
            Ladder ladder = new Ladder(l[r][1], l[r][0]);
            this.ladders.add(ladder);
        }

    }
    public int getBoardSize(){
        return this.boardSize;
    }
    public ArrayList<Snake> getSnakes(){
        return this.snakes;
    }
    public ArrayList<Ladder> getLadders(){
        return this.ladders;
    }
    public void initiateGame(){
        int [][] l = {
                {2,23},
                {8,34},
                {20,77},
                {32,68},
                {41,79},
                {74,88},
                {82,100},
                {85,95}

        };
        addLadders(l);

        int[][] s = {
                {1, 6},
                {1, 3},
                {1, 4},
                {1, 5},
                {1, 2},
                {37, 62},
                {54, 86},
                {70, 92}
        };

        addSnakes(s);
    }


    public void movePlayerAround(Player p, int x) {
        p.moveAround(x, this.boardSize);
        for (Ladder l : this.ladders) {
            if (p.getPosition() == l.getBottomPosition()) {
                System.out.println(ANSI_GREEN + p.getName() + " you got Ladder from: " + l.getBottomPosition() + " To: " + l.getTopPosition() + ANSI_RESET);
                SoundPlayer.playSound("src//climbingstairs.wav");
                p.setPosition(l.getTopPosition());
            }
        }
        if (this.gameMode.equals("normal") || this.gameMode.equals("unique")) {
            for (Snake s : this.snakes) {
                if (p.getPosition() == s.getHeadPosition()) {
                    p.setPosition(s.getTailPosition());
                    System.out.println(ANSI_YELLOW + p.getName() + " you get snake head from " + s.getHeadPosition() + " slide down to " + s.getTailPosition() + ANSI_RESET);
                    SoundPlayer.playSound("src//snakehiss.wav");
                    if (this.gameMode.equals("unique")) {
                        extraRoll = true;
                        System.out.println(p.getName() + " keep your heads up, you get extra roll !!!");
                        System.out.println("");
                    }
                }
            }
        }
        // Check if player lands on bomb
        if (this.gameMode.equals("unique") && bombPositions.contains(p.getPosition())) {
            System.out.println(ANSI_RED + p.getName() + " STEPPED ON THE BOMB! GAME OVER!" + ANSI_RESET);
            System.out.println(ANSI_RED + "The bombs were at positions: " + bombPositions + ANSI_RESET);
            SoundPlayer.playSound("src//bomb.wav");
            this.gameStatus = 2;  // End the game
            int playerWin = this.players.indexOf(p) == 0 ? 1 : 0;  // Determine the other player as winner
            System.out.println("The winner is: " + this.players.get(playerWin).getName());
            SoundPlayer.playSound("src//gameover.wav");
            return ;
        }

        if (p.getPosition() == this.boardSize) {
            this.gameStatus = 2;
        }
    }

    public Player getWhoseTurn() {
        if (this.gameStatus == 0) {
            this.gameStatus = 1;
            double r = Math.random();
            if (r <= 0.5) {
                this.nowPlaying = 0;
                return this.players.get(0);
            } else {
                this.nowPlaying = 1;
                return this.players.get(1);
            }
        } else {
            if (this.nowPlaying == 0) {
                this.nowPlaying = 1;
                return this.players.get(1);
            } else {
                this.nowPlaying = 0;
                return this.players.get(0);
            }
        }
    }
}
