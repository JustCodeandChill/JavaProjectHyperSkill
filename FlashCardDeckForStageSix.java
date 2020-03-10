package flashcards;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class FlashCardDeckForStageSix<T> {
    ArrayList<Card> flashcards;

    public FlashCardDeckForStageSix() {
        flashcards = new ArrayList<>();
    }

    public void addCard(Card card) {
        flashcards.add(card);
    }

    public void removeCard(T term) {
        Card card = this.returnCardByTerm(term);
        flashcards.remove(card);
    }

    public Card returnCardByTerm(T term) {
        for (Card card : flashcards) {
            if (Objects.equals(card.getTerm(), term)) {
                return card;
            }
        }
        return null;
    }

    public void replaceCard(T term, T definition) {
        Card card = this.returnCardByTerm(term);
        if ( card != null) {
            card.setDefinition(definition);
        }
    }

    public int size() {
        return flashcards.size();
    }

    public Set keySet() {
        Set<T> hash_Set = new HashSet<T>();
        for (Card card : flashcards) {
            hash_Set.add((T) card.getTerm());
        }
        return hash_Set;
    }

    public T getDefinitionFromTerm(T term) {
        for (Card card : flashcards) {
            if (Objects.equals(card.getTerm(), term)) {
                return (T) card.getDefinition();
            }
        }
        return null;
    }

    public T getTermFromDefinition(T definition) {
        for (Card card : flashcards) {
            if (Objects.equals(card.getDefinition(), definition)) {
                return (T) card.getTerm();
            }
        }
        return null;
    }

    public boolean containsTerm(T term) {
        for (Card card : flashcards) {
            if (Objects.equals(card.getTerm(), term)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDefinition(T definition) {
        for (Card card : flashcards) {
            if (Objects.equals(card.getDefinition(), definition)) {
                return true;
            }
        }
        return false;
    }

}
