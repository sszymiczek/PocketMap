package aplikacja;

import java.util.Random;

public class MyRandom {
    private final Random random = new Random();
    int nLehmer32Seed;
    long nLehmer64Seed;

    public int nextLehmer32(int seed){
        setNLehmer32Seed(seed);
        return nextLehmer32();
    }

    public int nextLehmer32(){
        nLehmer32Seed += 0xe120fc15;
        long tmp = (long) nLehmer32Seed * 0x4a39b70d;
        int m1 = (int)((tmp >> 32) ^ tmp);
        tmp = (long)m1 * 0x12fad5c9;
        int m2 = (int)((tmp >> 32) ^ tmp);
        return Math.abs(m2);
    }

    public void setNLehmer32Seed(int seed){
        nLehmer32Seed = seed;
    }

    public long nextLehmer64(long seed){
        setNLehmer64Seed(seed);
        return nextLehmer64();
    }
    public long nextLehmer64(){
        nLehmer64Seed += 0xe120fc15;
        long tmp = nLehmer64Seed * 0x4a39b70d;
        long m1 = (tmp >> 32) ^ tmp;
        tmp = m1 * 0x12fad5c9;
        long m2 = (tmp >> 32) ^ tmp;
        return Math.abs(m2);
    }

    public void setNLehmer64Seed(long nLehmer64Seed) {
        this.nLehmer64Seed = nLehmer64Seed;
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
