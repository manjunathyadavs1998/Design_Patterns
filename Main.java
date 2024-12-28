public class Main {

    public boolean isSplitPossible(String str, int k) {
        int n = str.length();
        int allOne = 0;

        // Count total number of '1's in the string
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '1') {
                allOne++;
            }
        }

        // If the total number of '1's is not divisible by k, return false
        if (allOne % k != 0) {
            return false;
        }

        // Number of '1's that should be in each segment
        int segOnes = allOne / k;

        // Count trailing zeros at the end of the string
        int trailingZero = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == '1') {
                break;
            }
            trailingZero++;
        }

        String prevSegment = "";
        int oneCnt = 0, zeroCnt = 0, segCount = 0;
        StringBuilder sub = new StringBuilder();

        // Loop through the string to process segments
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                sub.append('1');
                oneCnt++;
            } else {
                if (oneCnt == segOnes) {
                    sub.append('0');
                    zeroCnt++;
                } else if (oneCnt != 0) {
                    sub.append('0');
                }
            }

            // When a segment is complete
            if (oneCnt == segOnes && trailingZero == zeroCnt) {
                if (prevSegment.isEmpty()) {
                    prevSegment = sub.toString();
                } else {
                    if (!prevSegment.equals(sub.toString())) {
                        return false;
                    }
                }

                segCount++;
                oneCnt = zeroCnt = 0;
                sub = new StringBuilder();
            }
        }

        // If the number of segments is not equal to k, return false
        if (segCount != k) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Main solution = new Main();
        String str = "10101";
        int k = 2;
        System.out.println(solution.isSplitPossible(str, k));  // Output: true or false based on the string
    }
}
