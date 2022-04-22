package logic;

import models.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Calendar;

public class LoggedInUserHolder {
    private static final Logger log = LogManager.getLogger(LoggedInUserHolder.class);


    private static User user = null;

    public static void logout() {
        log.info("user("+user.getId()+") logged out");
        LoggedInUserHolder.user = null;
    }



    // getters and setters


    public static User getUser() {
        if(user == null){
            log.error("'user' is null");
            throw new IllegalStateException("'user' is null");
        }
        return user;
    }

    public static void setUser(User user) {
        if(user == null){
            log.warn("'user' is null");
            return;
        }
        log.info("user("+user.getId()+") logged in");

        LoggedInUserHolder.user = user;

        Calendar cal = Calendar.getInstance();

        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);

        user.setLastVisit(hour + ":" + (minute) + ":" + second);
    }
}
