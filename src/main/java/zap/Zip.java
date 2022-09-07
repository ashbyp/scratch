package zap;

import java.util.ArrayList;
import java.util.List;

class Pair {
    private int x;
    private int y;
    
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public String toString() {
        StringBuffer b = new StringBuffer();
        b.append("(");
        b.append(this.x);
        b.append(", ");
        b.append(this.y);
        b.append(")");
        return b.toString();
    }
        
}

public class Zip {
    
    public static List<Pair> zip(int[] x, int[] y) {
        if (x.length != y.length) {
            throw new RuntimeException("oops");
        }
        List<Pair> zipped = new ArrayList<>();
        for (int i = 0; i < x.length; i++) {
            Pair p = new Pair(x[i], y[i]);
            zipped.add(p);
        }
        return zipped;
    }
    
    public static void main(String[] args) {
        int[] x = {1, 2, 3};
        int[] y = {4, 5, 6};
        System.out.println(zip(x, y));
    }

}
