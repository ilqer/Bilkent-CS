/**
 * Lab09_Q1
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class FlashCardApp {
    
    public static ArrayList<FlashCard> readCards( String filename ){

        String line;
        String[] data;
        
        //creates an empty list to store FlashCards
        ArrayList<FlashCard> cardList = new ArrayList<FlashCard>();

        //create a File object representing file to read
        File inFile = new File( filename );

        //try the following
        try {
            //create Scanner that reads from file with given name
            Scanner file = new Scanner( inFile );

            //while file contains more data
            while( file.hasNext() ) {
                //read the next line from the file
                line = file.nextLine();
                

                //split the line into tokens (hint: String split() method )
                data = line.split("\t",5);
                
                //create a FlashCard object using the line tokens - don't forget convert difficulty to int
                FlashCard card = new FlashCard(data[0],data[1],Integer.valueOf(data[2]));
                
                //add the card to the list
                cardList.add(card);
            }
            //close the file
            file.close();
        }catch( FileNotFoundException f ){
            System.out.println("file cannot be opened");
        }
        //return the ArrayList containing FlashCards from the file
        return cardList;
    }
    public static void bubbleSort( ArrayList<FlashCard> cardList){
        FlashCard card ;
        for(int i = 0;i<cardList.size()-1;i++){
            for(int count = 0; count<cardList.size()-1;count++){
                if(cardList.get(count).getQuestion().compareTo(cardList.get(count+1).getQuestion())>0){
                    card = cardList.get(count);
                    cardList.set(count,cardList.get(count+1)) ;
                    cardList.set(count+1,card) ;
                }
            }
        }
    }
    public static void sortByDifficulty( ArrayList<FlashCard> cardList){
        FlashCard card ;
        for(int i = 0;i<cardList.size()-1;i++){
            for(int count = 0; count<cardList.size()-1;count++){
                if(cardList.get(count).getDifficulty()>cardList.get(count+1).getDifficulty()){
                    card = cardList.get(count+i);
                    cardList.set(count,cardList.get(count+1)) ;
                    cardList.set(count+1,card) ;
                }
            }
        }

    }
    public static void main(String[] args) {
        int number;
        int score;
        String guess;
        String diff;
        ArrayList<FlashCard> flashCards = new ArrayList<FlashCard>();
        ArrayList<FlashCard> newCards = new ArrayList<FlashCard>();
        ArrayList<FlashCard> wrongCards = new ArrayList<FlashCard>();
        Scanner scan = new Scanner(System.in);
        Random rand = new Random(); 
        flashCards = readCards( "C:\\Users\\ilker.yigitel-ug\\Downloads\\turkish_english_words.txt" );

        score = 0;

        System.out.print("Enter the number of flash cards to generate:");
        number = scan.nextInt();
        System.out.println("Let's play!"); 
        System.out.print("Do you want cards in accending difficulty:");
        diff = scan.next();
        System.out.println();
        for(int count = 0;count<number;count++){
            boolean same;
            same = false;
            int random = rand.nextInt(flashCards.size());
            for(int count2 = 0;count2<newCards.size();count2++){
                if(flashCards.get(random).getQuestion().equals(newCards.get(count2).getQuestion())){
                    same = true;
                    count2 = Integer.MAX_VALUE;
                }
            }
            if(!same){
                newCards.add(flashCards.get(random));
            }
            else{
                count--;
            }
        }
        bubbleSort(newCards);
        if(diff.equals("Yes") || diff.equals("yes")){
            sortByDifficulty(newCards);
        }

        for(int count = 0; count<newCards.size();count++){
            newCards.get(count).showFlashCardQuestion();
            System.out.println("Enter your guess:");
            guess = scan.next();
            if(guess.equals(newCards.get(count).getAnswer())){
                System.out.println("Correct!");
                score++;
            }
            else{
                System.out.println("Wrong answer!");
                System.out.println("Let’s see the correct answer:");
                newCards.get(count).showFlashCardAnswer();
                wrongCards.add(newCards.get(count));
            }
            System.out.println();
        }
        System.out.println("Your score:"+score+"/"+number);

        System.out.println("Words you need to review:");
        System.out.println(wrongCards.toString());
    }
}