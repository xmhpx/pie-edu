package models;

public class Captcha {
    private String imagePath;
    private String correctAnswer;


    Captcha(String imagePath, String correctAnswer){
        setImagePath(imagePath);
        setCorrectAnswer(correctAnswer);
    }



    public boolean isCorrect(String answer){
        return correctAnswer.equals(answer);
    }



    // getters and setters


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
