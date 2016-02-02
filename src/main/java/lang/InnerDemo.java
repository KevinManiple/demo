package lang;

import java.util.ArrayList;

/**
 * 
 * @author Kai.Zhao
 * @version 1.0
 * @see
 */
public class InnerDemo {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add(i);
		}
		//ConcurrentModificationException
		for (Integer temp : list) {
			System.out.println(temp);
			list.remove(temp);
		}
	}

}
