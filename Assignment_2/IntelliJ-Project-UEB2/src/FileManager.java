import java.io.*;

public class FileManager {
    public static void fileToTree(String file, BinarySearchTree bst){
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/input/" + file +".txt"))) {
            while ((line = br.readLine()) != null) {
                bst.insert(Integer.parseInt(line), bst.getRoot());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
