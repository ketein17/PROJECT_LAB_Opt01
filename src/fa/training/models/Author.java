package fa.training.models;

import fa.training.utils.EmailFormatException;

import fa.training.utils.GenderFormatException;
import fa.training.utils.Validator;
import java.io.Serializable;

public class Author implements Serializable {
    private static final long serialVersionUID=1L;
    private String name;
    private String email;
    private char gender;
    public Author(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) throws EmailFormatException {
        if(Validator.isEmail(email)){
            this.email=email;
        }
        else{
            throw new EmailFormatException("Email is invalid!");
        }
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) throws GenderFormatException {
        if(Validator.isGender(gender)){
            this.gender=gender;
        }
        else{
            throw new GenderFormatException("Gender is invalid!");
        }
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name  +
                ", email='" + email +
                ", gender=" + gender +
                '}';
    }
}
