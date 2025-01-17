import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class WordBag {

    ArrayList<Word> allWords = new ArrayList<Word>();

    public WordBag() {

    }

    public void processSentence(String sentence) {

        String[] split = sentence.split(" ");

        Word[] words = new Word[split.length+2];

        words[0] = findOrCreate("<START>");

        for (int i = 1; i < split.length + 1; i++) {
            words[i] = findOrCreate(split[i - 1]);
        }

        words[words.length-1] = findOrCreate("<END>");
        for (int i = 0; i < words.length-1; i++) {
            words[i].addFollowedBy(words[i+1]);
        }
        
    }

    

    public Word findOrCreate(String wordText) {

        boolean newWord = true;
        Word temp = new Word(wordText);
        for (int i = 0; i < allWords.size(); i++) {
            if (allWords.get(i).getStringForm().equals(wordText)) {
                newWord = false;
                temp = allWords.get(i);
                return temp;
            }
        }
    
        if (newWord) {
            allWords.add(temp);
        }
        
        return temp;
    }

    public String generateSentence(int softLimit, int hardLimit) {
        String sentence = "";
        Random rand = new Random();
        Word temp = findOrCreate("<START>");

        for (int i = 0; i < hardLimit; i++) {
            int range = temp.getCanBeFollowedBy().size();
            if(range > 0) {
                int num = rand.nextInt(temp.getCanBeFollowedBy().size());
                if (temp.getCanBeFollowedBy().get(num).getStringForm().equals("<END>")) {
                    i = Integer.MAX_VALUE;
                }
                if(!temp.getCanBeFollowedBy().get(num).getStringForm().equals("<END>")){
                    sentence += temp.getCanBeFollowedBy().get(num).getStringForm() + " ";
                }
                
                temp = temp.getCanBeFollowedBy().get(num);
            }
            else{
                return sentence;
            }
        }
        return sentence;
    }

    public void writeToTextFile(String outputName, int sentenceCount, int softLimit, int hardLimit) {
        String text = "";
        try {
            // open the output writer
            FileWriter writer = new FileWriter(outputName);

            for (int i = 0; i < sentenceCount; i++) {
                text += generateSentence(softLimit, hardLimit);
                text += "\n";
            }
            // write into file
            writer.write(text);
            

            // in the end we need to close the file
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
