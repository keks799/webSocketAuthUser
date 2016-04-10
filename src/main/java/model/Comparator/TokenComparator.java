package model.Comparator;

import model.entity.Token;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Business_Book on 10.04.2016.
 */
public class TokenComparator implements Serializable, Comparator<Token> {

    private static final Logger logger = Logger.getLogger(TokenComparator.class);

    public int compare(Token t1, Token t2) {
        return t2.getCreationDate().compareTo(t1.getCreationDate());
    }
}
