import java.io.*;

public class FileManager {

    public static String readFromFileAndBuildTree(String filename, BinaryTree tree){
        String file =  System.getProperty("user.dir") + "/input/" + filename +".txt";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((line = br.readLine()) != null) {
                tree.insertNode(Integer.parseInt(line));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Finished";
    }

}
