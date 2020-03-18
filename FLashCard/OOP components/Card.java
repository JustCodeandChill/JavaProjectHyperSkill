package flashcards;

public class Card<T, U> {
    private T term;
    private U definition;
    private int errorness;
    private boolean updateError;

    public Card(T t, U d, int e) {
        this.term = t;
        this.definition = d;
        this.errorness = e;
        this.updateError = false;
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

    public void increaseErrornessByOne() {
        this.errorness += 1;
    }

    public int getErrorness() {
        return errorness;
    }

    public void setErrorness(int errorness) {
        this.errorness = errorness;
    }

    public boolean hasBeenUpdated() {
        setUpdateError(true);
        return this.updateError;
    }

    public boolean getUpdateError() {
        return updateError;
    }

    public void setUpdateError(boolean updateError) {
        this.updateError = updateError;
    }
}
