package ru.job4j.question;

import java.util.HashMap;
import java.util.Set;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int deleted = 0;
        int changed = 0;
        HashMap<Integer, User> currentMap = new HashMap<>();

        for (User user : current) {
            currentMap.put(user.getId(), user);
        }

        for (User user : previous) {

            if (!currentMap.containsKey(user.getId())) {
                deleted++;
            } else if (!user.equals(currentMap.get(user.getId()))) {
                changed++;
            }
            added = currentMap.size() - previous.size() + deleted;
        }

        return new Info(added, changed, deleted);
    }

}