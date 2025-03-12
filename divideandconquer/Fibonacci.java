package divideandconquer;

import java.util.ArrayList;
import java.util.HashMap;

public class Fibonacci {

    public static int fibonacci(int n) {
        if (n < 0) return -1;
        if (n == 1) return 0;
        if (n == 2) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int fibonacciDpTopDown(int n, HashMap<Integer, Integer> memo) {
        if (n < 0) return -1;
        if (n == 1) return 0;
        if (n == 2) return 1;

        if (!memo.containsKey(n)) {
            memo.put(n, (fibonacciDpTopDown(n-1, memo) + fibonacciDpTopDown(n-2, memo)));
        }
        return memo.get(n);
    }

    public static int fibonacciDpBottomUp(int n) {
        ArrayList<Integer> tb = new ArrayList<>();
        tb.add(0);
        tb.add(1);

        for (int i = 2; i < n; i++) {
            int n1 = tb.get(i-1);
            int n2 = tb.get(i-2);
            tb.add(n1 + n2);
        }
        return tb.get(n-1);
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(6));

        HashMap<Integer, Integer> memo = new HashMap<>();
        System.out.println(fibonacciDpTopDown(6, memo));

        System.out.println(fibonacciDpBottomUp(6));
    }
}
