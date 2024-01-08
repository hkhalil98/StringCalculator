// Solution to first task - katka

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public static int add(String number) { // add method checks if the input string is empty, if so, 
                                            // 0 returned - as the sum of an empty string is 0.
        if (number.isEmpty()) {
            return 0;
        }

        // Add method handles new lines between the numbers
        number = number.replace("\n", ",");

        // Add method can handle a different delimiter
        // Check if the string starts with "//" to determine if a custom delimiter is provided
        //If so, delimiter extracted updates the number variable to contain only the numbers.
        if (number.startsWith("//")) {
            int delimiterEndIndex = number.indexOf("\n");
            String custDelimiter = number.substring(2, delimiterEndIndex);
            number = number.substring(delimiterEndIndex + 1);
            number = number.replace(custDelimiter, ",");
        }

        // Exception thrown when Add is called with a negative number
        if (number.contains("-")) {
            List<Integer> negativeNumbers = extractNegNumber(number);
            throw new RuntimeException("Negatives are not allowed: " + negativeNumbers);
        }

        // Numbers > 1000 ignored
        // If negative numbers found - IllegalArgumentException thrown

        int sum = 0;
        String[] numArray = number.split(",");
        for (String num : numArray) {
            int parsedNum = Integer.parseInt(num);
            if (parsedNum <= 1000) {
                sum += parsedNum;
            }
        }

        return sum;
    }

    private static List<Integer> extractNegNumber(String numbers) {
        List<Integer> negNumber = new ArrayList<>();
        String[] numsArray = numbers.split(",");
        for (String num : numsArray) {
            int parsedNums = Integer.parseInt(num);
            if (parsedNums < 0) {
                negNumber.add(parsedNums);
            }
        }
        return negNumber;
    }

    public static void main(String[] args) {
        // Testing the StringCalculator
        // Test cases to verify the implementation 
        // Prints the results for various scenarios; empty strings, different delimiters and negative numbers

        try {
            // 1
            System.out.println(add("")); // output; 0

            // 2
            System.out.println(add("1")); // output; 1

            // 2
            System.out.println(add("1,2")); // output; 3

            // 4
            System.out.println(add("1\n2,3")); // output; 6

            // 5
            System.out.println(add("//;\n1;2")); // output; 3

            // 6
            System.out.println(add("//[|||]\n1|||2|||3")); // output; 6

            // 7
            System.out.println(add("-1,2")); // output; RuntimeException message "Negatives are not allowed: [-1]"

            // 8
            System.out.println(add("1001,2")); // output; 2

            // 9
            System.out.println(add("//[|][%]\n1|2%3")); // output; 6
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}