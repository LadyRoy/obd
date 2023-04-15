package com.example.kurs.controller;

import com.example.kurs.dto.AuthorDto;
import com.example.kurs.dto.BookDto;
import com.example.kurs.dto.BookStorageDto;
import com.example.kurs.dto.BookStorageReaderDto;
import com.example.kurs.dto.PublishingHouseDto;
import com.example.kurs.dto.ReaderDto;
import com.example.kurs.dto.StorageDto;
import com.example.kurs.dto.fxml.BookFxmlDto;
import com.example.kurs.dto.fxml.BookStorageReaderFxmlDto;
import com.example.kurs.dto.report.BookReportDto;
import com.example.kurs.dto.report.BookStorageReaderReportDto;
import com.example.kurs.helper.DirectoryChooserHelper;
import com.example.kurs.helper.SceneHelper;
import com.example.kurs.loader.FxmlPageLoader;
import com.example.kurs.mapper.fxml.BookFxmlDtoMapper;
import com.example.kurs.mapper.fxml.BookStorageReaderFxmlDtoMapper;
import com.example.kurs.service.AuthorService;
import com.example.kurs.service.BookService;
import com.example.kurs.service.BookStorageReaderService;
import com.example.kurs.service.MailService;
import com.example.kurs.service.PdfReportService;
import com.example.kurs.service.PublishingHouseService;
import com.example.kurs.service.ReaderService;
import com.sun.javafx.collections.ImmutableObservableList;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.util.stream.Collectors.toList;
import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import static javafx.scene.control.ButtonType.OK;
import static javafx.scene.input.MouseButton.MIDDLE;
import static javafx.scene.input.MouseButton.PRIMARY;
import static javafx.scene.input.MouseButton.SECONDARY;
import static javafx.stage.Modality.WINDOW_MODAL;

@Component
@FxmlView("book.fxml")
public class BookController implements Initializable {
    private final MailService mailService;
    private final PdfReportService pdfReportService;
    private final ReaderService readerService;
    private final AuthorService authorService;
    private final PublishingHouseService publishingHouseService;
    private final BookService bookService;
    private final BookStorageReaderService bookStorageReaderService;
    private final FxmlPageLoader fxmlPageLoader;
    @FXML
    private TextField nameTextField;
    @FXML
    private ChoiceBox<PublishingHouseDto> publishingHouseDropdownMenu;
    @FXML
    private ChoiceBox<AuthorDto> authorDropdownMenu;
    @FXML
    private TextField locationTextField;
    @FXML
    private TextField sectionTextField;
    @FXML
    private DatePicker releaseDateTextField;
    @FXML
    private DatePicker registerDateTextField;
    @FXML
    private TableView<BookFxmlDto> bookTable;
    @FXML
    private TableColumn<BookFxmlDto, Integer> idColumn;
    @FXML
    private TableColumn<BookFxmlDto, String> nameColumn;
    @FXML
    private TableColumn<BookFxmlDto, String> authorColumn;
    @FXML
    private TableColumn<BookFxmlDto, String> publishingHouseColumn;
    @FXML
    private TableColumn<BookFxmlDto, String> locationColumn;
    @FXML
    private TableColumn<BookFxmlDto, String> sectionColumn;
    @FXML
    private TableColumn<BookFxmlDto, String> releaseDateColumn;
    @FXML
    private TableColumn<BookFxmlDto, String> registerDateColumn;
    @FXML
    private ChoiceBox<ReaderDto> readerDropdownMenu;
    @FXML
    private DatePicker claimDateTextField;
    @FXML
    private DatePicker returnDateTextField;
    @FXML
    private TableView<BookStorageReaderFxmlDto> claimedBookTable;
    @FXML
    private TableColumn<BookStorageReaderFxmlDto, Integer> bookStorageReaderIdColumn;
    @FXML
    private TableColumn<BookStorageReaderFxmlDto, String> claimedBookColumn;
    @FXML
    private TableColumn<BookStorageReaderFxmlDto, String> readerColumn;
    @FXML
    private TableColumn<BookStorageReaderFxmlDto, String> claimedDateColumn;
    @FXML
    private TableColumn<BookStorageReaderFxmlDto, String> returnDateColumn;
    @FXML
    private TableView<BookStorageReaderFxmlDto> debtorTable;
    @FXML
    private TableColumn<BookStorageReaderFxmlDto, Integer> debtorIdColumn;
    @FXML
    private TableColumn<BookStorageReaderFxmlDto, String> debtorBookColumn;
    @FXML
    private TableColumn<BookStorageReaderFxmlDto, String> debtorColumn;
    @FXML
    private TableColumn<BookStorageReaderFxmlDto, String> debtorClaimDateColumn;
    @FXML
    private TableColumn<BookStorageReaderFxmlDto, String> debtorReturnDateColumn;

