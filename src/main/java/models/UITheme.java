package models;

public class UITheme {
    public static final String skyBlue = "#ceecec";
    public static final String red = "#ff9797";
    public static final String dark = "#555555";
    public static final String purple = "#bb5588";

    private String color = skyBlue;



    // getters and setters


    public String getColor() {
        return color;
    }


    public void changeColorToSkyBlue(){
        color = skyBlue;
    }

    public void changeColorToRed(){
        color = red;
    }

    public void changeColorToDark(){
        color = dark;
    }

    public void changeColorToPurple(){
        color = purple;
    }
}
