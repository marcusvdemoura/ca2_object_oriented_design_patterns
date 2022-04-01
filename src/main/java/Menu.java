
import filesmanagement.ReadFromFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    private Scanner sc = new Scanner(System.in);


    public void runMenu() throws IOException {

        // All steps of the way the user has the option to exit the application or to go back to the previous menu.
        /*
        First menu where the user will have the option to see all transactions for the three companies or
        to chose more options for each company
        */
        firstMenu:
        while (true) {
            System.out.println("Menu options:\n" +
                    "1: See all transactions\n" +
                    "2: Choose company for more information\n" +
                    "0: EXIT the program\n");
            int choiceFirstMenu = sc.nextInt();
            switch (choiceFirstMenu) {
                case 1:
                    System.out.println(ReadFromFile.getInstance().readFromFile("all_transactions"));
                    continue firstMenu;

                case 2:
                    /*
                    Second menu where the user will have the option choose the company to each they want more info.
                    */
                    secondMenu:
                    while (true) {
                        System.out.println("Which company do you want more information about?");
                        System.out.println("1 - BIG-Alpha\n" +
                                "2 - BIG-Beta\n" +
                                "3 - BIG-Cappa\n" +
                                "4 - Back to the previous option\n" +
                                "0 - EXIT the program\n");
                        int choiceSecondMenu = sc.nextInt();
                        String fileToRead = "";

                        if (choiceSecondMenu == 4) {
                            continue firstMenu;
                        }

                        switch (choiceSecondMenu) {
                            /*
                            Third menu where the user will have the option choose
                            to check the stocks (native and external) for each depot in a company,
                            to check the cash balance for each depot of the company.
                            */
                            case 1:
                                bigAlphaMenu:
                                while (true) {
                                    System.out.println("1 - See transactions\n" +
                                            "2 - Own products per depot\n" +
                                            "3 - Foreign products per depot\n" +
                                            "4 - Cash balance per depot\n" +
                                            "5 - Go back to previous options\n" +
                                            "6 - Go back to first menu\n" +
                                            "0 - EXIT program\n");
                                    int choiceThirdMenu = sc.nextInt();

                                    if (choiceThirdMenu == 5) {
                                        continue secondMenu;
                                    } else if (choiceThirdMenu == 6) {
                                        continue firstMenu;
                                    } else if (choiceThirdMenu == 0) {
                                        System.out.println("Good bye!");
                                        System.exit(0);
                                    }

                                    switch (choiceThirdMenu) {
                                        case 1:
                                            System.out.println(ReadFromFile.getInstance().readFromFile("Big_Alpha_transactions"));
                                            continue bigAlphaMenu;
                                        case 2:
                                            Arrays.asList(CompanyDriver.bigAlpha.getDepotsArr()).
                                                    stream()
                                                    .forEach(d -> {
                                                        System.out.printf(
                                                                "For depot number %s there are %d native products\n",
                                                                d.getDepotNumber(), d.countNative());
                                                    });
                                            continue bigAlphaMenu;

                                        case 3:
                                            Arrays.asList(CompanyDriver.bigAlpha.getDepotsArr()).
                                                    stream()
                                                    .forEach(d -> {
                                                        System.out.printf(
                                                                "For depot number %s there are %d foreign products\n",
                                                                d.getDepotNumber(), d.countExternal());
                                                    });
                                            continue bigAlphaMenu;

                                        case 4:
                                            Arrays.asList(CompanyDriver.bigAlpha.getDepotsArr()).
                                                    stream()
                                                    .forEach(d -> {
                                                        System.out.printf(
                                                                "For depot number %s there is %d balance\n",
                                                                d.getDepotNumber(), d.getAllowance());
                                                    });
                                            continue bigAlphaMenu;


                                    }
                                }
                            case 2:
                                bigBetaMenu:
                                while (true) {
                                    System.out.println("1 - See transactions\n" +
                                            "2 - Own products per depot\n" +
                                            "3 - Foreign products per depot\n" +
                                            "4 - Cash balance per depot\n" +
                                            "5 - Go back to previous options\n" +
                                            "6 - Go back to first menu\n" +
                                            "0 - EXIT program\n");
                                    int choiceThirdMenu = sc.nextInt();

                                    if (choiceThirdMenu == 5) {
                                        continue secondMenu;
                                    } else if (choiceThirdMenu == 6) {
                                        continue firstMenu;
                                    } else if (choiceThirdMenu == 0) {
                                        System.out.println("Good bye!");
                                        System.exit(0);
                                    }

                                    switch (choiceThirdMenu) {
                                        case 1:
                                            System.out.println(ReadFromFile.getInstance().readFromFile("Big_Beta_transactions"));
                                            continue bigBetaMenu;
                                        case 2:
                                            Arrays.asList(CompanyDriver.bigBeta.getDepotsArr()).
                                                    stream()
                                                    .forEach(d -> {
                                                        System.out.printf(
                                                                "For depot number %s there are %d native products\n",
                                                                d.getDepotNumber(), d.countNative());
                                                    });
                                            continue bigBetaMenu;

                                        case 3:
                                            Arrays.asList(CompanyDriver.bigBeta.getDepotsArr()).
                                                    stream()
                                                    .forEach(d -> {
                                                        System.out.printf(
                                                                "For depot number %s there are %d foreign products\n",
                                                                d.getDepotNumber(), d.countExternal());
                                                    });
                                            continue bigBetaMenu;

                                        case 4:
                                            Arrays.asList(CompanyDriver.bigBeta.getDepotsArr()).
                                                    stream()
                                                    .forEach(d -> {
                                                        System.out.printf(
                                                                "For depot number %s there is %d balance\n",
                                                                d.getDepotNumber(), d.getAllowance());
                                                    });
                                            continue bigBetaMenu;


                                    }
                                }
                            case 3:
                                bigCappaMenu:
                                while (true) {
                                    System.out.println("1 - See transactions\n" +
                                            "2 - Own products per depot\n" +
                                            "3 - Foreign products per depot\n" +
                                            "4 - Cash balance per depot\n" +
                                            "5 - Go back to previous options\n" +
                                            "6 - Go back to first menu\n" +
                                            "0 - EXIT program\n");
                                    int choiceThirdMenu = sc.nextInt();

                                    if (choiceThirdMenu == 5) {
                                        continue secondMenu;
                                    } else if (choiceThirdMenu == 6) {
                                        continue firstMenu;
                                    } else if (choiceThirdMenu == 0) {
                                        System.out.println("Good bye!");
                                        System.exit(0);
                                    }

                                    switch (choiceThirdMenu) {
                                        case 1:
                                            System.out.println(ReadFromFile.getInstance().readFromFile("Big_Cappa_transactions"));
                                            continue bigCappaMenu;
                                        case 2:
                                            Arrays.asList(CompanyDriver.bigCappa.getDepotsArr()).
                                                    stream()
                                                    .forEach(d -> {
                                                        System.out.printf(
                                                                "For depot number %s there are %d native products\n",
                                                                d.getDepotNumber(), d.countNative());
                                                    });
                                            continue bigCappaMenu;

                                        case 3:
                                            Arrays.asList(CompanyDriver.bigCappa.getDepotsArr()).
                                                    stream()
                                                    .forEach(d -> {
                                                        System.out.printf(
                                                                "For depot number %s there are %d foreign products\n",
                                                                d.getDepotNumber(), d.countExternal());
                                                    });
                                            continue bigCappaMenu;

                                        case 4:
                                            Arrays.asList(CompanyDriver.bigCappa.getDepotsArr()).
                                                    stream()
                                                    .forEach(d -> {
                                                        System.out.printf(
                                                                "For depot number %s there is %d balance\n",
                                                                d.getDepotNumber(), d.getAllowance());
                                                    });
                                            continue bigCappaMenu;


                                    }
                                }

                            case 0:
                                System.out.println("Good bye!");
                                System.exit(0);

                        }


                    }


                case 0:
                    System.out.println("Good bye!");
                    System.exit(0);
                default:
                    break;

            }
        }

    }


}
