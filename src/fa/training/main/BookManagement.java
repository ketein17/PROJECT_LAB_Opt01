package fa.training.main;

import fa.training.models.Book;
import fa.training.services.BookService;
import fa.training.utils.Constant;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BookManagement {
    private static List<Book> listNewBook;
    private static List<Book> listBook;

    public static void main(String[] args) {
        BookService bookService=new BookService();
        Scanner scanner=null;
        String status, authorName,choice,bookId;
        List<Book> bookByAuthor;
        try{
            scanner=new Scanner(System.in);
            do{
                System.out.println("-----------------------------MENU-----------------------------");
                System.out.println("1.Create new Book\n 2.Save to File\n 3.Sort by Name\n "
                +"4.Find by Author\n 5.Remove book\n 6.Exit");
                System.out.println("Select: ");
                choice=scanner.nextLine();
                choice=choice.trim();
                switch (choice){
                    case Constant.INPUT:{
                        if(listNewBook!=null){
                            listNewBook.clear();
                        }
                        listNewBook=bookService.createBook(scanner);
                        System.out.println("Input Done!");
                        break;
                    }
                    case Constant.SAVE:{
                        try{
                            if(listNewBook==null){
                                throw new Exception();
                            }
                            status=bookService.save(listNewBook);
                            System.out.println("Save: "+status);
                        }catch (Exception e){
                            System.out.println("Save Fail!");
                        }
                        break;
                    }
                    case Constant.SORT:{
                        if(listBook!=null){
                            listBook.clear();
                        }
                        try{
                            listBook=bookService.getAll();
                            if(listBook==null){
                                throw new IOException();
                            }
                            bookService.sortAndDisplay(listBook);
                        }catch (IOException e){
                            System.out.println("No Data!");
                        }
                        break;
                    }

                    case Constant.SEARCH:{
                        try{
                            System.out.println("Enter author name: ");
                            authorName=scanner.nextLine();
                            bookByAuthor=bookService.getByAuthor(authorName);
                            if(bookByAuthor==null){
                                throw new IOException();
                            }
                            System.out.println("----List of Book----");
                            for(Book book:bookByAuthor){
                                System.out.println(book.getIsbn()+"\t"+book.getName()+"\t"
                                +book.getPrice()+"\t"+book.getQtyInStock());
                            }
                        }catch (IOException e){
                            System.out.println("No Data");
                        }
                        break;
                    }

                    case Constant.REMOVE:{
                        System.out.println("Enter book id to remove: ");
                        bookId=scanner.nextLine();

                        try{
                            status=bookService.remove(bookId);

                            System.out.println("Remove: "+status);
                        }catch (Exception e){
                            System.out.println("Remove Fail!");
                        }
                        break;
                    }

                    default:{
                        choice= Constant.EXIT;
                        break;
                    }
                }
            }while(!choice.equalsIgnoreCase(Constant.EXIT));
        }finally {
            if(scanner!=null){
                scanner.close();
            }
        }
    }
}
