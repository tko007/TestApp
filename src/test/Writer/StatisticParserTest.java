package Writer;

import at.tko.collector.classes.Statistic;
import at.tko.collector.interfaces.CsvParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

/**
 * Sample is from http://www.football-data.co.uk/notes.txt
 */
@RunWith(JUnit4.class)
public class StatisticParserTest {

    String statistic;
    CsvParser stringParser;

    @Before
    public void setupStatistic(){
        statistic =
                "E0,13/08/16,Burnley,Swansea,0,1,A,0,0,D,J Moss,10,17,3,9,10,14,7,4,3,2,0,0,2.4,3.3,3.25,2.45,3.1,2.95,2.5,3.3,2.65,2.45,3.25,3.1,2.47,3.32,3.19,2.5,3.2,2.9,2.5,3.2,3.25,55,2.55,2.43,3.35,3.21,3.3,3.1,40,2.4,2.3,1.68,1.61,32,-0.25,2.13,2.06,1.86,1.81,2.79,3.16,2.89";

        // for test purpose only used a string reader
        // cause we want to test a file reader mechanism which already work
        stringParser = new CsvParser() {};
    }

    @Test
    public void testParsingStatics() {
        List<Statistic> res =
                stringParser.parseFootballStatistics(new StringReader(statistic));

        Assert.assertNotNull(res);
        Assert.assertTrue(res.size() == 1);

        res.stream().forEach(element -> {
            System.out.println(element);
            Assert.assertEquals("E0", element.getDiv());
            Assert.assertEquals("13/08/16", element.getDate().format(Statistic.FORMATTER));
            Assert.assertEquals("Burnley", element.getHomeTeam());
            Assert.assertEquals("Swansea", element.getAwayTeam());
            Assert.assertEquals("0", element.getFTHG());
            Assert.assertEquals("1", element.getFTAG());
            Assert.assertEquals("A", element.getFTR());
            Assert.assertEquals(0, element.getHTHG());
            Assert.assertEquals(0, element.getHTAG());
            Assert.assertEquals("D", element.getHTR());
        });

    }
}
