package at.tko.collector.classes;

import at.tko.collector.interfaces.Parsable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Representation of a handicap bet from BET-AT-HOME.
 *
 */
public class Bet implements Parsable{

    private static final String LINE_SEPARATOR = "\n";
    public static final String DATE_PATTERN = "dd.MM.yy HH:mm";
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    private String firstRow;
    private String date;
    private float team1Win;
    private float team2Win;
    private float x;

    private BetParsed betParsed;

    public Bet(String data){
        // input format: team1 - team2 date, 1,X,2
        String[] parsedData = data.split(LINE_SEPARATOR);

        // setting teams,handicap and date
        firstRow = parsedData[0];
        date = parsedData[1];

        // setting multiplier
        team1Win = Float.parseFloat(parsedData[2]);
        x = Float.parseFloat(parsedData[3]);
        team2Win = Float.parseFloat(parsedData[4]);
    }

    public String getFirstRow() {
        return firstRow;
    }

    public float getTeam1Win() {
        return team1Win;
    }

    public float getTeam2Win() {
        return team2Win;
    }

    public float getX() {
        return x;
    }

    public BetParsed getBetParsed() { return betParsed; }

    @Override
    public String toString() {
        return "Bet{" +
                "firstRow='" + firstRow + '\'' +
                ", date='" + date + '\'' +
                ", team1Win=" + team1Win +
                
                ", team2Win=" + team2Win +
                ", x=" + x +
                '}';
    }

    @Override
    public void doParse() {
        betParsed = new BetParsed();
    }

    public class BetParsed{
        private static final String PATTERN_TEAM1 = "^(.*?)-";
        private static final String PATTERN_TEAM2 = ".+?(?=\\(0|\\(1|\\(2)";

        private String teamHome;
        private String teamAway;
        private String handiCap;
        private LocalDateTime timeOfPlay;

        private BetParsed() {
            // parse team1
            Matcher m1 = Pattern.compile(PATTERN_TEAM1)
                    .matcher(firstRow);
            teamHome = m1.find() ? m1.group(1).trim() : "";

            // first row without team home
            String firstRowNoTeamHome =
                    firstRow.replaceFirst(m1.group(0), "");

            // parse team2 & handicap
            Matcher m2 = Pattern.compile(PATTERN_TEAM2)
                    .matcher(firstRowNoTeamHome);
            teamAway = m2.find() ? m2.group(0).trim() : "";
            handiCap = firstRowNoTeamHome
                    .replaceFirst(teamAway, "")
                    .replace("(", "")
                    .replace(")", "")
                    .trim();

            timeOfPlay = LocalDateTime.parse(date, FORMATTER);

        }

        public String getTeamHome() {
            return teamHome;
        }

        public String getTeamAway() {
            return teamAway;
        }

        public String getHandiCap() {
            return handiCap;
        }

        public LocalDateTime getTimeOfPlay() {
            return timeOfPlay;
        }

        @Override
        public String toString() {
            return "BetParsed{" +
                    "teamHome='" + teamHome + '\'' +
                    ", teamAway='" + teamAway + '\'' +
                    ", handiCap='" + handiCap + '\'' +
                    ", timeOfPlay=" + timeOfPlay.format(FORMATTER) +
                    '}';
        }
    }
}
