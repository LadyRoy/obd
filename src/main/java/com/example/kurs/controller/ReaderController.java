package com.example.kurs.controller;

import com.example.kurs.dto.ReaderDto;
import com.example.kurs.dto.fxml.ReaderFxmlDto;
import com.example.kurs.dto.report.ReaderReportDto;
import com.example.kurs.helper.DirectoryChooserHelper;
import com.example.kurs.helper.SceneHelper;
import com.example.kurs.loader.FxmlPageLoader;
import com.example.kurs.mapper.fxml.ReaderFxmlDtoMapper;
import com.example.kurs.service.PdfReportService;
import com.example.kurs.service.ReaderService;
import com.sun.javafx.collections.ImmutableObservableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static java.util.stream.Collectors.toList;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import static javafx.scene.control.ButtonType.OK;
import static javafx.scene.input.MouseButton.PRIMARY;
import static javafx.scene.input.MouseButton.SECONDARY;
import static javafx.stage.Modality.WINDOW_MODAL;

@Component
@FxmlView("reader.fxml")
public class ReaderController implements Initializable {
    private final PdfReportService pdfReportService;
    private final ReaderService readerService;
    private final FxmlPageLoader fxmlPageLoader;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField phoneNumberTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField mailTextField;
    @FXML
    private TableView<ReaderFxmlDto> readerTable;
    @FXML
    private TableColumn<ReaderFxmlDto, Integer> idColumn;
    @FXML
    private TableColumn<ReaderFxmlDto, String> firstNameColumn;
    @FXML
    private TableColumn<ReaderFxmlDto, String> lastNameColumn;
    @FXML
    private TableColumn<ReaderFxmlDto, String> phoneNumberColumn;
    @FXML
    private TableColumn<ReaderFxmlDto, String> addressColumn;
    @FXML
    private TableColumn<ReaderFxmlDto, String> mailColumn;
    @FXML
    private TableColumn<ReaderFxmlDto, String> bookNamesColumn;

    private Integer editingReaderId;

    public ReaderController(PdfReportService pdfReportService,
                            ReaderService readerService,
                            FxmlPageLoader fxmlPageLoader) {

        this.pdfReportService = pdfReportService;
        this.readerService = readerService;
        this.fxmlPageLoader = fxmlPageLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addColumnPropertyValueFactories();
        fillReaderTable();
    }

    public void onReport(ActionEvent event) {
        var selectedReader = readerTable.getSelectionModel().getSelectedItem();
        var pdfFileName = "reader-report";
        var jasperFileName = "/report/reader-report.jrxml";

        if (!ObjectUtils.isEmpty(selectedReader)) {
            doReport(event, selectedReader, pdfFileName, jasperFileName);
            readerTable.getSelectionModel().select(null);
            return;
        }
        var readers = readerTable.getItems().stream()
                .map(ReaderReportDto::new)
                .collect(toList());
        doReport(event, readers, pdfFileName, jasperFileName);
    }

    private void doReport(ActionEvent event, Object data, String pdfFileName, String jasperFileName) {
        doReport(event, List.of(data), pdfFileName, jasperFileName);
    }

    private void doReport(ActionEvent event, List<?> data, String pdfFileName, String jasperFileName) {
        var absolutePath = DirectoryChooserHelper.getAbsolutePath(event, pdfFileName);

        pdfReportService.doReport(absolutePath, jasperFileName, data);
        showReportAlert(absolutePath);
    }

    private void showReportAlert(String path) {
        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Отчет");
        informationAlert.setHeaderText("Отчет успешно сформирован!");
        informationAlert.setContentText("Отчет сохранен по пути: " + path);
        informationAlert.show();
    }

