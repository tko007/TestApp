package at.tko.collector.util;


import at.tko.collector.classes.Bet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Uses Apache POI lib to create the excel file.
 * It is only support older excel file creation,
 * which using the .xls extension.
 *
 *
 */
public class ExcelCreator {

    private static final String CONFIG_VALUE = "output/sample/bet.xls";

    public static void writeBetsToFile(List<Bet> collectedBet) throws IOException {
        File excelFile = new File(CONFIG_VALUE);

        // create file structures
        Files.createDirectories(
                Paths.get(excelFile.getParent()));
        excelFile.createNewFile();

        try (FileOutputStream fileOutputStream =
                     new FileOutputStream(excelFile)) {

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("BetSheet");

            createHeader(sheet);
            fillContent(sheet, collectedBet);

            workbook.write(fileOutputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createHeader(HSSFSheet sheet){
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("FirstRow");
        header.createCell(1).setCellValue("1");
        header.createCell(2).setCellValue("2");
        header.createCell(3).setCellValue("x");
    }

    private static void fillContent(HSSFSheet sheet, List<Bet> collectedBet){
        collectedBet.forEach(el -> {
            HSSFRow row = sheet.createRow(
                    collectedBet.indexOf(el) + 1);

            row.createCell(0).setCellValue(el.getFirstRow());
            row.createCell(1).setCellValue(el.getTeam1Win());
            row.createCell(2).setCellValue(el.getTeam2Win());
            row.createCell(3).setCellValue(el.getX());
        });
    }
}
