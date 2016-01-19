package lang;

import java.util.HashMap;
import java.util.Map;

public class HashCodeTest {

	public static void main(String[] args) {
		Map<MyBook, String> books = new HashMap<MyBook, String>();
		MyBook b1 = new MyBook();
		b1.setId(12);
		b1.setName("Hello");
		books.put(b1, "ok");
		MyBook b2 = new MyBook();
		b2.setId(12);
		b2.setName("Hello");
		MyBook b3 = new MyBook();
		b3.setId(15);
		b3.setName("World");
		System.out.println(books.get(b2));
		System.out.println(books.get(b3));
		System.out.println(b2.hashCode() == b3.hashCode());
	}
}

class MyBook {
	private int		id;
	private String	name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		MyBook other = (MyBook) obj;
		if (id != other.id) return false;
		if (name == null) {
			if (other.name != null) return false;
		} else if (!name.equals(other.name)) return false;
		return true;
	}

	// lazy initialied, cached hashcode
//	private volatile int	hashcode;

	/*@Override
	public int hashCode() {
		int result = hashcode;
		if (result == 0) {
			result = 17;
			result = result * 31 + id;
			result = result * 31 + name.hashCode();
		}
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof MyBook)) {
			return false;
		}
		MyBook book = (MyBook) obj;
		return book.getId() == id && book.getName().equals(name);
	}*/

}
