package flashcards;

import java.util.*;

public class FlashCardDeck<T, U> {
    private Map<T, U> flashcards;

    public FlashCardDeck() {
        flashcards = new LinkedHashMap<>();
    }

    public void addCard(Card c) {
        flashcards.put((T) c.getTerm(), (U) c.getDefinition());
    }

    public T getDefinitionFromTerm(T term) {
        return (T) flashcards.get(term);
    }

    public T getTermFromDefinition(T definition) {
        Set<Map.Entry<T, U>> entrySet = flashcards.entrySet();

        for (Map.Entry<T, U> pair : entrySet) {
            if (Objects.equals(pair.getValue(), definition)) {
                return (T) pair.getValue();
            }
        }
        return null;
    }

    public boolean containsTerm(T term) {
        Set<Map.Entry<T, U>> entrySet = flashcards.entrySet();

        for (Map.Entry<T, U> pair : entrySet) {
            if (pair.getKey().equals(term)) return true;
        }
        return false;
    }

    public boolean containsDefinition(T definition) {
        Set<Map.Entry<T, U>> entrySet = flashcards.entrySet();

        for (Map.Entry<T, U> pair : entrySet) {
            if (pair.getValue().equals(definition)) return true;
        }
        return false;
    }

    public Set<T> keySet() {
        return flashcards.keySet();
    }
}
