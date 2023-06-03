package org.neetcode.math;

class P6_PowXtoN {
    public double myPow(double x, int n) {
        boolean invert = false;

        long n1 = n;

        if(n1 < 0) {
            n1 = -n1;
            invert = true;
        }

        double res = myPowLong(x, n1);

        return invert ? 1 / res : res;
    }
    public double myPowLong(double x, long n) {
        if(x == 1.0 || n == 1) return x;
        if(n == 0) return 1;
        double res = 1;

        if(n%2 > 0) {
            n = n - 1;
            res = x;
        }

        double sqrtRes = myPowLong(x, n/2);
        if(Double.isInfinite(sqrtRes)) return sqrtRes;
        res = res * sqrtRes * sqrtRes;

        return res;
    }


    public static void main(String[] args) {
        double x = 2;
        int n = -2147483648;
//        x = 2.1;
//        n = 3;
        double res = new P6_PowXtoN().myPow(x,n);
        System.out.println(res);
    }
}
