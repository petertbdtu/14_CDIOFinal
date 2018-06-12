package data.dao;

import java.io.*;
import java.util.Map;


public class StorageDAO {
    String path = System.getProperty("user.home");

    protected synchronized void save(Map<?, ?> map) {
        String newpath = getFilePath();

        File tempFile = new File(newpath);
        if(tempFile.exists()) { while (!tempFile.canWrite()){} }

        //Start stream to file(path)
        try {
            FileOutputStream fos = new FileOutputStream(tempFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(map);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected synchronized Map<?, ?> load() throws ClassNotFoundException, IOException {
        String newpath = getFilePath();

        File tempFile = new File(newpath);
        if(tempFile.exists()) { while (!tempFile.canRead()){} }

        //Start stream to file(path)
        FileInputStream fis = new FileInputStream(tempFile);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Map<?, ?> map = (Map<?, ?>) ois.readObject();

        //Close stream
        ois.close();

        return map;
    }

    public String getPath(String simpleName) {
		String filePath = path + "/.weightData/";
		filePath += simpleName.substring(0, simpleName.length()-3) + ".data";
		return filePath;
    }

    private String getFilePath(){
        //Check Dir Path
        String newpath = path + "/.weightData/";

        File file = new File(newpath);
        if(!file.exists())
            file.mkdir();

        //Get File Path
        String className = this.getClass().getSimpleName();
        newpath += className.substring(0, className.length()-3) + ".data";
        return newpath;
    }

	public boolean deleteFile(String simpleName) {
		String filePath = path + "/.weightData/";
		filePath += simpleName.substring(0, simpleName.length()-3) + ".data";
		File file = new File(filePath);
		return file.delete();
	}
}
