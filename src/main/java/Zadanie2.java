import java.util.ArrayList;
import java.util.HashMap;

public class Zadanie2 {

    public static void main(String[] args) {
        Zadanie2 zadanie2 = new Zadanie2();
        int m = 3;
        String str = "nie jest fajnie";
        String strBinary = zadanie2.strToBinary(str);
        m = zadanie2.checkBlockSize(m, strBinary);
        zadanie2.pokerTest(strBinary, m);

    }

    public String strToBinary(String s){
        StringBuilder result = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            result.append(
                    String.format("%8s", Integer.toBinaryString(aChar))
                            .replaceAll(" ", "0")
            );
        }
        return result.toString();
    }

    public int checkBlockSize(Integer m, String strBinary){
        while (strBinary.length()%m != 0)
            m++;
        return m;
    }

    public void pokerTest(String s, Integer m){
        int k = s.length()/m;
        HashMap<Long, Integer> freq = getSamplesFrequencyMap(s, m);
        ArrayList<Double> exp = expectedValues(m, k);
        Double chi = chiSquared(freq, exp);
        printResults(freedomLevels(chi, m));
    }

    public double chiSquared(HashMap<Long, Integer> combFreq, ArrayList<Double> expectedValues){
        double chi = 0;
        for (int i = 0; i < combFreq.size(); i++) {
            chi += Math.pow((combFreq.get((long)i) - expectedValues.get(i)), 2)/expectedValues.get(i);
        }
        return chi;
    }

    public HashMap<Long, Integer> getSamplesFrequencyMap(String s, Integer m){
        HashMap<Long, Integer> combFreq = new HashMap<>();
        for (long i = 0; i <= m; i++) {
            combFreq.put(i, 0);
        }
        for (int i = 0; i < s.length() - m; i += m) {
            String str = s.substring(i, i + m);
            combFreq = occurrences(str, combFreq);
        }
        return combFreq;
    }

    public HashMap<Long, Integer> occurrences(String str, HashMap<Long, Integer> combFreq){
        long count = str.chars().filter(ch -> ch == '1').count();
        int add = combFreq.get(count);
        combFreq.replace(count, add + 1);

        return combFreq;
    }

    public ArrayList<Double> expectedValues(Integer m, Integer k){
        ArrayList<Double> expectedValues = new ArrayList<>();
        expectedValues.add(1/Math.pow(2, m) * k);
        expectedValues.add(m/Math.pow(2, m) * k);
        for (int i = 2; i <= m-1; i++) {
            int exp = combinations(m, i);
            expectedValues.add(exp / Math.pow(2, m) * k);
        }
        expectedValues.add(1/Math.pow(2, m) * k);
        return expectedValues;
    }

    public int combinations(int N, int R) {
        int n; int r; int nr; int NR = N-R; int result;
        n = factorialGenerator(N);
        r = factorialGenerator(R);
        nr = factorialGenerator(NR);
        result = n / (r*nr);

        return result;
    }

    public int factorialGenerator(int givenNumberToReturnFactorial){
        if (givenNumberToReturnFactorial == 0)
            return 1;
        else
            return givenNumberToReturnFactorial * factorialGenerator(givenNumberToReturnFactorial - 1);
    }

    public Boolean freedomLevels(Double pokerResult, Integer m){
        double[] levels = {3.84,5.99,7.81,9.48,11.07,12.59,14.06};
        return levels[m-1] > pokerResult;
    }

    public void printResults(Boolean test){
        if (test)
            System.out.println("Gratulacje, test został zdany");
        else
            System.out.println("Niestety, test został niezdany");
    }
}
