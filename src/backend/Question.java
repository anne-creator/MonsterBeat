package backend;

public class Question {
    public int answerKey;
    public String questionString;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    Question(int answerKey, String questionString, String option1, String option2, String option3, String option4){
        this.answerKey = answerKey;
        this.questionString = questionString;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
}
