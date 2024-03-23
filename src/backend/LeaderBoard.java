package backend;

import java.util.ArrayList;
import java.util.Collections;

/**
 * LeaderBoard controller control the data in leaderboard.
 * how to use it: LeaderBoard.getSortedList(1); // sort by level1Level
 */
public class LeaderBoard {

    public static ArrayList<User> getSortedList(int difficultyLevel) {
        ArrayList<User> sortedList = DataProcessing.readData();
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
