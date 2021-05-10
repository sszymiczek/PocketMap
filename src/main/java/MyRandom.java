import java.util.Random;

public class MyRandom {
    private final Random random = new Random();
    int nLehmerSeed;

    public int nextLehmer32(int seed){
        setNLehmerSeed(seed);
        return nextLehmer32();
    }
    public int nextLehmer32(){
        nLehmerSeed += 0xe120fc15;
        long tmp = (long)nLehmerSeed * 0x4a39b70d;
        int m1 = (int)((tmp >> 32) ^ tmp);
        tmp = (long)m1 * 0x12fad5c9;
        int m2 = (int)((tmp >> 32) ^ tmp);
        return Math.abs(m2);
    }

    public void setNLehmerSeed(int seed){
        nLehmerSeed = seed;
    }

    public int nextJavaRandom(int seed){
        random.setSeed(seed);
        return random.nextInt();
    }

    public int nextParkMiller(int seed){
        long a = 16807;
        long m = ((long)2 << 31)-1;
        seed = (int) ((a * seed) % m);

        return (int) Math.abs(seed);
    }

    public int nextLCG(int seed){
        long a = 630360016;
        long x = seed;
        long  m = (1 << 31) - 1;
        long c = 0;

        x = (multi(a, x, m) + c) % m;
        return Math.abs((int)x);
    }

    private static long multi(long a, long x, long m){
        long r = 0;
        long n = 1;
        long b = 1;
        while (n <= 64) {
            if ((a & b) != 0){
                r = (r + x) % m;
            }
            x = (x + x) % m;
            b *= 2;
            n++;
        }
        return r;
    }

}
