import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println(calc(sc.nextLine()));  // ловим ошибки
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String [] arab = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
    public static String[] znak = {"/", "*", "+", "-"}; // разрешенные операции
    public static String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
    public static String[] romanDopusk = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",}; // допуск от 1 до 10
    public static String calc(String input) throws Exception {
        String[] ras = input.split(" "); // делим строку по пробелам на массив
        if (ras.length != 3){
            throw new Exception(); // формат математической операции не удовлетворяет заданию - два операнда и один оператор
        }
        String s = "";
        if (Arrays.asList(znak).contains(ras[1])) { // ищем в разреш операциях введенное
            if (Arrays.asList(romanDopusk).contains(ras[2]) && Arrays.asList(romanDopusk).contains(ras[0])) {  // есть ли в допустим значениях роман цифрах введенные и тип роман ли это
                int x = Arrays.asList(romanDopusk).indexOf(ras[0]);  // поиск индекса нужного числа А
                int y = Arrays.asList(romanDopusk).indexOf(ras[2]);  // поиск индекса нужного числа Б
                 if (y>=x){
                    throw new Exception(); // ошибка т.к. результат будет меньше 1
                 } else {
                    return convetrer(arabMath(Integer.parseInt(arab[x]), ras[1], Integer.parseInt(arab[y])));
                 } // вызов метода для решения по араб цифрам с вызовом метода ковертации для ответа в римских цифрах
            } else if (Arrays.asList(arab).contains(ras[0]) && Arrays.asList(arab).contains(ras[2])) {   // есть ли в допустим значениях араб цифрах введенные и тип араб ли это
                int i = Integer.parseInt(ras[0]), j = Integer.parseInt(ras[2]);
                if (i <= 10 && j <= 10 && i >= 1 && j >= 1) {
                    return arabMath(i, ras[1], j); // вызов метода для решения задачи по арабским цифрам
                } else {
                    throw new Exception(); // ошибка если введенные значения меньше 1 или больше 10
                }
            } else {
                throw new Exception(); // ошибка если А и Б не в допустимых значениях/не целые числа/или вообще не числа
            }
        } else {
            throw new Exception();  // ошибка если введенный знак операции не соответствует допустимому
        }
    }

    static String arabMath(int a, String s, int b) {
        if (s.equals(znak[0])) {
            s = Integer.toString(a / b);
            return s;
        } else if (s.equals(znak[1])) {
            s = Integer.toString(a * b);
            return s;
        } else if (s.equals(znak[2])) {
            s = Integer.toString(a + b);
            return s;
        } else {
            s = Integer.toString(a - b);
            return s;
        }
    }

    static String convetrer (String s){
        int x = Integer.parseInt(s);
        String r = "";
        for(int i = arab.length-1; i>=0; i--){
            int y = Integer.parseInt(arab[i]);
            while (x >= y) {
                x -= y;
                r += roman[i];
            }
        }
        return r;
    }
}
