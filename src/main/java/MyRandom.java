import java.util.Random;

public class MyRandom {
    private final Random random = new Random();

    public int nextLehmer32(int seed){
        seed += 0xe120fc15;
        long tmp = (long)seed * 0x4a39b70d;
        int m1 = (int)((tmp >> 32) ^ tmp);
        tmp = (long)m1 * 0x12fad5c9;
        int m2 = (int)((tmp >> 32) ^ tmp);
        return Math.abs(m2);
    }

    public int nextJavaRandom(int seed){
        random.setSeed(seed);
        return random.nextInt();
    }

    public int nextParkMiller(long seed){
        long a = 16807;
        long m = ((long)2 << 31)-1;
        seed = (a * seed) % m;

        return (int) Math.abs(seed);
    }

}
