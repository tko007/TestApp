package at.tko.collector.interfaces;

import at.tko.collector.classes.Statistic;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import java.io.Reader;
import java.util.List;

/**
 * Created by tko007 on 2017. 05. 14..
 */
public interface CsvParser {

    default List parseFootballStatistics(Reader customReader) {
        CSVReader reader = new CSVReader(customReader);
        CsvToBean csv = new CsvToBean();

        ColumnPositionMappingStrategy strat =
                new ColumnPositionMappingStrategy();
        strat.setType(Statistic.class);

        return csv.parse(strat, reader);
    }
}
