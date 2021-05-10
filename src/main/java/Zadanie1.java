import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Zadanie1 {
    private MyRandom myRandom = new MyRandom();
    private static int COUNT = 100;
    private static String PATH = "src/main/resources/inputFile.txt";

    public void pokazLiczby(){
        for (long i = 1; i < 32_000_000; i++) {
            int result = myRandom.nextLehmer32();
            if(i%1000 == 0) {
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
            myWriter.write("# generator Lehmer32              seed = 1\n");
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

    public static void main(String[] args) {
        Zadanie1 zadanie1 = new Zadanie1();
        zadanie1.createFile();
        zadanie1.addHeader();
        zadanie1.pokazLiczby();
    }
}
