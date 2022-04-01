package Entities;

import filesmanagement.WriteToFile;

import java.io.IOException;
import java.util.Random;

public class BigAlpha extends Company{

    public BigAlpha(String name, String address, Integer nativeProduct) throws IOException {
        super(name, address, nativeProduct);

        for(int i = 0; i < getDepotsArr().length; i++){
//            Companies and depots most be created in memory with random values of allowance
            Integer allowance = new Random().nextInt(100 - 50 + 1) + 50;
            Depot depot = new Depot((""+(i+1)), allowance, nativeProduct);
            depot.setCompany(this);
            WriteToFile.getInstance().AppendToFile(name.replace(" ", "_"),
                    depot.toString());
            getDepotsArr()[i] = depot;
        }

        WriteToFile.getInstance().WriteToFile(name.replace(" ", "_"), this.toString());
        WriteToFile.getInstance().WriteToFile(name.replace(" ", "_")+"_transactions"
                , "");
    }
}
