package com.donnfelker.android.bootstrap.core;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ismet on 1/19/14.
 */
public class MatchingUtils {
    public static ArrayList<User> userList;
    public static List<User> euclideanUserList = new ArrayList<User>();
    public static List<User> manhattanUserList = new ArrayList<User>();
    public static List<User> mahalanobisUserList = new ArrayList<User>();
    public static final char EUCLIDEAN = 'e';
    public static final char MANHATTAN = 'm';
    public static final char MAHALANOBIS = 's';

    public static void setUserList(ArrayList<User> userList) {
        MatchingUtils.userList = userList;
    }

    private static int[] convertPreferenceListToArray(Map preferenceList) {
        int[] result = new int[Constants.IMeetapp.PREFERENCE_COUNT];
        result[0] = (Integer) preferenceList.get(Constants.IMeetapp.JAVA);
        result[1] = (Integer) preferenceList.get(Constants.IMeetapp.C);
        result[2] = (Integer) preferenceList.get(Constants.IMeetapp.CPLUSPLUS);
        result[3] = (Integer) preferenceList.get(Constants.IMeetapp.EXPERIENCE);
        result[4] = (Integer) preferenceList.get(Constants.IMeetapp.SCHOOL_GRADE);
        return result;
    }

    private static int[] convertUserToArray(User user) {
        int[] result = new int[Constants.IMeetapp.PREFERENCE_COUNT];
        Map technical = user.getTechnical();
        result[0] = Integer.parseInt((String) technical.get(Constants.IMeetapp.JAVA));
        result[1] = Integer.parseInt((String) technical.get(Constants.IMeetapp.C));
        result[2] = Integer.parseInt((String) technical.get(Constants.IMeetapp.CPLUSPLUS));
        result[3] = Integer.parseInt((String) technical.get(Constants.IMeetapp.EXPERIENCE));
        result[4] = Integer.parseInt((String) technical.get(Constants.IMeetapp.SCHOOL_GRADE));
        return result;
    }

    public static HashMap<Character, List<User>> matchUsers(Map<String, Integer> preferenceList) throws IOException {
        Log.d(Constants.IMeetapp.Log, "Matching users with euclidean distance method");
        Log.d(Constants.IMeetapp.Log, "USer List size: " + userList.size() + " preferenceList size: " + preferenceList.size());
        Log.d(Constants.IMeetapp.Log, "USER LIST OK: USER TECHNICALS" + userList.get(1).getTechnical().toString());
        Log.d(Constants.IMeetapp.Log, "USER LIST OK: PREFERENCE TECHNICALS" + preferenceList.toString());
        Log.d(Constants.IMeetapp.Log, "CONVERTED TO " + Arrays.toString(convertUserToArray(userList.get(1))));
        Log.d(Constants.IMeetapp.Log, "CONVERTED TO " + Arrays.toString(convertPreferenceListToArray(preferenceList)));

        HashMap<Character, List<User>> matchedUsers = new HashMap<Character, List<User>>();

        int[] preferenceTechnicals = convertPreferenceListToArray(preferenceList);
        double distance = 100;
        for (User user : userList) {
            int[] userTechnicals = convertUserToArray(user);
            try {
                distance = DistanceUtils.euclideanDistance(preferenceTechnicals, userTechnicals);
                user.setDistance(distance);
                euclideanUserList.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(euclideanUserList, new Comparator<User>() {
            @Override
            public int compare(User lhs, User rhs) {
                return (lhs.getDistance() < rhs.getDistance() ? -1 : (lhs.getDistance() == rhs.getDistance() ? 0 : 1));
            }
        });

        for (User user : userList) {
            int[] userTechnicals = convertUserToArray(user);
            try {
                distance = DistanceUtils.manhattanDistance(preferenceTechnicals, userTechnicals);
                user.setDistance(distance);
                manhattanUserList.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(manhattanUserList, new Comparator<User>() {
            @Override
            public int compare(User lhs, User rhs) {
                return (lhs.getDistance() < rhs.getDistance() ? -1 : (lhs.getDistance() == rhs.getDistance() ? 0 : 1));
            }
        });

        euclideanUserList = euclideanUserList.subList(0,5);
        manhattanUserList =  manhattanUserList.subList(0,5);


        matchedUsers.put(MatchingUtils.EUCLIDEAN,  euclideanUserList);
        matchedUsers.put(MatchingUtils.MANHATTAN,  manhattanUserList);
        matchedUsers.put(MatchingUtils.MAHALANOBIS,  mahalanobisUserList);
        return matchedUsers;
    }


}
