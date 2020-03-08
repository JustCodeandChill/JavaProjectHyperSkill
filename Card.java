package flashcards;

public class Card<T, U> {
    private T term;
    private U definition;

    public Card(T t, U d) {
        this.term = t;
        this.definition = d;
    }

    public U getDefinition() {
        return definition;
    }

    public void setDefinition(U definition) {
        this.definition = definition;
    }

    public T getTerm() {
        return term;
    }

    public void setTerm(T term) {
        this.term = term;
    }
}
