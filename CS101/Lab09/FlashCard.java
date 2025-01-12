/**
 * Lab09_Q1
 * 
 *
 * @author İlker
 * @version 1.00 2023/2/14
 */
public class FlashCard {
    String question; 
    String answer;
    int difficulty;
    public FlashCard(String question, String answer, int difficulty){
        this.setQuestion(question);
        this.setAnswer(answer);
        this.setDifficulty(difficulty);


    }
    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
    public int getDifficulty(){
        return difficulty;
    }
    public void setQuestion(String question){
        this.question = question;
    }
    public void setAnswer(String answer){
        this.answer = answer;
    }
    public void setDifficulty(int difficulty){
        if(difficulty>0 && difficulty<6){
            this.difficulty = difficulty;
        }
        else{
            this.difficulty = 0;
        }
        
    }
    public void reverseCard(){
        String temp;
        temp = answer;
        answer = question;
        question = temp;
    }
    public boolean equals(String answer){
        if(question.equals(answer)){
            return true;
        }
        return false;
    }
    public String toString(){
        return this.question+"-"+this.answer+":"+this.difficulty;
    }
    public void showFlashCardQuestion(){
        int length;
        length = question.length();
        System.out.println("****************");
        System.out.println("**            **");
        System.out.println("**            **");
        System.out.println("**            **");
        System.out.printf("**  %s     **\n",this.question);
        System.out.println("**            **");
        System.out.println("**            **");
        System.out.println("**            **");
        System.out.println("****************");
    }
    public void showFlashCardAnswer(){
        int length;
        length = question.length();
        System.out.println("****************");
        System.out.println("**            **");
        System.out.println("**            **");
        System.out.println("**            **");
        System.out.printf("**  %s     **\n",answer);
        System.out.println("**            **");
        System.out.println("**            **");
        System.out.println("**            **");
        System.out.println("****************");
    }
}
