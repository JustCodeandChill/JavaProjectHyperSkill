package flashcards;

public class Card<T> {
    private T term;
    private T definition;

    public Card(T t, T d) {
        this.term = t;
        this.definition = d;
    }

    public T getDefinition() {
        return definition;
    }

    public void setDefinition(T definition) {
        this.definition = definition;
    }

    public T getTerm() {
        return term;
    }

    public void setTerm(T term) {
        this.term = term;
    }
}
