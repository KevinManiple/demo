package lang;

import org.apache.commons.lang3.time.DateUtils;

/**
 * 
 * @author Kai.Zhao
 * @version 1.0
 * @see
 */
public class Test extends DateUtils {

	public static void main(String[] args) {
		new Test().saySonName();
		new Test().sayFatherName();
	}

	public void saySonName() {
		System.out.println(super.getClass().getName());
	}

	public void sayFatherName() {
		System.out.println(getClass().getSuperclass().getName());
	}

}
