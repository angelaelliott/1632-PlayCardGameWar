package cs1632;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;
import java.util.Queue;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    public void testPlayer1WinsRound() {
        Queue<PlayingCard> p1 = new LinkedList<>();
        Queue<PlayingCard> p2 = new LinkedList<>();

        // player 1 has a card valued at 11
        p1.add(new PlayingCard(11, 0));
        // player 2 has a card valued at 0
        p2.add(new PlayingCard(0, 0));

        // play one round using their hands
        Main.playOneRound(p1, p2);

        // player 1 should win and take both cards
        assertEquals(2, p1.size());
        // player 2 should lose and have 0 cards
        assertEquals(0, p2.size());
    }

    @Test
    public void testPlayer2WinsRound() {
        Queue<PlayingCard> p1 = new LinkedList<>();
        Queue<PlayingCard> p2 = new LinkedList<>();

        // player 1 has a card valued at 2
        p1.add(new PlayingCard(2, 0));
        // player 2 has a card valued at 12
        p2.add(new PlayingCard(12, 0));

        // play one round using their hands
        Main.playOneRound(p1, p2);

        // player 2 should win and take both cards
        assertEquals(2, p2.size());
        // player 1 should lose and have 0 cards
        assertEquals(0, p1.size());
    }

    @Test
    public void testPlayer1WinsWar() {
        Queue<PlayingCard> p1 = new LinkedList<>();
        Queue<PlayingCard> p2 = new LinkedList<>();

        // player 1 and 2 have cards that tie
        p1.add(new PlayingCard(8, 0));
        p2.add(new PlayingCard(8, 1));

        // add enough cards for each player to play war
        for (int i = 0; i < 3; i++) {
            p1.add(new PlayingCard(0, 0));
            p2.add(new PlayingCard(0, 0));
        }

        // give payer 1 a winning card
        p1.add(new PlayingCard(12, 0));
        p2.add(new PlayingCard(0, 0));

        // play the round and trigger war
        Main.playOneRound(p1, p2);

        // player 1 should win all 10 cards
        assertEquals(10, p1.size());
        // player 2 should lose and have 0
        assertEquals(0, p2.size());
    }

    @Test
    public void testPlayer1DoesNotHaveEnoughCardsForWar() {
        Queue<PlayingCard> p1 = new LinkedList<>();
        Queue<PlayingCard> p2 = new LinkedList<>();

        // player 1 and 2 have cards that tie
        p1.add(new PlayingCard(8, 0));
        p2.add(new PlayingCard(8, 1));

        // player 1 only has 2 cards in their hand
        p1.add(new PlayingCard(0, 0));
        p1.add(new PlayingCard(0, 0));

        // player 2 has 5 cards left in their hand
        for (int i = 0; i < 5; i++) {
            p2.add(new PlayingCard(0, 0));
        }

        // play the round and trigger war
        Main.playOneRound(p1, p2);

        // player 1 should have their hand cleared and lose if they do not have enough cards to play war
        assertEquals(0, p1.size());

        // player 2 should have 2 cards left
        assertEquals(5, p2.size());
    }
}