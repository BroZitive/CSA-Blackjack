import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

//This class is mainly responsible for the window statuses (open/closed) and other miscellaneous related to saving and loading data.
public class TransferClass
{
    public static boolean leaderboardStatus = false;
    public static boolean saveStatus = false;
    public static boolean loadStatus = false;
    public static boolean devModeStatus = false;
    public static boolean cheatsEnabled = false;

    public static List<String> savesList = Collections.emptyList();
    public static String currentSave;

    //When called this method populates the saveList with the saves from the Save.txt file.
    public static void generateSavesList()
    {
        try
        {
            savesList = Files.readAllLines(Paths.get("Saves.txt"), StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
        }
    }

    //When called this method saves the saveList as a Saves.txt file.
    public static void saveAsTextFile()
    {
        Path output = Paths.get("Saves.txt");
        try
        {
            Files.write(output, savesList);
        }
        catch (Exception e)
        {
        }
    }
}