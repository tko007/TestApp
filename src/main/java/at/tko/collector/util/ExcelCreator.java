package at.tko.collector.util;


import at.tko.collector.classes.Bet;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * Uses Apache POI lib to create the excel file.
 * It is only support older excel file creation,
 * which using the .xls extension.
 *
 *
 */
public class ExcelCreator {

    private static final String CONFIG_VALUE = "output/sample/bet.xls";

    private HSSFWorkbook workbook;
    private HSSFSheet sheet;
    private List<Bet> collectedBet;

    private static File createFile() throws IOException {
        File excelFile = new File(CONFIG_VALUE);

        // create file structures
        Files.createDirectories(
                Paths.get(excelFile.getParent()));
        excelFile.createNewFile();

        return excelFile;
    }

    private void mergeOnlyCellsInARow(HSSFRow row, int startCol, int colCount){
        int rowNum = row.getRowNum();

        sheet.addMergedRegion(
                new CellRangeAddress(rowNum, rowNum, startCol, (startCol + colCount) -1 ));
    }

    private void mergeRowsCellWithContent(HSSFRow row, double content, int cellNum) {
        IntStream.range(0, cellNum).forEach((index) -> {
            HSSFCell cell = row.createCell(index);
            if(index == 0){
                cell.setCellValue(content);
            }
        });

        mergeOnlyCellsInARow(row, 0, cellNum);
    }

    private void mergeRowsCellWithContent(HSSFRow row, String content, int cellNum) {
        IntStream.range(0, cellNum).forEach((index) -> {
            HSSFCell cell = row.createCell(index);
            if(index == 0){
                cell.setCellValue(content);
            }
        });

        mergeOnlyCellsInARow(row, 0, cellNum);
    }

    private CellStyle setCellStyleForDate(String dateFormat) {
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(
                createHelper.createDataFormat().getFormat(dateFormat));

        return cellStyle;
    }

    public ExcelCreator(List<Bet> collectedBet) {
        this.collectedBet = collectedBet;
        try (FileOutputStream fileOutputStream =
                     new FileOutputStream(createFile())) {

            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet("BetSheet");

            fillContent();
            autoSizeAll();

            workbook.write(fileOutputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillContent(){
        collectedBet.forEach(el -> {
            createMatch(el);
        });
    }

    private void autoSizeAll() {
        HSSFRow aRow = sheet.getRow(sheet.getFirstRowNum());
        int colNum = aRow.getLastCellNum();

        //do tha work
        IntStream.range(0, colNum).forEach((i) -> {
            System.out.println("autosizing column num " + i);
            sheet.autoSizeColumn(i);
        });
    }

    private void createMatch(Bet aBet) {
        int lastNum = sheet.getLastRowNum();

        // TODO Liga
        HSSFRow ligaRow = sheet.createRow(lastNum++);
        mergeRowsCellWithContent(ligaRow, "liga", 7);

        // Empty row
        mergeRowsCellWithContent(sheet.createRow(lastNum++), "", 7);

        // Actual match row
        HSSFRow actualMatchRow = sheet.createRow(lastNum++);
        String content = "Meccs: " + aBet.getBetParsed().getTeamHome()
                + " : " + aBet.getBetParsed().getTeamAway();
        mergeRowsCellWithContent(actualMatchRow, content, 7);

        // Favorite Team row
        HSSFRow favoriteTeamRow = sheet.createRow(lastNum++);
        mergeRowsCellWithContent(favoriteTeamRow,
                "Cs1: " + aBet.getBetParsed().getFavoriteTeam(), 7);

        // Home team stadion
        HSSFRow placeRow = sheet.createRow(lastNum++);
        mergeRowsCellWithContent(placeRow,
                "Stadion neve", 7);

        // Multiplier
        HSSFRow multiplierRow = sheet.createRow(lastNum++);
        mergeRowsCellWithContent(multiplierRow,
                aBet.getBetParsed().getFavoriteTeamMultiplier(), 7);

        // Info Header
        HSSFRow infoRow = sheet.createRow(lastNum++);
        infoRow.createCell(0).setCellValue("Ellenfel Cs2");

        infoRow.createCell(1).setCellValue("Rangsor");
        infoRow.createCell(2);
        mergeOnlyCellsInARow(infoRow, 1, 2);

        infoRow.createCell(3).setCellValue("Idopont");
        infoRow.createCell(4).setCellValue("Helyszin");
        infoRow.createCell(5).setCellValue("Minosites Cs1");
        infoRow.createCell(6).setCellValue("Minosites Cs2");

        // Content row
        HSSFRow contentRow = sheet.createRow(lastNum++);
        contentRow.createCell(0).setCellValue(aBet.getBetParsed().getOtherTeam());
        contentRow.createCell(1).setCellValue("Rangsor cs1");
        contentRow.createCell(2).setCellValue("Rangsor cs2");
        // setting excel date
        HSSFCell aCell = contentRow.createCell(3);
        aCell.setCellValue(aBet.getBetParsed().getTimeOfPlayDate());
        aCell.setCellStyle(setCellStyleForDate(Bet.DATE_PATTERN));

        contentRow.createCell(4).setCellValue(
                aBet.getBetParsed().getFavoriteTeam().equals(aBet.getBetParsed().getTeamHome()));
        contentRow.createCell(5).setCellValue("Minosites Cs1");
        contentRow.createCell(6).setCellValue("Minosites Cs2");
    }
}
