import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void writeFile(AppData appData){
        try(PrintWriter out = new PrintWriter("file.csv")){
            for (String v: appData.getHeader()){
                out.print(v + ";");
            }
            out.println();

            int [][] valListData = appData.getData();
            for (int[] valListDatum : valListData) {
                for (int i : valListDatum) {
                    out.print(i + ";");
                }
                out.write("\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void readFile() throws IOException{
        BufferedReader in = new BufferedReader(new FileReader("file.csv"));
        List<int[]> data = new ArrayList<>();
        String[] dataHead = in.readLine().split(";");
        String line;
        while ((line= in.readLine()) != null){
            List <Integer> list = new ArrayList<>();
            String [] string = line.split(";");
            for (String s : string) {
                list.add(Integer.parseInt(s));
            }
            data.add(list.stream().mapToInt(i -> i).toArray());
        }
        AppData appData = new AppData(dataHead, data.toArray(new int[0][]));
        appData.getData();
    }

    public static void main(String[] args) throws IOException {
        String[] header = {"Value1", "Value2", "Value3"};
        int [][] data = {{100, 200, 300}, { 400,500,600}};
        AppData appData = new AppData(header, data);
        writeFile(appData);
        readFile();
    }
}