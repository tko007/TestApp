package at.tko.collector.classes;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by tko007 on 2017. 05. 13..
 */
public class Statistic {

    public static final String DATE_PATTERN = "dd/MM/yy";
    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern(DATE_PATTERN);

    /*
    Div = League Division
    Date = Match Date (dd/mm/yy)
    HomeTeam = Home Team
    AwayTeam = Away Team
    FTHG = Full Time Home Team Goals
    FTAG = Full Time Away Team Goals
    FTR = Full Time Result (H=Home Win, D=Draw, A=Away Win)
    HTHG = Half Time Home Team Goals
    HTAG = Half Time Away Team Goals
    HTR = Half Time Result (H=Home Win, D=Draw, A=Away Win)

    ->Match Statistics (where available)
    Attendance = Crowd Attendance
    Referee = Match Referee
    HS = Home Team Shots
    AS = Away Team Shots
    HST = Home Team Shots on Target
    AST = Away Team Shots on Target
    HHW = Home Team Hit Woodwork
    AHW = Away Team Hit Woodwork
    HC = Home Team Corners
    AC = Away Team Corners
    HF = Home Team Fouls Committed
    AF = Away Team Fouls Committed
    HO = Home Team Offsides
    AO = Away Team Offsides
    HY = Home Team Yellow Cards
    AY = Away Team Yellow Cards
    HR = Home Team Red Cards
    AR = Away Team Red Cards
    HBP = Home Team Bookings Points (10 = yellow, 25 = red)
    ABP = Away Team Bookings Points (10 = yellow, 25 = red)
    */

    @CsvBindByPosition(position = 0)
    private String div;
    @CsvBindByPosition(position = 1)
    private String date;
    @CsvBindByPosition(position = 2)
    private String homeTeam;
    @CsvBindByPosition(position = 3)
    private String awayTeam;
    @CsvBindByPosition(position = 4)
    private String FTHG;
    @CsvBindByPosition(position = 5)
    private String FTAG;
    // should be enum
    @CsvBindByPosition(position = 6)
    private String FTR;
    @CsvBindByPosition(position = 7)
    private int HTHG;
    @CsvBindByPosition(position = 8)
    private int HTAG;
    @CsvBindByPosition(position = 9)
    private String HTR;

    public String getDiv() {
        return div;
    }

    public LocalDate getDate() {
        return LocalDate.parse(date, FORMATTER);
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getFTHG() {
        return FTHG;
    }

    public String getFTAG() {
        return FTAG;
    }

    public String getFTR() {
        return FTR;
    }

    public int getHTHG() {
        return HTHG;
    }

    public int getHTAG() {
        return HTAG;
    }

    public String getHTR() {
        return HTR;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "div='" + div + '\'' +
                ", date=" + date +
                ", homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", FTHG='" + FTHG + '\'' +
                ", FTAG='" + FTAG + '\'' +
                ", FTR='" + FTR + '\'' +
                ", HTHG=" + HTHG +
                ", HTAG=" + HTAG +
                ", HTR='" + HTR + '\'' +
                '}';
    }
}

