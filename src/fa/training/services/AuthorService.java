package fa.training.services;

import fa.training.models.Author;
import fa.training.utils.EmailFormatException;
import fa.training.utils.GenderFormatException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorService {
    /*
    * Create new authors
    *
    * */

    public List<Author> createAuthor(Scanner scanner){
        String loop="";
        String name,gender,email;
        Author author;
        List<Author> authors=new ArrayList<Author>();
        do{
            author=new Author();
            System.out.println("Enter Author name:");
            name=scanner.nextLine();
            author.setName(name);
            do{
                System.out.println("Enter gender:");
                gender=scanner.nextLine();
                try{
                    author.setGender(gender.charAt(0));
                }catch(GenderFormatException exception){
                    continue;
                }
                break;
            }while(true);
            do{
                System.out.println("Enter email:");
                email=scanner.nextLine();
                try{
                    author.setEmail(email);
                }catch(EmailFormatException exception){
                    continue;
                }
                break;
            }while(true);
            /*
            * Add to author list
            * */
            authors.add(author);
            System.out.println("Do you want to continue to input author for this book(Y/N)?: ");
            loop=scanner.nextLine();
        }while(loop.charAt(0)=='Y'|loop.charAt(0)=='y');
        return authors;
    }
}
