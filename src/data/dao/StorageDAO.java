package data.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class StorageDAO {

	public void save(Map<?, ?> map) {
		//Check Dir Path
		String path = "data\\";
		File file = new File(path);
		if(!file.exists())
			file.mkdir();
		
		//Get File Path
		String className = this.getClass().getSimpleName();
		path += className.substring(0, className.length()-3) + ".data";
		
		//Start stream to file(path)
		try {
			FileOutputStream fos = new FileOutputStream(path);
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
		String path = "data\\";
		File file = new File(path);
		if(!file.exists())
			file.mkdir();
		
		//Get File Path
		String className = this.getClass().getSimpleName();
		path += className.substring(0, className.length()-3) + ".data";

		//Start stream to file(path)
		FileInputStream fis = new FileInputStream(path);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Map<?, ?> map = (Map<?, ?>) ois.readObject();
			
		//Close stream
		ois.close();

		return map;
	}
	
}
