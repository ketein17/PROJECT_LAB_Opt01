package fa.training.models;

import java.util.Comparator;

public class BookNameCompare implements Comparator<Book> {
    public int compare(Book o1, Book o2){
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
