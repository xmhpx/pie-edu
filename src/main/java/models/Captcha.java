package models;

public class Captcha {
    protected static int nextId = 1;

    protected int id;
    protected String imagePath;
    protected String correctAnswer;


    Captcha(String imagePath, String correctAnswer){
        id = nextId++;

        setImagePath(imagePath);
        setCorrectAnswer(correctAnswer);
    }


    public boolean isCorrect(String answer){
        return correctAnswer.equals(answer);
    }



    // getters and setters


    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Captcha.nextId = nextId;
    }

    public int getId() {
        return id;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

}
