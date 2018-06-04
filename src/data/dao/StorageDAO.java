package data.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class StorageDAO {

	public void save(List<?> list) throws IOException {
		//Get Path
		String path = "data/";
		String className = this.getClass().toString();
		path += className.substring(0, className.length()-4) + ".data";
		
		//Start stream to file(path)
		FileOutputStream fos = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(list);
		
		//Close stream
		oos.close();
	}
	
	public List<?> load() throws IOException, ClassNotFoundException {
		//Get Path
		String path = "data/";
		String className = this.getClass().toString();
		path += className.substring(0, className.length()-4) + ".data";
		
		//Start stream to file(path)
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		List<?> list = (List<?>) ois.readObject();
		
		//Close stream
		ois.close();
		
		return list;
	}
	
}
