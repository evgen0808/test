package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class PlayerHuman implements Player {
    String name = null;
    int wins = 0;
    int loses = 0;
    int ties = 0;
    int roundScore = 0;
    Card[] roundCards = null;
    int cardsCount = 0;

    PlayerHuman(String name) {
        this.name = name;
        roundCards = new Card[36];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void loseGame() {
        loses++;
    }

    @Override
    public void winGame() {
        wins++;
    }

    @Override
    public void tieGame() {
        ties++;
    }

    @Override
    public int getWins() {
        return wins;
    }

    @Override
    public void takeCard(Card card) {
        roundCards[cardsCount++] = card;
        updateScore();
    }

    @Override
    public void resetCards() {
        cardsCount = 0;
        updateScore();
    }

    @Override
    public boolean isWillingToTakeCard() {
        Scanner in = new Scanner(System.in);
        System.out.println("  More?");
        String answer = in.next();
        while (!answer.equals("y") && !answer.equals("n")) {
            System.out.println("  Wrong answer. Enter y or n: ");
            answer = in.next();
        }
        return answer.equals("y");
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int getRoundScore() {
        return roundScore;
    }

    @Override
    public Card[] getRoundCards() {
        return Arrays.copyOfRange(roundCards, 0, cardsCount);
    }

    private void updateScore() {
        roundScore = 0;
        boolean acesFound = false;
        for (int i = 0; i < cardsCount; i++) {
            switch (roundCards[i].getValue()) {
                case N6:
                    roundScore += 6;
                    break;
                case N7:
                    roundScore += 7;
                    break;
                case N8:
                    roundScore += 8;
                    break;
                case N9:
                    roundScore += 9;
                    break;
                case N10:
                    roundScore += 10;
                    break;
                case NJ:
                    roundScore += 2;
                    break;
                case NQ:
                    roundScore += 3;
                    break;
                case NK:
                    roundScore += 4;
                    break;
                case NA:
                    roundScore += 1;
                    acesFound = true;
                    break;
            }
        }
        if (roundScore <= 11 && acesFound) {
            roundScore += 10;
        }
    }
}