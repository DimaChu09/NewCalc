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

//В классе Мейн ты создаешь поле мапы(коллекция, где есть ключ и значение),
// далее в конструкторе инициализируешь(то есть будешь заполнять Мапу сразу при создании объекта класса Мейн),
// далее прописываешь два метода для конвертаций
//
//В первом методе в параметре идёт буквенное обозначение цифры,
// с ней и будем работать, для этого в методе создаем переменную result,
// которая будет искомым значением, и переменную i,
// которую использовать будем как индекс для обхода str
// (то есть i будет показывать текущее положение в строке, на каком символе мы находимся для определения количества символов в строке),
// далее в цикле, в первом if мы проверяем на наличие составных цифр таких как IV, IX, в условии пишем,
// если значение индекса меньше длины строки и Мапа содержит строку с двумя символами,
// то присваиваем переменной result значение из мапы соответствующее римской цифры, если содержит 1 символ,
// то значение однобуквенной римской цифры присваиваем в результ, если нет совпадений то бросаем эксепшн
//
//Второй метод в параметр принимает число,
// которое нужно перевести в строку, после проверки диапазона числа с эксепшном,
// создаём объект стрингбилдера, и аррайлист ключей из мапы(ключи - это римские цифры),
// цикл for проходит по каждому римскому числу в обратном порядке, вложенный цикл while проверяет,
// может ли римское число быть вычтено из арабского числа num, если может,
// то римское число добавляется к result, и соответствующее арабское значение вычитается из num,
// и потом рузульт приводим к ту стринг, чтобы получить строку из стрингбилдера
//
//Далее в методе мейн(это точка входа в программу),
// создаём объект класса Мэйн(для заполнения мапы из конструктора),
// создаём объект сканера, который принимает в параметрах ввод в консоль,
// потом создаём переменную типа стринг для того, что будем вводить,
// приводим к верхнему регистру и удаляем пробелы, потом сплитим эту строку на 3 части
//
//Дальше создаём два булеан является ли число римским, если одно римское а другое нет, то исключение
//
//Дальше проверка, если int a, римское, то переводим в арабское, и так же про б
//
//Потом условие если число вне диапазона, то исключение бросается
//
//Потом через свитч связываем знак операции с конкретной операцией
//
//И в конце выводим результат