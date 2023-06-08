package org.neetcode.dynamic2d;

class P7_EditDistance {
    public int minDistance(String word1, String word2) {
        //Modify word1, keep word2 constant
        this.word1 = word1;
        this.word2 = word2;
        len1 = word1.length();
        len2 = word2.length();

        cache = new Integer[len1 + 1][len2 + 1];

        int res = editDistance(0,0);

        return res;
    }

    Integer[][] cache;

    public int editDistance(int start1, int start2) {
        if(cache[start1][start2] != null) return cache[start1][start2];

        int res = -1;
        if(start1 == len1 && start2 == len2) {
            res = 0;
        } else if(start1 < len1 && start2 < len2 && word1.charAt(start1) == word2.charAt(start2)) {
            //abcd
            //aaxyz
            res = editDistance(start1 + 1, start2 + 1);
        } else if(start1 <= len1 && start2 <= len2){
            int insertBefore = Integer.MAX_VALUE;
            int deleteFirst = Integer.MAX_VALUE;
            int replaceFirst = Integer.MAX_VALUE;
            int temp;

            if(start2 < len2) { //Insert Before
                //abc ->  xabc
                //xyz ->  xyz
                temp = editDistance(start1, start2 + 1);
                if(temp > -1) insertBefore = 1 + temp;
            }

            if(start1 < len1) { //Delete First
                //abc -> bc
                //bca -> bca
                temp = editDistance(start1 + 1, start2);
                if(temp > -1) deleteFirst = 1 + temp;
            }

            if(start1 < len1 && start2 < len2) { //Replace First
                //abc -> xbc
                //xyz -> xyz
                temp = editDistance(start1 + 1, start2 + 1);
                if(temp > -1) replaceFirst = 1 + temp;
            }

            res = Math.min(insertBefore, Math.min(deleteFirst, replaceFirst));
        }

        cache[start1][start2] = res;
        return res;
    }

    String word1, word2;
    int len1, len2;

    public static void main(String[] args) {
        String word1 = "horse"; String word2 = "ros";
        word1 = "intention"; word2 = "execution";
        System.out.println(new P7_EditDistance().minDistance(word1, word2));
    }

}
