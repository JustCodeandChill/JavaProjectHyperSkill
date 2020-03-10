package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class FlashCardCoordinatorStageSix<T> {
    FlashCardDeckForStageSix flashcards;
    Scanner scanner;

    public FlashCardCoordinatorStageSix(FlashCardDeckForStageSix flashCardDeck) {
        this.flashcards = flashCardDeck;
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
        T definition = (T) scanner.nextLine();

        if (flashcards.containsDefinition((T) definition)) {
            System.out.println("The definition \"" + definition + "\" already exists.");
            return;
        }

        Card card = new Card(term, definition);
        flashcards.addCard(card);
        System.out.println("The pair (\""+ term + "\":\""+ definition + "\") has been added.");
        //System.out.println(flashcards.toString());
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

    public void importCards() {
        System.out.println("File name:");
        T filename = (T) scanner.nextLine();

        File file = new File("./" + filename);
        if (file.exists()) {
            try (Scanner scanner = new Scanner(file)){
//            scanner.skip("\n");
                int size = 0;
                while (scanner.hasNext()) {
                    T term = (T) scanner.nextLine();
                    T definition = (T) scanner.nextLine();
//                    System.out.println(term + ":" + definition);
                    size++;
                    if (flashcards.containsTerm(term)) {
                        if (flashcards.containsDefinition((T) definition))
                            continue;

                        flashcards.replaceCard(term, definition);
                    }else {
                        Card card = new Card(term, definition);
                        flashcards.addCard(card);
                    }
                }

                this.scanner = new Scanner(System.in);
                System.out.println( size + " cards have been loaded.");

            }catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        }else {
            System.out.println("File not found.");
        }
    }

    public void export() {
        System.out.println("File name:");
        T filename = (T) scanner.nextLine();

        File file = new File("./" + filename);
        try (FileWriter writer = new FileWriter(file)){
            int size = 0;
            for (Object term : flashcards.keySet()) {
                var definition = flashcards.getDefinitionFromTerm(term);
                writer.write(term + "\n");
                writer.write(definition + "\n");
                size++;
            }

            System.out.println(size + " cards have been saved.");
        }catch (IOException e){
            System.out.println(" An exception occur" + e);
        }
    }

    public void ask() {
        System.out.println("How many times to ask?");
        int times =  scanner.nextInt();
        scanner.skip("\n");
        int size = flashcards.size();

        if (times > size) {
//            System.out.println("In times > size");
            int timeToLoopTheWholeDeck = times / size;
            int numberOfCardsToLoop = times % size;
            int count = 0;

            //Loop the whole deck
            while (count < timeToLoopTheWholeDeck ){
                askDefinitionOfAllCards();
                count++;
            }
            // Loop timeToLoopTheDeckPartially elements in the deck
//            System.out.println("In times > size part2");
            askDefinitionOfNCards(numberOfCardsToLoop + 1);
        }else if (times < size) {
            int numberOfCardsToLoop = size - times + 1;
//            System.out.println("In times < size");
            askDefinitionOfNCards(numberOfCardsToLoop);
        }else {
            askDefinitionOfAllCards();
        }
    }

    public void askDefinitionOfAllCards() {
        for (Object term : flashcards.keySet()) {

            System.out.println("Print the definition of \"" + term + "\":");
            T answer = (T) scanner.nextLine();

            T correctAnswer = (T) flashcards.getDefinitionFromTerm(term);
            //System.out.println("Answer:" + answer + " .Correct answer:" + correctAnswer);
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
        for (Object term : flashcards.keySet()) {
            if (count == n - 1) break;
            System.out.println("Print the definition of \"" + term + "\":");
            T answer = (T) scanner.nextLine();

            T correctAnswer = (T) flashcards.getDefinitionFromTerm(term);

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
        }
    }
}
