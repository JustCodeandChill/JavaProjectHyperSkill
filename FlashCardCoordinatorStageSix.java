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
    ArrayList<String> logFile;

    public FlashCardCoordinatorStageSix(FlashCardDeckForStageSix flashCardDeck) {
        this.flashcards = flashCardDeck;
        scanner = new Scanner(System.in);
        this.logFile =  new ArrayList<>();
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
//                    Integer errorness = Integer.valueOf(scanner.nextLine());
//                    System.out.println(term + ":" + definition);
                    size++;
                    if (flashcards.containsTerm(term)) {
                        if (flashcards.containsDefinition((T) definition))
                            continue;

                        flashcards.replaceCard(term, definition, 2);
                    }else {
                        Card card = new Card(term, definition, 5);
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
                Card card = flashcards.getCardByTerm(term);
                card.increaseErrornessByOne();
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
                Card card = flashcards.getCardByTerm(term);
                card.increaseErrornessByOne();
                if (flashcards.containsDefinition(answer)) {
                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\", you've just written the definition of \"" + flashcards.getTermFromDefinition(answer) + "\".");
                } else {
                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\".");
                }
            }
            count++;
        }
    }
//End of stage 5 functions

// Stage 6 functions
    public void hardestCard() {
        int max = Integer.MIN_VALUE;
        ArrayList<T> errorCards = new ArrayList<>();

        for (Object term : flashcards.keySet()) {
            Card card = flashcards.getCardByTerm(term);
            
            if (max < card.getErrorness())
                max = card.getErrorness();
        }
        
        //Find card with most error
        if (max < 0) {
            String line = "There are no cards with errors.";
            System.out.println(line);
            this.logFile.add(line);
            return;
        }

        if (max == 0) {
            String line = "There are no cards with errors.";
            System.out.println("There are no cards with errors.");
            this.logFile.add(line);
            return;
        }

        for (Object term : flashcards.keySet()) {
            Card card = flashcards.getCardByTerm(term);
            if (card.getErrorness() == max)
                errorCards.add((T) card.getTerm());
        }

        printErrorStatistic(errorCards);
    }
    
    public void printErrorStatistic(ArrayList<T> errorCards) {
        int numberOfErrorTerm = errorCards.size();
        String line = "";

        if (logFile == null)
            System.out.println("It is null");

        if (numberOfErrorTerm == 0) {
            System.out.println("There are no cards with errors.");
            this.logFile.add(line);
        }else if (numberOfErrorTerm == 1) {
            T term = errorCards.get(0);
            int numberOfError = flashcards.getCardByTerm(term).getErrorness();
            line = "The hardest card is \"" + term + "\". You have " + numberOfError + " errors answering it.";

            System.out.println(line);
            this.logFile.add(line);

        }else if (numberOfErrorTerm > 1) {
            line += "The hardest cards are ";
            System.out.print("The hardest cards are ");
            for (T term : errorCards) {
                // To print out "term. " if it is the last word of error cards
                if (errorCards.lastIndexOf(term) != (errorCards.size() - 1)) {
                    line += "\"" + term + "\", ";
                    System.out.print("\"" + term + "\", ");
                }
                else{
                    line += "\"" + term + "\". ";
                    System.out.print("\"" + term + "\". ");
                }
            }
            int numberOfError = flashcards.getCardByTerm(errorCards.get(0)).getErrorness();
            line += "You have " + numberOfError + " errors answering them.";
            System.out.print("You have " + numberOfError + " errors answering them.\n");
            this.logFile.add(line);
        }
    }

    public void resetStats() {
        for (Object term : flashcards.keySet()) {
            Card card = flashcards.getCardByTerm(term);
            card.setErrorness(0);
        }
        String line = "reset";
        System.out.println(line);
        this.logFile.add(line);
    }

    public void log() {
        System.out.println("File name:");
        T filename = (T) scanner.nextLine();
//        for (int i = 0; i < logFile.size(); i++) {
//            System.out.print(logFile.get(i));
//        }
//        System.out.println("");

        File file = new File("./" + filename);
        try (FileWriter fw = new FileWriter(file)) {
            for (int i = 0; i < this.logFile.size() - 1; i++) {
                T line = (T) logFile.get(i);
                fw.write((String) line);
                fw.write("\n");
            }
            String line = "saved";
            System.out.println(line);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
