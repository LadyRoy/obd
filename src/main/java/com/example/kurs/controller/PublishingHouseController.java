package com.example.kurs.controller;

import com.example.kurs.dto.PublishingHouseDto;
import com.example.kurs.dto.fxml.PublishingHouseFxmlDto;
import com.example.kurs.dto.report.AuthorReportDto;
import com.example.kurs.dto.report.PublishingHouseReportDto;
import com.example.kurs.helper.DirectoryChooserHelper;
import com.example.kurs.helper.SceneHelper;
import com.example.kurs.loader.FxmlPageLoader;
import com.example.kurs.mapper.fxml.PublishingHouseFxmlDtoMapper;
import com.example.kurs.service.PdfReportService;
import com.example.kurs.service.PublishingHouseService;
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
import org.springframework.beans.factory.annotation.Autowired;
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
@FxmlView("publishing.fxml")
public class PublishingHouseController implements Initializable {
    private final PdfReportService pdfReportService;
    private final PublishingHouseService publishingHouseService;
    private final FxmlPageLoader fxmlPageLoader;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField locationTextField;
    @FXML
    private TableView<PublishingHouseFxmlDto> publishingHouseTable;
    @FXML
    private TableColumn<PublishingHouseFxmlDto, Integer> idColumn;
    @FXML
    private TableColumn<PublishingHouseFxmlDto, String> nameColumn;
    @FXML
    private TableColumn<PublishingHouseFxmlDto, String> locationColumn;
    @FXML
    private TableColumn<PublishingHouseFxmlDto, Integer> releasedAmountColumn;
    @FXML
    private TableColumn<PublishingHouseFxmlDto, String> bookNamesColumn;

    private Integer editingPublishingHouseId;

    @Autowired
    public PublishingHouseController(PdfReportService pdfReportService,
                                     PublishingHouseService publishingHouseService,
                                     FxmlPageLoader fxmlPageLoader) {

        this.pdfReportService = pdfReportService;
        this.publishingHouseService = publishingHouseService;
        this.fxmlPageLoader = fxmlPageLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addColumnPropertyValueFactories();
        fillPublishingHouseTable();
    }

    public void onReport(ActionEvent event) {
        var selectedPublishingHouse = publishingHouseTable.getSelectionModel().getSelectedItem();
        var pdfFileName = "publishing-house-report";
        var jasperFileName = "/report/publishing-house-report.jrxml";

        if (!ObjectUtils.isEmpty(selectedPublishingHouse)) {
            doReport(event, selectedPublishingHouse, pdfFileName, jasperFileName);
            publishingHouseTable.getSelectionModel().select(null);
            return;
        }
        var publishingHouses = publishingHouseTable.getItems().stream()
                .map(PublishingHouseReportDto::new)
                .collect(toList());
        doReport(event, publishingHouses, pdfFileName, jasperFileName);
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
        if (ObjectUtils.isEmpty(editingPublishingHouseId)) {
            publishingHouseService.create(buildPublishingHouseDto(null));

        } else {
            publishingHouseService.edit(buildPublishingHouseDto(editingPublishingHouseId));

            var informationAlert = new Alert(INFORMATION);

            informationAlert.initModality(WINDOW_MODAL);
            informationAlert.setTitle("Успешное редактирование издательства");
            informationAlert.setHeaderText("Оповещение об успешном редактировании издательства");
            informationAlert.setContentText("Издательство отредактировано");
            informationAlert.show();
        }
        editingPublishingHouseId = null;
        clearTextFields();
        fillPublishingHouseTable();
    }

    public void onMouseClicked(MouseEvent event) {
        if (event.getButton() == PRIMARY && event.getClickCount() >= 2) {
            handleEditPublishingHouse();
        }
        if (event.getButton() == SECONDARY) {
            handleDeletePublishingHouse();
            clearTextFields();
        }
    }

    public void onBackToMainPage(ActionEvent event) {
        SceneHelper.switchScene(event, fxmlPageLoader.loadFxmlFile(MainController.class));
    }

    private void handleEditPublishingHouse() {
        var selectedPublishingHouse = publishingHouseTable.getSelectionModel().getSelectedItem();

        editingPublishingHouseId = selectedPublishingHouse.getId();
        nameTextField.setText(selectedPublishingHouse.getName());
        locationTextField.setText(selectedPublishingHouse.getLocation());

        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Изменение издательства");
        informationAlert.setHeaderText("Оповещение об изменении издательства");
        informationAlert.setContentText("Издательство " + selectedPublishingHouse.getName() + " в режиме редактирования");
        informationAlert.show();
    }

    private void handleDeletePublishingHouse() {
        var selectedPublishingHouse = publishingHouseTable.getSelectionModel().getSelectedItem();
        var confirmationAlert = new Alert(CONFIRMATION);

        confirmationAlert.initModality(WINDOW_MODAL);
        confirmationAlert.setTitle("Удаление издательства");
        confirmationAlert.setHeaderText("Удалить \"" + selectedPublishingHouse.getName() + "\"?");
        confirmationAlert.setContentText("Вы уверены, что хотите удалить это издательство?");

        var pressedButton = confirmationAlert.showAndWait();

        if (pressedButton.isEmpty() || pressedButton.get() != OK) {
            return;
        }
        publishingHouseService.deleteById(selectedPublishingHouse.getId());

        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Успешное удаление");
        informationAlert.setHeaderText("Оповещение об успешном удалении издательства");
        informationAlert.setContentText("Издательство " + selectedPublishingHouse.getName() + " удалено");
        informationAlert.show();
        fillPublishingHouseTable();
    }

    private void clearTextFields() {
        nameTextField.setText("");
        locationTextField.setText("");
    }

    private void addColumnPropertyValueFactories() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setVisible(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        releasedAmountColumn.setCellValueFactory(new PropertyValueFactory<>("releasedAmount"));
        bookNamesColumn.setCellValueFactory(new PropertyValueFactory<>("bookNames"));
    }

    private void fillPublishingHouseTable() {
        fillPublishingHouseTable(publishingHouseService.getAll());
    }

    private void fillPublishingHouseTable(List<PublishingHouseDto> publishingHouseDtos) {
        publishingHouseTable.setItems(mapToPublishingHouseFxmlDto(publishingHouseDtos));
    }

    private ObservableList<PublishingHouseFxmlDto> mapToPublishingHouseFxmlDto(List<PublishingHouseDto> publishingHouseDtos) {
        return new ImmutableObservableList<>(publishingHouseDtos.stream()
                .map(publishingHouseDto ->
                        PublishingHouseFxmlDtoMapper.mapToDto(
                                publishingHouseDto,
                                publishingHouseService.getBooksByPublishingHouseId(publishingHouseDto.getId())
                        )
                )
                .toArray(PublishingHouseFxmlDto[]::new)
        );
    }

    private PublishingHouseDto buildPublishingHouseDto(Integer id) {
        return new PublishingHouseDto(
                id,
                nameTextField.getText(),
                locationTextField.getText(),
                null
        );
    }
}
