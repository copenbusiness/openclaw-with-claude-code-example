public class SumOneToFiveThousand {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 5000; i++) {
            sum += i;
        }
        System.out.println("Sum of numbers from 1 to 5000: " + sum);
        
        // Verify with formula: n*(n+1)/2
        int expected = 5000 * (5000 + 1) / 2;
        System.out.println("Expected sum using formula: " + expected);
        
        if (sum == expected) {
            System.out.println("Calculation is correct!");
        } else {
            System.out.println("Calculation error!");
        }
    }
}