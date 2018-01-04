public class Synch extends Thread {
    private int c1, c2, c3;
    final static int ITER = 10000000;

    public void m1() {
        for(int i = 0; i < Synch.ITER; i++) {
            c1 ++;
            // synchronized(this) {
            //     c1 ++;
            // }
            c2 ++;
        }
    }

    public void m2() {
        for(int i = 0; i < Synch.ITER; i++) {
            c1 ++;
            // synchronized(this) {
            //     c1 ++;
            // }
            c3 ++;
        }
    }

    public void run() {
        m1();
    }

    public String toString() {
        return this.c2 + " + " + this.c3 + " = " + (this.c2 + this.c3) + " = " + this.c1;
    }

    public static void main(String argv[]) throws Exception {
        Synch s = new Synch();

        s.start();
        s.m2();
        s.join();

        System.out.println(s);
    }
}
