import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

/**My Name: Daniella Brandt**/

public class ChainReactionMain {

    public static void main(String[] args){

        ArrayList<ArrayList<String>> wordSets = new ArrayList<>();
        String filename = "wordList.txt";

        try{
            FileInputStream file = new FileInputStream(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String words = scanner.nextLine();
                String[] wordArray = words.split(",");
                ArrayList<String> row = new ArrayList<>(Arrays.asList(wordArray));
                wordSets.add(row);
            }
        }
        catch (FileNotFoundException e){
            System.out.printf("File %s does not exist\n",filename);
        }

        cleanData(wordSets);

        while(true)
        {
            int chainLength = 0;
            int guesses = 0;

            System.out.println("*********************************");
            System.out.println("*                               *");
            System.out.println("*       CHAIN REACTION          *");
            System.out.println("*  CAN YOU COMPLETE THE CHAIN?  *");
            System.out.println("*                               *");
            System.out.println("*********************************\n");
			System.out.println("Tutorial..................press 0");
            System.out.println("Beginner..................press 1");
            System.out.println("Pro.......................press 2");
            System.out.println("Superstar.................press 3");
            System.out.println("Custom....................press 4\n");
            System.out.print("SELECT DIFFICULTY: ");
            int difficulty = new Scanner(System.in).nextInt();
            switch (difficulty) {
                case 0:{
                    chainLength = 3;
                    guesses = 3;
                    break;
                }
                case 1:{
                    chainLength = 3;
                    guesses = 10;
                    break;
                }
                case 2: {
                    chainLength = 5;
                    guesses = 15;
                    break;
                }
                case 3: {
                    chainLength = 7;
                    guesses = 20;
                    break;
                }
                default: {
                    System.out.print("Enter Chain Length: ");
                    chainLength = new Scanner(System.in).nextInt();
                    System.out.print("Enter Number of Guesses: ");
                    guesses = new Scanner(System.in).nextInt();
                    break;
                }
            }

            ChainReaction c = new ChainReaction(guesses,chainLength, wordSets);
            c.playGame();
            System.out.println("\nPlay Again (y)es or (n)o");
            char option = new Scanner(System.in).next().toLowerCase().charAt(0);
            if(option == 'n'){
                System.out.println("\nTHANK YOU FOR PLAYING!");
                break;
            }
        }
    }

    public static void cleanData(ArrayList<ArrayList<String>> wordSets){
	/**Add Code here to clean data set**/
        //my code to clean dataset.....
        //here im creating a Set!, to store all the words, that comes as 1st word in 
        //every row.....
        Set<String>First_word=new HashSet<>();
        //start iterating over loop
        for(ArrayList<String> row : wordSets){
            if(!row.isEmpty()){
                //adding first words in the created set here...... if the row is not empty only!!!!!!!
                First_word.add(row.get(0));
            }
        }
        //now here i'm iterating over the loop of words Set to remove Invalid words......
       for (ArrayList<String> row : wordSets) {
            row.removeIf(word -> !First_word.contains(word));
        }

       //here, I'm removing a row, that has or include onlky the one 'Valid' word!!!!!!!
       wordSets.removeIf(row -> row.size() <=1);	  
       
        validate(wordSets);
    }
    public static void validate(ArrayList<ArrayList<String>> wordSets){
        final int wordCountValid = 8033;
        final int wordSetCountValid = 2334;

        int numTotalWords = 0;
        for (ArrayList<String> wordSet : wordSets) {
            for (int j = 0; j < wordSet.size(); j++) {
                numTotalWords++;
            }
        }
        System.out.println("Total Word Count: " + numTotalWords);
        System.out.println("Number of Unique Words: " + wordSets.size());
        String status = "Incomplete";
        if(wordCountValid == numTotalWords && wordSetCountValid == wordSets.size()){
            status = "Complete";
        }
        System.out.println("Dataset Cleaning: " + status);
    }
}
