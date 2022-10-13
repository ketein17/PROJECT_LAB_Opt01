package fa.training.utils;

public class GenderFormatException extends Exception{
    private static final long serialVersionUID=1L;
    public GenderFormatException(){
        super();
    }

    public GenderFormatException(String message){
        super(message);
    }
}
