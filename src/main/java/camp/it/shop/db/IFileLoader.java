package camp.it.shop.db;

import java.io.IOException;

public interface IFileLoader {

    void readDataFromFile() throws IOException;
    void saveDataToFile() throws IOException;

}
