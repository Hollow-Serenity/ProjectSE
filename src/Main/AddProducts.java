package Main;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddProducts {

	public static Stage Window;
	Database db = new Database();
	@SuppressWarnings("unchecked")
	public void AddProduct() {

		ObservableList<Inventory> DataList = FXCollections.observableArrayList();
		TableView<Inventory> ProductTable = new TableView<Inventory>(DataList);
		
		TableColumn<Inventory, Integer> LiveStockID = new TableColumn<Inventory, Integer>("Live Stock ID");
		TableColumn<Inventory, Integer>  Quantity = new TableColumn<Inventory, Integer>("Quantity");
		TableColumn<Inventory, String>  DateAdded = new TableColumn<Inventory, String>("Date Added");
		TableColumn<Inventory, String>  Details = new TableColumn<Inventory, String>("Details");
		
		LiveStockID.setCellValueFactory(new PropertyValueFactory<>("LiveStockID"));
		Quantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
		DateAdded.setCellValueFactory(new PropertyValueFactory<>("DateAdded"));
		Details.setCellValueFactory(new PropertyValueFactory<>("Details"));
	    
	    try {
			db.prestatement = db.Connect.prepareStatement("SELECT * FROM Inventory WHERE Email = ?");
			db.prestatement.setString(1, Login.StoreUName);
			db.resultSet =  db.prestatement.executeQuery();
			while (db.resultSet.next()) {
				DataList.add(new Inventory(
						db.resultSet.getInt(1), 
						db.resultSet.getInt(2),
						db.resultSet.getString(3),  
						db.resultSet.getString(4)
				));
			}
		} catch (SQLException e1) {
			System.out.println("Error while fetching data from Products!");
		}
				
		ProductTable.getColumns().addAll(LiveStockID, Quantity, DateAdded, Details);
		
		VBox TableVB = new VBox();
		TableVB.getChildren().add(ProductTable);
		TableVB.setMinWidth(320);		
			
		HBox ButtonB = new HBox();
		
		Button New = new Button("New");
		New.setOnAction(e->NewProduct());
		
		Button Edit = new Button("Edit");
		Edit.setOnAction(e->UpdateProduct(ProductTable.getSelectionModel().getSelectedItem()));
		
		Button Delete = new Button("Delete");
		Delete.setOnAction(e->DeleteProduct(ProductTable.getSelectionModel().getSelectedItem()));
				
		ButtonB.getChildren().addAll(New, Edit, Delete);
		ButtonB. setSpacing(10);		
							
		VBox Center = new VBox();
	    Center.getStyleClass().add("hbox");
	    Center.getChildren().addAll(TableVB, ButtonB);
	    Center.setMaxHeight(800);
	    Center.setMaxWidth(1000);
		Center.setSpacing(20);
	    
		Login.Layout.setCenter(Center);
	}
	public void NewProduct() {	
		Stage DialogStage = new Stage();
		Label QuantityLabel = new Label("Quantity");
		Label DateLabel = new Label("Date Added");
		Label DetailsLabel = new Label("Details");
		
		TextField QuantityField = new TextField();
		TextField DateField = new TextField();
		TextField DetailsField = new TextField();

		DateField.setPromptText("dd-mm-yyyy");
		
		VBox DialogNewProduct = new VBox();
		DialogNewProduct.getChildren().addAll(
				QuantityLabel, QuantityField,
				DateLabel, DateField,
				DetailsLabel, DetailsField
		);
		
		HBox ButtonBox = new HBox();
				
		Button EnterBtn = new Button("Enter");
		EnterBtn.setOnAction(e->{			
			try {
				db.prestatement = db. Connect.prepareStatement("INSERT INTO Inventory(Quantity, DateAdded, Details, Email) VALUES(?,?,?,?)");
				db.prestatement.setString(1, QuantityField.getText());
				db.prestatement.setString(2, DateField.getText());
				db.prestatement.setString(3, DetailsField.getText());
				db.prestatement.setString(4, Login.StoreUName);
				db.prestatement.executeUpdate();
				DialogStage.hide();
				AddProduct();
			} catch (SQLException e1) {
				System.out.println(e1.getMessage());
			}			
		});
		
		Button CancelBtn = new Button("Cancel");
		CancelBtn.setOnAction(e->DialogStage.hide());
		
		ButtonBox.getChildren().addAll(EnterBtn, CancelBtn);		
		ButtonBox.setSpacing(10);
				
		VBox Center = new VBox();
	    Center.getStyleClass().add("hbox");
	    Center.getChildren().addAll(DialogNewProduct, ButtonBox);
	    Center.setMaxHeight(500);
	    Center.setMaxWidth(500);
		Center.setSpacing(20);
		
		DialogStage.setResizable(false);
		Scene DialogScn = new Scene(Center, 700, 600);
		DialogScn.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());
		DialogStage.setScene(DialogScn);
				
		DialogStage.setTitle("Add New Inventory");
		DialogStage.show();
	}	
	
	public void UpdateProduct(Inventory P) {
		if(P != null) {
			Stage DialogStage = new Stage();
			Label QuantityLabel = new Label("Quantity");
			Label DateLabel = new Label("Date Added");
			Label DetailsLabel = new Label("Details");

			TextField QuantityField = new TextField();
			TextField DateField = new TextField();
			TextField DetailsField = new TextField();

			DateField.setPromptText("dd-mm-yyyy");

			QuantityField.setText(P.getQuantity() + "");
			DateField.setText(P.getDateAdded());
			DetailsField.setText(P.getDetails());
			
			VBox DialogNewProduct = new VBox();
			DialogNewProduct.getChildren().addAll(
					QuantityLabel, QuantityField,
					DateLabel, DateField,
					DetailsLabel, DetailsField
			);
			
			HBox ButtonBox = new HBox();
					
			Button UpdateBtn = new Button("Update");
			UpdateBtn.setOnAction(e->{			
				try {
					db.prestatement = db.Connect.prepareStatement(""
							+ "UPDATE Inventory SET Quantity=?,"
							+ "DateAdded=?, Details=? WHERE LiveStockID = ?");
					db.prestatement.setString(1, QuantityField.getText());
					db.prestatement.setString(2, DateField.getText());
					db.prestatement.setString(3, DetailsField.getText());
					db.prestatement.setInt(4, P.LiveStockID);
					db.prestatement.executeUpdate();
					DialogStage.hide();
					AddProduct();
				} catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}			
			});
			
			Button CancelBtn = new Button("Cancel");
			CancelBtn.setOnAction(e->DialogStage.hide());
			
			ButtonBox.getChildren().addAll(UpdateBtn, CancelBtn);		
			ButtonBox.setSpacing(10);
					
			VBox Center = new VBox();
			Center.getStyleClass().add("hbox");
			Center.getChildren().addAll(DialogNewProduct, ButtonBox);
			Center.setMaxHeight(500);
			Center.setMaxWidth(500);
			Center.setSpacing(20);
			
			DialogStage.setResizable(false);
			Scene DialogScn = new Scene(Center, 700, 600);
			DialogScn.getStylesheets().add(getClass().getResource("../css/application.css").toExternalForm());
			DialogStage.setScene(DialogScn);
					
			DialogStage.setTitle("Add New Inventory");
			DialogStage.show();			
		}
		else
			AddProduct();
	}
	
	public void DeleteProduct(Inventory P) {	
		if(P != null) {
			try {
				db.prestatement = db.Connect.prepareStatement("DELETE FROM Inventory WHERE LiveStockID = ?");
				db.prestatement.setInt(1, P.LiveStockID);
				db.prestatement.executeUpdate();
				AddProduct();
			} catch (SQLException e1) {
				System.out.println("Error while deleteing data from Inventory!");
			}			
		}
		else
			AddProduct();
	}
	public class Inventory{
		private int LiveStockID;
		private int Quantity;
		private String DateAdded;
		private String Details;
		public Inventory(int liveStockID, int quantity, String dateAdded, String details){
			LiveStockID = liveStockID;
			Quantity = quantity;
			DateAdded = dateAdded;
			Details = details;
		}
		public int getLiveStockID(){
			return LiveStockID;
		}
		public int getQuantity(){
			return Quantity;
		}
		public String getDateAdded(){
			return DateAdded;
		}
		public String getDetails(){
			return Details;
		}
	};

}

