package com.example.kurs.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdfReportService {

    public void doReport(String reportPath, String jasperFileName, Object data) {
        doReport(reportPath, jasperFileName, List.of(data));
    }

    public void doReport(String reportPath, String jasperFileName, List<?> data) {
        try {
            var jasperPrint = JasperFillManager.fillReport(
                    JasperCompileManager.compileReport(getClass().getResourceAsStream(jasperFileName)),
                    null,
                    new JRBeanCollectionDataSource(data)
            );
            var exporter = new JRPdfExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(reportPath));

            var reportConfig = new SimplePdfReportConfiguration();

            reportConfig.setSizePageToContent(true);
            reportConfig.setForceLineBreakPolicy(false);

            var exportConfig = new SimplePdfExporterConfiguration();

            exportConfig.setMetadataAuthor("Roy");
            exportConfig.setEncrypted(true);
            exportConfig.setAllowedPermissionsHint("PRINTING");
            exporter.setConfiguration(reportConfig);
            exporter.setConfiguration(exportConfig);
            exporter.exportReport();

        } catch (JRException exception) {
        }
    }
}
