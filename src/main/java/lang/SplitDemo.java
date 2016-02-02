package lang;

import java.util.StringTokenizer;

/**
 * 
 * @author Kai.Zhao
 * @version 1.0
 * @see
 */
public class SplitDemo {

	public static void main(String[] args) {
		String temp = "a,c,d,e,f";
		String[] s1 = temp.split(",");
		for (String s : s1) {
			System.out.println(s);
		}
		StringTokenizer st = new StringTokenizer(temp, ",");
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	}
}
