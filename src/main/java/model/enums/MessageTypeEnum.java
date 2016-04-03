package model.enums;

/**
 * Created by Business_Book on 03.04.2016.
 */
public enum MessageTypeEnum {
    LOGIN_CUSTOMER(0, "loginCustomer"),
    CUSTOMER_API_TOKEN(1, "customerApiToken"),
    CUSTOMER_ERROR(2, "customerError");

    private int code;

    private String name;

    MessageTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
