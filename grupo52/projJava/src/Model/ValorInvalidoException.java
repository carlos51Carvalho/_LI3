package Model;

import java.io.Serializable;

public class ValorInvalidoException extends Exception implements Serializable {

    public ValorInvalidoException() {
        super();
    }

    public ValorInvalidoException(String s){
        super(s);
    }
}
