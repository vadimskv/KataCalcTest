import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите операцию в формате: 2 + 3 или VI - II");
        System.out.println("Поддерживаются операции с арабскими или римскими цифрами от 1 до 10");
        System.out.println("Поддерживаемые операнды: + - * /");
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        System.out.println(calc(operation));
    }

    public static String calc(String input) throws Exception {
        String[] operands = input.split(" ");

        if (operands.length < 3) {
            throw new Exception("т.к. строка не является математической операцией");
        } else if (operands.length > 3) {
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        Roman roman = new Roman();
        boolean verify1 = roman.verifyRoman(operands[0]);
        boolean verify2 = roman.verifyRoman(operands[2]);
        if (verify1 ^ verify2) {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        } else if (verify1) {
            operands[0] = roman.romanToArab(operands[0]);
            operands[2] = roman.romanToArab(operands[2]);
        }
        int num1, num2, result;
        try {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[2]);
        } catch (NumberFormatException ex) {
            throw new Exception("т.к неподходящий формат операндов");
        }
        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
            throw new Exception("т.к калькулятор принимает на вход числа N, 1<=N<=10");
        }
        String operator = operands[1];
        result = switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default ->
                    throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        };
        if (verify1) {
            if (result <= 0) {
                throw new Exception("//т.к. в римской системе нет ноля и отрицательных чисел");
            }
            return roman.romanNumbers[result];
        }
        return Integer.toString(result);


    }
}
    class Roman {
        String[] romanNumbers = {"",
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

        boolean verifyRoman(String number) {
            for (String n : romanNumbers) {
                if (number.equals(n)) {
                    return true;
                }
            }
            return false;
        }

        String romanToArab(String number) {
            for (int i = 1; i <= 100; i++) {
                if (number.equals(romanNumbers[i])) {
                    return Integer.toString(i);
                }
            }
            return "";
        }
    }
