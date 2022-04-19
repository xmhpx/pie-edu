package logic;

import models.universityitems.ReportCard;
import models.universityitems.ReportCardStatus;

import java.util.ArrayList;

public class LogicCenter {
    static LogicCenter logicCenterInstance;

    public LogicCenter getInstance(){
        if(logicCenterInstance == null){
            logicCenterInstance = new LogicCenter();
        }
        return logicCenterInstance;
    }



    public static double getAverageOfReportCardIds(ArrayList<Integer> reportCardIds) {
        Backend backend = Backend.getInstance();
        int numberOfScores = 0;
        double sumOfScores = 0;

        for(int reportCardId : reportCardIds){
            ReportCard reportCard = backend.getReportCard(reportCardId);
            if(reportCard != null){
                if(reportCard.getStatus() == ReportCardStatus.CREDITED || reportCard.getStatus() == ReportCardStatus.FAILED){
                    try {
                        double score = Double.parseDouble(reportCard.getScore());
                        sumOfScores += score;
                        numberOfScores++;
                    }
                    catch (NumberFormatException ignored){}
                }
            }
        }

        if(numberOfScores == 0)return -1;
        return sumOfScores/numberOfScores;
    }



    // getters and setters


}
