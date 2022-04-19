package logic;

import models.User;

public class LoggedInUserHolder {
    private static User user = null;

    public static void setUser(User user) {
        LoggedInUserHolder.user = user;
    }

    public static User getUser() {
        return user;
    }
}
