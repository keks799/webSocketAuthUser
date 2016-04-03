package model.response;

import org.apache.log4j.Logger;

/**
 * Created by Business_Book on 03.04.2016.
 */
public class ErrorResponseEntity {

    private static final Logger logger = Logger.getLogger(ErrorResponseEntity.class);

    private String errorDescription;

    private String errorCode;

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
