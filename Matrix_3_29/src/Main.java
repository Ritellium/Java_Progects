import java.util.*;

/*
Бельский Алексей

29.	Упорядочить ее строки по неубыванию их наибольших элементов.
*/

/*
Test:



*/

public class Main {

    //заполнение матрицы рандомными значениями
    public static void create_matr(ArrayList<ArrayList<Integer>> matr){
        Random numb = new Random();
        for(int i = 0; i < matr.size(); i++)
            for (int j = 0; j < matr.get(i).size(); j++) {
                matr.get(i).set(j, numb.nextInt() % 10);
            }
    }

    public static int max_elem(ArrayList<Integer> elem)     //поиск максимального элемента из Matr[i]
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

    public static boolean compare(ArrayList<Integer> elem1, ArrayList<Integer> elem2) {
        return elem1.get(max_elem(elem1)) <= elem2.get(max_elem(elem2));
    }

    public static void sort(ArrayList<ArrayList<Integer>> matr)
    {
        boolean isSorted = false;
        ArrayList<Integer> buf = new ArrayList<Integer>();
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < matr.size()-1; i++) {
                if (!compare(matr.get(i), matr.get(i+1)))
                {
                    isSorted = false;

                    buf = matr.get(i);
                    matr.set(i, matr.get(i+1));
                    matr.set(i+1, buf);
                }
            }
        }
    }

    //главная функция
    public static void main_procces(ArrayList<ArrayList<Integer>> matr)
    {
        sort(matr);
    }

    public static void print(ArrayList<ArrayList<Integer>> matr)      //вывод
    {
        System.out.println("Matrix:");
        for(int i = 0; i < matr.size(); i++) {
            for (int j = 0; j < matr.get(i).size(); j++) {
                System.out.print(matr.get(i).get(j) + "\t");
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
            ArrayList<ArrayList<Integer>> matr = new ArrayList<ArrayList<Integer>>();

            for (int i=0; i<numb; i++)
            {
                ArrayList<Integer> line = new ArrayList<Integer>();
                for (int j=0; j<numb; j++)
                {
                    line.add(0);
                }
                matr.add(line);
            }

            create_matr(matr);
            print(matr);
            main_procces(matr);
            print(matr);
        }
        else
            System.out.println("Wrong matrix size!");
    }
}