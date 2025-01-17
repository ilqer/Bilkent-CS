import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordApp {
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        int option;
        boolean exit = false;
        /*
         * READING FILE
         */

        // as reading a file involves first opening that file there can be exceptions
        // such exceptions should be handled in the code
        // (you will see more of this topic later in the course)
        // however, for now, just know that surrounding the part that can cause exceptions
        // with a try-catch block is one solution for the exceptions
        WordBag w = new WordBag();
        try {
            // create a new buffered reader with the given file to read its text
            // "sentences.txt" is the name of the file that is included in your project folder
            // you can also write the full path of the file
            BufferedReader reader = new BufferedReader(new FileReader(new File("sentences.txt")));
            
            
            
            
            // read the first line of the text into currentLine
            String currentLine = reader.readLine();
            
            // unless we encounter a null line
            while (currentLine != null) {

                w.processSentence(currentLine);
                // and get the next line
                currentLine = reader.readLine();
                
            }

        } catch (FileNotFoundException e) {
            // in case the file we provide cannot be found, this is the exception we get
            e.printStackTrace();
        } catch (IOException e) {
            // in case there is problem with reading the file, this is the exception we get
            e.printStackTrace();
        }
        System.out.println("Input file \"sentences.txt\" processed.");
        while(!exit){

                int hardLimit;
                int softLimit;
                int sentenceCount;
                String fileName;

                System.out.println("1. Generate Sentence\r\n" + "2. Output Sentences to Text File\r\n" + "3. Exit");
                System.out.print("Please choose an option: ");
                option = scan.nextInt();
                System.out.println();
                if(option == 1){

                    System.out.print("Soft Limit:");
                    softLimit = scan.nextInt();
                    System.out.print("Hard Limit:");
                    hardLimit = scan.nextInt();
                    System.out.println("Sentence: "+ w.generateSentence(softLimit, hardLimit));
                }
                else if(option == 2){
                    System.out.print("File Name: ");
                    fileName = scan.next();
                    System.out.println();
                    System.out.print("Sentence Count: ");
                    sentenceCount = scan.nextInt();
                    System.out.println();
                    System.out.print("Soft Limit:");
                    softLimit = scan.nextInt();
                    System.out.println();
                    System.out.print("Hard Limit:");
                    hardLimit = scan.nextInt();
                    System.out.println();
                    w.writeToTextFile(fileName, sentenceCount, softLimit, hardLimit);
                    System.out.println("Saved to "+ fileName +".txt");
                }
                else{
                    exit = true;
                }
            }
    }
    
    
}
