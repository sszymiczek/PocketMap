package zadania;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import aplikacja.MyRandom;


public class Exercise3 {
    private MyRandom myRandom = new MyRandom();

    private static long COUNT = 32_000_000;
    private static String PATH = "src/main/resources/inputFile.txt";
    private static long SEED = 2414934980L;
    private Random random = new Random(SEED);

    public static void main(String[] args) {
        Exercise3 exercise3 = new Exercise3();
        exercise3.createFile();
        exercise3.addHeader();
        exercise3.showNumbers();
//        exercise3.showNumbers2();
    }

    public void showNumbers(){                          //uzywa generatora Lehmer32
        for (long i = 0; i < COUNT; i++) {
            int result = myRandom.nextLehmer32();
            if(i%10000 == 0) {
                System.out.println(i);
            }
            addIntLine(Integer.toString(result));
        }
    }

    public void showNumbers2(){                         //uzywa generatora java.util.Random
        for (long i = 0; i < COUNT; i++) {
            int result = random.nextInt();
            if(i%10000 == 0) {
                System.out.println(i);
            }
            addIntLine(Integer.toString(result));
        }
    }

    public void createFile(){
        File myInputFile = new File(PATH);
        try {
            if (myInputFile.createNewFile()) {
                System.out.println("File created: " + myInputFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addHeader(){
        try {
            FileWriter myWriter = new FileWriter(PATH);
            myWriter.write("#==================================================================\n");
            myWriter.write("# generator Lehmer32              seed = 0\n");
            myWriter.write("#==================================================================\n");
            myWriter.write("type: d\n" +
                    "count: " + COUNT + "\n" +
                    "numbit: 32");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void addIntLine(String line){
        try {
            FileWriter myWriter = new FileWriter(PATH, true);
            BufferedWriter bw = new BufferedWriter(myWriter);
            bw.newLine();
            bw.write(line);
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
