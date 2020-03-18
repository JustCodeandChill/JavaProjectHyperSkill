This Repository contains all the Java Project I finished in https://hyperskill.org/ website

How to install and run project
- Clone this repository
- Open the folder with project name you want to run
- Open the Main.java file in your favorite IDE
- Hit run

The first project is Flash Card project:
 Support these actions:
    add a card: add,
    remove a card: remove,
    load cards from file ("deserialization"): import,
    save cards to file ("serialization"): export,
    ask for a definition of some random cards: ask,
    saves the application log to the given file: log,
    prints the term of the card that has the most mistakes: hardest card,
    erases the mistake counts for all cards: reset stats,
    exit the program: exit.

    java Flashcards -import words13june.txt -export words14june.txt read the command-line arguments and perform import at the   head of Main and export at the end of Main file

You can use the following file format. The file consists of pairs of lines. The first line of each pair is a term, and the second line is a definition.

In this stage, if you try to add a card with an existing term or an existing definition, the application must just reject it by printing an error message (see example 1).

When you load cards from a file, you shouldn't erase the cards that aren't in the file. If the imported card already exists, it should update the old one (look at cards Japan and Moscow in the example 2). It is guaranteed, that there won't be any conflicts with definitions in the tests.
Examples

The symbol > represents the user input. Notice that it's not the part of the input.

Example:
Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> hardest card
There are no cards with errors.
 
Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> import
File name:
> capitals.txt
28 cards have been loaded.
 
Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> hardest card
The hardest card is "France". You have 10 errors answering it.
 
Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> ask
How many times to ask?
> 1
Print the definition of "Russia":
> Paris
Wrong answer. (The correct one is "Moscow", you've just written the definition of "France" card.)
 
Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> hardest card
The hardest cards are "Russia", "France". You have 10 errors answering them.
 
Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> reset stats
Card statistics has been reset.
 
Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> hardest card
There are no cards with errors.
 
Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> log
File name:
> todayLog.txt
The log has been saved.
 
Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):
> exit
Bye bye!
