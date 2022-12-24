import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;

/*
Бельский Алексей

1.	Поменять местами строку, содержащую элемент с наибольшим значением в матрице со строкой,
содержащей элемент с наименьшим значением. Вывести на экран полученную матрицу.
Для каждой строки с нулевым элементом на главной диагонали вывести ее
номер  и значение наибольшего из элементов этой строки.
*/

/*
Test:

Enter matrix size:
5
Matrix:
0	-5	-2	-3	-9
2	0	-7	-9	6
-5	0	-1	2	6
-3	5	-3	-2	3
6	-8	-4	-7	4
Строка с нулевым элементом 0, максимум: 0
Строка с нулевым элементом 1, максимум: 6
Меняем матрицу...
Matrix:
2	0	-7	-9	6
0	-5	-2	-3	-9
-5	0	-1	2	6
-3	5	-3	-2	3
6	-8	-4	-7	4

*/
public class Main {

    //заполнение матрицы рандомными значениями
    public static void create_matr(Integer matr[][]){
        Random numb = new Random();
        for(int i = 0; i < matr.length; i++)
            for (int j = 0; j < matr[i].length; j++) {
                matr[i][j] = numb.nextInt() % 10;
            }
    }

    //главная функция
    public static void main_procces(Integer matr[][]){
        for (int i=0; i<matr.length; i++)
        {
            if (matr[i][i] == 0)
            {
                int max = max_elem(matr[i]);
                System.out.println("Строка с нулевым элементом " + Integer.toString(i) + ", максимум: " + Integer.toString(matr[i][max]));
            }
        }
        System.out.println("Меняем матрицу...");
        int maxs = max_str(matr), mins = min_str(matr);
        Integer elem[] = new Integer[matr.length];
        elem = matr[mins];
        matr[mins] = matr[maxs];
        matr[maxs] = elem;
    }

    public static int max_elem(Integer elem[])     //поиск максимального элемента из Matr[i]
    {
        if(elem.length > 0)
        {
            int max = elem[0];
            int maxn = 0;
            for (int i=0; i<elem.length; i++)
            {
                if (elem[i] > max)
                {
                    max = elem[i];
                    maxn = i;
                }
            }
            return maxn;
        }
        else
            return -1;
    }

    public static int max_str(Integer matr[][])     //поиск максимального элемента из Matr
    {
        if(matr.length > 0)
        {
            int max = matr[0][0];
            int maxs = 0;
            for (int i=0; i<matr.length; i++)
            {
                for (int j=0; j<matr[i].length; j++)
                {
                    if (matr[i][j] > max)
                    {
                        max = matr[i][j];
                        maxs = i;
                    }
                }
            }
            return maxs;
        }
        else
            return -1;
    }

    public static int min_str(Integer matr[][])     //поиск минимального элемента из Matr
    {
        if(matr.length > 0)
        {
            int min = matr[0][0];
            int mins = 0;
            for (int i=0; i<matr.length; i++)
            {
                for (int j=0; j<matr[i].length; j++)
                {
                    if (matr[i][j] < min)
                    {
                        min = matr[i][j];
                        mins = i;
                    }
                }
            }
            return mins;
        }
        else
            return -1;
    }

    public static void print(Integer matr[][])      //вывод
    {
        System.out.println("Matrix:");
        for(int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr[i].length; j++) {
                System.out.print(matr[i][j] + "\t");
            }
            System.out.print('\n');
        }
    }

    public static void main(String[] args)      // main
    {
        System.out.println("Enter matrix size: ");
        Scanner sc = new Scanner(System.in);
        String numbS = sc.next();
        Integer numb = Integer.parseInt(numbS);
        if(numb > 0) {
            Integer matr[][] = new Integer[numb][numb];
            create_matr(matr);
            print(matr);
            main_procces(matr);
            print(matr);
        }
        else
            System.out.println("Wrong matrix size!");
    }
}