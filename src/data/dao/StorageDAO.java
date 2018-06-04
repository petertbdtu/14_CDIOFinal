package data.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class StorageDAO {
	private List<?> list;
	
	public void save() throws IOException {
		String className = this.getClass().toString();
		className = className.substring(0, className.length()-4);
		FileOutputStream fos = new FileOutputStream("data/" + className);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(list);
		oos.close();
	}
	
	public void load() throws IOException, ClassNotFoundException {
		String className = this.getClass().toString();
		className = className.substring(0, className.length()-4);
		FileInputStream fis = new FileInputStream("data/" + className);
		ObjectInputStream ois = new ObjectInputStream(fis);
		list = (List<?>) ois.readObject();
		ois.close();
	}
	
}