    private Integer editingBookId;
    private Integer editingBookStorageReaderId;
    private String oldLibraryLocation;

    @Autowired
    public BookController(MailService mailService,
                          PdfReportService pdfReportService,
                          ReaderService readerService,
                          AuthorService authorService,
                          PublishingHouseService publishingHouseService,
                          BookService bookService,
                          BookStorageReaderService bookStorageReaderService,
                          FxmlPageLoader fxmlPageLoader) {

        this.mailService = mailService;
        this.pdfReportService = pdfReportService;
        this.readerService = readerService;
        this.authorService = authorService;
        this.publishingHouseService = publishingHouseService;
        this.bookService = bookService;
        this.bookStorageReaderService = bookStorageReaderService;
        this.fxmlPageLoader = fxmlPageLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        publishingHouseDropdownMenu.setItems(new ObservableListWrapper<>(publishingHouseService.getAll()));
        authorDropdownMenu.setItems(new ObservableListWrapper<>(authorService.getAll()));
        readerDropdownMenu.setItems(new ObservableListWrapper<>(readerService.getAll()));
        addColumnPropertyValueFactories();
        fillBookTable();
        fillClaimedBookTable();
        fillDebtorTable();
    }

    public void onMailReport(ActionEvent event) {
        var pdfFileName = "book-report" + "-" + LocalDate.now() + ".pdf";
        var jasperFileName = "/report/book-report.jrxml";
        var bookReports = bookTable.getItems().stream()
                .map(BookReportDto::new)
                .collect(toList());
        pdfReportService.doReport(pdfFileName, jasperFileName, bookReports);

        var textInputDialog = new TextInputDialog("");

        textInputDialog.setHeaderText("Введите вашу почту");

        var mail = textInputDialog.showAndWait();

        mail.ifPresent(email ->
                {
                    var path = "";

                    try {
                        path = new File(".").getCanonicalPath() + "\\" + pdfFileName;

                        mailService.sendEmail(
                                email,
                                "Отчет",
                                "Сформирован отчет",
                                "Отчет.pdf",
                                path
                        );
                        Files.delete(Path.of(path));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Отчет");
        informationAlert.setHeaderText("Отчет успешно сформирован!");
        informationAlert.setContentText("Отчет отправлен на почту: " + mail.orElse(""));
        informationAlert.show();
    }

    public void onReport(ActionEvent event) {
        var selectedBook = bookTable.getSelectionModel().getSelectedItem();
        var pdfFileName = "book-report";
        var jasperFileName = "/report/book-report.jrxml";

        if (!ObjectUtils.isEmpty(selectedBook)) {
            doReport(event, selectedBook, pdfFileName, jasperFileName);
            bookTable.getSelectionModel().select(null);
            return;
        }
        var bookReports = bookTable.getItems().stream()
                .map(BookReportDto::new)
                .collect(toList());
        doReport(event, bookReports, pdfFileName, jasperFileName);
    }

    public void onClaimedReport(ActionEvent event) {
        var selectedBookStorageReader = claimedBookTable.getSelectionModel().getSelectedItem();
        var pdfFileName = "claimed-book-report";
        var jasperFileName = "/report/claimed-book-report.jrxml";

        if (!ObjectUtils.isEmpty(selectedBookStorageReader)) {
            doReport(event, selectedBookStorageReader, pdfFileName, jasperFileName);
            claimedBookTable.getSelectionModel().select(null);
            return;
        }
        var bookStorageReaderReports = claimedBookTable.getItems().stream()
                .map(BookStorageReaderReportDto::new)
                .collect(toList());
        doReport(event, bookStorageReaderReports, pdfFileName, jasperFileName);
    }

    public void onDebtorReport(ActionEvent event) {
        var selectedDebtor = debtorTable.getSelectionModel().getSelectedItem();
        var pdfFileName = "debtor-report";
        var jasperFileName = "/report/debtor-report.jrxml";

        if (!ObjectUtils.isEmpty(selectedDebtor)) {
            doReport(event, selectedDebtor, pdfFileName, jasperFileName);
            debtorTable.getSelectionModel().select(null);
            return;
        }
        var bookStorageReaderReports = debtorTable.getItems().stream()
                .map(BookStorageReaderReportDto::new)
                .collect(toList());
        doReport(event, bookStorageReaderReports, pdfFileName, jasperFileName);
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

    public void onClaim() {
        if (ObjectUtils.isEmpty(editingBookStorageReaderId)) {
            bookService.claimBook(buildBookStorageReaderDto(null));

        } else {
            bookStorageReaderService.edit(buildBookStorageReaderDto(editingBookStorageReaderId));

            var informationAlert = new Alert(INFORMATION);

            informationAlert.initModality(WINDOW_MODAL);
            informationAlert.setTitle("Успешное редактирование выданной книги");
            informationAlert.setHeaderText("Оповещение об успешном редактировании выданной книги");
            informationAlert.setContentText("Выданная книга отредактирована");
            informationAlert.show();
        }
        editingBookStorageReaderId = null;
        clearTextFields();
        fillClaimedBookTable();
    }

    private BookStorageReaderDto buildBookStorageReaderDto(Integer id) {
        var selectedBook = bookTable.getSelectionModel().getSelectedItem();
        var selectedReader = readerDropdownMenu.getSelectionModel().getSelectedItem();

        return new BookStorageReaderDto(
                id,
                claimDateTextField.getValue(),
                returnDateTextField.getValue(),
                bookService.getBookStorageDtoByBookId(selectedBook.getId()),
                selectedReader,
                false
        );
    }

    public void onSave() {
        if (ObjectUtils.isEmpty(editingBookId)) {
            bookService.create(buildBookDto(null));

        } else {
            bookService.edit(buildBookDto(editingBookId), oldLibraryLocation);

            var informationAlert = new Alert(INFORMATION);

            informationAlert.initModality(WINDOW_MODAL);
            informationAlert.setTitle("Успешное редактирование книги");
            informationAlert.setHeaderText("Оповещение об успешном редактировании книги");
            informationAlert.setContentText("Книга отредактирован");
            informationAlert.show();
        }
        editingBookId = null;
        oldLibraryLocation = null;
        clearTextFields();
        fillBookTable();
        fillClaimedBookTable();
    }

    public void onMouseClicked(MouseEvent event) {
        if (event.getButton() == PRIMARY && event.getClickCount() >= 2) {
            handleEditBook();
        }
        if (event.getButton() == SECONDARY) {
            handleDeleteBook();
            clearTextFields();
        }
    }

    public void onMouseClaimedBookClicked(MouseEvent event) {
        if (event.getButton() == PRIMARY && event.getClickCount() >= 2) {
            handleEditClaimedBook();
        }
        if (event.getButton() == SECONDARY) {
            handleDeleteClaimedBook();
            clearTextFields();
        }
        if (event.getButton() == MIDDLE) {
            doDebtor();
            clearTextFields();
        }
    }

    private void doDebtor() {
        var selectedBookStorageReaderDto = claimedBookTable.getSelectionModel().getSelectedItem();
        var confirmationAlert = new Alert(CONFIRMATION);

        confirmationAlert.initModality(WINDOW_MODAL);
        confirmationAlert.setTitle("Добавление должника");
        confirmationAlert.setHeaderText("Добавить должника "
                + selectedBookStorageReaderDto.getReader() + "?");
        confirmationAlert.setContentText("Вы уверены, что хотите сделать читателя должником?");

        var pressedButton = confirmationAlert.showAndWait();

        if (pressedButton.isEmpty() || pressedButton.get() != OK) {
            return;
        }
        var savedBookStorageReader = bookStorageReaderService.getById(selectedBookStorageReaderDto.getId());
        savedBookStorageReader.setDebtor(!savedBookStorageReader.isDebtor());
        bookService.editClaimedBook(savedBookStorageReader);

        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Должник создан");
        informationAlert.setHeaderText("Оповещение об успешном добавлении должника");
        informationAlert.setContentText("Читатель " + selectedBookStorageReaderDto.getReader() + " стал должником");
        informationAlert.show();
        fillDebtorTable();
    }

    public void onDebtorMouseClick(MouseEvent event) {
        if (event.getButton() == SECONDARY) {
            changeDebtorStatus();
        }
    }

    private void changeDebtorStatus() {
        var selectedBookStorageReaderDto = debtorTable.getSelectionModel().getSelectedItem();
        var confirmationAlert = new Alert(CONFIRMATION);

        confirmationAlert.initModality(WINDOW_MODAL);
        confirmationAlert.setTitle("Удаление должника из списка");
        confirmationAlert.setHeaderText("Удалить должника "
                + selectedBookStorageReaderDto.getReader() + "?");
        confirmationAlert.setContentText("Вы уверены, что хотите удалить этого должника (он перестанет быть должником)?");

        var pressedButton = confirmationAlert.showAndWait();

        if (pressedButton.isEmpty() || pressedButton.get() != OK) {
            return;
        }
        var savedBookStorageReader = bookStorageReaderService.getById(selectedBookStorageReaderDto.getId());
        savedBookStorageReader.setDebtor(!savedBookStorageReader.isDebtor());
        bookService.editClaimedBook(savedBookStorageReader);

        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Успешное удаление должника из списка");
        informationAlert.setHeaderText("Оповещение об успешном удалении должника из списка");
        informationAlert.setContentText("Читатель " + selectedBookStorageReaderDto.getReader() + " больше не является должником");
        informationAlert.show();
        fillDebtorTable();
    }

    private void handleEditClaimedBook() {
        var selectedBookStorageReaderDto = claimedBookTable.getSelectionModel().getSelectedItem();
        editingBookStorageReaderId = selectedBookStorageReaderDto.getId();

        claimDateTextField.setValue(LocalDate.parse(
                selectedBookStorageReaderDto.getClaimDate(), DateTimeFormatter.ISO_DATE)
        );
        returnDateTextField.setValue(LocalDate.parse(
                selectedBookStorageReaderDto.getReturnDate(), DateTimeFormatter.ISO_DATE)
        );
        readerDropdownMenu.setValue(readerDropdownMenu.getItems().stream()
                .filter(readerDto -> readerDto.toString().equals(selectedBookStorageReaderDto.getReader()))
                .findFirst()
                .orElse(null)
        );
        bookTable.getSelectionModel().select(bookTable.getItems().stream()
                .filter(bookFxmlDto -> bookFxmlDto.getName().equals(selectedBookStorageReaderDto.getBook()))
                .findFirst()
                .orElse(null)
        );
        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Изменение выданных книг");
        informationAlert.setHeaderText("Оповещение об изменении выданных книг");
        informationAlert.setContentText("Выданная книга " + selectedBookStorageReaderDto.getBook()
                + " для читателя " + selectedBookStorageReaderDto.getReader() + " в режиме редактирования");
        informationAlert.show();
    }

    private void handleDeleteClaimedBook() {
        var selectedBookStorageReaderDto = claimedBookTable.getSelectionModel().getSelectedItem();
        var confirmationAlert = new Alert(CONFIRMATION);

        confirmationAlert.initModality(WINDOW_MODAL);
        confirmationAlert.setTitle("Удаление выданной книги");
        confirmationAlert.setHeaderText("Удалить выданную книгу \""
                + selectedBookStorageReaderDto.getBook() + "\" для читателя "
                + selectedBookStorageReaderDto.getReader() + "?");
        confirmationAlert.setContentText("Вы уверены, что хотите удалить выданную книгу?");

        var pressedButton = confirmationAlert.showAndWait();

        if (pressedButton.isEmpty() || pressedButton.get() != OK) {
            return;
        }
        bookStorageReaderService.deleteById(selectedBookStorageReaderDto.getId());

        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Успешное удаление");
        informationAlert.setHeaderText("Оповещение об успешном удалении выданной книги");
        informationAlert.setContentText("Выданная книга" + selectedBookStorageReaderDto.getBook() + " для читателя "
                + selectedBookStorageReaderDto.getReader() + " удалена");
        informationAlert.show();
        fillClaimedBookTable();
    }

    public void onBackToMainPage(ActionEvent event) {
        SceneHelper.switchScene(event, fxmlPageLoader.loadFxmlFile(MainController.class));
    }

    private void handleEditBook() {
        var selectedBook = bookTable.getSelectionModel().getSelectedItem();
        editingBookId = selectedBook.getId();
        oldLibraryLocation = selectedBook.getLocation();
        var author = authorDropdownMenu.getItems().stream()
                .filter(authorDto -> authorDto.toString().equals(selectedBook.getAuthor()))
                .findFirst()
                .orElse(null);
        var publishingHouse = publishingHouseDropdownMenu.getItems().stream()
                .filter(publishingHouseDto -> publishingHouseDto.toString().equals(selectedBook.getPublishingHouse()))
                .findFirst()
                .orElse(null);
        nameTextField.setText(selectedBook.getName());
        authorDropdownMenu.setValue(author);
        publishingHouseDropdownMenu.setValue(publishingHouse);
        locationTextField.setText(selectedBook.getLocation());
        sectionTextField.setText(selectedBook.getSection());
        releaseDateTextField.setValue(LocalDate.parse(selectedBook.getReleaseDate(), DateTimeFormatter.ISO_DATE));
        registerDateTextField.setValue(LocalDate.parse(selectedBook.getRegisterDate(), DateTimeFormatter.ISO_DATE));

        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Изменение книги");
        informationAlert.setHeaderText("Оповещение об изменении книги");
        informationAlert.setContentText("Книга " + selectedBook.getName() + " в режиме редактирования");
        informationAlert.show();
    }

    private void handleDeleteBook() {
        var selectedBook = bookTable.getSelectionModel().getSelectedItem();
        var confirmationAlert = new Alert(CONFIRMATION);

        confirmationAlert.initModality(WINDOW_MODAL);
        confirmationAlert.setTitle("Удаление книги");
        confirmationAlert.setHeaderText("Удалить \"" + selectedBook.getName() + "\"?");
        confirmationAlert.setContentText("Вы уверены, что хотите удалить эту книгу?");

        var pressedButton = confirmationAlert.showAndWait();

        if (pressedButton.isEmpty() || pressedButton.get() != OK) {
            return;
        }
        bookService.deleteById(selectedBook.getId());

        var informationAlert = new Alert(INFORMATION);

        informationAlert.initModality(WINDOW_MODAL);
        informationAlert.setTitle("Успешное удаление");
        informationAlert.setHeaderText("Оповещение об успешном удалении книги");
        informationAlert.setContentText("Книга" + selectedBook.getName() + " удалена");
        informationAlert.show();
        fillBookTable();
    }

    private void clearTextFields() {
        nameTextField.setText("");
        authorDropdownMenu.setValue(null);
        publishingHouseDropdownMenu.setValue(null);
        readerDropdownMenu.setValue(null);
        locationTextField.setText("");
        sectionTextField.setText("");
        releaseDateTextField.setValue(null);
        registerDateTextField.setValue(null);
        claimDateTextField.setValue(null);
        returnDateTextField.setValue(null);
    }

    private void addColumnPropertyValueFactories() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setVisible(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishingHouseColumn.setCellValueFactory(new PropertyValueFactory<>("publishingHouse"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        sectionColumn.setCellValueFactory(new PropertyValueFactory<>("section"));
        releaseDateColumn.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
        registerDateColumn.setCellValueFactory(new PropertyValueFactory<>("registerDate"));

        bookStorageReaderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookStorageReaderIdColumn.setVisible(false);
        claimedBookColumn.setCellValueFactory(new PropertyValueFactory<>("book"));
        readerColumn.setCellValueFactory(new PropertyValueFactory<>("reader"));
        claimedDateColumn.setCellValueFactory(new PropertyValueFactory<>("claimDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

        debtorIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        debtorIdColumn.setVisible(false);
        debtorBookColumn.setCellValueFactory(new PropertyValueFactory<>("book"));
        debtorColumn.setCellValueFactory(new PropertyValueFactory<>("reader"));
        debtorClaimDateColumn.setCellValueFactory(new PropertyValueFactory<>("claimDate"));
        debtorReturnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
    }

    private void fillBookTable() {
        fillBookTable(bookService.getAll());
    }

    private void fillBookTable(List<BookDto> books) {
        bookTable.setItems(mapToBookFxmlDto(books));
    }

    private void fillClaimedBookTable() {
        claimedBookTable.setItems(mapToBookStorageReaderFxmlDto(bookStorageReaderService.getAll()));
    }

    private void fillDebtorTable() {
        debtorTable.setItems(mapToBookStorageReaderFxmlDto(bookStorageReaderService.getAllDebtors()));
    }

    private ObservableList<BookFxmlDto> mapToBookFxmlDto(List<BookDto> books) {
        return new ImmutableObservableList<>(books.stream()
                .map(BookFxmlDtoMapper::mapToDto
                )
                .toArray(BookFxmlDto[]::new)
        );
    }

    private ObservableList<BookStorageReaderFxmlDto> mapToBookStorageReaderFxmlDto(List<BookStorageReaderDto> bookStorageReaders) {
        return new ImmutableObservableList<>(bookStorageReaders.stream()
                .map(BookStorageReaderFxmlDtoMapper::mapToDto)
                .toArray(BookStorageReaderFxmlDto[]::new)
        );
    }

    private BookDto buildBookDto(Integer bookId) {
        return new BookDto(
                bookId,
                nameTextField.getText(),
                authorDropdownMenu.getSelectionModel().getSelectedItem(),
                publishingHouseDropdownMenu.getSelectionModel().getSelectedItem(),
                new BookStorageDto(
                        null,
                        null,
                        new StorageDto(
                                null,
                                locationTextField.getText()
                        ),
                        sectionTextField.getText()
                ),
                releaseDateTextField.getValue(),
                registerDateTextField.getValue()
        );
    }
}
