import Entities.BigBeta;
import Entities.BigCappa;
import Entities.Company;
import Entities.enums.Products;

import java.io.IOException;

public class CompanyDriver {

    public static Company bigCappa;
    public static Company bigBeta;
    public static Company bigAlpha;

    public void driver() throws IOException {

        bigCappa = new BigCappa("Big Cappa",
                "Big Cappa Address", Products.CRATES.getCode());
        bigBeta = new BigBeta("Big Beta",
                "Big Beta Address", Products.BRACES.getCode());
        bigAlpha = new BigBeta("Big Alpha",
                "Big Alpha Address", Products.WIDGETS.getCode());


        bigAlpha.getDepotsArr()[0].buyProduct(Products.WIDGETS, bigCappa);
        bigAlpha.getDepotsArr()[2].buyProduct(Products.BRACES, bigCappa);
        bigAlpha.getDepotsArr()[2].buyProduct(Products.CRATES, bigCappa);

    }
}
