import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class PlayerTest {

    @Test
    public void should_find_babylone_is_the_nearest(){

        Player.Queen myQueen = new Player.Queen(1,2);

        Player.Site babylone = new Player.Site(1,15,30,2);
        Player.Site carthage = new Player.Site(2,50,200,2);
        Player.Site alexandrie = new Player.Site(3,600,500,2);

        ArrayList<Player.Site> sites = new ArrayList<>();
        sites.add(babylone);
        sites.add(carthage);
        sites.add(alexandrie);

        Player.Site nearestSite = Player.findNearestSite(myQueen.position, sites);

        Assertions.assertThat(nearestSite).isEqualTo(babylone);

    }

    @Test
    public void should_find_alexendrie_is_the_nearest(){

        Player.Queen myQueen = new Player.Queen(1,2);

        Player.Site babylone = new Player.Site(1,15,30,2);
        Player.Site carthage = new Player.Site(2,50,200,2);
        Player.Site alexandrie = new Player.Site(3,1,1,2);

        ArrayList<Player.Site> sites = new ArrayList<>();
        sites.add(babylone);
        sites.add(carthage);
        sites.add(alexandrie);

        Player.Site nearestSite = Player.findNearestSite(myQueen.position, sites);

        Assertions.assertThat(nearestSite).isEqualTo(alexandrie);

    }

}