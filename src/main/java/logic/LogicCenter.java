package logic;

import models.User;
import models.universityitems.ReportCard;
import models.universityitems.ReportCardStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class LogicCenter {
    private static final Logger log = LogManager.getLogger(LogicCenter.class);

    static LogicCenter logicCenterInstance;

    public LogicCenter getInstance(){
        if(logicCenterInstance == null){
            logicCenterInstance = new LogicCenter();
        }
        return logicCenterInstance;
    }



    public static double getAverageOfReportCardIds(ArrayList<Integer> reportCardIds) {
        if(reportCardIds == null){
            log.warn("'user' is null");
            return -1;
        }

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
                    catch (NumberFormatException ignored){
                        log.warn("CREDITED or a FAILED report card with id " + reportCard.getId() + " doesn't have a proper score");
                    }
                }
            }
        }

        if(numberOfScores == 0)return -1;
        return sumOfScores/numberOfScores;
    }
}
