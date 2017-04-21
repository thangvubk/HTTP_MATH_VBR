package Client;

import Generator.GenerateScript;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by phanhaiphong on 4/2/15.
 */
public class ExcelExporter {
    //do not change this because it's hard coded into the template result.xlsx file
    private static final String PATH = GenerateScript.OUTPUT_DIR + File.separator + "result.xlsx";
    private Workbook wb;

    private Sheet exportSheet;
    private int exportIndex = 0;
    //CY: https://www.youtube.com/watch?v=1VnITD0rU1A How to create dynamic range chart in Excel.
    // For example: all column A, B, C...
    // =OFFSET(Experiment!$A$2,,,COUNT(Experiment!$A:$A),1)
    // =OFFSET(Experiment!$B$2,,,COUNT(Experiment!$B:$B),1)
    // =OFFSET(Experiment!$C$2,,,COUNT(Experiment!$C:$C),1)
    // =OFFSET(Experiment!$D$2,,,COUNT(Experiment!$D:$D),1)
    // =OFFSET(Experiment!$E$2,,,COUNT(Experiment!$E:$E),1)
    // =OFFSET(Experiment!$F$2,,,COUNT(Experiment!$F:$F),1)
    //from the second row of b, offset x 0, offset y 0, height = height of column B, width = 1 (just 1 column)

    //ASSUMPTION FOR TEMPLATE EXCEL FILE!!!! : previous file must be named result.xlsx, sheet named Experiment, first row is named the same as String[] title. Chart is Scatter chart, with dynamic named range for horizontal axis (first column), and 3 column axis: Segment throughput, bitrate, buffer.
    //=result.xlsx!allA, =result.xlsx!allB
    //Mình đã copy result.xlsx vào thư mục Input để dành làm file template mẫu, đừng xóa cái này đi
    public ExcelExporter() {
        //check if previous result file exist (recommended)
        if (new File(PATH).isFile()) {
            try {
                FileInputStream in = new FileInputStream(PATH);
                wb = new XSSFWorkbook(in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //We don't want this, we should always have a template file for proper chart range. This is just a basic template excel file, without chart. For a full-feature template file, see the Assumption above
            wb = new XSSFWorkbook();
        }
    }

    public void initExportSheet() {
        String name = "Experiment"; //CY: this is hard coded
        exportSheet = wb.getSheet(name);
        if (exportSheet != null) {
            //clear all rows
            for (int i = 0; i <= exportSheet.getLastRowNum(); i++) {
                exportSheet.removeRow(exportSheet.getRow(i));
            }
        } else {
            exportSheet = wb.createSheet(name);
        }
    }

    /**
     * @param columnNumber 0 based
     * @return all numeric values
     */
    public ArrayList<Double> readColumn(String sheetName, int columnNumber) {
        Sheet sheet = wb.getSheet(sheetName);

        ArrayList<Double> values = new ArrayList<>(sheet.getLastRowNum() + 1);
        //+1 incase we want full column
        for (Row r : sheet) {
            Cell c = r.getCell(columnNumber);
            if (c != null) {
                if (c.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    values.add(c.getNumericCellValue());
                } else if (c.getCellType() == Cell.CELL_TYPE_FORMULA && c.getCachedFormulaResultType() == Cell.CELL_TYPE_NUMERIC) {
                    values.add(c.getNumericCellValue());
                }
            }
        }
        return values;
    }

    /**
     * Must call after export sheet has been init
     */
    public void writeAndOpen(Set<String> titles) {
//        Drawing drawing = sheet.createDrawingPatriarch();
//        //size of Chart
//        Chart chart = drawing.createChart(drawing.createAnchor(0, 0, 0, 0, 1, 6, 18, 32));
//        chart.getOrCreateLegend().setPosition(LegendPosition.BOTTOM);
//
//        //create axis
//        ChartAxisFactory chartAxisFactory = chart.getChartAxisFactory();
//        ValueAxis bottomAxis = chartAxisFactory.createValueAxis(AxisPosition.BOTTOM);
//        ValueAxis leftAxis = chartAxisFactory.createValueAxis(AxisPosition.LEFT);
//        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
//
//        LineChartData data = chart.getChartDataFactory().createLineChartData();
//        ChartDataSource<Number> xs = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1, sheet.getLastRowNum(), 0, 0));
//        for (int column : columnsToPlot) {
//            ChartDataSource<Number> ys = DataSources.fromNumericCellRange(sheet, new CellRangeAddress(1, sheet.getLastRowNum(), column, column));
//            LineChartSeries series = data.addSeries(xs, ys);
//            series.setTitle(title[column]);
//        }
//        chart.plot(data, bottomAxis, leftAxis);

        //create the title row
        CellStyle titleCellStyle = wb.createCellStyle();
        {
            Font font = wb.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setColor(Font.COLOR_RED);
            font.setBoldweight(Font.BOLDWEIGHT_BOLD);
            titleCellStyle.setFont(font);
        }
        Row titleRow = exportSheet.createRow(0);
        {
            int i = 0;
            for (String aTitle : titles) {
                Cell titleCell = titleRow.createCell(i++);
                titleCell.setCellStyle(titleCellStyle);
                titleCell.setCellValue(aTitle);
            }
        }
        try (FileOutputStream out = new FileOutputStream(PATH)) {
            wb.write(out);
            //use system application to open excel file
            Desktop.getDesktop().open(new File(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Must call after export sheet has been init
     */
    public void updateExportSheet(Collection<Double> values) {
        //+1 because of the title row
        Row row = exportSheet.createRow(++exportIndex);
        int i = 0;
        for (Double number : values) {
            Cell cell = row.createCell(i++);
            cell.setCellValue(number);
        }
    }
}
