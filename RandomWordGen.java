import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;
import java.lang.Math;

public class RandomWordGen {
    public static void main(String[] args) throws IOException {
        int random = (int)(Math.random()*10);
        String delimiter = Integer.toString(random);
        Map<String, String> map = new HashMap<>();

        try(Stream<String> lines = Files.lines(Paths.get("/Users/Zeus/Documents/JavaBookClub/Dictionary.txt"))){
            lines.filter(line -> line.contains(delimiter)).forEach(
                    line -> map.put("",line.split(delimiter)[1])
            );
        }
        System.out.println(map);
    }
}