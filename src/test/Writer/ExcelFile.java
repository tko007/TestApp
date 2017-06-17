package Writer;

import at.tko.collector.classes.Bet;
import at.tko.collector.util.ExcelCreator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Created by tko007 on 2017. 05. 13..
 */
@RunWith(JUnit4.class)
public class ExcelFile {

    Bet testBet;

    @Before
    public void setupBet() {
        testBet = new Bet(
                "1. FC Köln - Werder Bremen (1:0)\n" +
                "30.05.17 20:00\n" +
                "1.399999976\n" +
                "5.349999905\n" +
                "4.619999886\n");
    }

    /**
     * Testing a bet parsing working
     */
    @Test
    public void testParsingBet() {
        String expectedTeamHome = "1. FC Köln";
        String expectedTeamAway = "Werder Bremen";
        String expectedHandiCap = "1:0";
        float expectedTeamHomeWin = 1.399999976f;
        float expectedTeamAwayWin = 4.619999886f;
        float expectedEqual = 5.349999905f;
        LocalDateTime expectedDate =
                LocalDateTime.of(2017, 5, 30, 20, 0);

        testBet.doParse();

        Assert.assertEquals(expectedTeamHome,
                testBet.getBetParsed().getTeamHome());
        Assert.assertEquals(expectedTeamAway,
                testBet.getBetParsed().getTeamAway());
        Assert.assertEquals(expectedHandiCap,
                testBet.getBetParsed().getHandiCap());
        Assert.assertEquals(expectedDate,
                testBet.getBetParsed().getTimeOfPlay());
        Assert.assertTrue(expectedTeamHomeWin == testBet.getTeam1Win());
        Assert.assertTrue(expectedTeamAwayWin == testBet.getTeam2Win());
        Assert.assertTrue(expectedEqual == testBet.getX());

    }

    @Test
    public void testExcelFileWriter() throws IOException {
        ExcelCreator.writeBetsToFile(Arrays.asList(testBet));
    }
}
