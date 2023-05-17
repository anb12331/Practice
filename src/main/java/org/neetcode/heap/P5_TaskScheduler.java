package org.neetcode.heap;

import java.util.*;

public class P5_TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        pendingMap = new HashMap<>();
        coolDownSet = new HashSet<>();
        coolDownQ = new ArrayDeque<>(n + 1);
        Map<Character, Integer> counts = new HashMap<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> -Integer.compare(o1[0], o2[0]));


        for(char task: tasks) {
            Integer count = pendingMap.get(task);
            if(count == null) count = 0;
            pendingMap.put(task, count + 1);
        }

        for(Character c: pendingMap.keySet()) {
            pq.add(new int[] {pendingMap.get(c), (int)c});
        }

        int time = 0;
        while(!(pq.isEmpty() && counts.isEmpty())) {
            if(coolDownQ.size() == n + 1) {
                Character freedUpTask = coolDownQ.pollFirst();
                coolDownSet.remove(freedUpTask);
                if(counts.containsKey(freedUpTask)) {
                    pq.add(new int[]{counts.remove(freedUpTask), freedUpTask});
                }
//                task = getTask(task);
            }

            Character task;

            if(!pq.isEmpty()) {
                int[] maxPendingTask = pq.poll();
                task = (char) maxPendingTask[1];
                if(maxPendingTask[0] > 1)
                    counts.put(task, maxPendingTask[0] - 1);
            } else {
                task = ' ';
            }
//                task = getTask(null);

            if(task != null) {
                time++;
                System.out.print(task + " -> ");
                coolDownQ.addLast(task);
                if(task != ' ') coolDownSet.add(task);
            }
        }

        return time;
    }

    Map<Character, Integer> pendingMap;
    Set<Character> coolDownSet;
    Deque<Character> coolDownQ;

    private Character getTask(Character task) {
        Integer val = 1;
        if(task != null) {
            val = pendingMap.get(task);
            if(val == null) {
                return null;
            }
            val--;
            pendingMap.put(task, val);
        } else {
            if(pendingMap.isEmpty())
                return null;
            task = ' ';
            for(Map.Entry<Character, Integer> entry: pendingMap.entrySet()) {
                if(!coolDownSet.contains(entry.getKey())) {
                    val = entry.getValue() - 1;
                    entry.setValue(val);
                    task = entry.getKey();
                    break;
                }
            }
        }

        if(val == 0) {
            pendingMap.remove(task);
        }
        return task;
    }

    public static void main(String[] args) {
        char[] tasks1 = {'A','A','A','A','A','A','B','C','D','E','F','G'};
        char[] tasks = {'A','A','A','B','B','B', 'C','C','C', 'D', 'D', 'E'};
        System.out.println(new P5_TaskScheduler().leastInterval(tasks, 2));
    }
}
