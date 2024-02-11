import java.util.*;

public class Main {

    private final Map<String, Integer> romanMap = new LinkedHashMap<>();

    public Main() {
        romanMap.put("I", 1);
        romanMap.put("IV", 4);
        romanMap.put("V", 5);
        romanMap.put("IX", 9);
        romanMap.put("X", 10);
    }

    public int romanToInteger(String str) {
        int result = 0, i = 0;
        while (i < str.length()) {
            if (i + 1 < str.length() && romanMap.containsKey(str.substring(i, i + 2))) {
                result += romanMap.get(str.substring(i, i + 2));
                i += 2;
            } else if (romanMap.containsKey(str.substring(i, i + 1))) {
                result += romanMap.get(str.substring(i, i + 1));
                i++;
            } else {
                throw new IllegalArgumentException("Неверное римское число");
            }
        }
        return result;
    }

    public String integerToRoman(int num) {
        if (num <= 0) throw new IllegalArgumentException("Римские цифры не могут быть отрицательными или равными нулю");

        StringBuilder result = new StringBuilder();
        List<String> keys = new ArrayList<>(romanMap.keySet());
        Collections.reverse(keys);

        for (String s : keys) {
            while (num >= romanMap.get(s)) {
                result.append(s);
                num -= romanMap.get(s);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Main calculator = new Main();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше выражение:");
        String input = scanner.nextLine().toUpperCase().trim();

        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат");
        }

        boolean isRomanA = !Character.isDigit(parts[0].charAt(0));
        boolean isRomanB = !Character.isDigit(parts[2].charAt(0));

        if (isRomanA != isRomanB) {
            throw new IllegalArgumentException("Нельзя смешивать русские и арабские цифры");
        }

        int a = isRomanA ? calculator.romanToInteger(parts[0]) : Integer.parseInt(parts[0]);
        int b = isRomanB ? calculator.romanToInteger(parts[2]) : Integer.parseInt(parts[2]);

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно");
        }

        int result = 0;
        switch (parts[1].charAt(0)) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Неверная операция");
        }

        if (isRomanA && result <= 0) {
            throw new IllegalArgumentException("Результат меньше или равен нулю в римских цифрах");
        }

        System.out.println(isRomanA ? calculator.integerToRoman(result) : result);
    }
}

