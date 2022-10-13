package fa.training.services;

import fa.training.models.Author;
import fa.training.models.Book;
import fa.training.models.BookNameCompare;
import fa.training.utils.Constant;
import fa.training.utils.Validator;

import java.io.*;
import java.util.*;

public class BookService {

    public List<Book> createBook(Scanner scanner) {
        String loop, isbn, name, price, qtyInStock;
        double doPrice;
        int quantity;

        List<Book> books = new ArrayList<>();
        List<Author> authors = new ArrayList<Author>();
        AuthorService authorService = new AuthorService();
        Book book;
        do {
            book = new Book();

            System.out.println("Enter book id:");
            isbn = scanner.nextLine();
            System.out.println("Enter book name:");
            name = scanner.nextLine();

            do {
                System.out.println("Enter book price:");
                price = scanner.nextLine();
                try {
                    doPrice = Validator.isPrice(price);
                } catch (NumberFormatException exception) {
                    continue;
                }
                break;
            } while (true);

            do {
                System.out.println("Enter quantity of book:");
                qtyInStock = scanner.nextLine();
                try {
                    quantity = Validator.isQtyInStock(qtyInStock);
                } catch (NumberFormatException exception) {
                    continue;
                }
                break;
            } while (true);

            System.out.println("----Enter Author of Book----");
            authors = authorService.createAuthor(scanner);
            book.setIsbn(isbn);
            book.setName(name);
            book.setPrice(doPrice);
            book.setQtyInStock(quantity);
            book.setAuthors(authors);
            books.add(book);
            System.out.println("Do you want to continue to input book(Y/N)?:");
            loop = scanner.nextLine();
        } while (loop.charAt(0) == 'Y' | loop.charAt(0) == 'y');
        return books;
    }

    public String save(List<Book> listBook) throws Exception {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(Constant.FILE_PATH));
            objectOutputStream.writeObject(listBook);
        } catch (Exception exception) {
            throw new Exception();
        } finally {
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        }
        return Constant.SUCCESS;
    }

    @SuppressWarnings("unchecked")

    public List<Book> getAll() throws IOException {
        ObjectInputStream objectInputStream = null;
        List<Book> lisBook;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(Constant.FILE_PATH));
            lisBook = (List<Book>) objectInputStream.readObject();
        } catch (Exception exception) {
            throw new IOException();
        } finally {
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
        return lisBook;
    }

    public void sortAndDisplay(List<Book> listBook) {
        Collections.sort(listBook, new BookNameCompare());
        System.out.println("---------------BOOK LIST------------------");
        for (Book book : listBook) {
            System.out.format("%s%20s%12.3f%5d%20s%n", book.getIsbn(), book.getName(), book.getPrice(), book.getQtyInStock(), book.getAuthors());
        }
    }

    public String remove(String bookId) throws Exception {
        List<Book> listBook = getAll();
        if (listBook == null) {
            throw new Exception();
        }
        Iterator<Book> iterator = listBook.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();

            if (bookId.equalsIgnoreCase(book.getIsbn())) {
                iterator.remove();
            }
        }
        /*
         * Update file
         * */
        try {
            save(listBook);
        } catch (Exception e) {
            throw new Exception();
        }

        return Constant.SUCCESS;
    }

    public List<Book> getByAuthor(String author) throws IOException {
        List<Book> listBook = getAll();
        List<Book> bookByAuthor = new ArrayList<>();

        if (listBook != null) {
            for (Book book : listBook) {
                for (Author authorBook : book.getAuthors()) {
                    if (author.equalsIgnoreCase(authorBook.getName())) {
                        bookByAuthor.add(book);
                    }
                }
            }
        }
        return bookByAuthor;
    }
}
