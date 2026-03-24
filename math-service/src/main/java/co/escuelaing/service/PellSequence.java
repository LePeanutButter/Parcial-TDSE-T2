package co.escuelaing.service;

import java.util.HashMap;
import java.util.Map;

public class PellSequence {
    public Map<Integer, Integer> memoisation = new HashMap<>();

    public int pellseq(int n) {
        // P(0) = 0
        // P(1) = 1
        // P(n) = 2P(n-1)+P(n-2)
        int value = 0; // P(0) = 0
        if (n == 1) {
            value = 1; // P(1) = 1
        } else if (n > 1) {
            int previousNum = n-1;
            int lastPreviousNum = n-2;

            int firstSeq = memoisation.containsKey(previousNum) ? memoisation.get(previousNum) : pellseq(previousNum);
            int secondSeq = memoisation.containsKey(lastPreviousNum) ? memoisation.get(lastPreviousNum) : pellseq(lastPreviousNum);

            value = 2 * firstSeq + secondSeq; // P(n) = 2P(n-1)+P(n-2)

            memoisation.put(n, value);
        }
        return value;
    }
}
