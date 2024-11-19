/* (C)2024 */
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* (C)2024 */
public class Challenges {

    /* *****
    Challenge 1

    "Readable Time"

    The function "readableTime" accepts a positive number as argument,
    you should be able to modify the function to return the time from seconds
    into a human readable format.

    Example:

    Invoking "readableTime(3690)" should return "01:01:30" (HH:MM:SS)
    ***** */

    public String readableTime(Integer seconds) {
        // YOUR CODE HERE...
        // Given that Integer in signed, we must verify if that is positive
        if (seconds < 0) {
            return "";
        }

        enum UNIT {
            SECOND(1, 2),
            MINUTE(60 * SECOND.value, 2),
            HOUR(60 * MINUTE.value, 2);

            public final int value;
            public final int length;

            UNIT(int value, int length) {
                this.value = value;
                this.length = length;
            }
        }

        // The required format is set up in a descending list by value
        UNIT[] units = { UNIT.HOUR, UNIT.MINUTE, UNIT.SECOND };
        List<String> formattedTime = new ArrayList<>(units.length);
        for (UNIT unit : units) {
            String value = String.valueOf(seconds / unit.value);
            int requiredZeros = unit.length - value.length();
            if (requiredZeros > 0) {
                value = "0".repeat(requiredZeros) + value;
            }

            seconds = seconds % unit.value;
            formattedTime.add(value);

            // The integer division is used to obtain the value in each unit and the
            // modulus is used to obtain the remainders after each iteration.
        }

        return String.join(":", formattedTime);
    }
    ;

    /* *****
    Challenge 2

    "Circular Array"

    Given the following array "COUNTRY_NAMES", modify the function "circularArray"
    to return an array that meets the following criteria:

    - The index number passed to the function should be the first element in the resulting array
    - The resulting array must have the same length as the initial array
    - The value of the argument "index" will always be a positive number

    Example:

    Invoking "circularArray(2)" should return "["Island", "Japan", "Israel", "Germany", "Norway"]"
    ***** */

    public String[] circularArray(int index) {
        String[] COUNTRY_NAMES = {"Germany", "Norway", "Island", "Japan", "Israel"};
        // YOUR CODE HERE...
        // Given that Integer in signed, we must verify if that is positive
        if (index < 0) {
            return COUNTRY_NAMES;
        }

        // Use the module to avoid out-of-range indexes
        if (index > COUNTRY_NAMES.length) {
            index %= COUNTRY_NAMES.length;
        }

        String[] shiftedCountryNames = new String[COUNTRY_NAMES.length];
        for (int idx = 0; idx < COUNTRY_NAMES.length; ++idx) {
            shiftedCountryNames[idx] = COUNTRY_NAMES[(index + idx) % COUNTRY_NAMES.length];
        }

        /*
            - - 'n' is COUNTRY_NAMES' length
            Given an array A of n items where [0][1][2]...[n-1], and other one B of same size
            We use the modulus for copy them as follows:
                - Given i, where i in [0, 1, 2, ... n - 1]
                - We use it for populate B:
                    - for instance: B[i] = A[(i + index) % n] // in order to iterate over all array indexes
        */

        return shiftedCountryNames;
    }
    ;

    /* *****
    Challenge 3

    "Own Powers"

    The function "ownPower" accepts two arguments. "number" and "lastDigits".

    The "number" indicates how long is the series of numbers you are going to work with, your
    job is to multiply each of those numbers by their own powers and after that sum all the results.

    "lastDigits" is the length of the number that your function should return, as a string!.
    See example below.

    Example:

    Invoking "ownPower(10, 3)" should return "317"
    because 1^1 + 2^2 + 3^3 + 4^4 + 5^5 + 6^6 + 7^7 + 8^8 + 9^9 + 10^10 = 10405071317
    The last 3 digits for the sum of powers from 1 to 10 is "317"
    ***** */

