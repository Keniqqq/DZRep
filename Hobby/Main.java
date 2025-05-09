import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        compare(1);
        compare(2);
        compare(5);
        compare(15);
    }

    public static void compare(int day) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = {21, 1, 20, 23};
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day, new int[day + 1]);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive);
        System.out.println();
    }

    public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] memory) {
        if (day < 4) {
            return startNumbers[day];
        }
        if (memory[day] != 0) {
            return memory[day];
        }

        int prev = chooseHobbyRecursive(startNumbers, day - 1, memory);
        int prePrePrev = chooseHobbyRecursive(startNumbers, day - 3, memory);

        int result = (prev * prePrePrev) % 10 + 1;
        memory[day] = result;
        return result;
    }

    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            numbers.add(startNumbers[i]);
        }

        for (int d = 4; d <= day; d++) {
            int prev = numbers.get(d - 1);
            int prePrePrev = numbers.get(d - 3);
            numbers.add((prev * prePrePrev) % 10 + 1);
        }

        return numbers.get(day);
    }
}