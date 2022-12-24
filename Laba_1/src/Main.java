import java.util.Scanner;
import java.lang.Math;
import java.io.IOException;
import java.io.*;
import java.text.*;

/**
 * Вариант 1
 * Бельский Алексей
 * @author fpm.belskiyAA2
 */

/*
Вычислить приближённое значение суммы бесконечного ряда.
Вычисления заканчивать, когда очередное слагаемое окажется
по модулю меньше заданного числа ε. Вид этого числа
определяется следующим условием:
ε=10^(-k), где k - натуральное число. Сравнить полученный
результат со значением, вычисленным через стандартные функции.
Вывести на консоль оба результата.
Ряд: e^x=1+x/1!+(x^2)/2!..,x ∈ (-inf,+inf)
*/

public class Main {
    public  static double myPow(double x, int stepen) {
        double result = 1;
        if (stepen > 0) {
            for (int i = stepen; i > 0; i--) {
                result *= x;
            }
        } else if (stepen < 0) {
            for (int i = stepen; i < 0; i++) {
                result /= x;
            }
        } else {
            result = 1;
        }
        return result;
    }
    public static double myFunction(double x, double eps_k) {
        double sum = 1;
        double chl_t = 1, per = 1;
        while (Math.abs(chl_t) > eps_k) {
            chl_t *= x / per;
            per++;
            sum += chl_t;
        }
        return sum;
    }

    public static void main(String[] args) {
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        try {
            System.out.println("Введите x ∈ (-inf,+inf): ");
            String line=br.readLine();
            double ourNumber = Double.parseDouble(line);
            System.out.println("Введите натуральное число k: ");
            String secondLine=br.readLine();
            int ourDegree = Integer.parseInt(secondLine);
            ourDegree = -ourDegree;
            double e = myPow(10, ourDegree);
            System.out.println("Результат через стандартные функции: ");
            double result = Math.exp(ourNumber);
            NumberFormat formatter=NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(3);
            System.out.println(formatter.format(result));
            System.out.println("Мой результат: ");
            double myResult = myFunction(ourNumber, e);
            System.out.println(formatter.format(myResult));
        }
        catch (NumberFormatException e) {
            System.out.println("Не число");
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры");
        }
    }
}