    public String ownPower(int number, int lastDigits) {
        // YOUR CODE HERE...
        BigInteger sum = new BigInteger(BigInteger.ZERO.toByteArray());
        for (int i = 1; i <= number; ++i) {
            // We get i^i
            BigInteger ownPower = new BigInteger(BigInteger.ONE.toByteArray());
            for (int j = 1; j <= i; ++j) {
                ownPower = ownPower.multiply(BigInteger.valueOf(i));
            }

            // We add it to the accumulator
            sum = sum.add(ownPower);
        }

        String sumDigits = sum.toString();
        // Verify if is greater than lastDigits in order to get a substring
        if (sumDigits.length() >= lastDigits) {
            return sumDigits.substring(sumDigits.length() - lastDigits);
        }

        return sumDigits;
    }
    ;

    /* *****
    Challenge 4

    "Sum of factorial digits"

    A factorial (x!) means x! * (x - 1)... * 3 * 2 * 1.
    For example: 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800

    Modify the function "digitSum" to return a number that
    equals to the sum of the digits in the result of 10!

    Example:

    Invoking "digitSum(10)" should return "27".
    Since 10! === 3628800 and you sum 3 + 6 + 2 + 8 + 8 + 0 + 0
    ***** */

    public Integer digitSum(int n) {
        // YOUR CODE HERE...
        // Declare a accumulator variable
        BigInteger factorial = new BigInteger(BigInteger.ONE.toByteArray());
        for (int number = 1; number <= n; ++number) {
            // perform factorial
            factorial = factorial.multiply(BigInteger.valueOf(number));
        }

        int sum = 0;
        String factorialDigits = factorial.toString();
        // Parse chars to Integers and sum them
        for (int index = 0; index < factorialDigits.length(); ++index) {
            sum += Character.getNumericValue(factorialDigits.charAt(index));
        }

        return sum;
    }

    /**
     * Decryption.
     * Create a decryption function that takes as parameter an array of ASCII values. The addition between values is the ascii value decrypted.
     * decrypt([ 72, 33, -73, 84, -12, -3, 13, -13, -68 ]) ➞ "Hi there!"
     * H = 72, the sum of H 72 and 33 gives 105 which ascii value is i;
     * The function must return the string encoded using the encryption function below.
     *
     * @param ascivalues  hand, player2 hand
     */
    public String decrypt(List<Integer> ascivalues) {
        // YOUR CODE HERE...
        // If the list is empty, return an empty string
        if (ascivalues.size() == 0) {
            return "";
        }

        // Create a local list in order to avoid modifications (references)
        List<Integer> cypheredText = new ArrayList<>(ascivalues);

        StringBuilder plainText = new StringBuilder();
        // Because the first value is always the same, the following is added directly
        plainText.append((char) cypheredText.get(0).intValue());
        for (int index = 1; index < cypheredText.size(); ++index) {
            // Set the cypheredText[index] for accumulate values
            cypheredText.set(index, cypheredText.get(index) + cypheredText.get(index - 1));
            // Cast the values and add them to out string builder
            plainText.append((char) cypheredText.get(index).intValue());
        }

        return plainText.toString();
    }

    /**
     * Encryption Function.
     * Create am encryption function that takes a string and converts into an array of ASCII character values.
     * encrypt("Hello") ➞ [72, 29, 7, 0, 3]
     * // H = 72, the difference between the H and e is 29
     * The function must return an array of integer ascii values.
     *
     * @param text  hand, player2 hand
     */
    public List<Integer> encrypt(String text) {
        // YOUR CODE HERE...
        // If the text is empty, return an empty list
        if (text.length() == 0) {
            return Collections.emptyList();
        }

        // Declare a list for store the cyphered text
        List<Integer> cypheredText = new ArrayList<>(text.length());

        // Because the first value is always the same, the following is added directly
        cypheredText.add((int) text.charAt(0));
        // Iterate over the text and store the differences of text[index] - text[index - 1]
        for (int index = 1; index < text.length(); ++index) {
            cypheredText.add(text.charAt(index) - text.charAt(index - 1));
        }

        return cypheredText;
    }
}
