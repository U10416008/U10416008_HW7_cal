//U10416008丁杰

import javafx.geometry.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.event.*;
import java.math.BigDecimal;
import java.util.*;

public class Caculator extends Application {
	BigDecimal compute1;
	BigDecimal compute2;
	GridPane grid = new GridPane();
	TextArea tex = new TextArea();
	TextArea process = new TextArea();
	MenuBar menuBar = new MenuBar();
	Menu menuView = new Menu("檢視(V)");
	Menu menuEdit = new Menu("編輯(E)");
	Menu menu = new Menu("說明(H)");
	Menu menuJob = new Menu("工作表(W)");
	Menu menuHistory = new Menu("歷程記錄(H)");
	MenuItem[] items = new MenuItem[10];
	MenuItem[] itemsJ = new MenuItem[4];
	MenuItem[] itemsE = new MenuItem[4];
	MenuItem[] itemsH = new MenuItem[4];
	MenuItem[] itemsT = new MenuItem[4];
	Button[] bts = new Button[28];
	
	boolean quotient = false ;
	boolean cross = false ;
	boolean add = false ;
	boolean minus = false ;
	boolean remainder = false;
	boolean sqrt = false; 	
	boolean inverseNumber = false;
	boolean result = false;
	boolean isMR = false ;
	boolean mAdd = false;
	boolean mMinus = false;
	double number1 = 0 ;
	double number2 = 0 ;
	double mR = 0;
	double mRAdd = 0;
	double mRMinus = 0;
	ArrayList<Double> m = new ArrayList<>() ;
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		// Create a scene and place a button in the scene
		
		String[] bt  = {"MC","MR","MS","M+","M-","←","CE","C","±","√","7","8","9","/","%","4","5","6","*","1/x","1","2","3","-","=","0",".","+"};
		
		menuBar.getMenus().addAll(menuView, menuEdit, menu);
		grid.add(menuBar,0,0,5,1);
		
