package flashcards;
import java.util.*;

public class FlashCardCoordinator<T, U> {
    private FlashCardDeck<T, U> flashcards;
    Scanner scanner;

    public FlashCardCoordinator(FlashCardDeck<T, U> newFlashCards) {
        this.flashcards = newFlashCards;
        scanner = new Scanner(System.in);
    }

    public void add() {
        System.out.println("The card:");
        T term = (T) scanner.nextLine();

        if (flashcards.containsTerm(term)) {
            System.out.println("The card \"" + term + "\" already exists.");
            return;
        }

        System.out.println("The definition of the card:");
        U definition = (U) scanner.nextLine();

        if (flashcards.containsDefinition((T) definition)) {
            System.out.println("The definition \"" + definition + "\" already exists.");
            return;
        }

        Card<T, U> card = new Card<>(term, definition);
        flashcards.addCard(card);
        System.out.println("The pair (\""+ term + "\":\""+ definition + "\") has been added.");
    }

    public void remove() {
        System.out.println("The card:");
        T term = (T) scanner.nextLine();

        if (flashcards.containsTerm(term)) {
            flashcards.removeCard(term);
            System.out.println("The card has been removed.");
        } else {
            System.out.println("Can't remove \"" + term + "\": there is no such card");
        }
    }

    public void askDefinitionOfAllCards() {
        for (T term : flashcards.keySet()) {

            System.out.println("Print the definition of \"" + term + "\":");
            T answer = (T) scanner.nextLine();

            T correctAnswer = (T) flashcards.getDefinitionFromTerm(term);
            System.out.println("Answer:" + answer + "Correct answer:" + correctAnswer);
            if (Objects.equals(correctAnswer, answer)) {
                System.out.println("Correct answer.");
            } else {
                if (flashcards.containsDefinition(answer)) {
                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\", you've just written the definition of \"" + flashcards.getTermFromDefinition(answer) + "\".");
                } else {
                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\".");
                }
            }
        }
    }
    public void askDefinitionOfNCards(int n) {
        int count = 0;
        for (T term : flashcards.keySet()) {

            System.out.println("Print the definition of \"" + term + "\":");
            T answer = (T) scanner.nextLine();

            U correctAnswer = flashcards.getDefinitionFromTerm(term);

            if (Objects.equals(correctAnswer, answer)) {
                System.out.println("Correct answer.");
            } else {
                if (flashcards.containsDefinition(answer)) {
                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\", you've just written the definition of \"" + flashcards.getTermFromDefinition(answer) + "\".");
                } else {
                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\".");
                }
            }
            count++;
            if (count == n - 1) break;
        }
    }
    public void ask() {
        System.out.println("How many times to ask?");
        int times =  scanner.nextInt();
        int size = flashcards.size();

        if (times > size) {
            int timeToLoopTheWholeDeck = times / size;
            int numberOfCardsToLoop = times % size;
            int count = 0;

            //Loop the whole deck
            while (count < timeToLoopTheWholeDeck){
                askDefinitionOfAllCards();
                count++;
            }
            // Loop timeToLoopTheDeckPartially elements in the deck
            askDefinitionOfNCards(numberOfCardsToLoop);
        }else if (times < size) {
            int numberOfCardsToLoop = size - times;
            askDefinitionOfNCards(numberOfCardsToLoop);
        }else {
            askDefinitionOfAllCards();
        }
    }
}
