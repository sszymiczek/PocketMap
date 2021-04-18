public class MyRandom {
    private int nLehmer = 0;

    public int nextLehmer32(){
        nLehmer += 0xe120fc15;
        long tmp = (long)nLehmer * 0x4a39b70d;
        int m1 = (int)((tmp >> 32) ^ tmp);
        tmp = (long)m1 * 0x12fad5c9;
        int m2 = (int)((tmp >> 32) ^ tmp);
        return Math.abs(m2);
    }

    public int nextLehmer32(int bound){
        return nextLehmer32()%bound;
    }

    public void setNLehmerSeed(int nLehmer){
        this.nLehmer = nLehmer;
    }
}
