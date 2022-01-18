package np.qa.lesson7.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import np.qa.lesson7.base.ZipHelper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZipTest extends ZipHelper {


    @Test
    void zipTest() throws Exception {
        ArrayList<String> listNames = getListFileNames();
        for (int i = 0; i < listNames.size(); i++) {
            ZipEntry zipEntry = zipFile.getEntry(listNames.get(i));
            if (listNames.get(i).endsWith(".csv")) {


                try (InputStream CsvStream = zipFile.getInputStream(zipEntry)) {
                    CSVReader reader = new CSVReader(new InputStreamReader(CsvStream));
                    List<String[]> list = reader.readAll();
                    assertThat(list)
                            .hasSize(1)
                            .contains(
                                    new String[]{"1", "Dulce", "Abril", "Female", "United States", "32", "15.10.2017", "1562"}
                            );
                }
            } else if (listNames.get(i).endsWith(".xlsx")) {

                try (InputStream stream = zipFile.getInputStream(zipEntry)) {
                    XLS parsed = new XLS(stream);
                    assertEquals(parsed.excel.getSheetAt(0).getRow(1).getCell(2).getStringCellValue(),
                            "Abril");
                }
            } else if (listNames.get(i).endsWith(".pdf")) {
                try (InputStream stream = zipFile.getInputStream(zipEntry)) {
                    PDF parsed = new PDF(stream);

                    assertEquals(parsed.numberOfPages, 17);
                }
            }
        }
    }


    public ZipTest() throws IOException {
    }
}

