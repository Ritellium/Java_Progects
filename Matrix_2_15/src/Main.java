import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;
import java.util.*;

/*
Бельский Алексей

15.	Для заданной целочисленной матрицы найти максимум среди сумм элементов диагоналей,
параллельных главной диагонали матрицы.

Используем Vector
*/

/*
Test:

Enter matrix size:
5
Matrix:
-8	7	-8	5	-6
3	3	4	-2	-7
-6	0	3	-7	4
-2	-8	4	3	6
9	6	-5	-4	-7
Massiv_summ:
-6	10	-6	-2	-6 	3	-19	4	9
Максимальная сумма: 10

*/
public class Main {

    //заполнение матрицы рандомными значениями
    public static void create_matr(Vector<Vector<Integer>> matr){
        Random numb = new Random();
        for(int i = 0; i < matr.size(); i++)
            for (int j = 0; j < matr.elementAt(i).size(); j++) {
                matr.get(i).set(j, numb.nextInt() % 10);
            }
    }

    public static int max_elem(Vector<Integer> elem)     //поиск максимального элемента из Matr[i]
    {
        if(elem.size() > 0)
        {
            int max = elem.get(0);
            int maxn = 0;
            for (int i=0; i<elem.size(); i++)
            {
                if (elem.get(i) > max)
                {
                    max = elem.get(i);
                    maxn = i;
                }
            }
            return maxn;
        }
        else
            return -1;
    }

    //главная функция
    public static void main_procces(Vector<Vector<Integer>> matr)
    {
        Vector<Integer> diag_sums = new Vector(matr.size()*2 - 1);
        for (int j=0; j<matr.size()*2 - 1; j++)
        {
            diag_sums.add(0);
        }

        for (int k=0; k<matr.size(); k++) {
            int i = 0;
            int j = k;
            while (i < matr.size() && j < matr.size()) {
                diag_sums.set(k, diag_sums.get(k) + matr.get(i).get(j));
                i++;
                j++;
            }
        }

        for (int k=1; k<matr.size(); k++) {
            int i = k;
            int j = 0;
            while (i < matr.size() && j < matr.size()) {
                diag_sums.set(matr.size()+ k-1, diag_sums.get(matr.size()+ k-1) + matr.get(i).get(j));
                i++;
                j++;
            }
        }

        print_massiv(diag_sums);
        System.out.print("Максимальная сумма: " + Integer.toString(diag_sums.get(max_elem(diag_sums))));
    }

    public static void print(Vector<Vector<Integer>> matr)      //вывод
    {
        System.out.println("Matrix:");
        for(int i = 0; i < matr.size(); i++) {
            for (int j = 0; j < matr.get(i).size(); j++) {
                System.out.print(matr.get(i).get(j) + "\t");
            }
            System.out.print('\n');
        }
    }

    public static void print_massiv(Vector<Integer> massiv)      //вывод massiv
    {
        System.out.println("Massiv_summ:");
        for(int i = 0; i < massiv.size(); i++)
        {
            System.out.print(massiv.get(i) + "\t");
        }
        System.out.print('\n');
    }

    public static void main(String[] args)      // main
    {
        System.out.println("Enter matrix size: ");
        Scanner sc = new Scanner(System.in);
        String numbS = sc.next();
        int numb = Integer.parseInt(numbS);
        if(numb > 0) {

            Vector<Vector<Integer>> matr = new Vector();

            for (int i=0; i<numb; i++)
            {
                Vector<Integer> line = new Vector();
                for (int j=0; j<numb; j++)
                {
                    line.add(0);
                }
                matr.add(line);
            }

            create_matr(matr);
            print(matr);
            main_procces(matr);
        }
        else
            System.out.println("Wrong matrix size!");
    }
}