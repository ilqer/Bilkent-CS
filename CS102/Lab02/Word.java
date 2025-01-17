import java.util.ArrayList;

public class Word {

    private String text;
    private ArrayList<Word> canBeFollowedBy;

    public Word(String t){
        text = t;
        canBeFollowedBy = new ArrayList<Word>();  
    }

    public void addFollowedBy(Word w){
        canBeFollowedBy.add(w);
    }
    public String getStringForm(){
        return text;
    }
    public ArrayList<Word> getCanBeFollowedBy() {
        return canBeFollowedBy;
    }
    public String toString(){
        return text;
    }

}