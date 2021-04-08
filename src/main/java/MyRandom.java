public class MyRandom {
    private int nLehmer = 0;

    public int Lehmer32(){
        nLehmer += getDecimal("0xe120fc15");
        long tmp = (long)nLehmer * getDecimal("0x4a39b70d");
        int m1 = (int)((tmp >> 32) ^ tmp);
        tmp = (long)m1 * getDecimal("0x12fad5c9");
        int m2 = (int)((tmp >> 32) ^ tmp);
        return m2;
    }

    public void setNLehmer(int nLehmer){
        this.nLehmer = nLehmer;
    }

    public static int getDecimal(String hex){
        String digits = "0123456789ABCDEF";
        hex = hex.toUpperCase();
        int val = 0;
        for (int i = 0; i < hex.length(); i++)
        {
            char c = hex.charAt(i);
            int d = digits.indexOf(c);
            val = 16*val + d;
        }
        return val;
    }
}
