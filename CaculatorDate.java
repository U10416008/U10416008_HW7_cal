
//U10416008丁杰

import javafx.geometry.*;
import javafx.application.Application;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.time.*;
import java.time.temporal.*;
import java.util.*;

public class CaculatorDate extends Application {
	LocalDate getStart;
	LocalDate getEnd ;
	long betweenDay;
	long betweenYear;
	long betweenMonth;
	long betweenWeek;
	long betweenDay2;
	boolean minusMonth = false;
	boolean minusYear = false;
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a scene and place a button in the scene
		Label date = new Label("選取想要的日期計算");
		FlowPane grid = new FlowPane();
		//TextArea tex = new TextArea();
		ObservableList<String> items = FXCollections.observableArrayList(
			"計算兩個日期間的差距","對指定日期增加或減少天數");
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20,20,20,20));
		Button[] bts = new Button[28];
		
		//grid.add(tex,0,1,5,1);
		
		date.setStyle("-fx-stroke: #3278fa;-fx-text-fill: RED;-fx-font-size: 2.0em;");
		ChoiceBox cB = new ChoiceBox(items);
		cB.setStyle("-fx-background-color: PINK;");
		cB.setMaxWidth(Double.MAX_VALUE);
		
		DatePicker startDay = new DatePicker();
		startDay.setOnAction(e ->{getStart = startDay.getValue();});
		
		DatePicker endDay = new DatePicker();
		endDay.setOnAction(e ->{getEnd = endDay.getValue();});
		
		Button compute = new Button("計算");
		Label start = new Label("從");
		Label end = new Label("到");
		Label minus = new Label("差異年、月、週、日");
		TextField minusDate = new TextField();
		minusDate.setEditable(false);
		Label minus2 = new Label("差異天");
		TextField minusDay = new TextField();
		minusDay.setEditable(false);
		HBox hb = new HBox();
		hb.getChildren().addAll(start, startDay, end, endDay);
		hb.setSpacing(50);
		VBox vb = new VBox();
		vb.getChildren().addAll(date, cB, hb, minus, minusDate, minus2 ,minusDay,compute);
        vb.setSpacing(30);
        vb.setPadding(new Insets(10, 0, 0, 10));
		vb.setMargin(compute , new Insets(0,0,0,440));
		compute.setPrefSize(100,20);
		compute.setOnAction(e ->{
			betweenDay = ChronoUnit.DAYS.between(getStart, getEnd);
			if(getEnd.getDayOfMonth() >= getStart.getDayOfMonth()){
				betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth();
			}else{
				minusMonth = true ;
				//System.out.println(getEnd.getDayOfMonth());
				switch(getEnd.getMonthValue()){
					case 1 : betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 31;break;
					case 2 : betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 31;break;
					case 3 :
						if(getEnd.isLeapYear() == true){
							betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 29;
						}else{
							betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 28;
						}
						break;
					case 4 : betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 31;break;
					case 5 : betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 30;break;
					case 6 : betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 31;break;
					case 7 : betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 30;break;
					case 8 : betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 31;break;
					case 9 : betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 31;break;
					case 10 : betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 30;break;
					case 11 : betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 31;break;
					case 12 : betweenDay2 = getEnd.getDayOfMonth() - getStart.getDayOfMonth() + 30;
				}
			}
			betweenWeek = betweenDay2 / 7;
			betweenDay2 = betweenDay2 % 7 ;
			
			if(minusMonth == true){
				if(getEnd.getMonthValue() - 1 >= getStart.getMonthValue()){
					betweenMonth = getEnd.getMonthValue() - 1 - getStart.getMonthValue();
				}else{
					betweenMonth = getEnd.getMonthValue() - 1 - getStart.getMonthValue() + 12;
					minusYear = true ;
				}
				minusMonth = false ; 
			}else{
				if(getEnd.getMonthValue() >= getStart.getMonthValue()){
					betweenMonth = getEnd.getMonthValue() - getStart.getMonthValue();
				}else{
					betweenMonth = getEnd.getMonthValue() - getStart.getMonthValue() + 12;
					minusYear = true ;
				}
			}
			if(minusYear == true){
				betweenYear = getEnd.getYear() - 1 - getStart.getYear();
				minusYear = false;
			}else{
				betweenYear = getEnd.getYear() - getStart.getYear();
			}
			minusDate.setText(String.valueOf(betweenDay2) + "天，" + String.valueOf(betweenWeek) + "週，" + String.valueOf(betweenMonth) + "月，" + String.valueOf(betweenYear) + "年");
			
			minusDay.setText(String.valueOf(betweenDay)+"天");
		});
		
		grid.getChildren().addAll(vb);
		
		
		grid.setStyle(
			"-fx-text-fill: #FFFFFF; -fx-border-color: red; -fx-background-color: WHITE;");
    
		Scene scene = new Scene(grid, 600, 500);
		primaryStage.setTitle("NodeStyleRotateDemo"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
  
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
