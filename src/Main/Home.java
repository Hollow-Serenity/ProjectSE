package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Home {
    HBox tableVBox = new HBox();
    @SuppressWarnings("static-access")
    public void Homes() throws SQLException {
        Button InventoryBtn = new Button("Add Inventory");
        Button EditUser = new Button("Edit Profile");
        Button LogoutBtn = new Button("Logout");
        Button MarketBtn = new Button("Market");
        Button UserAddBtn = new Button("Add User");


        Button MedicalPlatformbtn = new Button("Medical Platform");
        Button EduPlatformbtn = new Button("Eduplatform");

        InventoryBtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image APImg = new Image(getClass().getResourceAsStream("../Images/AddProduct.png"));
        ImageView APIV = new ImageView(APImg);
        APIV.setFitWidth(50);
        APIV.setFitHeight(50);
        InventoryBtn.setGraphic(APIV);
        InventoryBtn.setTooltip(new Tooltip("Add Products"));
        AddProducts ap = new AddProducts();
        InventoryBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ap.AddProduct();
            }
        });




        EditUser.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image AUImg = new Image(getClass().getResourceAsStream("../Images/AddUser.png"));
        ImageView AUIV = new ImageView(AUImg);
        AUIV.setFitWidth(50);
        AUIV.setFitHeight(50);

        EditUser.setGraphic(AUIV);
        EditUser.setTooltip(new Tooltip("Edit User"));
        EditUser.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    new Registration();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        LogoutBtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image LgImg = new Image(getClass().getResourceAsStream("../Images/Logout.png"));
        ImageView LgIV = new ImageView(LgImg);
        LgIV.setFitWidth(50);
        LgIV.setFitHeight(50);
        LogoutBtn.setGraphic(LgIV);
        LogoutBtn.setTooltip(new Tooltip("Logout"));
        LogoutBtn.setOnAction(e -> {
            Login d = new Login();
            Login.setUName(null);
            Login.setIsLogin(false);
            Login.setIsDoctor(false);
            d.login();
        });

        //Market Button
        MarketBtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image MrImg = new Image(getClass().getResourceAsStream("../Images/AddProduct.png"));
        ImageView MrIV = new ImageView(MrImg);
        MrIV.setFitWidth(50);
        MrIV.setFitHeight(50);
        MarketBtn.setGraphic(MrIV);
        MarketBtn.setTooltip(new Tooltip("Market"));
        Market mr = new Market();
        MarketBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mr.Market();
            }
        });

        //Medical_Platform
        MedicalPlatformbtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        MedicalPlatformbtn.setOnAction(e -> {
            try {
                new Medical_Platform();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        //Edu_platform
        EduPlatformbtn.getStyleClass().addAll("HomeBtn", "LightGreen");

        UserAddBtn.getStyleClass().addAll("HomeBtn", "LightGreen");
        Image UAImg = new Image(getClass().getResourceAsStream("../Images/AddUser.png"));
        ImageView UAIV = new ImageView(UAImg);
        UAIV.setFitWidth(50);
        UAIV.setFitHeight(50);
        UserAddBtn.setGraphic(UAIV);
        UserAddBtn.setTooltip(new Tooltip("Add User"));
        UserAddBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                try {
                    new ContactTypeGui();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        HBox RowOne = new HBox();
        HBox RowTwo = new HBox();
        HBox RowThree = new HBox();
        HBox RowFour = new HBox();
        RowOne.setSpacing(20);
        RowTwo.setSpacing(20);
        RowThree.setSpacing(20);
        RowFour.setSpacing(20);
        if(Login.getIsDoctor()) {
            RowOne.getChildren().addAll(InventoryBtn, MarketBtn);
            RowTwo.getChildren().addAll(EditUser, UserAddBtn);
            RowThree.getChildren().addAll(MedicalPlatformbtn, EduPlatformbtn);
            RowFour.getChildren().addAll(LogoutBtn);
        }else {
            RowOne.getChildren().addAll(InventoryBtn, MarketBtn);
            RowTwo.getChildren().addAll(EditUser, UserAddBtn);
            RowThree.getChildren().addAll(MedicalPlatformbtn, EduPlatformbtn);
            RowFour.getChildren().addAll(LogoutBtn);
        }

        RowOne.setAlignment(Pos.CENTER);
        RowTwo.setAlignment(Pos.CENTER);
        RowThree.setAlignment(Pos.CENTER);
        RowFour.setAlignment(Pos.CENTER);

        showReservedSlots();

        tableVBox.setAlignment(Pos.CENTER);
//        tableVBox.setPrefWidth(100);
        VBox Center = new VBox();
        //Center.setAlignment(Pos.CENTER);
        if(Login.getIsDoctor()) {
            Center.getChildren().addAll(RowOne, RowTwo, RowThree, RowFour, tableVBox);
        }else {
            Center.getChildren().addAll(RowOne, RowTwo, RowThree, RowFour);
        }
        Center.getStyleClass().addAll("HomeVBox");
        Center.setMaxWidth(screenBounds.getWidth() * 0.95);
        Center.setMaxHeight(screenBounds.getHeight() * 0.75);
        Center.setSpacing(20);

        Menu m = new Menu();
        Login.getLayout().setTop(m.Menu());
        Login.getLayout().setCenter(Center);


    }

    public void showReservedSlots() {
        Stage stage = new Stage();
        ObservableList<Appointment> DataList = FXCollections.observableArrayList();
        TableView<Appointment> AppointmentTable = new TableView<Appointment>(DataList);

        TableColumn<Appointment, Integer> srNo = new TableColumn<Appointment, Integer>("Sr.#");
        TableColumn<Appointment, String> date = new TableColumn<Appointment, String>("Date");
        TableColumn<Appointment, Time> sTime = new TableColumn<Appointment, Time>("Start Time");
        TableColumn<Appointment, Time> eTime = new TableColumn<Appointment, Time>("End Time");
        TableColumn<Appointment, String> pName = new TableColumn<Appointment, String>("Patient Name");

        srNo.setCellValueFactory(new PropertyValueFactory<>("appCount"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        sTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        eTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        pName.setCellValueFactory(new PropertyValueFactory<>("patientName"));




        try {
            Database db = new Database();
            String query = "SELECT appointment.date, appointment.TIME, appointment.TIME + INTERVAL 45 MINUTE AS endTime, users.firstName, users.lastName FROM appointment LEFT JOIN users ON users.userName = appointment.patientId WHERE doctorName = ? ORDER BY DATE DESC, time DESC LIMIT 3";
            db.prestatement = db.Connect.prepareStatement(query);
            db.prestatement.setString(1, Login.getUName());

            db.resultSet = db.prestatement.executeQuery();
            int count = 1;
            while (db.resultSet.next()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Appointment app = new Appointment();
                app.setAppCount(count);

                Date dd = db.resultSet.getDate(1);
                String appDate = formatter.format(dd);
                app.setDate(appDate);
                app.setTime(db.resultSet.getTime(2));
                app.setEndTime(db.resultSet.getTime(3));


                String firstName = db.resultSet.getString(4);
                String lastName = db.resultSet.getString(5);

                String patientName = firstName + " " + lastName;
                app.setPatientName(patientName);

                DataList.add(app);
                count++;
            }
        } catch (Exception e1) {
            System.out.println("Error while fetching data from Appointment!");
            e1.printStackTrace();
        }

        AppointmentTable.getColumns().addAll(srNo, pName, date, sTime, eTime);

        tableVBox.getChildren().add(AppointmentTable);

//        tableVBox.setMinWidth(100);

        HBox ButtonBox = new HBox();
    }
}