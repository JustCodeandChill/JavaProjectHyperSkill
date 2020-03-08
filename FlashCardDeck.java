package flashcards;

import java.util.*;

public class FlashCardDeck<T, U> {
    private Map<T, U> flashcards;

    public FlashCardDeck() {
        flashcards = new LinkedHashMap<>();
    }

    public void addCard(Card<T, U> c) {
        flashcards.put(c.getTerm(), c.getDefinition());
    }

    public U getDefinitionFromTerm(T term) {
        Set<Map.Entry<T, U>> entrySet = flashcards.entrySet();

        for (Map.Entry<T, U> pair : entrySet) {
            if (Objects.equals(pair.getKey(), term)) {
                return pair.getValue();
            }
        }
        return null;
    }

    public T getTermFromDefinition(T definition) {
        Set<Map.Entry<T, U>> entrySet = flashcards.entrySet();

        for (Map.Entry<T, U> pair : entrySet) {
            if (Objects.equals(pair.getValue(), definition)) {
                return pair.getKey();
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
