package flashcards;

public class Main {
    public static void main(String[] args) {
        //Instantiaze scanner
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

        //There are 3 provided inputs, term, definition and answer in 3 lines
        System.out.println("Input the number of cards:");
        int numberOfCards = scanner.nextInt();

        String[] cardDefinitions = new String[numberOfCards];
        String[] cardTerms = new String[numberOfCards];
        scanner.nextLine();
        //Ask for all the term in numberOfCards times and build the cardTerms array
        int count = 0;

        //flash cards will be store in a Hash map in form "term" : "definition"
        Map<String, String> flashCardsInFormDefinitionTerm = new LinkedHashMap<>();
        Map<String, String> flashCardsInFormTermDefinition = new LinkedHashMap<>();

        while (count < numberOfCards){
            System.out.println("The card #" + (count+1) + ":");
            String term = scanner.nextLine();

            while (flashCardsInFormTermDefinition.containsKey(term)) {
                System.out.println("The card \"" + term + "\" already exists. Try again:");
                term = scanner.nextLine();
            }

            System.out.println("The definition of the card #" + (count+1) + ":");
            String definition = scanner.nextLine();

            while (flashCardsInFormDefinitionTerm.containsKey(definition)) {
                System.out.println("The definition \"" + definition + "\" already exists. Try again:");
                definition = scanner.nextLine();
            }

            //create a flash card and put in flash cards
            flashCardsInFormTermDefinition.put(term ,definition);
            flashCardsInFormDefinitionTerm.put(definition, term);

            count++;
        }

        //Ask for all the definition in numberOfCards times and build the cardDefinition array

        count = 0;
        List keys = new ArrayList(flashCardsInFormTermDefinition.keySet());
        for (int i = 0; i < keys.size(); i++) {
            Object obj = keys.get(i);

            System.out.println("Print the definition of \"" + obj.toString() + "\":");
            String answer = scanner.nextLine();

            String correctAnswer = flashCardsInFormTermDefinition.get(obj);

            if (Objects.equals(correctAnswer, answer)) {
                System.out.println("Correct answer.");
            }else{

                if (flashCardsInFormDefinitionTerm.containsKey(answer)) {
                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\", you've just written the definition of \"" + flashCardsInFormDefinitionTerm.get(answer) + "\".");

                }else {
                    System.out.println("Wrong answer. The correct one is \"" + correctAnswer + "\".");
                }
            }
        }
        // while (count < numberOfCards){
        //     System.out.println("Print the definition of \"" + cardTerms[count] + "\":");
        //     String answer = scanner.nextLine();
        //     if (answer.equals(cardDefinitions[count]))
        //     {
        //         System.out.println("Correct answer.");
        //     }else{
        //         System.out.println("Wrong answer. The correct one is \"" + cardDefinitions[count] + "\".");
        //     }
        //     count++;
        // }
    }
}
