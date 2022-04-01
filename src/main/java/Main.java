import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        CompanyDriver cd = new CompanyDriver();
        cd.driver();

        Menu menu = new Menu();
        menu.runMenu();

    }
}
