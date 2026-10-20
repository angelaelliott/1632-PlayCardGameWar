package cs1632;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        //keep track of number of rounds
        int rounds = 0;
        PlayingCardDeck deck = new PlayingCardDeck();
        Queue<PlayingCard> player1 = new LinkedList<>();
        Queue<PlayingCard> player2 = new LinkedList<>();

        // deal the cards evenly to both player's hands
        while (!deck.isEmpty()) {
            PlayingCard c1 = deck.nextCard();
            player1.add(c1);
            PlayingCard c2 = deck.nextCard();
            player2.add(c2);
        }
        // continue playing until either player runs out of cards
        while(!player1.isEmpty() && !player2.isEmpty()) {
            //update number of rounds
            rounds+=1;
            // stop the game in the case of an infinite loop
            if (rounds > 5000) {
                System.out.println("Infinite loop detected! Stopping the game.");
                return;
            }
            playOneRound(player1, player2);
        }
            //if both player's hands are empty, they tied
            if (player1.isEmpty() && player2.isEmpty()) {
                System.out.println("It's a tie!");
                System.out.println("Total rounds played: " + rounds);
            }
            // if one player's hand is empty, they win
            // print the winner and total number of rounds played
            else if (player1.isEmpty()) {
                System.out.println("Player 2 wins the game!");
                System.out.println("Total rounds played: " + rounds);
            } else {
                System.out.println("Player 1 wins the game!");
                System.out.println("Total rounds played: " + rounds);
            }
        }



    public static void playOneRound(Queue<PlayingCard> player1, Queue<PlayingCard> player2) {
        //take the top card from each player's hand
        PlayingCard c1 = player1.poll();
        PlayingCard c2 = player2.poll();

        // compare the cards and determine a winner, add both cards to the winner's hand
        if (c1.playingCardValue() > c2.playingCardValue()) {
            player1.add(c1);
            player1.add(c2);
            System.out.println("Player 1 won this round!");
        } else if (c2.playingCardValue() > c1.playingCardValue()) {
            player2.add(c1);
            player2.add(c2);
            System.out.println("Player 2 won this round!");
        } else {
            // if players play the same card, begin war
            boolean warIsOver = false;
            Queue<PlayingCard> cardPile = new LinkedList<>();
            System.out.println("A war has started");
            cardPile.add(c1);
            cardPile.add(c2);
            while (!warIsOver) {
                // if both players don't have enough cards to play war, they tie
                if (player1.size() < 4 && player2.size() < 4) {
                    player1.clear();
                    player2.clear();
                    System.out.println("Both players ran out of cards! Tie!");
                    warIsOver = true;
                }
                // if one of the players doesn't have enough cards to play war, they lose the war
                else if (player1.size() < 4) {
                    System.out.println("Player 1 doesn't have enough cards for war!");
                    player1.clear();
                    warIsOver = true;
                } else if (player2.size() < 4) {
                    System.out.println("Player 2 doesn't have enough cards for war!");
                    player2.clear();
                    warIsOver = true;
                }
                // if both players have enough cards to play the war, add their top 3 cards to a card pile
                else {
                    for (int i = 0; i < 3; i++) {
                        cardPile.add(player1.poll());
                        cardPile.add(player2.poll());
                    }
                    c1 = player1.poll();
                    c2 = player2.poll();

                    // compare their next card and winner gets the whole pile
                    if (c1.playingCardValue() > c2.playingCardValue()) {
                        player1.add(c1);
                        player1.add(c2);
                        player1.addAll(cardPile);
                        System.out.println("Player 1 won the war!");
                        warIsOver = true;
                    } else if (c2.playingCardValue() > c1.playingCardValue()) {
                        player2.add(c1);
                        player2.add(c2);
                        player2.addAll(cardPile);
                        System.out.println("Player 2 won the war!");
                        warIsOver = true;
                    } else {
                        // if they tie again, play another war
                        System.out.println("The war has tied! Starting another war...");
                        cardPile.add(c1);
                        cardPile.add(c2);
                    }
                }
            }
        }
    }
    }