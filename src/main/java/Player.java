import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    private static final int ME = 0;
    private static final int HE = 1;
    static boolean ALWAYS_TRUE = true;

    public static Site findNearestSite(Position from, List<Site> sites) {
        return sites.stream().reduce(
                (s1, s2) -> getDistance(from, s1.position) < getDistance(from, s2.position) ? s1 : s2).get();
    }

    private static double getDistance(Position from, Position site) {
        return Math.sqrt((from.x - site.x) * (from.x - site.x) + (from.y - site.y) * (from.y - site.y));
    }

    /**
     * Model
     */
    static class Site {
        final int siteId;
        final int radius;
        final Position position;

        public Site(int siteId, int x, int y, int radius) {
            this.siteId = siteId;
            this.position = new Position(x, y);
            this.radius = radius;
        }
    }

    /**
     * Main
     */
    public static void main(String args[]) {
        // Context
        List<Site> sites = new ArrayList<>();
        List<Unit> units = new ArrayList<>();
        Ownership ownership = new Ownership();
        Queen myQueen = new Queen(200,200);
        List<Knight> myKnights;
        List<Archer> myArchers;
        Queen hisBitch;
        List<Knight> hisKnights;
        List<Archer> hisArchers;

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
            // Sites
            for (int i = 0; i < numSites; i++) {
                int siteId = in.nextInt();
                int ignore1 = in.nextInt(); // used in future leagues
                int ignore2 = in.nextInt(); // used in future leagues
                int structureType = in.nextInt(); // -1 = No structure, 2 = Barracks
                int owner = in.nextInt(); // -1 = No structure, 0 = Friendly, 1 = Enemy
                int param1 = in.nextInt();
                int param2 = in.nextInt();
            }
            // Units
            int numUnits = in.nextInt();
            units = new ArrayList<>();
            for (int i = 0; i < numUnits; i++) {
                affectUnit(ownership, in);
            }
            myQueen = ownership.mine.stream().filter(unit -> (unit instanceof Queen))
                    .map(u -> (Queen) u).findFirst().get();
            myKnights = ownership.mine.stream().filter(unit -> (unit instanceof Knight))
                    .map(u -> (Knight) u).collect(Collectors.toList());
            myArchers = ownership.mine.stream().filter(unit -> (unit instanceof Archer))
                    .map(u -> (Archer) u).collect(Collectors.toList());
            hisBitch = ownership.his.stream().filter(unit -> (unit instanceof Queen))
                    .map(u -> (Queen) u).findFirst().get();
            hisKnights = ownership.mine.stream().filter(unit -> (unit instanceof Knight))
                    .map(u -> (Knight) u).collect(Collectors.toList());
            hisArchers = ownership.mine.stream().filter(unit -> (unit instanceof Archer))
                    .map(u -> (Archer) u).collect(Collectors.toList());


            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");


            // First line: A valid queen action
            // Second line: A set of training instructions
            if (touchedSite > -1) {
                Site nearestSite = findNearestSite(myQueen.position, sites);
                System.out.println("MOVE " + nearestSite.position.x +" " +nearestSite.position.y );
            } else {
                System.out.println("WAIT");
            }
            System.out.println("TRAIN");
        }
    }

    private static void affectUnit(Ownership ownership, Scanner in) {
        Unit unit;
        //Read
        int x = in.nextInt();
        int y = in.nextInt();
        int owner = in.nextInt();
        int unitType = in.nextInt(); // -1 = QUEEN, 0 = KNIGHT, 1 = ARCHER
        int health = in.nextInt();
        //Factory
        switch (unitType) {
            case -1:
                unit = new Queen(x, y, health);
                break;
            case 0:
                unit = new Knight(x, y, health);
                break;
            case 1:
                unit = new Archer(x, y, health);
                break;
            default:
                unit = null;
        }
        //Affect
        if (owner == ME) {
            ownership.mine.add(unit);
        } else {
            ownership.his.add(unit);
        }
    }

    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Unit {
        protected int owner;
        protected int health;
        protected Position position;

        public Unit(int x, int y, int health) {
            this.health = health;
            this.position = new Position(x, y);
        }

        public Unit() {

        }

        public Unit(int x, int y) {
            this.position= new Position(x, y);
        }
    }

    private static class Archer extends Unit {
        public Archer(int x, int y, int health) {
            super(x, y, health);
        }
    }

    private static class Knight extends Unit {
        public Knight(int x, int y, int health) {
            super(x, y, health);
        }
    }

    public static class Queen extends Unit {
        public Queen(int x, int y, int health) {
            super(x, y, health);
        }

        public Queen() {
            super();
        }

        public Queen(int x, int y) {
            super(x,y);
        }
    }

    private static class Ownership {
        public List<Unit> mine = new ArrayList<>();
        public List<Unit> his = new ArrayList<>();
    }
}
