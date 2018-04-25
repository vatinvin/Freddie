import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sun.javafx.geom.Ellipse2D;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    static boolean ALWAYS_TRUE = true;
    public static Position position;


    public static Site findNearestSite(Position player, ArrayList<Site> sites) {
        return sites.stream().reduce((s1, s2) -> getDistance(player, s1) < getDistance(player, s2) ? s1 : s2).get();
    }

    private static double getDistance(Position player, Site site) {
        return Math.sqrt((player.x - site.x) * (player.x - site.x) + (player.y - site.y) * (player.y - site.y));
    }

    /**
     * Model
     */
    static class Site {
        final int siteId;
        final int x;
        final int y;
        final int radius;

        public Site(int siteId, int x, int y, int radius) {
            this.siteId = siteId;
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
    }

    /**
     * Main
     */
    public static void main(String args[]) {
        // Context
        List<Site> sites = new ArrayList<>();

        // Init
        Scanner in = new Scanner(System.in);
        int numSites = in.nextInt();
        for (int i = 0; i < numSites; i++) {
            int siteId = in.nextInt();
            int x = in.nextInt();
            int y = in.nextInt();
            int radius = in.nextInt();
            Site site = new Site(siteId, x, y, radius);
            sites.add(site);
        }

        // game loop
        while (ALWAYS_TRUE) {
            int gold = in.nextInt();
            int touchedSite = in.nextInt(); // -1 if none
            for (int i = 0; i < numSites; i++) {
                int siteId = in.nextInt();
                int ignore1 = in.nextInt(); // used in future leagues
                int ignore2 = in.nextInt(); // used in future leagues
                int structureType = in.nextInt(); // -1 = No structure, 2 = Barracks
                int owner = in.nextInt(); // -1 = No structure, 0 = Friendly, 1 = Enemy
                int param1 = in.nextInt();
                int param2 = in.nextInt();
            }
            int numUnits = in.nextInt();
            for (int i = 0; i < numUnits; i++) {
                int x = in.nextInt();
                int y = in.nextInt();
                int owner = in.nextInt();
                int unitType = in.nextInt(); // -1 = QUEEN, 0 = KNIGHT, 1 = ARCHER
                int health = in.nextInt();
            }

            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // First line: A valid queen action
            // Second line: A set of training instructions
            if (touchedSite > -1) {
                System.out.println("MOVE 1920 300");
            } else {
                System.out.println("WAIT");
            }
            System.out.println("TRAIN");
        }
    }

    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {

        }
    }
}