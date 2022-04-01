package Entities.enums;

public enum Products {

    WIDGETS(1, "Widgets"),
    BRACES(2, "Braces"),
    CRATES(3, "Crates");

    Integer code;
    String description;

    Products(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
    public Integer getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
    public static Products toEnum(Integer code){
        if (code == null){
            return null;
        }

        for (Products x : Products.values()){
            if(code.equals(x.getCode())){
                return x;
            }
        }

        throw new IllegalArgumentException("Wrong product code input. Code: " + code);
    }
}
