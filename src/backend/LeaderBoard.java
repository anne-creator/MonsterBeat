package backend;

import java.util.ArrayList;
import java.util.Collections;

/**
 * LeaderBoard controller control the data in leaderboard.
 * how to use it sortedList static method
 *   LeaderBoard.getSortedList(1); // sort by level1Level
 *   @params int difficulty level
 *   @return an sorted Arraylist that can be showed in order
 *   a static class, no need to declare a LeaderBoard class first
 */
public class LeaderBoard {

    public static ArrayList<User> getSortedList(int difficultyLevel) {
        ArrayList<User> sortedList = DataProcessing.readUserInfo(); //data from UserInfo.csv
        System.out.println(sortedList.size());
        //testing
//        for (User p : sortedList) {
//            System.out.println(p.toString());
//        }

        Collections.sort(sortedList, (p1, p2) -> {
            if (difficultyLevel == 1) {
                return Integer.compare(p2.getLevel1HighestScore(), p1.getLevel1HighestScore());
            } else if (difficultyLevel == 2) {
                return Integer.compare(p2.getLevel2HighestScore(), p1.getLevel2HighestScore());
            } else {
                return Integer.compare(p2.getLevel3HighestScore(), p1.getLevel3HighestScore());
            }
        });

        //testing
//        for (User p : sortedList) {
//            System.out.println(p.toString());
//        }

        return sortedList;
    }

    //testing
    public static void main(String[] args) {
        LeaderBoard.getSortedList(1);
    }

}
