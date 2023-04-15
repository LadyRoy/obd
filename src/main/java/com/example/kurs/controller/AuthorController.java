package com.example.kurs.controller;

import com.example.kurs.dto.AuthorDto;
import com.example.kurs.dto.fxml.AuthorFxmlDto;
import com.example.kurs.dto.report.AuthorReportDto;
import com.example.kurs.dto.report.ReaderReportDto;
import com.example.kurs.helper.DirectoryChooserHelper;
import com.example.kurs.helper.SceneHelper;
import com.example.kurs.loader.FxmlPageLoader;
import com.example.kurs.mapper.fxml.AuthorFxmlDtoMapper;
import com.example.kurs.service.AuthorBookService;
import com.example.kurs.service.AuthorService;
import com.example.kurs.service.PdfReportService;
import com.sun.javafx.collections.ImmutableObservableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
@FxmlView("author.fxml")
public class AuthorController implements Initializable {
    private final PdfReportService pdfReportService;
    private final AuthorService authorService;
    private final AuthorBookService authorBookService;
    private final FxmlPageLoader fxmlPageLoader;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private DatePicker birthdayTextField;
    @FXML
    private TextField shortInfoTextField;
    @FXML
    private TableView<AuthorFxmlDto> authorsTable;
    @FXML
    private TableColumn<AuthorFxmlDto, Integer> idColumn;
    @FXML
    private TableColumn<AuthorFxmlDto, String> surnameColumn;
    @FXML
    private TableColumn<AuthorFxmlDto, String> nameColumn;
    @FXML
    private TableColumn<AuthorFxmlDto, String> birthdayColumn;
    @FXML
    private TableColumn<AuthorFxmlDto, String> shortInfoColumn;
    @FXML
    private TableColumn<AuthorFxmlDto, String> bookNamesColumn;

    private Integer editingAuthorId;

    @Autowired
    public AuthorController(PdfReportService pdfReportService,
                            AuthorService authorService,
                            AuthorBookService authorBookService,
                            FxmlPageLoader fxmlPageLoader) {

        this.pdfReportService = pdfReportService;
        this.authorService = authorService;
        this.authorBookService = authorBookService;
        this.fxmlPageLoader = fxmlPageLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addColumnPropertyValueFactories();
        fillAuthorsTable();
    }

    public void onReport(ActionEvent event) {
        var selectedAuthor = authorsTable.getSelectionModel().getSelectedItem();
        var pdfFileName = "author-report";
        var jasperFileName = "/report/author-report.jrxml";

        if (!ObjectUtils.isEmpty(selectedAuthor)) {
            doReport(event, selectedAuthor, pdfFileName, jasperFileName);
            authorsTable.getSelectionModel().select(null);
            return;
        }
        var authors = authorsTable.getItems().stream()
                .map(AuthorReportDto::new)
                .collect(toList());
        doReport(event, authors, pdfFileName, jasperFileName);
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
        if (ObjectUtils.isEmpty(editingAuthorId)) {
            authorService.create(buildAuthorDto(null));

        } else {
            authorService.edit(buildAuthorDto(editingAuthorId));

            var informationAlert = new Alert(INFORMATION);

            informationAlert.initModality(WINDOW_MODAL);
            informationAlert.setTitle("Успешное редактирование автора");
            informationAlert.setHeaderText("Оповещение об успешном редактировании автора");
            informationAlert.setContentText("Автор отредактирован");
            informationAlert.show();
        }
        editingAuthorId = null;
        clearTextFields();
        fillAuthorsTable();
    }

    public void onMouseClicked(MouseEvent event) {
        if (event.getButton() == PRIMARY && event.getClickCount() >= 2) {
            handleEditAuthor();
        }
        if (event.getButton() == SECONDARY) {
            handleDeleteAuthor();
            clearTextFields();
        }
    }

    public void onBackToMainPage(ActionEvent event) {
        SceneHelper.switchScene(event, fxmlPageLoader.loadFxmlFile(MainController.class));
    }

    private void handleEditAuthor() {
        var selectedAuthor = authorsTable.getSelectionModel().getSelectedItem();

        editingAuthorId = selectedAuthor.getId();
        surnameTextField.setText(selectedAuthor.getSurname());
        nameTextField.setText(selectedAuthor.getName());
        birthdayTextField.setValue(LocalDate.parse(selectedAuthor.getBirthday(), DateTimeFormatter.ISO_DATE));
        shortInfoTextField.setText(selectedAuthor.getShortInfo());

        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Изменение автора");
        informationAlert.setHeaderText("Оповещение об изменении автора");
        informationAlert.setContentText("Автор " + selectedAuthor.getSurname()
                + " " + selectedAuthor.getName() + " в режиме редактирования"
        );
        informationAlert.show();
    }

    private void handleDeleteAuthor() {
        var selectedAuthor = authorsTable.getSelectionModel().getSelectedItem();
        var confirmationAlert = new Alert(CONFIRMATION);

        confirmationAlert.initModality(WINDOW_MODAL);
        confirmationAlert.setTitle("Удаление автора");
        confirmationAlert.setHeaderText("Удалить \"" + selectedAuthor.getSurname() + " " + selectedAuthor.getName() + "\"?");
        confirmationAlert.setContentText("Вы уверены, что хотите удалить этого автора?");

        var pressedButton = confirmationAlert.showAndWait();

        if (pressedButton.isEmpty() || pressedButton.get() != OK) {
            return;
        }
        authorService.deleteById(selectedAuthor.getId());

        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Успешное удаление");
        informationAlert.setHeaderText("Оповещение об успешном удалении автора");
        informationAlert.setContentText("Автор" + selectedAuthor.getSurname() + " " + selectedAuthor.getName() + " удален");
        informationAlert.show();
        fillAuthorsTable();
    }

    private void clearTextFields() {
        surnameTextField.setText("");
        nameTextField.setText("");
        birthdayTextField.setValue(null);
        shortInfoTextField.setText("");
    }

    private void addColumnPropertyValueFactories() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setVisible(false);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        shortInfoColumn.setCellValueFactory(new PropertyValueFactory<>("shortInfo"));
        bookNamesColumn.setCellValueFactory(new PropertyValueFactory<>("bookNames"));
    }

    private void fillAuthorsTable() {
        fillAuthorsTable(authorService.getAll());
    }

    private void fillAuthorsTable(List<AuthorDto> authors) {
        authorsTable.setItems(mapToAuthorFxmlDto(authors));
    }

    private ObservableList<AuthorFxmlDto> mapToAuthorFxmlDto(List<AuthorDto> authors) {
        return new ImmutableObservableList<>(authors.stream()
                .map(authorDto ->
                        AuthorFxmlDtoMapper.mapToDto(authorDto, authorBookService.getBooksByAuthorId(authorDto.getId()))
                )
                .toArray(AuthorFxmlDto[]::new)
        );
    }

    private AuthorDto buildAuthorDto(Integer id) {
        return new AuthorDto(
                id,
                surnameTextField.getText(),
                nameTextField.getText(),
                birthdayTextField.getValue(),
                shortInfoTextField.getText()
        );
    }
}
