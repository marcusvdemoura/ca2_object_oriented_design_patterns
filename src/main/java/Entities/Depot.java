package Entities;

import Entities.enums.Products;
import filesmanagement.WriteToFile;

import java.io.IOException;
import java.util.*;

public class Depot {
    private String depotNumber;
    private Integer allowance;
    private Integer nativeProduct;
    private Company company;

    private Map<Integer, Integer> productsPriceMap = new HashMap<>();
    private Integer deliveryPrice;

    private ArrayList<Products> productsList = new ArrayList<>();

    public Depot(String depotNumber, Integer allowance, Integer nativeProduct) {
        this.depotNumber = depotNumber;
        this.allowance = allowance;
        this.nativeProduct = nativeProduct;

        // Companies and depots most be created in memory with random value prices (products and delivery).
        Integer price = (new Random().nextInt(10 - 1 + 1) + 1);
        productsPriceMap.put(Products.WIDGETS.getCode(), price);

        price = (new Random().nextInt(10 - 1 + 1) + 1);
        productsPriceMap.put(Products.BRACES.getCode(), price);

        price = (new Random().nextInt(10 - 1 + 1) + 1);
        productsPriceMap.put(Products.CRATES.getCode(), price);

        price = (new Random().nextInt(10 - 1 + 1) + 1);
        deliveryPrice = price;

        setItemsDepots();
    }

    public String getDepotNumber() {
        return depotNumber;
    }

    public void setDepotNumber(String depotNumber) {
        this.depotNumber = depotNumber;
    }

    public Integer getAllowance() {
        return allowance;
    }

    public void setAllowance(Integer allowance) {
        this.allowance = allowance;
    }

    public Products getNativeProduct() {
        return Products.toEnum(nativeProduct);
    }

    public void setNativeProduct(Products nativeProduct) {
        this.nativeProduct = nativeProduct.getCode();
    }

    public ArrayList<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(ArrayList<Products> productsList) {
        this.productsList = productsList;
    }

    public Map<Integer, Integer> getProductsPriceMap() {
        return productsPriceMap;
    }

    public void setProductsPriceMap(Map<Integer, Integer> productsPriceMap) {
        this.productsPriceMap = productsPriceMap;
    }

