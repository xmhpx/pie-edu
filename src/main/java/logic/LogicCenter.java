package logic;

public class LogicCenter {
    static LogicCenter logicCenterInstance;

    public LogicCenter getInstance(){
        if(logicCenterInstance == null){
            logicCenterInstance = new LogicCenter();
        }
        return logicCenterInstance;
    }



    // getters and setters


}
