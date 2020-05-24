package Model;

import java.io.Serializable;

public class NotValideException extends Exception implements Serializable {

    public NotValideException() {
        super();
    }

    public NotValideException(String s){
        super(s);
    }
}

