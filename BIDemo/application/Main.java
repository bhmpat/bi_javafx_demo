package application;
	
import java.util.Random;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends Application {
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 1920, 880);
			
			// --- Menu Section ---
			MenuBar menubar = new MenuBar();
			
			Menu filemenu = new Menu("File");
			Menu editmenu = new Menu("Edit");
			Menu viewmenu = new Menu("View");
			Menu reportmenu = new Menu("Report");
			Menu exportmenu = new Menu("Export");
			Menu helpmenu = new Menu("Help");
			
			menubar.getMenus().addAll(filemenu, editmenu, viewmenu, reportmenu, exportmenu, helpmenu);
			
			root.setTop(menubar);
			
			// --- Status Bar Section ---
			HBox hbox = new HBox();
			hbox.setPadding(new Insets(5, 5, 5, 5));
			hbox.setSpacing(10);
			
			Label helper = new Label("");
			helper.setMinWidth(5);
			Label recentData = new Label("Recent Data: ");
			recentData.setFont(Font.font("Arial", FontWeight.BOLD, 14));
			recentData.setMinWidth(120);
			Label countryLabel = new Label("Selected country: ");
			countryLabel.setMinWidth(120);
			Label countryLabelValue = new Label("Austria");
			countryLabelValue.setMinWidth(60);
			Label orderCount = new Label("Order Count: ");
			orderCount.setMinWidth(90);
			Label orderCountValue = new Label("78.597");
			orderCountValue.setMinWidth(55);
			Label monitoredProducts = new Label("Monitored Products: ");
			monitoredProducts.setMinWidth(140);
			Label monitoredProductsValue = new Label("4");
			monitoredProductsValue.setMinWidth(20);
			Label salesProfitRatio = new Label("Sales/Profit Ratio: ");
			salesProfitRatio.setMinWidth(120);
			Label salesProfitRatioValue = new Label("32%");
			salesProfitRatioValue.setMinWidth(140);
			Label invisiblespace = new Label("");
			invisiblespace.setPrefWidth(780.0);
			
			ProgressBar pbar = new ProgressBar(0.0);
			Label progress = new Label("Done");
			
			hbox.getChildren().addAll(helper, recentData, countryLabel, countryLabelValue, orderCount, orderCountValue, monitoredProducts, monitoredProductsValue,
					salesProfitRatio, salesProfitRatioValue, invisiblespace, pbar, progress);
			
			root.setBottom(hbox);
			
			// --- Left Country section ---
			VBox countryLayout = new VBox();
			
			countryLayout.setPadding(new Insets(5, 5, 5, 5));
			countryLayout.setSpacing(10);
			
			Label chooseCountry = new Label("Show data by country: ");
			
			ListView<String> countrySelect = new ListView<String>();
			
			ObservableList<String> countries = FXCollections.observableArrayList(
					"Germany", "France", "Austria", "Italy", "Spain"
					);
			
			countrySelect.setItems(countries);
			countrySelect.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			countrySelect.setMinHeight(500);
			countrySelect.setMaxHeight(500);
			countrySelect.setPrefHeight(500);
			
			countryLayout.getChildren().addAll(chooseCountry, countrySelect);
			
			countryLayout.setPrefWidth(170.0);
			countryLayout.setMinWidth(170.0);
			countryLayout.setMaxWidth(170.0);
			
			root.setLeft(countryLayout);
			
			// --- Right Graph section
			// Pie Chart
			VBox graphLayoutRight = new VBox();
			
			ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
					new PieChart.Data("Product A (30%)", 30),
					new PieChart.Data("Product B (20%)", 20),
					new PieChart.Data("Product C (40%)", 40),
					new PieChart.Data("Product D (10%)", 10)
					);
			
			PieChart pie = new PieChart(pieChartData);
			pie.setTitle("Product order share: ");
			
			Label details = new Label();
			details.setTextFill(Color.BLACK);
			details.setStyle("-fx-font: 15 arial;");
			
			for(final PieChart.Data data : pie.getData()) {
				data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
					details.setTranslateX(event.getSceneX());
					details.setTranslateY(event.getSceneY());
					details.setText(String.valueOf(data.getPieValue()) + "%");
				});
			}
			
			//Area Chart
			NumberAxis xAxis = new NumberAxis(2012, 2015, 1);
			NumberAxis yAxis = new NumberAxis(0, 20, 5);
			
			AreaChart<Number, Number> areaChart = new AreaChart<Number, Number>(xAxis, yAxis);
			areaChart.setTitle("Sales/Profit: ");
			xAxis.setLabel("years");
			yAxis.setLabel("in M €");
			xAxis.setForceZeroInRange(true);
			
			XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
			series1.setName("Sales");
			series1.getData().add(new XYChart.Data<>(2012, 10));
			series1.getData().add(new XYChart.Data<>(2013, 13));
			series1.getData().add(new XYChart.Data<>(2014, 16));
			series1.getData().add(new XYChart.Data<>(2015, 18));
			
			XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
			series2.setName("Profit");
			series2.getData().add(new XYChart.Data<>(2012, 3));
			series2.getData().add(new XYChart.Data<>(2013, 4));
			series2.getData().add(new XYChart.Data<>(2014, 5));
			series2.getData().add(new XYChart.Data<>(2015, 6));
			
			areaChart.getData().addAll(series1, series2);
			
			graphLayoutRight.getChildren().addAll(pie, areaChart);
			graphLayoutRight.setPrefWidth(600.0);
			graphLayoutRight.setMinWidth(600.0);
			graphLayoutRight.setMaxWidth(600.0);
			
			root.setRight(graphLayoutRight);
			
			// --- Center Heatmap section ---
			
			NumberAxis xAxisScatter = new NumberAxis(0, 50, 5);
			NumberAxis yAxisScatter = new NumberAxis(0, 25, 5);
			xAxisScatter.setTickLabelsVisible(false);
			yAxisScatter.setTickLabelsVisible(false);
			
			ScatterChart<Number, Number> scatterChart = new ScatterChart<Number, Number>(xAxisScatter, yAxisScatter);
			scatterChart.setTitle("Orders by location: ");
			xAxisScatter.setForceZeroInRange(true);
			
			XYChart.Series<Number, Number> series1Scatter = new XYChart.Series<>();
			series1Scatter.setName("Order");
			
			Random rnd = new Random();
			
			for(int i = 0; i < 350; i++) {
				series1Scatter.getData().add(new XYChart.Data<Number, Number>(rnd.nextDouble()*100, rnd.nextDouble()*50));
			}
			
			scatterChart.getData().addAll(series1Scatter);
			
			StackPane stackPane = new StackPane();
			
			Image backgroundmap = new Image(getClass().getResourceAsStream("austriamap.png"), 
					1100, 700, false, false);
			ImageView mapView = new ImageView(backgroundmap);
			
			stackPane.getChildren().addAll(mapView, scatterChart);
			stackPane.setPrefWidth(1150.0);
			stackPane.setMinWidth(1150.0);
			stackPane.setMaxWidth(1150.0);
			stackPane.setPrefHeight(800.0);
			stackPane.setMinHeight(800.0);
			stackPane.setMaxHeight(800.0);
			
			root.setCenter(stackPane);
			
			// --- Final settings and show scene
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Business Intelligence Demo by Patrick Böhm");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
