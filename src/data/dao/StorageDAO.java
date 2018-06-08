package data.dao;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;


public class StorageDAO {
    String path = System.getProperty("user.home");

    public void save(Map<?, ?> map) {
        //Check Dir Path
        String newpath = path + "/.weightData/";

        File file = new File(newpath);
        if(!file.exists())
            file.mkdir();

        //Get File Path
        String className = this.getClass().getSimpleName();
        newpath += className.substring(0, className.length()-3) + ".data";

        //Start stream to file(path)
        try {
            FileOutputStream fos = new FileOutputStream(newpath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Map<?, ?> load() throws ClassNotFoundException, IOException {
        //Check Dir Path
        String newpath = path + "/.weightData/";

        File file = new File(newpath);
        if(!file.exists())
            file.mkdir();

        //Get File Path
        String className = this.getClass().getSimpleName();
        newpath += className.substring(0, className.length()-3) + ".data";

        //Start stream to file(path)
        FileInputStream fis = new FileInputStream(newpath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Map<?, ?> map = (Map<?, ?>) ois.readObject();

        //Close stream
        ois.close();

        return map;
    }

}
