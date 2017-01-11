package com.example.quickstart;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NavAid {
    private String name;
    private String type;
    private String ident;
    private int    max_range;
    private int    max_alt;
    private double alt;
    private String x,y;


    public String getName() {
        return name;
    }


    public NavAid(String name, String id, String what, String latlong, String limitString) {
        // Convert the Lat and Lng strings to double values and use them to establish a position

        String[] coordinates = parseCoordinates(latlong);
        x=coordinates[0];
        y=coordinates[1];
        this.name = name;
        type=what;
        ident = id;
        int[] limits = parseLimitations(limitString);
        max_alt = limits[0];
        max_range=limits[1];
    }


    public int getMax_range() {
        return max_range;
    }

    public void setMax_range(int max_range) {
        this.max_range = max_range;
    }

    public int getMax_alt() {
        return max_alt;
    }

    public void setMax_alt(int max_alt) {
        this.max_alt = max_alt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdent() {
        return ident;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    @Override
    public String toString() {
        return "NavAid{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", ident='" + ident + '\'' +
                ", max_range=" + max_range +
                ", max_alt=" + max_alt +
                ", alt=" + alt +
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
                '}';
    }

    /*
         * Convert the different variation of limitations from VFG Denmark to
         * an integer array with the FL and the NM as first and second element.
         *
         * Accepts Strings like "FL 500/60 NM", "20NM", "FL 500/200NM"
         */
    private static int[] parseLimitations(String input) {
        int[] result = {0,0};
        String[] parts=input.trim().replace(" ","").split("/");
        for (String s : parts) {
            if (s.indexOf("NM")>=0) {
                result[1]= Integer.parseInt(s.replace("NM",""));
            } else if (s.indexOf("FL")>=0) {
                result[0]=Integer.parseInt(s.replace("FL",""));
            }
        }

        return result;
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



