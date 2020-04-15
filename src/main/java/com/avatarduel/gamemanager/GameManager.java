package com.avatarduel.gamemanager;

import com.avatarduel.gamemanager.phase.*;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.avatarduel.cards.*;


public class GameManager {
    // attribute
    private Phase phase;
    private Player player;
    private Player enemy;

    // ctor
    GameManager() {
        this.phase = new DrawPhase(this);
        this.player = new Player();
        this.enemy = new Player();
    }
    GameManager(Player player, Player enemy) {
        this.phase = new DrawPhase(this);
        this.player = player;
        this.enemy = enemy;
    }
    /**
     * @return the phase
     */
    public Phase getPhase() {
        return phase;
    }
    /**
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * @return the enemy
     */
    public Player getEnemy() {
        return enemy;
    }
    /**
     * @param phase the phase to set
     */
    public void setPhase(Phase phase) {
        this.phase = phase;
    }
    /**
     * @param player the player to set
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    /**
     * @param enemy the enemy to set
     */
    public void setEnemy(Player enemy) {
        this.enemy = enemy;
    }


    public void changeTurn() {
        Player temp = player;
        player = enemy;
        enemy = temp;
    }
    public void printPhaseInfo() {
        this.phase.phaseInfo();
    }
    public void changePhase(Phase f) {
        this.phase = f;
    }
    public void nextPhase() {
        this.phase.nextPhase();
    }
    public static void main(String[] args) {

        // instantiate game
        GameManager game = new GameManager();
        boolean endGame = false;

        Player player, enemy;
        Scanner scanner = new Scanner(System.in);
        game.getPlayer().setNama("Fitra");
        game.getEnemy().setNama("Satrio");

        while(!endGame) {
            // check turn
            player = game.getPlayer();
            enemy = game.getEnemy();
            player.printNama();

            // draw phase
            if (game.getPhase().getType() == PhaseType.DRAW) {
                game.printPhaseInfo();
                player.draw();
                System.out.println("daftar perintah");
                System.out.println("0. next phase");
                System.out.println("1. print field");
                System.out.println("2. print cardsInHand");
                System.out.println("3. print enemy field");
                int input = scanner.nextInt();
                if (input == 0) {
                    game.nextPhase();
                } else if (input == 1) {
                    player.getField().printCharacterRow();
                } else if (input == 2) {
                    player.printCardsInHand();
                } else if (input == 3) {
                    enemy.getField().printCharacterRow();
                } else {
                    System.out.println("perintah salah!");
                    System.out.println(input);
                }

            // main phase
            } else if (game.getPhase().getType() == PhaseType.MAIN) {
                game.printPhaseInfo();
                System.out.println("daftar perintah");
                System.out.println("0. next phase");
                System.out.println("1. print field");
                System.out.println("2. print cardsInHand");
                System.out.println("3. print enemy field");
                System.out.println("4. taruh character ke field dalam posisi attack");
                System.out.println("5. taruh character ke field dalam posisi defense");
                System.out.println("6. ubah posisi character");
                System.out.println("7. taruh skill ke field");
                System.out.println("8. buang skill dari field");
                int input = scanner.nextInt();
                if (input == 0) {
                    game.nextPhase();
                } else if (input == 1) {
                    player.getField().printCharacterRow();
                } else if (input == 2) {
                    player.printCardsInHand();
                } else if (input == 3) {
                    enemy.getField().printCharacterRow();
                } else if (input == 4) {
                    System.out.println("Masukkan index cardsInHand: ");
                    int posInHand = scanner.nextInt();
                    System.out.println("Masukkan index field: ");
                    int posInField = scanner.nextInt();
                    game.getPhase().process(Command.SUMMONATTACK, posInHand, posInField, 0, true);
                } else if (input == 5) {
                    System.out.println("Masukkan index cardsInHand: ");
                    int posInHand = scanner.nextInt();
                    System.out.println("Masukkan index field: ");
                    int posInField = scanner.nextInt();
                    game.getPhase().process(Command.SUMMONDEFENSE, posInHand, posInField, 0, true);
                } else if (input == 6) {
                    System.out.println("Masukkan index field: ");
                    int posInField = scanner.nextInt();
                    game.getPhase().process(Command.CHANGEPOSITION, 0, posInField, 0, true);
                } else if (input == 7) {
                    System.out.println("Masukkan index cardsInHand: ");
                    int posInHand = scanner.nextInt();
                    System.out.println("Masukkan index field: ");
                    int posInField = scanner.nextInt();
                    System.out.println("Masukkan index target di field");
                    int target = scanner.nextInt();
                    System.out.println("Apakah target adalah punyamu?true/false");
                    boolean isOnPlayer = true;
                    try {
                        isOnPlayer = scanner.nextBoolean();
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input!");
                    }
                    game.getPhase().process(Command.PLACESKILL, posInHand, posInField, target, isOnPlayer);
                } else if (input == 8) {
                    System.out.println("Masukkan index field: ");
                    int posInField = scanner.nextInt();
                    game.getPhase().process(Command.REMOVESKILL, 0, posInField, 0, true);
                } else {
                    System.out.println("perintah salah!");
                }
            } else {
                game.printPhaseInfo();
                System.out.println("daftar perintah");
                System.out.println("0. next phase");
                System.out.println("1. print field");
                System.out.println("2. print cardsInHand");
                System.out.println("3. print enemy field");
                System.out.println("4. serang kartu lawan");
                int input = scanner.nextInt();
                if (input == 0) {
                    game.nextPhase();
                } else if (input == 1) {
                    player.getField().printCharacterRow();
                } else if (input == 2) {
                    player.printCardsInHand();
                } else if (input == 3) {
                    enemy.getField().printCharacterRow();
                } else {
                    System.out.println("perintah salah!");
                }
            }
        }

    }
}