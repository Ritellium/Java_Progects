import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/*
1) Задача "контакты"
а) разработать класс Контакт, определяющий запись в электронной книге мобильного
телефона и содержащий по меньшей мере следующие поля:
- *Наименование (имя человека или организации)
- *Номер телефона мобильного
- Номер телефона рабочего
- Номер телефона (домашнего)
- Адрес электронной почты
- Адрес веб-страницы
- Адрес

Обязательными является поля помеченные *, остальные поля могут быть пустыми

б) класс Контакт должен реализовать:
-интерфейс Comparable и Comparator с возможностью выбора одного из полей для сравнения
-интерфейс Iterator - индексатор по всем полям объекта Контакт
-метод для сохранения значений всех полей в строке текста (переопределить toString())
-конструктор или метод для инициализации объекта из строки текста
*/

public class Contact implements Comparable<Contact>
{
    static public class ToFewArguments extends Throwable {}
    static public class ToMuchArguments extends Throwable {}
    private ArrayList<String> data;

    public Contact(String input, String delimiter) throws ToFewArguments, ToMuchArguments
    {
        String inputSplitted[] = input.split(delimiter);
        if (inputSplitted.length < 2)
        {
            throw new ToFewArguments();
        }
        else if(inputSplitted.length > 7)
        {
            throw new ToMuchArguments();
        }
        else
        {
            data = new ArrayList<>();
            for(String field: inputSplitted)
            {
                data.add(field);
            }
        }
    }

    public Iterator<String> getIterator()
    {
        return data.iterator();
    }

    @Override
    public String toString()
    {
        String str = new String();
        for (String cont: data)
        {
            str += cont + " ";
        }
        return str;
    }

    public int compareTo(Contact cont) // CompareName
    {
        return data.get(0).compareTo(cont.data.get(0));
    }

    static class ContactComparatorName implements Comparator<Contact> {

        public int compare(Contact contact1, Contact contact2){

            return contact1.data.get(0).compareTo(contact2.data.get(0));
        }
    }

    static class ContactComparatorMobileNumber implements Comparator<Contact> {

        public int compare(Contact contact1, Contact contact2){

            return contact1.data.get(1).compareTo(contact2.data.get(1));
        }
    }

    static class ContactComparatorWorkNumber implements Comparator<Contact> {

        public int compare(Contact contact1, Contact contact2)
        {
            if (contact1.data.size() > 2 && contact2.data.size() > 2)
            {
                return contact1.data.get(2).compareTo(contact2.data.get(2));
            }
            else
            {
                Integer size1 = contact1.data.size();
                Integer size2 = contact2.data.size();
                return size1.compareTo(size2);
            }
        }
    }

    static class ContactComparatorHomeNumber implements Comparator<Contact> {

        public int compare(Contact contact1, Contact contact2)
        {
            if (contact1.data.size() > 3 && contact2.data.size() > 3)
            {
                return contact1.data.get(3).compareTo(contact2.data.get(3));
            }
            else
            {
                Integer size1 = contact1.data.size();
                Integer size2 = contact2.data.size();
                return size1.compareTo(size2);
            }
        }
    }

    static class ContactComparatorEmail implements Comparator<Contact> {

        public int compare(Contact contact1, Contact contact2)
        {
            if (contact1.data.size() > 4 && contact2.data.size() > 4)
            {
                return contact1.data.get(4).compareTo(contact2.data.get(4));
            }
            else
            {
                Integer size1 = contact1.data.size();
                Integer size2 = contact2.data.size();
                return size1.compareTo(size2);
            }
        }
    }

    static class ContactComparatorVebPage implements Comparator<Contact> {

        public int compare(Contact contact1, Contact contact2)
        {
            if (contact1.data.size() > 5 && contact2.data.size() > 5)
            {
                return contact1.data.get(5).compareTo(contact2.data.get(5));
            }
            else
            {
                Integer size1 = contact1.data.size();
                Integer size2 = contact2.data.size();
                return size1.compareTo(size2);
            }
        }
    }

    static class ContactComparatorAdress implements Comparator<Contact> {

        public int compare(Contact contact1, Contact contact2)
        {
            if (contact1.data.size() > 6 && contact2.data.size() > 6)
            {
                return contact1.data.get(6).compareTo(contact2.data.get(6));
            }
            else
            {
                Integer size1 = contact1.data.size();
                Integer size2 = contact2.data.size();
                return size1.compareTo(size2);
            }
        }
    }
}
