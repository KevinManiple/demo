package collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapDemo {

    public static void main(String[] args) {
        ArrayList<String> l1 = new ArrayList<String>();
        l1.add("12");
        Vector<String> v = new Vector<String>();
        v.add("2");
        LinkedList<String> l = new LinkedList<String>();
        l.add("3");
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("", "");
        map.get("");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key);
            System.out.println("key = " + key);
            System.err.println("");
        }
        Hashtable<String, String> table = new Hashtable<String, String>();
        table.put("", "");
        HashSet<String> s = new HashSet<String>();
        s.add("12");
        s.remove("12");
        Iterator<String> it = s.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        ConcurrentHashMap<String, String> c = new ConcurrentHashMap<String, String>();
        c.put("", "");
        String tt = "";
        tt.split(",");
        StringTokenizer st = new StringTokenizer("s,df,sad,f", ",");
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }
    }

}

abstract class Te {
    int i;

    public void dos() {
        System.out.println(i);
    }

    final int ii = 0;

    public void doso() {
        System.out.println(ii);
    }

    public final String HH = "";

    public abstract boolean gets(String tt);

    protected abstract void say();

}

interface T {

}

interface AT extends T {

}

class HH {
    private int id;
    private String name;

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
        HH other = (HH) obj;
        if (id != other.id) return false;
        if (name == null) {
            if (other.name != null) return false;
        } else if (!name.equals(other.name)) return false;
        return true;
    }

}
