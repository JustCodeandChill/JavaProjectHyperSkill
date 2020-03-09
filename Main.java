package flashcards;


import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Instantiaze scanner
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        //flash cards will be store in a Hash map in form "term" : "definition"
        FlashCardDeck<String, String> flashCards = new FlashCardDeck<>();
        FlashCardCoordinator application = new FlashCardCoordinator(flashCards);

        String action =  " ";
        boolean isValidAction = true;
        String actionToExit = "exit";
        do {
            scanner = new Scanner(System.in).useDelimiter("\\n");
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            action = scanner.nextLine();

            switch (action){
                case "add":
                    application.add();
                    break;

                case "remove":
                    application.remove();
                    break;

                case "import":
                    application.importFile();
                    break;

                case "export":
                    application.export();
                    break;

                case "ask":
                    application.ask();
                    break;

                case "exit":
                    System.out.println("Bye bye!");
                    break;
                default:
                    System.out.println("Action not found" + action);
                    isValidAction = false;
            }
        }while(!Objects.equals(action, actionToExit) && isValidAction);



        //Ask for all the definition in numberOfCards times and build the cardDefinition array
        // When the user tries to add a duplicated term or definition,
        // forbid it and ask again until the user inputs a unique one
//        while (count < numberOfCards){
//            System.out.println("The card #" + (count+1) + ":");
//            String term = scanner.nextLine();
//
//            while (flashCards.containsTerm(term)) {
//                System.out.println("The card \"" + term + "\" already exists. Try again:");
//                term = scanner.nextLine();
//            }
//
//            System.out.println("The definition of the card #" + (count+1) + ":");
//            String definition = scanner.nextLine();
//
//            while (flashCards.containsDefinition(definition)) {
//                System.out.println("The definition \"" + definition + "\" already exists. Try again:");
//                definition = scanner.nextLine();
//            }
//
//            //create a flash card and put in flash cards
//            //flashCards is in form of term : definition
//            Card<String, String> card = new Card<>(term, definition);
//            flashCards.addCard(card);
//
//            count++;
//        }
//
//        //the memory game start,
//        //Ask all card's definitions in the order of addition. If wrong, outpu the correct definition
//        //If the definition is wrong for the current term but it is correct for another, output the original term.
//        for (String term : flashCards.keySet()) {
//
//            System.out.println("Print the definition of \"" + term + "\":");
//            String answer = scanner.nextLine();
//
//            String correctAnswer =  flashCards.getDefinitionFromTerm(term);
//
//            if (Objects.equals(correctAnswer, answer)) {
//                System.out.println("Correct answer.");
//            }else{
//
//                if (flashCards.containsDefinition(answer)) {
//                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\", you've just written the definition of \"" + flashCards.getTermFromDefinition(answer) + "\".");
//
//                }else {
//                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\".");
//                }
//            }
//        }
    }
}
