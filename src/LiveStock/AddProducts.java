package LiveStock;

import Main.Database;
import Main.Menu;
import UserManagement.Login;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.String.valueOf;

public class AddProducts {
    private static BorderPane Layout;

    private static Connection Connect = Database.getConnection();
    private static PreparedStatement prestatement = Database.getPrestatement();
    private static ResultSet resultSet = Database.getResultSet();

    public static VBox createCenter(int spacing, int height, int width) {
        VBox Center = new VBox(spacing);
        Center.getStyleClass().add("hbox");
        Center.setMaxHeight(height);
        Center.setMaxWidth(width);
        return Center;
    }

    @SuppressWarnings("unchecked")
    public void AddProduct(BorderPane layout) {
        this.Layout = layout;

        ObservableList<LiveStock> DataList = FXCollections.observableArrayList();
        TableView<LiveStock> ProductTable = new TableView<LiveStock>(DataList);

        TableColumn<LiveStock, Integer> idlive_stock = new TableColumn<LiveStock, Integer>("Live Stock ID");
        TableColumn<LiveStock, Integer> Quantity = new TableColumn<LiveStock, Integer>("Quantity");
        TableColumn<LiveStock, String> DateAdded = new TableColumn<LiveStock, String>("Date Added");
        TableColumn<LiveStock, String> Details = new TableColumn<LiveStock, String>("Details");
        TableColumn<LiveStock, Integer> Weight = new TableColumn<LiveStock, Integer>("Weight");
        TableColumn<LiveStock, Double> Price = new TableColumn<LiveStock, Double>("Price");

        idlive_stock.setCellValueFactory(new PropertyValueFactory<>("idlive_stock"));
        Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        DateAdded.setCellValueFactory(new PropertyValueFactory<>("DateAdded"));
        Details.setCellValueFactory(new PropertyValueFactory<>("Details"));
        Weight.setCellValueFactory(new PropertyValueFactory<>("Weight"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));

        try {
            prestatement = Connect.prepareStatement("SELECT * FROM livestock WHERE owner = ?");
            prestatement.setString(1, Menu.getUName());
            resultSet = prestatement.executeQuery();
            while (resultSet.next()) {
                DataList.add(new LiveStock(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5),
                        resultSet.getDouble(6)
                ));
            }
        } catch (SQLException e1) {
            System.out.println("Error while fetching data from Products!");
        }

        ProductTable.getColumns().addAll(idlive_stock, Quantity, DateAdded, Details, Weight, Price);

        VBox TableVB = new VBox();
        TableVB.getChildren().add(ProductTable);
        TableVB.setMinWidth(320);

        HBox ButtonB = new HBox();

        Button New = new Button("New");
        New.setOnAction(e -> NewProduct());

        Button Edit = new Button("Edit");
        Edit.setOnAction(e -> UpdateProduct(ProductTable.getSelectionModel().getSelectedItem()));

        Button Delete = new Button("Delete");
        Delete.setOnAction(e -> DeleteProduct(ProductTable.getSelectionModel().getSelectedItem()));

        ButtonB.getChildren().addAll(New, Edit, Delete);
        ButtonB.setSpacing(10);

        VBox Center = createCenter(20, 800, 1000);
        Center.getChildren().addAll(TableVB, ButtonB);

