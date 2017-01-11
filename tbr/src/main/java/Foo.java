import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Foo {
    public static void main(String[] args) {

        String line = "57 05 03.80N 009 40 53.20E";

        String[] coordinates= parseCoordinates(line);
        System.out.println("Lat: " + coordinates[0] + "N");
        System.out.println("Lon: " + coordinates[1] + "E");
    }

    /*
     * Convert a Location String in the format from VFG Denmark and converts it to an
     * array with two strings - the first for lat, and the second for lon with N and E
     * stripped.
     *
     * Accepts formats like "57 05 03.80N 009 40 53.20E"
     */
    public static String[] parseCoordinates(String line) {
        Pattern pattern = Pattern.compile("(.*?)N (.*)E");
        Matcher matcher = pattern.matcher(line);

        if (matcher.find())
           return new String[] {matcher.group(1), matcher.group(2)};
        else
            return new String[] {"",""};
    }


}
