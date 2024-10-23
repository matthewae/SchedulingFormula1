package sistempenjadwalanotomatisf1motgp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableRow;
import javafx.scene.input.MouseEvent;

public class FXMLDocumentController implements Initializable {

    @FXML
    private TableView<modelrace> viewtbv;
    @FXML
    private TableColumn<modelrace, Integer> idv;
    @FXML
    private TableColumn<modelrace, String> rnv;
    @FXML
    private TableColumn<modelrace, String> lv;
    @FXML
    private TableColumn<modelrace, String> rtv;
    @FXML
    private TableColumn<modelrace, LocalDate> dv;
    @FXML
    private TableColumn<modelrace, Integer> lcv;
    @FXML
    private TableColumn<modelrace, BigDecimal> llv;
    @FXML
    private TableColumn<modelrace, Time> stv;
    @FXML
    private TableColumn<modelrace, Time> etv;

    @FXML
    private TextField racetxt;
    @FXML
    private TextField tracktxt;
    @FXML
    private TextField laptxt;
    @FXML
    private DatePicker date;
    @FXML
    private ComboBox<String> typebox;

    private modelrace selectedRace;
    private ObservableList<modelrace> raceList;
    private DBRace dbRace;
    @FXML
    private Tab viewtab;
    @FXML
    private Button selupbtn;
    @FXML
    private Button schedulebtn;
    @FXML
    private Tab instab;
    @FXML
    private ComboBox<String> locbtn1;
    @FXML
    private TextField starttxt;
    @FXML
    private TextField endtxt;
    @FXML
    private TextField imgtxt1;
    @FXML
    private Button filebtn;
    @FXML
    private Button finishbtn;
    @FXML
    private Button resetbtn;
    @FXML
    private TextField idtxt;
    @FXML
    private Tab updatetab;
    @FXML
    private DatePicker date2;
    @FXML
    private TextField racetxt2;
    @FXML
    private TextField starttxt2;
    @FXML
    private TextField endtxt2;
    @FXML
    private ComboBox<String> typebox2;
    @FXML
    private TextField tracktxt2;
    @FXML
    private TextField laptxt2;
    @FXML
    private TextField imgtxt2;
    @FXML
    private Button filebtn2;
    @FXML
    private Button updatebtn;
    @FXML
    private Tab deltab;
    @FXML
    private TableView<modelrace> deltbv;
    @FXML
    private TableColumn<modelrace, Integer> idv1;
    @FXML
    private TableColumn<modelrace, String> rnv1;
    @FXML
    private TableColumn<modelrace, String> lv1;
    @FXML
    private TableColumn<modelrace, String> rtv1;
    @FXML
    private TableColumn<modelrace, LocalDate> dv1;
    @FXML
    private TableColumn<modelrace, Integer> lcv1;
    @FXML
    private TableColumn<modelrace, BigDecimal> llv1;
    @FXML
    private TableColumn<modelrace, Time> stv1;
    @FXML
    private TableColumn<modelrace, Time> etv1;
    @FXML
    private Button delbtn;
    @FXML
    private ComboBox<String> locbtn2;
    @FXML
    private Button exbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dbRace = new DBRace();
        raceList = FXCollections.observableArrayList();
        setupTableView();
        setupDelTableView();
        loadRaceData();
        typebox.getItems().addAll("Main Race", "Qualification", "Practice");
        locbtn1.getItems().addAll(
                "Bahrain International Circuit",
                "Jeddah Corniche Circuit",
                "Melbourne Grand Prix Circuit",
                "Azerbaijan Grand Prix Circuit",
                "Circuit de Barcelona-Catalunya",
                "Circuit de Monaco",
                "Circuit Gilles Villeneuve",
                "Silverstone Circuit",
                "Hungaroring",
                "Circuit de Spa-Francorchamps",
                "Zandvoort Circuit",
                "Monza Circuit",
                "Singapore Grand Prix",
                "Las Vegas Grand Prix",
                "Yas Marina Circuit"
        );