        Layout.setCenter(Center);
    }

    public void NewProduct() {
        Stage DialogStage = new Stage();
        Label QuantityLabel = new Label("Quantity");
        Label DateLabel = new Label("Date Added");
        Label DetailsLabel = new Label("Details");
        Label WeightLabel = new Label("Weight");
        Label PriceLabel = new Label("Price");

        TextField QuantityField = new TextField();
        TextField DateField = new TextField();
        TextField WeightField = new TextField();
        TextField PriceField = new TextField();
        TextField DetailsField = new TextField();

        DateField.setPromptText("dd-mm-yyyy");

        VBox DialogNewProduct = new VBox();
        DialogNewProduct.getChildren().addAll(
                QuantityLabel, QuantityField,
                DateLabel, DateField,
                DetailsLabel, DetailsField,
                WeightLabel, WeightField,
                PriceLabel, PriceField
        );

        HBox ButtonBox = new HBox();

        Button EnterBtn = new Button("Enter");
        EnterBtn.setOnAction(e -> {
            try {
                prestatement = Connect.prepareStatement("INSERT INTO LiveStock(quantity, dateAdded, details, weight, price, owner) VALUES(?,?,?,?,?,?)");
                prestatement.setString(1, QuantityField.getText());
                prestatement.setString(2, DateField.getText());
                prestatement.setString(3, DetailsField.getText());
                prestatement.setString(4, WeightField.getText());
                prestatement.setString(5, PriceField.getText());
                prestatement.setString(6, Menu.getUName());
                prestatement.executeUpdate();
                DialogStage.hide();
                AddProduct(Layout);
            } catch (SQLException e1) {
                System.out.println(e1.getMessage());
            }
        });

        Button CancelBtn = new Button("Cancel");
        CancelBtn.setOnAction(e -> DialogStage.hide());

        ButtonBox.getChildren().addAll(EnterBtn, CancelBtn);
        ButtonBox.setSpacing(10);

        VBox Center = createCenter(20, 500, 500);
        Center.getChildren().addAll(DialogNewProduct, ButtonBox);

        DialogStage.setResizable(false);
        Scene DialogScn = new Scene(Center, 700, 600);
        DialogScn.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());
        DialogStage.setScene(DialogScn);

        DialogStage.setTitle("Add New LiveStock");
        DialogStage.show();
    }

    public void UpdateProduct(LiveStock P) {
        if (P != null) {
            Stage DialogStage = new Stage();
            Label QuantityLabel = new Label("Quantity");
            Label DateLabel = new Label("Date Added");
            Label DetailsLabel = new Label("Details");
            Label WeightLabel = new Label("Weight");
            Label PriceLabel = new Label("Price");

            TextField QuantityField = new TextField();
            TextField DateField = new TextField();
            TextField DetailsField = new TextField();
            TextField WeightField = new TextField();
            TextField PriceField = new TextField();

            DateField.setPromptText("dd-mm-yyyy");

            QuantityField.setText(P.getQuantity() + "");
            DateField.setText(P.getDateAdded());
            DetailsField.setText(P.getDetails());
            WeightField.setText(valueOf(P.getWeight()));
            PriceField.setText(valueOf(P.getPrice()));

            VBox DialogNewProduct = new VBox();
            DialogNewProduct.getChildren().addAll(
                    QuantityLabel, QuantityField,
                    DateLabel, DateField,
                    DetailsLabel, DetailsField,
                    WeightLabel, WeightField,
                    PriceLabel, PriceField
            );

            HBox ButtonBox = new HBox();

            Button UpdateBtn = new Button("Update");
            UpdateBtn.setOnAction(e -> {
                try {
                    prestatement = Connect.prepareStatement(""
                            + "UPDATE LiveStock SET Quantity=?,"
                            + "dateAdded=?, Details=?, Weight=?, Price=? WHERE idlive_stock = ?");
                    prestatement.setString(1, QuantityField.getText());
                    prestatement.setString(2, DateField.getText());
                    prestatement.setString(3, DetailsField.getText());
                    prestatement.setString(4, WeightField.getText());
                    prestatement.setString(5, PriceField.getText());
                    prestatement.setInt(6, P.idlive_stock);
                    prestatement.executeUpdate();
                    DialogStage.hide();
                    AddProduct(Layout);
                } catch (SQLException e1) {
                    System.out.println(e1.getMessage());
                }
            });

            Button CancelBtn = new Button("Cancel");
            CancelBtn.setOnAction(e -> DialogStage.hide());

            ButtonBox.getChildren().addAll(UpdateBtn, CancelBtn);
            ButtonBox.setSpacing(10);

            VBox Center = createCenter(20, 500, 500);
            Center.getChildren().addAll(DialogNewProduct, ButtonBox);

            DialogStage.setResizable(false);
            Scene DialogScn = new Scene(Center, 700, 600);
            DialogScn.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());
            DialogStage.setScene(DialogScn);

            DialogStage.setTitle("Add New LiveStock");
            DialogStage.show();
        } else {
            AddProduct(Layout);
        }
    }

    public void DeleteProduct(LiveStock P) {
        if (P != null) {
            try {
                prestatement = Connect.prepareStatement("DELETE FROM LiveStock WHERE idlive_stock = ?");
                prestatement.setInt(1, P.idlive_stock);
                prestatement.executeUpdate();
                AddProduct(Layout);
            } catch (SQLException e1) {
                System.out.println("Error while deleteing data from LiveStock!");
            }
        } else {
            AddProduct(Layout);
        }
    }

    public static class LiveStock {

        private int idlive_stock;
        private int Quantity;
        private String DateAdded;
        private String Details;
        private Integer Weight;
        private Double Price;

        public LiveStock(int liveStockID, int quantity, String dateAdded, String details, Integer weight, Double price) {
            idlive_stock = liveStockID;
            Quantity = quantity;
            DateAdded = dateAdded;
            Details = details;
            Weight = weight;
            Price = price;
        }

        public int getidlive_stock() {
            return idlive_stock;
        }

        public int getQuantity() {
            return Quantity;
        }

        public String getDateAdded() {
            return DateAdded;
        }

        public String getDetails() {
            return Details;
        }

        public Integer getWeight() {
            return Weight;
        }

        public Double getPrice() {
            return Price;
        }

    }

    ;

}
