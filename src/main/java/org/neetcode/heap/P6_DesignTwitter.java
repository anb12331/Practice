package org.neetcode.heap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

class P6_DesignTwitter {

    class Twitter {
        private Map<Integer, UserData> userDataMap;
        private int time;


        public Twitter() {
            userDataMap = new HashMap<>();
            time = 0;
        }

        public void postTweet(int userId, int tweetId) {
            if(!userDataMap.containsKey(userId)) {
                userDataMap.put(userId, new UserData());
            }
            UserData userData = userDataMap.get(userId);
            int[] tweet = new int[] {time, tweetId};
            userData.postTweet(tweet);
            time++;
        }

        public List<Integer> getNewsFeed(int userId) {
            if(userDataMap.containsKey(userId)) {
                UserData userData = userDataMap.get(userId);
                return userData.getNewsFeed();
            }
            time++;
            return new ArrayList<>();
        }

        public void follow(int followerId, int followeeId) {
            if(!userDataMap.containsKey(followerId)) {
                userDataMap.put(followerId, new UserData());
            }

            if(!userDataMap.containsKey(followeeId)) {
                userDataMap.put(followeeId, new UserData());
            }


            userDataMap.get(followerId).addFollowee(userDataMap.get(followeeId));

            userDataMap.get(followeeId).addFollower(userDataMap.get(followerId));

            time++;
        }

        public void unfollow(int followerId, int followeeId) {
            UserData follower = userDataMap.get(followerId);
            follower.removeFollowee(userDataMap.get(followeeId));

            userDataMap.get(followeeId).removeFollower(follower);

            time++;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        P6_DesignTwitter p = new P6_DesignTwitter();
        Twitter t = p.new Twitter();

        String[] methodCalls = {"postTweet", "getNewsFeed", "follow", "postTweet", "getNewsFeed", "unfollow", "getNewsFeed"};
        Integer[][] testArgs = {{1, 5},       {1},           {1, 2},   {2, 6},      {1},           {1, 2},     {1}};

        Map<String, Method> methodMap = new HashMap<>();
        for(Method m: Twitter.class.getMethods()) {
            methodMap.put(m.getName(), m);
        }

        for(int i = 0; i  < methodCalls.length; i++) {
            System.out.println(
                    methodMap.get(methodCalls[i]).invoke(t, testArgs[i])

            );
        }
    }
}

class UserData{
    private static int feedLen = 10;
    private Set<UserData> followers = new HashSet<>();
    private Set<UserData> followedByMe = new HashSet<>();
    private Deque<int[]> recentTweets = new ArrayDeque<>();

    private PriorityQueue<int[]> myNewsFeed;

    public UserData() {
        myNewsFeed = new PriorityQueue<>(Comparator.comparingInt(o->o[0]));
    }

    private void rebuildNewsFeed() {
        myNewsFeed = new PriorityQueue<>(Comparator.comparingInt(o->o[0]));
        for(UserData userData: followedByMe) {
            for(int[] tweet: userData.recentTweets) {
                addToMyNewsFeed(tweet);
            }
        }

        for(int[] tweet:recentTweets) {
            addToMyNewsFeed(tweet);
        }
    }

    public void postTweet(int[] tweet) {
        addToMyNewsFeed(tweet);
        recentTweets.addFirst(tweet);
        for(UserData userData: followers) {
            userData.addToMyNewsFeed(tweet);
        }
        if(recentTweets.size() > 10) recentTweets.pollLast();
    }

    private void addToMyNewsFeed(int[] tweet) {
        if(myNewsFeed == null) rebuildNewsFeed();
        myNewsFeed.add(tweet);
        if(myNewsFeed.size() > feedLen)
            myNewsFeed.poll();
    }

    public void addFollowee(UserData followee) {
        if(!followedByMe.contains(followee)) {
            followedByMe.add(followee);
            if (myNewsFeed != null) {
                for (int[] tweet : followee.recentTweets) {
                    addToMyNewsFeed(tweet);
                }
            }
        }
    }

    public void addFollower(UserData follower) {
        followers.add(follower);
    }

    public void removeFollower(UserData follower) {
        followers.remove(follower);
    }

    public void removeFollowee(UserData followee) {
        followedByMe.remove(followee);
        clearNewsFeed();
    }

    public List<Integer> getNewsFeed() {
        if(myNewsFeed == null) rebuildNewsFeed();
        PriorityQueue<int[]> newsFeed = new PriorityQueue<>(myNewsFeed);
        List<Integer> res = new ArrayList<>(feedLen);

        while(!newsFeed.isEmpty()) {
            res.add(newsFeed.poll()[1]);
        }

        Collections.reverse(res);

        return res;
    }

    private void clearNewsFeed() {
        myNewsFeed = null;
    }
}