        locbtn2.getItems().addAll(
                "Bahrain International Circuit",
                "Jeddah Corniche Circuit",
                "Melbourne Grand Prix Circuit",
                "Azerbaijan Grand Prix Circuit",
                "Circuit de Barcelona-Catalunya",
                "Circuit de Monaco",
                "Circuit Gilles Villeneuve",
                "Silverstone Circuit",
                "Hungaroring",
                "Circuit de Spa-Francorchamps",
                "Zandvoort Circuit",
                "Monza Circuit",
                "Singapore Grand Prix",
                "Las Vegas Grand Prix",
                "Yas Marina Circuit"
        );

    }

    private void setupTableView() {
        idv.setCellValueFactory(new PropertyValueFactory<>("id"));
        rnv.setCellValueFactory(new PropertyValueFactory<>("raceName"));
        lv.setCellValueFactory(new PropertyValueFactory<>("location"));
        rtv.setCellValueFactory(new PropertyValueFactory<>("raceType"));
        dv.setCellValueFactory(new PropertyValueFactory<>("raceDate"));
        lcv.setCellValueFactory(new PropertyValueFactory<>("lapCount"));
        llv.setCellValueFactory(new PropertyValueFactory<>("circuitLengthKm"));
        stv.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        etv.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        viewtbv.setRowFactory(tv -> {
            TableRow<modelrace> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    selectedRace = row.getItem();
                }
            });
            return row;
        });
    }

    private void setupDelTableView() {
        idv1.setCellValueFactory(new PropertyValueFactory<>("id"));
        rnv1.setCellValueFactory(new PropertyValueFactory<>("raceName"));
        lv1.setCellValueFactory(new PropertyValueFactory<>("location"));
        rtv1.setCellValueFactory(new PropertyValueFactory<>("raceType"));
        dv1.setCellValueFactory(new PropertyValueFactory<>("raceDate"));
        lcv1.setCellValueFactory(new PropertyValueFactory<>("lapCount"));
        llv1.setCellValueFactory(new PropertyValueFactory<>("circuitLengthKm"));
        stv1.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        etv1.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        deltbv.setRowFactory(tv -> {
            TableRow<modelrace> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty()) {
                    selectedRace = row.getItem();
                }
            });
            return row;
        });
    }

    private void loadRaceData() {
        List<modelrace> races = dbRace.readRaces();
        raceList.setAll(races);
        viewtbv.setItems(raceList);
        deltbv.setItems(raceList);
    }

    @FXML
    private void finishklik(ActionEvent event) {
        int id;
        try {
            id = Integer.parseInt(idtxt.getText());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please ensure the ID is a valid number.");
            return;
        }

        String raceName = racetxt.getText();
        String location = locbtn1.getSelectionModel().getSelectedItem();
        LocalDate raceDate = date.getValue();
        String raceType = typebox.getValue();
        int lapCount;
        BigDecimal trackLength;
        Time startTime;
        Time endTime;

        try {
            startTime = Time.valueOf(starttxt.getText());
            endTime = Time.valueOf(endtxt.getText());
        } catch (IllegalArgumentException e) {
            showAlert("Input Error", "Please enter valid times in HH:MM:SS format.");
            return;
        }

        try {
            lapCount = Integer.parseInt(laptxt.getText());
            trackLength = new BigDecimal(tracktxt.getText());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please ensure all fields are filled correctly.");
            return;
        }

        if (raceName.isEmpty() || location.isEmpty() || raceDate == null || raceType == null) {
            System.out.println(locbtn1.getSelectionModel().getSelectedItem());
            showAlert("Input Error", "Please ensure all fields are filled.");
            return;
        }

        modelrace newRace = new modelrace(id, raceName, location, raceType, Date.valueOf(raceDate), lapCount, trackLength, startTime, endTime);

        String imgPath = imgtxt1.getText();
        if (!imgPath.isEmpty()) {
            newRace.setImagePath(imgPath);
        }

        dbRace.createRace(newRace);

        loadRaceData();
        showAlert("Success", "Race has been successfully scheduled.");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void selupklik(ActionEvent event) {
        if (selectedRace == null) {
            showAlert("No Selection", "Please select a race to update.");
            return;
        }
        updatetab.getTabPane().getSelectionModel().select(updatetab);
        updatetab.setDisable(false);
//        updatetab.setVisible(true); 

        idtxt.setText(String.valueOf(selectedRace.getId())); // ID cannot be changed
        racetxt2.setText(selectedRace.getRaceName());
        locbtn2.getSelectionModel().select(selectedRace.getLocation());
        date2.setValue(selectedRace.getRaceDate().toLocalDate());
        typebox2.getSelectionModel().select(selectedRace.getRaceType());
        tracktxt2.setText(String.valueOf(selectedRace.getCircuitLengthKm()));
        laptxt2.setText(String.valueOf(selectedRace.getLapCount()));
        starttxt2.setText(selectedRace.getStartTime().toString());
        endtxt2.setText(selectedRace.getEndTime().toString());
    }

    @FXML
    private void schklik(ActionEvent event) {
        
        String[] raceNames = {"Grand Prix 1", "Speed Challenge", "Race Championship", "Moto Sprint", "Circuit Master"};
        String randomRaceName = raceNames[(int) (Math.random() * raceNames.length)];
        racetxt.setText(randomRaceName);

       
        String[] raceTypes = {"Main Race", "Qualification", "Practice"};
        String randomRaceType = raceTypes[(int) (Math.random() * raceTypes.length)];
        typebox.setValue(randomRaceType);

        LocalDate today = LocalDate.now();
        LocalDate eventDate;

        switch (randomRaceType) {
            case "Practice":
                int randomWeekday = 1 + (int) (Math.random() * 5);
                eventDate = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.of(randomWeekday)));
                break;

            case "Qualification":
                eventDate = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
                break;

            case "Main Race":
                eventDate = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + randomRaceType);
        }

        date.setValue(eventDate);

        String[] tracks = {
            "Bahrain International Circuit",
            "Jeddah Corniche Circuit",
            "Melbourne Grand Prix Circuit",
            "Azerbaijan Grand Prix Circuit",
            "Circuit de Barcelona-Catalunya",
            "Circuit de Monaco",
            "Circuit Gilles Villeneuve",
            "Silverstone Circuit",
            "Hungaroring",
            "Circuit de Spa-Francorchamps",
            "Zandvoort Circuit",
            "Monza Circuit",
            "Singapore Grand Prix",
            "Las Vegas Grand Prix",
            "Yas Marina Circuit"
        };

        
        int randomTrackIndex = (int) (Math.random() * tracks.length);
        String selectedTrack = tracks[randomTrackIndex];
        locbtn1.setValue(selectedTrack);

        
        boolean isNightRaceTrack = selectedTrack.equals("Bahrain International Circuit")
                || selectedTrack.equals("Jeddah Corniche Circuit")
                || selectedTrack.equals("Yas Marina Circuit");

       
        int randomStartHour;
        if (isNightRaceTrack) {
           
            randomStartHour = 18 + (int) (Math.random() * 4);
        } else {
            
            randomStartHour = 9 + (int) (Math.random() * 5); 
        }
        int randomEndHour = randomStartHour + 2 + (int) (Math.random() * 3);

        starttxt.setText(String.format("%02d:00:00", randomStartHour));
        endtxt.setText(String.format("%02d:00:00", randomEndHour));

        
        double[] trackLengths = {
            5.412, // Bahrain International Circuit
            6.174, // Jeddah Corniche Circuit
            5.278, // Melbourne Grand Prix Circuit
            6.003, // Azerbaijan Grand Prix Circuit
            4.675, // Circuit de Barcelona-Catalunya
            3.337, // Circuit de Monaco
            4.361, // Circuit Gilles Villeneuve
            5.891, // Silverstone Circuit
            4.381, // Hungaroring
            7.004, // Circuit de Spa-Francorchamps
            4.259, // Zandvoort Circuit
            5.793, // Monza Circuit
            5.063, // Singapore Grand Prix
            6.12, // Las Vegas Grand Prix (Estimated)
            5.281 // Yas Marina Circuit
        };

       
        int[] averageLaps = {
            57, // Bahrain International Circuit
            50, // Jeddah Corniche Circuit
            58, // Melbourne Grand Prix Circuit
            51, // Azerbaijan Grand Prix Circuit
            66, // Circuit de Barcelona-Catalunya
            78, // Circuit de Monaco
            70, // Circuit Gilles Villeneuve
            52, // Silverstone Circuit
            70, // Hungaroring
            44, // Circuit de Spa-Francorchamps
            72, // Zandvoort Circuit
            53, // Monza Circuit
            61, // Singapore Grand Prix
            50, // Las Vegas Grand Prix (Estimated)
            58 // Yas Marina Circuit
        };

      
        tracktxt.setText(String.valueOf(trackLengths[randomTrackIndex]));
        laptxt.setText(String.valueOf(averageLaps[randomTrackIndex]));

        instab.getTabPane().getSelectionModel().select(instab);

     
    }

    @FXML
    private void viewklik(Event event) {

    }

    @FXML
    private void type(ActionEvent event) {

    }

    @FXML
    private void fileklik(ActionEvent event) {

    }

    @FXML
    private void resetklik(ActionEvent event) {
        racetxt.clear();
        idtxt.clear();
        tracktxt.clear();
        laptxt.clear();
        starttxt.clear();
        endtxt.clear();
        date.setValue(null);
        typebox.setValue(null);
        imgtxt1.clear();
    }

    @FXML
    private void insklik(Event event) {

    }

    @FXML
    private void updateklik(ActionEvent event) {
        if (selectedRace == null) {
            showAlert("No Selection", "Please select a race to update.");
            return;
        }

        int id = selectedRace.getId();

        String raceName = racetxt2.getText();
        String location = locbtn2.getSelectionModel().getSelectedItem();
        LocalDate raceDate = date2.getValue();
        String raceType = typebox2.getValue();
        int lapCount;
        BigDecimal trackLength;
        Time startTime;
        Time endTime;

        try {
            startTime = Time.valueOf(starttxt2.getText());
            endTime = Time.valueOf(endtxt2.getText());
        } catch (IllegalArgumentException e) {
            showAlert("Input Error", "Please enter valid times in HH:MM:SS format.");
            return;
        }

        try {
            lapCount = Integer.parseInt(laptxt2.getText());
            trackLength = new BigDecimal(tracktxt2.getText());
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Please ensure all fields are filled correctly.");
            return;
        }

        if (raceName.isEmpty() || location.isEmpty() || raceDate == null || raceType == null) {
            showAlert("Input Error", "Please ensure all fields are filled.");
            return;
        }

        modelrace updatedRace = new modelrace(id, raceName, location, raceType, Date.valueOf(raceDate), lapCount, trackLength, startTime, endTime);

        String imgPath = imgtxt2.getText();
        if (!imgPath.isEmpty()) {
            updatedRace.setImagePath(imgPath);
        }

        dbRace.updateRace(updatedRace);

        loadRaceData();

        showAlert("Success", "Race has been successfully updated.");
    }

    @FXML
    private void updatetabklik(Event event) {
        // Implementasi untuk updatetab jika diperlukan
    }

    @FXML
    private void selectdelklik(MouseEvent event) {
        // Implementasi jika ada tindakan saat memilih di deltab
    }

    @FXML
    private void delklik(ActionEvent event) {
        if (selectedRace == null) {
            showAlert("No Selection", "Please select a race to delete.");
            return;
        }

      
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Delete Confirmation");
        confirmationAlert.setContentText("Are you sure you want to delete the selected race?");

        confirmationAlert.showAndWait().ifPresent(response -> {
            if (response.getText().equals("OK")) {
                dbRace.deleteRace(selectedRace.getId());
                loadRaceData();
                showAlert("Success", "Race has been successfully deleted.");
            }
        });
    }

    @FXML
    private void deltabklik(Event event) {
        // Implementasi jika ada tindakan saat deltab diklik
    }

    @FXML
    private void exitklik(ActionEvent event) {
        System.exit(0);
    }
}
