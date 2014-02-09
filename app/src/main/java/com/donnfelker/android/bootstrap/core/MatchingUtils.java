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

    private static int[] convertPreferenceListToArray(Map<String, Integer> preferenceMap) {
        int[] result = new int[Constants.IMeetapp.PREFERENCE_COUNT];
        int i = 0;
        for (String key : preferenceMap.keySet()) {
            result[i] = preferenceMap.get(key);
            i++;
        }
        return result;
    }

    private static int[] convertUserToArray(User user, Map<String, Integer> preferenceMap) {
        int[] result = new int[Constants.IMeetapp.PREFERENCE_COUNT];
        Map<String, Integer> technical = user.getTechnical();
        int i = 0;
        for (String key : preferenceMap.keySet()) {
            result[i] =  technical.get(key);
            i++;
        }
        return result;
    }

    public static HashMap<Character, List<User>> matchUsers(Map<String, Integer> preferenceMap) throws IOException {
        Log.d(Constants.IMeetapp.Log, "Matching users with euclidean distance method");
        Log.d(Constants.IMeetapp.Log, "USer List size: " + userList.size() + " preferenceList size: " + preferenceMap.size());
        Log.d(Constants.IMeetapp.Log, "USER LIST OK: USER TECHNICALS" + userList.get(1).getTechnical().toString());
        Log.d(Constants.IMeetapp.Log, "USER LIST OK: PREFERENCE TECHNICALS" + preferenceMap.toString());
        Log.d(Constants.IMeetapp.Log, "USER TECHNICALS CONVERTED TO " + Arrays.toString(convertUserToArray(userList.get(1),preferenceMap)));
        Log.d(Constants.IMeetapp.Log, "PREFERENCE TECHNICALS CONVERTED TO " + Arrays.toString(convertPreferenceListToArray(preferenceMap)));

        HashMap<Character, List<User>> matchedUsers = new HashMap<Character, List<User>>();
        matchedUsers.clear();

        euclideanUserList.clear();
        manhattanUserList.clear();
        mahalanobisUserList.clear();
        int[] preferenceTechnicals = convertPreferenceListToArray(preferenceMap);
        double distance = 100;
        for (User user : userList) {
            int[] userTechnicals = convertUserToArray(user, preferenceMap);
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
            int[] userTechnicals = convertUserToArray(user, preferenceMap);
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

        for (User user : userList) {
            int[] userTechnicals = convertUserToArray(user, preferenceMap);
            try {
                distance = DistanceUtils.mahalanobisDistance(preferenceTechnicals, userTechnicals);
                user.setDistance(distance);
                mahalanobisUserList.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(mahalanobisUserList, new Comparator<User>() {
            @Override
            public int compare(User lhs, User rhs) {
                return (lhs.getDistance() > rhs.getDistance() ? -1 : (lhs.getDistance() == rhs.getDistance() ? 0 : 1));
            }
        });

        euclideanUserList = euclideanUserList.subList(0, 5);
        manhattanUserList = manhattanUserList.subList(0, 5);
        mahalanobisUserList = mahalanobisUserList.subList(0, 5);


        matchedUsers.put(MatchingUtils.EUCLIDEAN, euclideanUserList);
        matchedUsers.put(MatchingUtils.MANHATTAN, manhattanUserList);
        matchedUsers.put(MatchingUtils.MAHALANOBIS, mahalanobisUserList);
        return matchedUsers;
    }


}
