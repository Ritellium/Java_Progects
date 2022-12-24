import java.lang.Math;
import java.io.IOException;
import java.io.*;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.*;
import java.math.BigInteger;
import java.math.BigDecimal;

/**
 * Вариант 1
 * Бельский Алексей
 * @author fpm.belskiyAA2
 */

/*
* Тест
Введите x ∈ (-inf,+inf):
25
Введите натуральное число k:
8
Результат через стандартные функции:
72 004 899 337,386
Мой результат:
72 004 899 337,386

Введите x ∈ (-inf,+inf):
100
Введите натуральное число k:
10
Результат через стандартные функции:
26 881 171 418 161 356 000 000 000 000 000 000 000 000 000
Мой результат:
26 881 171 418 161 354 484 126 255 515 800 172 219 284 595,65
* */

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
    public  static BigDecimal myPow(BigDecimal x, BigInteger deg)
    {
        BigDecimal one = new BigDecimal("1");
        BigInteger zero = new BigInteger("0");
        if (deg.compareTo(zero) == 1)
        {
            for (BigInteger i = deg; i.compareTo(zero) == 1;
                 i = new BigInteger((i.remainder(BigInteger.ONE)).toString()) ) {
                one = new BigDecimal((one.multiply(x)).toString());
            }
        }
        else if (deg.compareTo(zero) == -1)
        {
            for (BigInteger i = deg; i.compareTo(zero) == -1;
                 i = new BigInteger((i.add(BigInteger.ONE)).toString())) {
                one = new BigDecimal((one.divide(x)).toString());
            }
        }
        return one;
    }

    public static BigDecimal myFunction(BigDecimal x, BigDecimal eps_k) {
        BigDecimal sum = BigDecimal.ONE,
                chl_t = BigDecimal.ONE,
                per = BigDecimal.ONE;

        while ((chl_t.abs()).compareTo(eps_k) == 1)
        {
            BigDecimal ar = new BigDecimal( (x.divide(per, MathContext.DECIMAL128)).toString() );

            chl_t = new BigDecimal( (chl_t.multiply(ar)).toString() );
            per = new BigDecimal( (per.add(BigDecimal.ONE)).toString() );
            sum = new BigDecimal( (sum.add(chl_t)).toString() );
        }
        return sum;
    }

    public static void main(String[] args) {
        InputStreamReader isr=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(isr);
        try {
            System.out.println("Введите x ∈ (-inf,+inf): ");
            String line = br.readLine();
            BigDecimal ourNumber = new BigDecimal(line);

            System.out.println("Введите натуральное число k: ");
            String secondLine = br.readLine();
            BigInteger ourDegree = new BigInteger(secondLine);
            ourDegree = new BigInteger((ourDegree.negate()).toString());

            BigDecimal e = myPow(BigDecimal.TEN, ourDegree);

            System.out.println("Результат через стандартные функции: ");
            double result = Math.exp(ourNumber.doubleValue());
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(Integer.parseInt(secondLine));
            System.out.println(formatter.format(result));

            System.out.println("Мой результат: ");
            BigDecimal myResult = myFunction(ourNumber, e);
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