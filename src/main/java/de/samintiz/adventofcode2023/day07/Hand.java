package de.samintiz.adventofcode2023.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand> {

    private List<Card> cards;
    private long bid;

    private Map<Card, Long> groupedCards;

    public Hand(String hand, long bid) {
        this.cards = convertToCards(hand);
        this.bid = bid;
        this.groupedCards = groupByCard();
    }

    public List<Card> getCards() {
        return cards;
    }

    public long getBid() {
        return bid;
    }

    private List<Card> convertToCards(String hand) {
        return new ArrayList<>(hand.chars().boxed().map(c -> new Card((char) (int) c)).toList());
    }

    private Map<Card, Long> groupByCard() {
        return cards.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private boolean isNTimesOfSameKind(int n) {
        if (!Day07.joker) {
            return groupedCards.values().stream().anyMatch(x -> x == n);
        } else {
            Card jCard = new Card('J');
            long jokerCount = groupedCards.get(jCard) == null ? 0 : groupedCards.get(jCard);
            return groupedCards.entrySet().stream()
                    .anyMatch(x -> x.getValue() + (x.getKey().equals(jCard) ? 0 : jokerCount) == n);
        }
    }

    private boolean isFullHouse() {
        if (!Day07.joker) {
            return groupedCards.values().stream().anyMatch(x -> x == 2)
                    && groupedCards.values().stream().anyMatch(x -> x == 3);
        } else {
            Card jCard = new Card('J');
            long jokerCount = groupedCards.get(jCard) == null ? 0 : groupedCards.get(jCard);
            boolean twoPairs = groupedCards.values().stream().filter(x -> x == 2).count() == 2;
            return (twoPairs && jokerCount == 1) || (groupedCards.values().stream().anyMatch(x -> x == 2)
                    && groupedCards.values().stream().anyMatch(x -> x == 3));
        }
    }

    private boolean hasTwoPair() {
        if (!Day07.joker) {
            return groupedCards.values().stream().filter(x -> x == 2).count() == 2;
        } else {
            Card jCard = new Card('J');
            long jokerCount = groupedCards.get(jCard) == null ? 0 : groupedCards.get(jCard);
            return jokerCount == 2 || groupedCards.values().stream().filter(x -> x == 2).count() == 2;
        }
    }

    private int getValueForType() {
        if (isNTimesOfSameKind(5)) {
            return 70;
        }
        if (isNTimesOfSameKind(4)) {
            return 60;
        }
        if (isFullHouse()) {
            return 50;
        }
        if (isNTimesOfSameKind(3)) {
            return 40;
        }
        if (hasTwoPair()) {
            return 30;
        }
        if (isNTimesOfSameKind(2)) {
            return 20;
        }
        return 10;
    }

    public long getHandValue() {
        long handValue = (getValueForType() * 10000000000L);
        for (int i = 0; i < cards.size(); i++) {
            int invertedIndex = cards.size() - 1 - i;
            handValue += (Card.orderedCharacters.size() - (Card.orderedCharacters.indexOf(cards.get(i).value())))
                    * (long) Math.pow(10.0, invertedIndex * 2);
        }
        return handValue;
    }

    @Override
    public int compareTo(Hand o) {
        long thisVal = this.getHandValue();
        long otheVal = o.getHandValue();
        return Long.compare(thisVal, otheVal);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cards == null) ? 0 : cards.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Hand other = (Hand) obj;
        if (cards == null) {
            if (other.cards != null)
                return false;
        } else if (!cards.equals(other.cards))
            return false;
        return true;
    }
}