		String[] menuV = {"標準型(T)　　　　　 Alt+1", "工程型(S)　　　　     Alt+2", "程式設計師(P)　　　 Alt+3", "統計資料(A)　　　　 Alt+4", "歷程記錄(Y)　　　　Ctrl+H", 
		"數字分位(I)", "基本(B)　　　　　   Ctrl+F4", "單位轉換(U)　　　　Ctrl+U", "日期計算(D)　　　　Ctrl+E"};
		String[] menuJ = {"貸款(M)","汽車租賃(V)","平均油耗(公里/公升)(F)","平均油耗(公升/100公里)(U)"};
		String[] menuE = {"複製(C)　　　Ctrl+C","貼上(V)　　　Ctrl+V"};
		String[] menuH = {"複製歷程記錄(I)","編輯(E)　　　　　　　   F2","取消編輯(N)　　　　　Esc","清除(L)　　　Ctrl+Shift+D"};
		String[] menuT = {"檢視說明(V)　　　　F1","關於小算盤(A)"};
		for(int i = 0 ; i <=8 ; i++){
			items[i] = new MenuItem(menuV[i]);
			menuView.getItems().addAll(items[i]);
		}
		for(int i = 0 ; i <=3 ; i++){
			itemsJ[i] = new MenuItem(menuJ[i]);
			menuJob.getItems().addAll(itemsJ[i]);
		}
		menuView.getItems().addAll(menuJob);
		for(int i = 0 ; i <=1 ; i++){
			itemsE[i] = new MenuItem(menuE[i]);
			menuEdit.getItems().addAll(itemsE[i]);
		}
		for(int i = 0 ; i <=3 ; i++){
			itemsH[i] = new MenuItem(menuH[i]);
			menuHistory.getItems().addAll(itemsH[i]);
		}
		menuEdit.getItems().addAll(menuHistory);
		for(int i = 0 ; i <=1 ; i++){
			itemsT[i] = new MenuItem(menuT[i]);
			menu.getItems().addAll(itemsT[i]);
		}
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20,20,20,20));
		
   
		for(int i = 0 ; i <=27 ; i++){
        bts[i] = new Button(bt[i]);
		bts[i].setPrefSize(60,70);
		
		}
		for(int i = 0 ;i <= 4 ; i++){
			grid.add(bts[i],i,3);
			
		}
		for(int i = 0 ;i <= 4 ; i++){
			grid.add(bts[5+i],i,4);
		}
		for(int i = 0 ;i <= 4 ; i++){
			grid.add(bts[10+i],i,5);
		}
		for(int i = 0 ;i <= 4 ; i++){
			grid.add(bts[15+i],i,6);
		}
		for(int i = 0 ;i <= 3 ; i++){
			grid.add(bts[20+i],i,7);
		}
		
		bts[24].setPrefSize(60,130);
		grid.add(bts[24],4,7,1,2);
		bts[25].setPrefSize(130,70);
		grid.add(bts[25],0,8,2,1);
		
		grid.add(bts[26],2,8);
		grid.add(bts[27],3,8);
		process.setPrefSize(340 ,40 );
		grid.add(process,0,1,5,1);
		tex.setEditable(false);
		tex.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
		tex.setPrefSize(340 ,120 );
		grid.add(tex,0,2,5,1);
		//items[0].setOnAction(e ->{});
		//items[1].setOnAction(e ->{});
		//items[2].setOnAction(e ->{});
		//items[3].setOnAction(e ->{});
		//items[4].setOnAction(e ->{});
		//items[5].setOnAction(e ->{});
		//items[6].setOnAction(e ->{});
		items[7].setOnAction(e ->{
			
		});
		items[8].setOnAction(e ->{
			CaculatorDate cD = new CaculatorDate();
			Stage secondStage = new Stage();
			cD.start(secondStage);
		});
		//MC
		bts[0].setOnAction(e -> {
			mR = 0;
			while(m.size() >= 1){
				m.remove(0);
			}
			tex.setText("0");
			isMR = false;
			mAdd = false;
			mMinus = false;
		});
		//MR
		bts[1].setOnAction(e -> {
			if(isMR == false){
				mR = Double.parseDouble(tex.getText()) ;
				tex.setText("0");
				isMR = true;
			}
		});
		//MS
		bts[2].setOnAction(e -> {
			mR = Double.parseDouble(tex.getText()) ;
			tex.setText("0");
			isMR = true;
		});
		//M+
		bts[3].setOnAction(e -> {
			m.add(Double.parseDouble(tex.getText()));
			mAdd = true;
			mMinus = false;
			cross = false ;
			add = false ;
			minus = false ;
			remainder = false; 
			quotient = false ;	
			sqrt = false;
		});
		//M-
		bts[4].setOnAction(e -> {
			m.add(Double.parseDouble(tex.getText()));
			mAdd = false;
			mMinus = true;
			cross = false ;
			add = false ;
			minus = false ;
			remainder = false; 
			quotient = false ;	
			sqrt = false;
		});
		//←
		bts[5].setOnAction(e -> {
			tex.selectBackward();
			tex.cut();
		});
		//CE
		bts[6].setOnAction(e -> {
			tex.setText("0");
		});
		//C
		bts[7].setOnAction(e -> {
			cross = false ;
			add = false ;
			minus = false ;
			remainder = false; 
			quotient = false ;
			sqrt = false;
			mR = 0;
			number1 = 0;
			number2 = 0;
			tex.setText("0");
		});
		//±
		bts[8].setOnAction(e -> {
			if(tex.getText(0,1).equals("-")){
				tex.replaceText(0,1,"");
			}else{
				tex.insertText(0,"-");
			}
		});
		//sqrt
		bts[9].setOnAction(e -> {
			sqrt = true;
			
			number2 = Double.parseDouble(tex.getText());
			caculate();								
			sqrt = false;
		});
		//7
		bts[10].setOnAction(e -> {
			if(result == true && quotient == false && remainder == false && minus == false && add == false && cross == false && sqrt == false){
				number1 = 0;
			}
			if(result == true || tex.getText().equals("0")){
				tex.setText("");
				result = false;
			}
			tex.appendText("7");
		});
		//8
		bts[11].setOnAction(e -> {
			if(result == true && quotient == false && remainder == false && minus == false && add == false && cross == false && sqrt == false){
				number1 = 0;
			}
			if(result == true || tex.getText().equals("0")){
				tex.setText("");
				result = false;
			}
			tex.appendText("8");
		});
		//9
		bts[12].setOnAction(e -> {
			if(result == true && quotient == false && remainder == false && minus == false && add == false && cross == false && sqrt == false){
				number1 = 0;
			}
			if(result == true || tex.getText().equals("0")){
				tex.setText("");
				result = false;
			}
			tex.appendText("9");
		});
		//÷
		bts[13].setOnAction(e -> {
			sqrt = false;
			if(number1 == 0){
				number1 = Double.parseDouble(tex.getText());
				tex.setText("");
			}else{
				number2 = Double.parseDouble(tex.getText());
				tex.setText("");			
				caculate();	
				
			}
			cross = false ;
			add = false ;
			minus = false ;
			remainder = false; 
			quotient = true ;			 
		});
		//%
		bts[14].setOnAction(e -> {
			sqrt = false;			
			if(number1 == 0){
				number1 = Double.parseDouble(tex.getText());
				tex.setText("");
			}else{
				number2 = Double.parseDouble(tex.getText());
				tex.setText("");			
				caculate();								
			}
			quotient = false ;
			cross = false ;
			add = false ;
			minus = false ;
			remainder = true ;
		});
		//4
		bts[15].setOnAction(e -> {
			if(result == true && quotient == false && remainder == false && minus == false && add == false && cross == false && sqrt == false){
				number1 = 0;
			}
			if(result == true || tex.getText().equals("0")){
				tex.setText("");
				result = false;
			}
			tex.appendText("4");
		});
		//5
		bts[16].setOnAction(e -> {
			if(result == true && quotient == false && remainder == false && minus == false && add == false && cross == false && sqrt == false){
				number1 = 0;
			}
			if(result == true || tex.getText().equals("0")){
				tex.setText("");
				result = false;
			}
			tex.appendText("5");
		});
		//6
		bts[17].setOnAction(e -> {
			if(result == true && quotient == false && remainder == false && minus == false && add == false && cross == false && sqrt == false){
				number1 = 0;
			}
			if(result == true || tex.getText().equals("0")){
				tex.setText("");
				result = false;
			}
			tex.appendText("6");
		});
		// ×
		bts[18].setOnAction(e -> {
			sqrt = false;			
			if(number1 == 0){
				number1 = Double.parseDouble(tex.getText());
				tex.setText("");
			}else{
				number2 = Double.parseDouble(tex.getText());
				tex.setText("");			
				caculate();								
			}
			quotient = false ;
			cross = true ;
			add = false ;
			minus = false ;
			remainder = false ;
		});
		// 1/x
		bts[19].setOnAction(e -> {
			inverseNumber = true;			
			number2 = Double.parseDouble(tex.getText());
			caculate();								
			inverseNumber = false;
		});
		//1
		bts[20].setOnAction(e -> {
			if(result == true && quotient == false && remainder == false && minus == false && add == false && cross == false && sqrt == false){
				number1 = 0;
			}
			if(result == true || tex.getText().equals("0")){				
				tex.setText("");
				result = false;
			}
			tex.appendText("1");
		});
		//2
		bts[21].setOnAction(e -> {
			if(result == true && quotient == false && remainder == false && minus == false && add == false && cross == false && sqrt == false){
				number1 = 0;
			}
			if(result == true || tex.getText().equals("0")){
				tex.setText("");
				result = false;
			}
			tex.appendText("2");
		});
		//3
		bts[22].setOnAction(e -> {
			if(result == true && quotient == false && remainder == false && minus == false && add == false && cross == false && sqrt == false){
				number1 = 0;
			}
			if(result == true || tex.getText().equals("0")){				
				tex.setText("");
				result = false;
			}
			tex.appendText("3");
		});
		//-
		bts[23].setOnAction(e -> {
			sqrt = false;			
			if(number1 == 0){
				number1 = Double.parseDouble(tex.getText());
				tex.setText("");
			}else{
				number2 = Double.parseDouble(tex.getText());
				tex.setText("");			
				caculate();								
			}
			cross = false ;
			add = false ;
			minus = true ;
			remainder = false; 
			quotient = false ;
		});
		//=
		bts[24].setOnAction(e -> {
			sqrt = false;			
			number2 = Double.parseDouble(tex.getText());
			tex.setText("");
			caculate();
			quotient = false ;
			cross = false ;
			add = false ;
			minus = false ;
			remainder = false;
			mAdd = false;
			mMinus = false;
			
			
		});
		//0
		bts[25].setOnAction(e -> {
			if(result == true && quotient == false && remainder == false && minus == false && add == false && cross == false && sqrt == false){
				number1 = 0;
			}
			if(result == true || tex.getText().equals("0")){
				tex.setText("");
				result = false;
			}
			tex.appendText("0");
		});
		//.
		bts[26].setOnAction(e -> {
			if(tex.getText().contains(".") == false){
				tex.appendText(".");
			}
		});
		//+
		bts[27].setOnAction(e -> {
			sqrt = false;
			if(number1 == 0){
				number1 = Double.parseDouble(tex.getText());
				tex.setText("");
			}else{
				number2 = Double.parseDouble(tex.getText());
				tex.setText("");			
				caculate();								
			}
			cross = false ;
			add = true ;
			minus = false ;
			remainder = false; 
			quotient = false ;
			
		});
    
		
		
    
    
		tex.setStyle("-fx-background-color: DARKGRAY;"
                + "-fx-text-fill: BLACK;"
                + "-fx-font-size: 24pt;");
		grid.setStyle(
			"-fx-border-color: red; -fx-background-color: lightgray;");
    
		Scene scene = new Scene(grid, 360, 600);
		primaryStage.setTitle("U10416008丁杰Caculator"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	public void caculate(){
		if(sqrt == true){
			number2 = Math.sqrt(number2);
			tex.setText(Double.toString(number2));
		}else if(inverseNumber == true){
			compute1 = new BigDecimal(1);
			compute2 = new BigDecimal(number2);
			number2 = compute1.divide(compute2).doubleValue();
			tex.setText(Double.toString(number2));
		}else if(quotient == true){
			compute1 = new BigDecimal(number1);
			compute2 = new BigDecimal(number2);
			number1 = compute1.divide(compute2).doubleValue();
			tex.setText(Double.toString(number1));
		}else if(remainder == true){
			compute1 = new BigDecimal(number1);
			compute2 = new BigDecimal(number2);
			number1 = compute1.remainder(compute2).doubleValue() ;
			tex.setText(Double.toString(number1));
		}else if(cross == true){
			compute1 = new BigDecimal(number1);
			compute2 = new BigDecimal(number2);
			number1 = compute1.multiply(compute2).doubleValue();
			tex.setText(Double.toString(number1));
		}else if(minus == true){
			compute1 = new BigDecimal(number1);
			compute2 = new BigDecimal(number2);
			number1 = compute1.subtract(compute2).doubleValue();
			tex.setText(Double.toString(number1));
		}else if(add == true){
			compute1 = new BigDecimal(number1);
			compute2 = new BigDecimal(number2);
			number1 = compute1.add(compute2).doubleValue();
			tex.setText(Double.toString(number1));
		}else if(isMR == true && mAdd == true){
			mRAdd = mR;
			while(m.size()>=1){
				mRAdd = mRAdd + m.get(0);
				m.remove(0);
			}
			number1 = mRAdd ;
			tex.setText(Double.toString(mRAdd));
		}else if(isMR == true && mMinus == true){
			mRMinus = mR ;
			while(m.size()>=1){
				mRMinus = mRMinus - m.get(0);
				m.remove(0);
			}
			number1 = mRMinus ; 
			tex.setText(Double.toString(mRMinus));
		}
		result = true ;		
	}
  public static void main(String[] args) {
    launch(args);
  }
}

