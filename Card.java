package flashcards;

public class Card<T, U> {
    private T term;
    private U definition;
    private int errorness;

    public Card(T t, U d, int e) {
        this.term = t;
        this.definition = d;
        this.errorness = e;
    }

    public Card(T t, U d) {
        this.term = t;
        this.definition = d;
        this.errorness = 0;
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
