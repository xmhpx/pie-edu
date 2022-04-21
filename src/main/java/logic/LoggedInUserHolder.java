package logic;

import models.User;

import java.util.Calendar;

public class LoggedInUserHolder {
    private static User user = null;

    public static void logout() {
        LoggedInUserHolder.user = null;
    }

    public static void setUser(User user) {
        LoggedInUserHolder.user = user;

        if(user != null) {
            Calendar cal = Calendar.getInstance();

            int second = cal.get(Calendar.SECOND);
            int minute = cal.get(Calendar.MINUTE);
            int hour = cal.get(Calendar.HOUR);

            user.setLastVisit(hour + ":" + (minute) + ":" + second);
        }
    }

    public static User getUser() {
        return user;
    }
}