    public Integer getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(Integer deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    // Companies and depots most be created in memory with random values of stock
    public void setItemsDepots() {
        int countNative = 0;
        int countExternal = 0;
        int totalProducts = (int) Math.floor(Math.random() * (70 - 17) + 17);

        while ((countNative + countExternal) < totalProducts) {
            int random = (int) Math.floor(Math.random() * (3 - 1 + 1) + 1);

            if (totalProducts == 70) {
                if (random == nativeProduct && countNative < 40) {
                    productsList.add(Products.toEnum(random));
                    countNative++;
                } else if (random != nativeProduct && countExternal < 30) {
                    productsList.add(Products.toEnum(random));
                    countExternal++;
                }
            } else {

                if (random == nativeProduct && countNative < 40) {
                    productsList.add(Products.toEnum(random));
                    countNative++;
                } else if (random != nativeProduct && countExternal < 30) {
                    productsList.add(Products.toEnum(random));
                    countExternal++;
                }

            }
        }

    }

    public String countItemsInDepots() {
        int countNative = 0;
        int countExternal = 0;
        int totalProducts = 0;
        String result = "";

        String productsPrices = "";

        for (Map.Entry<Integer, Integer> e : productsPriceMap.entrySet()) {
            productsPrices += Products.toEnum(e.getKey()).getDescription() + "= " + "€" + e.getValue() + ", ";
        }


        countNative = (int) productsList.stream().filter(p -> p.getCode() == nativeProduct).count();
        countExternal = (int) productsList.stream().filter(p -> p.getCode() != nativeProduct).count();
        totalProducts = countExternal + countNative;
        result += "\nDepot number " + depotNumber +
                "=\nNative products= " + countNative + "\nExternal products= " + countExternal
                + "\nAllowance= " + allowance + "\nDelivery price= " + deliveryPrice +
                "\nPrices= { " + productsPrices + "} " +
                "\nTOTAL PRODUCTS = " + totalProducts + "\n\n";


        return result;
    }

    public Integer countNative() {

        // Lambda expression to return the total of native products in the depot
        return (int) productsList.stream().filter(pr -> pr.getCode() == nativeProduct).count();
    }

    public Integer countExternal() {

        // Lambda expression to return the total of external products in the depot
        return (int) productsList.stream().filter(pr -> pr.getCode() != nativeProduct).count();
    }


    private Products sellProduct(Products p) throws IOException {
//      A depot cannot go below its minimum stock of its purchase products
        if (p != getNativeProduct() && countExternal() > 2) {
            Products pr = null;
            for (int i = 0; i < productsList.size(); i++) {
                if (p == productsList.get(i)) {
                    pr = productsList.get(i);
                    productsList.remove(i);
                    Integer index = p.getCode();
                    allowance += productsPriceMap.get(index) + deliveryPrice;
                    WriteToFile.getInstance().WriteToFile(
                            company.getName().replace(" ", "_"), toString());
                    return pr;
                }
            }
//      A depot cannot go below its minimum stock of its native product
        } else if (p == getNativeProduct() && countNative() > 15) {
            Products pr = null;
            for (int i = 0; i < productsList.size(); i++) {
                if (p == productsList.get(i)) {
                    pr = productsList.get(i);
                    productsList.remove(i);

                    Integer index = p.getCode();

                    allowance += productsPriceMap.get(index) + deliveryPrice;
                    WriteToFile.getInstance().WriteToFile(
                            company.getName().replace(" ", "_"), toString());
                    return pr;
                }
            }
        }
        return null;
    }

    // Each depot from one company should try to trade with all depots from the other companies
    public void buyProduct(Products p, Company c) throws IOException {

        WriteToFile.getInstance().AppendToFile(
                (company.getName().replace(" ", "_") + "_transactions"),
                "\nBuying product " + p.getDescription() + " from company " + c.getName() + "\n");


        boolean isNative = Objects.equals(p.getCode(), nativeProduct);

        //A depot cannot store above its maximum stock of its native product
        if (isNative && countNative() < 40) {


            for (int i = 0; i < c.getDepotsArr().length; i++) {

                Products product = c.getDepotsArr()[i].sellProduct(p);
                Integer index = p.getCode();

                if (product != null) {
                    productsList.add(p);
                    allowance -= (c.getDepotsArr()[i].productsPriceMap.get(index) + c.getDepotsArr()[i].getDeliveryPrice());
                    WriteToFile.getInstance().WriteToFile(
                            c.getName().replace(" ", "_"), c.toString());


                    /*
                    Here the transaction info is saved in each of the companies transactions logs and
                    on all transactions logs
                    */
                    WriteToFile.getInstance().AppendToFile(
                            (company.getName().replace(" ", "_") + "_transactions"),
                            product.getDescription() +
                                    " bought from Company "
                                    + c.getName() + " in Depot number: " + (i + 1) + "\n" +
                                    company.getName() + " - " + "Depot " + depotNumber
                                    + " balance: " + allowance + "\n" +
                                    c.getName() + " - " + "Depot " + c.getDepotsArr()[i].getDepotNumber()
                                    + " balance: " + c.getDepotsArr()[i].getAllowance() +
                                    "\n");

                    WriteToFile.getInstance().AppendToFile(
                            ("all_transactions"),
                            product.getDescription() +
                                    " bought from Company "
                                    + c.getName() + " in Depot number: " + (i + 1) + "\n" +
                                    company.getName() + " - " + "Depot " + depotNumber
                                    + " balance: " + allowance + "\n" +
                                    c.getName() + " - " + "Depot " + c.getDepotsArr()[i].getDepotNumber()
                                    + " balance: " + c.getDepotsArr()[i].getAllowance() +
                                    "\n");

                    /*
                    Here we also save the company data updated. We have to overwrite the previous doc as we don't
                    want the application to write same info about the same company over and over again just because
                    a few stock has changed or the allowance balance has changed.
                    */
                    WriteToFile.getInstance().WriteToFile(c.getName()
                            .replace(" ", "_"), this.toString());
                    WriteToFile.getInstance().WriteToFile(company.getName()
                            .replace(" ", "_"), this.toString());
                    break;
                }

            }

//  A depot cannot store above its maximum stock of its purchase products
        } else if (!isNative && countExternal() < 30) {


            for (int i = 0; i < c.getDepotsArr().length; i++) {

                Products product = c.getDepotsArr()[i].sellProduct(p);

                Integer index = p.getCode();


                if (product != null) {
                    productsList.add(p);
                    allowance -= c.getDepotsArr()[i].productsPriceMap.get(index)+ c.getDepotsArr()[i].getDeliveryPrice();
                    WriteToFile.getInstance().WriteToFile(
                            c.getName().replace(" ", "_"), c.toString());

                    /*
                    Here the transaction info is saved in each of the companies transactions logs and
                    on all transactions logs
                    */

                    WriteToFile.getInstance().AppendToFile(
                            (company.getName().replace(" ", "_") + "_transactions"),
                            product.getDescription() +
                                    " bought from Company "
                                    + c.getName() + " in Depot number: " + (i + 1) + "\n" +
                                    company.getName() + " - " + "Depot " + depotNumber
                                    + " balance: " + allowance + "\n" +
                                    c.getName() + " - " + "Depot " + c.getDepotsArr()[i].getDepotNumber()
                                    + " balance: " + c.getDepotsArr()[i].getAllowance() +
                                    "\n");
                    WriteToFile.getInstance().AppendToFile(
                            (("all_transactions")),
                            product.getDescription() +
                                    " bought from Company "
                                    + c.getName() + " in Depot number: " + (i + 1) + "\n" +
                                    company.getName() + " - " + "Depot " + depotNumber
                                    + " balance: " + allowance + "\n" +
                                    c.getName() + " - " + "Depot " + c.getDepotsArr()[i].getDepotNumber()
                                    + " balance: " + c.getDepotsArr()[i].getAllowance() +
                                    "\n");

                    /*
                    Here we also save the company data updated. We have to overwrite the previous doc as we don't
                    want the application to write same info about the same company over and over again just because
                    a few stock has changed or the allowance balance has changed.
                    */

                    WriteToFile.getInstance().WriteToFile(c.getName()
                            .replace(" ", "_"), this.toString());
                    WriteToFile.getInstance().WriteToFile(company.getName()
                            .replace(" ", "_"), this.toString());


                    break;
                }

            }


        } else {
            WriteToFile.getInstance().AppendToFile(
                    (company.getName().replace(" ", "_") + "_transactions"),
                    "Not possible to buy " + p.getDescription() + " as depot is full." + "\n");
        }


    }

    @Override
    public String toString() {
        return "Depot{" +
                "depotNumber='" + depotNumber + '\'' +
                ", allowance=" + allowance +
                ", nativeProduct=" + nativeProduct +
                ", productsPriceMap=" + productsPriceMap +
                ", deliveryPrice=" + deliveryPrice +
                ", productsList=" + productsList +
                '}';
    }
}
