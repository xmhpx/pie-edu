package models;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Captcha {
    private static final Logger log = LogManager.getLogger(Captcha.class);

    protected static int nextId = 1;

    protected int id;
    protected String imagePath;
    protected String correctAnswer;


    public Captcha(String imagePath, String correctAnswer){
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
        if(nextId <= 0){
            log.warn("'nextId' is weird");
        }
        Captcha.nextId = nextId;
    }

    public int getId() {
        return id;
    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        if(imagePath == null){
            log.warn("'imagePath' is null");
            return;
        }
        log.info("captcha("+getId()+") changed imagePath");
        this.imagePath = imagePath;
    }


    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        if(correctAnswer == null){
            log.warn("'correctAnswer' is null");
            return;
        }
        log.info("captcha("+getId()+") changed correctAnswer");
        this.correctAnswer = correctAnswer;
    }
}
