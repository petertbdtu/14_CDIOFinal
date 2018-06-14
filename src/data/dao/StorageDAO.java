package data.dao;

import java.io.*;
import java.util.Map;


public class StorageDAO {
    String path = System.getProperty("user.home");

    /*
     * Saves the input map to a file named
     * after this.class.simplename
     */
    protected synchronized void save(Map<?, ?> map) {
        String newpath = getFilePath();
        File tempFile = new File(newpath);
        if(tempFile.exists())
        	while (!tempFile.canWrite()); 

        //TODO throw instead of catch
        try {
            FileOutputStream fos = new FileOutputStream(tempFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Loades a map from the file layer
     * Path to file is given from the class simplename
     */
    protected synchronized Map<?, ?> load() throws ClassNotFoundException, IOException {
        String newpath = getFilePath();
        File tempFile = new File(newpath);
        if(tempFile.exists()) 
        	while (!tempFile.canRead());
        
        FileInputStream fis = new FileInputStream(tempFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Map<?, ?> map = (Map<?, ?>) ois.readObject();
        ois.close();

        return map;
    }

    /*
     * Returns a string containing a filepath
     * Also ensures that the directory exists
     */
    private String getFilePath(){
        String newpath = path + "/.weightData/";
        File file = new File(newpath);
        if(!file.exists())
            file.mkdir();

        String className = this.getClass().getSimpleName();
        newpath += className.substring(0, className.length()-3) + ".data";
        return newpath;
    }
    
    /*
     * Makes it possible to delete the file
     * ONLY USED FOR UNIT TEST!
     * SINCE WE NEVER DELETE DATA
     */
	public boolean deleteFile(String simpleName) {
		String filePath = path + "/.weightData/";
		filePath += simpleName.substring(0, simpleName.length()-3) + ".data";
		File file = new File(filePath);
		return file.delete();
	}
}
