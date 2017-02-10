import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.*;
import javafx.scene.input.*;


public class CalFX extends Application
{
	Stage primaryStage=new Stage();
	
	public void start(Stage stage) 
	{
		cal();
	}
	
	private void cal()
	{	
		primaryStage.setTitle("JFX Calculator");
		
		BorderPane root=new BorderPane();

		
		MenuBar mb=new MenuBar();
		Menu m1=new Menu("Help");
		MenuItem mi1=new MenuItem("_About");
		
		mi1.setMnemonicParsing(true);
		mi1.setAccelerator(new KeyCodeCombination(KeyCode.Q,KeyCombination.SHORTCUT_DOWN));
		
		m1.getItems().add(mi1);
		mb.getMenus().add(m1);
		
		BorderPane root1=new BorderPane();
		root1.setPadding(new Insets(10));

		
		Scene scn=new Scene(root,360,260);
		
		GridPane gp=new GridPane();
		gp.setHgap(10);
		gp.setVgap(10);
		
	
		GridPane gp1=new GridPane();
		gp1.setHgap(10);
		gp1.setVgap(10);
		root1.setMargin(gp1,new Insets(0,0,0,10));
		
		
		TextField t=new TextField();
		t.setPrefSize(230,40);
		t.setMaxSize(230,40);
		t.setAlignment(Pos.BOTTOM_RIGHT);
		
		Font f=new Font("System Regular",15);
		
		Button b0=new Button("0");
		Button b1=new Button("1");
		Button b2=new Button("2");
		Button b3=new Button("3");
		Button b4=new Button("4");
		Button b5=new Button("5");
		Button b6=new Button("6");
		Button b7=new Button("7");
		Button b8=new Button("8");
		Button b9=new Button("9");
		Button bdot=new Button(".");
		Button beql=new Button("=");
		Button bplus=new Button("+");
		Button bminus=new Button("-");
		Button bdiv=new Button("/");
		Button bmul=new Button("*");
		Button bexpo=new Button("^");
		Button blp=new Button("(");
		Button brp=new Button(")");
		Button bclr=new Button("C");
		
		bplus.setFont(f);
		bminus.setFont(f);
		bmul.setFont(f);
		bdiv.setFont(f);
		bexpo.setFont(f);
		bclr.setFont(f);
		blp.setFont(f);
		brp.setFont(f);
		
		
		gp.addEventFilter(ActionEvent.ACTION,new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				Button b=(Button)e.getTarget();
				if(b==beql)
				{
					try
					{
						String temp=t.getText();
						CalEngine engine=new CalEngine(temp);
						Double d=engine.eval(engine.toPostfixNum());
						t.setText((""+d).replaceAll((String)"\\.0$",""));
					}
					catch(Exception ex)
					{
						t.setText("Error");
					}
				}
				else
				{
					t.setText(t.getText()+b.getText());
				}
			}
		});
		
		
		gp1.addEventFilter(ActionEvent.ACTION,new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				Button b=(Button)e.getTarget();
				if(b==bclr)
				{
					t.setText("");
				}
				else
				{
					t.setText(t.getText()+b.getText());
				}
			}
		});
		
		mi1.setOnAction(new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent e)
				{
					about();
				}
			});
			
		
		
		b0.setPrefSize(70,30);
		b1.setPrefSize(70,30);
		b2.setPrefSize(70,30);
		b3.setPrefSize(70,30);
		b4.setPrefSize(70,30);
		b5.setPrefSize(70,30);
		b6.setPrefSize(70,30);
		b7.setPrefSize(70,30);
		b8.setPrefSize(70,30);
		b9.setPrefSize(70,30);
		bdot.setPrefSize(70,30);
		bplus.setPrefSize(40,30);
		bminus.setPrefSize(40,30);
		bmul.setPrefSize(40,30);
		bdiv.setPrefSize(40,30);
		bexpo.setPrefSize(40,30);
		blp.setPrefSize(40,30);
		brp.setPrefSize(40,30);
		bclr.setPrefSize(40,30);
		beql.setPrefSize(70,30);
		
		

		gp.add(b1,0,0);
		gp.add(b2,1,0);
		gp.add(b3,2,0);
		gp.add(b4,0,1);
		gp.add(b5,1,1);
		gp.add(b6,2,1);
		gp.add(b7,0,2);
		gp.add(b8,1,2);
		gp.add(b9,2,2);
		gp.add(b0,1,3);
		gp.add(bdot,0,3);
		gp.add(beql,2,3);
		
		gp1.add(blp,0,0);
		gp1.add(brp,1,0);
		gp1.add(bplus,0,1);
		gp1.add(bminus,1,1);
		gp1.add(bmul,0,2);
		gp1.add(bdiv,1,2);
		gp1.add(bclr,0,3);
		gp1.add(bexpo,1,3);

		root1.setMargin(t,new Insets(0,0,10,0));
		root1.setTop(t);
		root1.setCenter(gp);
		root1.setRight(gp1);
		root.setCenter(root1);
		root.setTop(mb);
		
		
		primaryStage.setScene(scn);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	private void about()
	{	
		
		Stage stg=new Stage(StageStyle.UTILITY);
		stg.initModality(Modality.WINDOW_MODAL);
		stg.initOwner(primaryStage.getScene().getWindow());
		
		Font f=new Font("System Regular",20);
		
		
		Text project=new Text("Project By");
		Text name=new Text("Harshvardhan Singh");
		
		project.setFont(f);
		name.setFont(f);
		
		
		FlowPane root=new FlowPane();
		
		Scene scn=new Scene(root,200,200);	
		
		root.getChildren().add(project);
		root.getChildren().add(name);
		
		stg.setScene(scn);
		stg.setResizable(false);
		stg.show();
		
	}

	public static void main (String[] args) 
	{
		launch(args);
	}
}