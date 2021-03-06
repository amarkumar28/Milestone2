package Singelton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Consumer;

//Step 10: enum //best way to implement singleton design pattern
enum MySigleton{
	INSTANCE;
}

class Singleton implements Serializable, Cloneable{
	//Step 1 : Eager initialisation
	//Step 2: Lazy initilasation
	private static volatile Singleton singleton = null;

	
	
	// Step 3: Concurency issue : making method synchronized
	// Step 4: Making critical block synchronized
	// Step 5: double checking of singleton==null  
	
	public static Singleton getSingleton() {
		if (singleton == null) {
			synchronized (Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}

		return singleton;
	}
	// Step 6 : use volatile (becuase it is breakable then 2 threads are trying to run on different cores)
	//Step 7: if u have defined the readResolve method then java will not for for de-ser
	
	private Object readResolve() {
		return singleton;
	}
	
	// Step 8: Cloning issue we can simply return singleton from clone
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return singleton;
	}
	//Step 9: Throw exception while reflection try to create object at runtime  
	private Singleton() {
		if(singleton!=null) {
			throw new IllegalStateException();
		}
	}

}

public class DemoSingletonPattern {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, CloneNotSupportedException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		
		MySigleton mySigleton=MySigleton.INSTANCE;
		MySigleton mySigleton2=MySigleton.INSTANCE;
		
		System.out.println(mySigleton.hashCode());
		System.out.println(mySigleton2.hashCode());	
		
//		Class class1=Class.forName("Singleton");
//		Constructor[]constructors=class1.getDeclaredConstructors();
//		constructors[0].setAccessible(true);
//		Singleton singleton2 = (Singleton) constructors[0].newInstance();
		
		// Serialization- deserialisation
//		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("demo.txt"));
//		oos.writeObject(singleton1);
		
//		
//		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("demo.txt"));
//		Singleton singleton2=(Singleton) ois.readObject();

	}
}