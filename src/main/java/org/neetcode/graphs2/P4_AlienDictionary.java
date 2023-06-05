package org.neetcode.graphs2;

import java.util.*;

class P4_AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Integer> outgoingCount = new HashMap<>();
        Map<Character, Set<Character>> incomingEdgesMap = new HashMap<>();
        Set<String> edges = new HashSet<>();
        Set<Character> letters = new HashSet<>();

        for(int i = 0; i < words.length; i++) {
            words[i].chars().forEach(c -> letters.add(Character.valueOf((char)c)));
            for(int j = i + 1; j < words.length; j++) {
                buildEdgesFromWords(words[i], words[j], edges);
            }
        }

        for(String e_str: edges) {
            char[] e = e_str.toCharArray();
            Integer outCount = outgoingCount.get(e[0]);
            if(outCount == null) {
                outgoingCount.put(e[0], 1);
            } else {
                outgoingCount.put(e[0], outCount + 1);
            }

            Set<Character> incomingEdges = incomingEdgesMap.get(e[1]);
            if(incomingEdges == null) {
                incomingEdges = new HashSet<>();
                incomingEdgesMap.put(e[1], incomingEdges);
            }
            incomingEdges.add(e[0]);
        }

        List<Character> lastLetters = new ArrayList<>();

        for(Character c: letters) {
            if(!outgoingCount.containsKey(c)) {
                lastLetters.add(c);
                break;
            }
        }

        if(lastLetters.isEmpty()) return "";
        List<String> orderedLetters = new ArrayList<>(letters.size());
        for(Character lastLetter: lastLetters) {
            orderedLetters.add(lastLetter.toString());
            while (lastLetter != null && incomingEdgesMap.containsKey(lastLetter)) {
                if(orderedLetters.size() > letters.size()) return "";
                Set<Character> incomingEdges = incomingEdgesMap.get(lastLetter);
                boolean foundNext = false;
                lastLetter = null;
                for (Character c : incomingEdges) {
                    Integer count = outgoingCount.get(c);
                    if (!foundNext && count == 1) {
                        orderedLetters.add(c.toString());
                        lastLetter = c;
                        foundNext = true;
                        outgoingCount.put(c, count - 1);
                    } else if (foundNext && count == 1) {
                        Set<Character> nextIncomingEdges = incomingEdgesMap.get(lastLetter);
                        if (nextIncomingEdges == null) {
                            nextIncomingEdges = new HashSet<>();
                            incomingEdgesMap.put(lastLetter, nextIncomingEdges);
                        }
                        nextIncomingEdges.add(c);
                    } else if (count != null && count > 1) {
                        outgoingCount.put(c, count - 1);
                    }
                }
                if (!foundNext) return "";
            }
        }

        Collections.reverse(orderedLetters);
        return String.join("", orderedLetters);
    }

    private void buildEdgesFromWords(String word, String lesserWord, Set<String> edges) {
        for(int i = 0; i < Math.min(word.length(), lesserWord.length()); i++) {
            Character c1 = word.charAt(i);
            Character c2 = lesserWord.charAt(i);

            if(!c1.equals(c2)) {
                edges.add(String.valueOf(new char[] {c1, c2}));
                return;
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"wrt","wrf","er","ett","rftt"};
//        words = new String[] {"z", "x"};
        words = new String[] {"ab", "ad", "ba", "bc", "bd"};
        System.out.println(new P4_AlienDictionary().alienOrder(words));
    }

    public String alienOrder_dfsPreOrder(String[] words) {
        Set<String> edges = new HashSet<>();

        for(int i = 0; i < words.length; i++) {
            words[i].chars().forEach(c -> letters.add(Character.valueOf((char)c)));
            for(int j = i + 1; j < words.length; j++) {
                buildEdgesFromWords(words[i], words[j], edges);
            }
        }

        orderedLetters = new ArrayList<>(letters.size());

        for(String e_str: edges) {
            char[] e = e_str.toCharArray();
            Set<Character> outgoingEdges = outgoingEdgesMap.get(e[0]);
            if(outgoingEdges == null) {
                outgoingEdges = new HashSet<>();
                outgoingEdgesMap.put(e[0], outgoingEdges);
            }
            outgoingEdges.add(e[1]);
        }

        for(Character c: letters) {
            if(dfsPreOrder(c))
                return "";
        }
        Collections.reverse(orderedLetters);
        return String.join("", orderedLetters);
    }

    Map<Character, Boolean> visited = new HashMap<>();
    Set<Character> letters = new HashSet<>();
    Map<Character, Set<Character>> outgoingEdgesMap = new HashMap<>();
    List<String> orderedLetters;
    private boolean dfsPreOrder(Character startC) {
        if(visited.containsKey(startC))
            return visited.get(startC);

        visited.put(startC, true);
        Set<Character> outgoingEdges = outgoingEdgesMap.get(startC);
        if(outgoingEdges != null) {
            for (Character c : outgoingEdges) {
                if (dfsPreOrder(c))
                    return true;
            }
        }
        visited.put(startC, false);

        orderedLetters.add(startC.toString());

        return false;
    }

}

