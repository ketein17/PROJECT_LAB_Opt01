package fa.training.utils;

public class EmailFormatException extends Exception{
    private static final long serialVersionUID=1L;
    public EmailFormatException(){
        super();
    }

    public EmailFormatException(String message){
        super(message);
    }
}