    public void onSave() {
        if (ObjectUtils.isEmpty(editingReaderId)) {
            readerService.create(buildReaderDto(null));

        } else {
            readerService.edit(buildReaderDto(editingReaderId));

            var informationAlert = new Alert(INFORMATION);

            informationAlert.initModality(WINDOW_MODAL);
            informationAlert.setTitle("Успешное редактирование читателя");
            informationAlert.setHeaderText("Оповещение об успешном редактировании читателя");
            informationAlert.setContentText("Читатель отредактирован");
            informationAlert.show();
        }
        editingReaderId = null;
        clearTextFields();
        fillReaderTable();
    }

    public void onMouseClicked(MouseEvent event) {
        if (event.getButton() == PRIMARY && event.getClickCount() >= 2) {
            handleEditReader();
        }
        if (event.getButton() == SECONDARY) {
            handleDeleteReader();
            clearTextFields();
        }
    }

    public void onBackToMainPage(ActionEvent event) {
        SceneHelper.switchScene(event, fxmlPageLoader.loadFxmlFile(MainController.class));
    }

    private void handleEditReader() {
        var selectedReader = readerTable.getSelectionModel().getSelectedItem();

        editingReaderId = selectedReader.getId();
        firstNameTextField.setText(selectedReader.getFirstName());
        lastNameTextField.setText(selectedReader.getLastName());
        phoneNumberTextField.setText(selectedReader.getPhoneNumber());
        addressTextField.setText(selectedReader.getAddress());
        mailTextField.setText(selectedReader.getMail());

        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Изменение читателя");
        informationAlert.setHeaderText("Оповещение об изменении читателя");
        informationAlert.setContentText("Издательство " + selectedReader.getFirstName() + " "
                + selectedReader.getLastName() + " в режиме редактирования");
        informationAlert.show();
    }

    private void handleDeleteReader() {
        var selectedReader = readerTable.getSelectionModel().getSelectedItem();
        var confirmationAlert = new Alert(CONFIRMATION);

        confirmationAlert.initModality(WINDOW_MODAL);
        confirmationAlert.setTitle("Удаление читателя");
        confirmationAlert.setHeaderText("Удалить \"" + selectedReader.getFirstName()
                + " " + selectedReader.getLastName() + "\"?");
        confirmationAlert.setContentText("Вы уверены, что хотите удалить этого читателя?");

        var pressedButton = confirmationAlert.showAndWait();

        if (pressedButton.isEmpty() || pressedButton.get() != OK) {
            return;
        }
        readerService.deleteById(selectedReader.getId());

        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Успешное удаление");
        informationAlert.setHeaderText("Оповещение об успешном удалении читателя");
        informationAlert.setContentText("Читатель " + selectedReader.getFirstName()
                + " " + selectedReader.getLastName() + " удален");
        informationAlert.show();
        fillReaderTable();
    }

    private void clearTextFields() {
        firstNameTextField.setText("");
        lastNameTextField.setText("");
        phoneNumberTextField.setText("");
        addressTextField.setText("");
        mailTextField.setText("");
    }

    private void addColumnPropertyValueFactories() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setVisible(false);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        mailColumn.setCellValueFactory(new PropertyValueFactory<>("mail"));
        bookNamesColumn.setCellValueFactory(new PropertyValueFactory<>("bookNames"));
    }

    private void fillReaderTable() {
        fillReaderTable(readerService.getAll());
    }

    private void fillReaderTable(List<ReaderDto> readerDtos) {
        readerTable.setItems(mapToPublishingHouseFxmlDto(readerDtos));
    }

    private ObservableList<ReaderFxmlDto> mapToPublishingHouseFxmlDto(List<ReaderDto> readerDtos) {
        return new ImmutableObservableList<>(readerDtos.stream()
                .map(ReaderFxmlDtoMapper::mapToDto)
                .toArray(ReaderFxmlDto[]::new)
        );
    }

    private ReaderDto buildReaderDto(Integer id) {
        return new ReaderDto(
                id,
                firstNameTextField.getText(),
                lastNameTextField.getText(),
                phoneNumberTextField.getText(),
                addressTextField.getText(),
                mailTextField.getText(),
                null
        );
    }
}
