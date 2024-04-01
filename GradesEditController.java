package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import Connectivity.ConnectionUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GradesEditController extends AllHomeController implements Initializable{

    private ObservableList<String> grades = FXCollections.observableArrayList("A", "B+", "B", "C+", "C", "D+", "D");
    private ObservableList<String> modules = FXCollections.observableArrayList("OOP", "DSA", "SAE", "CINTER", "DEVOPS", "SIP");
    int num, index = 0;
    double gpa = 0.0;
    
    private double total;
    
    public void setTotal(double total) {
 	   this.total = total;
    }

    public double getTotal() {
 	   return total;
    }

    @FXML
    private TextField e1;

    @FXML
    private TextField e2;

    @FXML
    private TextField e3;

    @FXML
    private TextField e4;

    @FXML
    private TextField e5;

    @FXML
    private TextField e6;

    @FXML
    protected Label g1;

    @FXML
    protected Label g2;

    @FXML
    protected Label g3;

    @FXML
    protected Label g4;

    @FXML
    protected Label g5;

    @FXML
    protected Label g6;

    @FXML
    protected Label gt;
        
    @FXML
    protected Label gt1;

    @FXML
    private ComboBox<String> mod;

    @FXML
    private TextField target;

    @FXML
	protected Label w1;

    @FXML
	protected Label w2;

    @FXML
	protected Label w3;

    @FXML
    protected Label w4;

    @FXML
    protected Label w5;

    @FXML
    protected Label w6;

    @FXML
    protected Label wt;

	@FXML
	private ComboBox<String> grade;

    @FXML
	protected Label output;
    
	static Connection con = ConnectionUtil.conDB();
	PreparedStatement preparedstatement = null;
	int resultSet = 0;
	String query = null;
	static String name = LogInController.Name;

	
	public void initialize(URL url, ResourceBundle rb) {
		w1.setText("-");
		w2.setText("-");
		w3.setText("-");
		w4.setText("-");
		w5.setText("-");
		w6.setText("-");
		wt.setText("Total:");
		mod.setItems((ObservableList<String>)modules);
	    mod.getSelectionModel().select(0);
	    grade.setItems((ObservableList<String>)grades);
	    grade.getSelectionModel().select(0);	
	}
	
    @FXML
    private Label txtgpa;
     
	
	@FXML
    void onGetClicked(ActionEvent event) {
		query = "SELECT OOPT, DSAT, SAET, CINTERT, DEVOPST FROM info WHERE Name = '"+name+"' ";
		try {
			preparedstatement = con.prepareStatement(query);
			ResultSet rs = preparedstatement.executeQuery();

        while(rs.next()) {
        	int t1 = rs.getInt("OOPT"); double gt1 = 5*gp(t1);
        	int t2 = rs.getInt("DSAT"); double gt2 = 5*gp(t2);
        	int t3 = rs.getInt("SAET"); double gt3 = 4*gp(t3);
        	int t4 = rs.getInt("CINTERT"); double gt4 = 5*gp(t4);
        	int t5 = rs.getInt("DEVOPST"); double gt5 = 4*gp(t5);
        	double gpa = (gt1 + gt2 + gt3 + gt4 + gt5)/23;
        	txtgpa.setText(String.valueOf(gpa));
        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
	
	public double gp(int t) {
		double gp = 0;
		if(0<t && t<50) gp = 0.0;
		else if (50<=t && t<55) gp = 1.0;
		else if (55<=t && t<60) gp = 1.5;
		else if (60<=t && t<65) gp = 2.0;
		else if (65<=t && t<70) gp = 2.5;
		else if (70<=t && t<75) gp = 3.0;
		else if (75<=t && t<80) gp = 3.5;
		else if (80<=t && t<101) gp = 4.0;
		return gp;
	}

	
	public void calOOP() {
	   getInfo("GP(10%)", "LT1(15%)", "LT2(15%)", "Quiz1(15%)", "Quiz2(15%)", "Proj.(30%)", 5, 11);
   }
	
	public void calcOOP() {
		double Total = Double.parseDouble(e1.getText()) * 0.1 + Double.parseDouble(e2.getText()) * 0.15 + 
			 Double.parseDouble(e3.getText()) * 0.15 + Double.parseDouble(e4.getText()) * 0.15 +
			 Double.parseDouble(e5.getText()) * 0.15 + Double.parseDouble(e6.getText()) * 0.3;
		   setTotal(Total); 
	}
	
	public void calDSA() {
		   getInfo("GP(10%)", "LT1(15%)", "LT2(15%)", "Quiz1(20%)", "Quiz2(20%)", "Proj.(20%)", 12, 18);
	}
		
	public void calcDSA() {
		   double Total = Double.parseDouble(e1.getText()) * 0.1 + Double.parseDouble(e2.getText()) * 0.15 + 
				   Double.parseDouble(e3.getText()) * 0.15 + Double.parseDouble(e4.getText()) * 0.2 + 
				   Double.parseDouble(e5.getText()) * 0.2 + Double.parseDouble(e6.getText()) * 0.2;
		   setTotal(Total); 
		}
		
	public void calSAE() {
		   getInfo1("CA1(15%)", "CA2(15%)", "MST(20%)", "Proj.(20%)", "EST(30%)",  19, 24);
	}
			
	public void calcSAE() {
		double Total = Double.parseDouble(e1.getText()) * 0.15 + Double.parseDouble(e2.getText()) * 0.15 + 
		    Double.parseDouble(e3.getText()) * 0.2 + Double.parseDouble(e4.getText()) * 0.2 + 
			Double.parseDouble(e5.getText()) * 0.3;
			setTotal(Total); 
	}
	
	public void calCINTER() {
		   getInfo1("Gp(10%)", "LT1(15%)", "LT2(15%)", "MST(20%)", "EST(40%)",  25, 30);
	}
			
	public void calcCINTER() {
		double Total = Double.parseDouble(e1.getText()) * 0.1 + Double.parseDouble(e2.getText()) * 0.15 + 
		    Double.parseDouble(e3.getText()) * 0.15 + Double.parseDouble(e4.getText()) * 0.2 + 
			Double.parseDouble(e5.getText()) * 0.4;
			setTotal(Total); 
	}
	
	public void calDEVOPS() {
		   getInfo1("Gp(10%)", "LT1(15%)", "LT2(15%)", "Quiz(20%)", "Proj.(40%)",  31, 36);
	}

   @FXML
   void onGoClicked(ActionEvent event) {
	   switch(mod.getSelectionModel().getSelectedIndex()) {
	   case 0:  calOOP();break;
	   case 1:  index = 1; calDSA(); break;
	   case 2:  index = 2; calSAE(); break;
	   case 3:  index = 3; calCINTER(); break;
	   case 4:  index = 4; calDEVOPS(); break;
	   case 5:  index = 5; calOOP();; break;
	   }
   }

    @FXML
    void onComputeClicked(ActionEvent event) {
    	
    	switch(grade.getSelectionModel().getSelectedIndex()) {
		case 0:  num = 85; target.setText("85"); gpa = 4.0; break;
		case 1:  num = 75; target.setText("75"); gpa = 3.5; break;
		case 2:  num = 70; target.setText("70"); gpa = 3.0; break;
		case 3:  num = 65; target.setText("65"); gpa = 2.5; break;
		case 4:  num = 60; target.setText("60"); gpa = 2.0; break;
		case 5:  num = 55; target.setText("55"); gpa = 1.5; break;
		case 6:  num = 50; target.setText("50"); gpa = 1.0; break;
		}
    	
    	switch(index) {
    	case 0:  calcOOP();break;
		case 1:  calcDSA(); break;
		case 2:  calcSAE(); break;
		case 3:  calcCINTER(); break;
		case 4:  calcCINTER(); break;
		case 5:  calcOOP(); break;
    	}
    	
    	double Total = getTotal();
		gt1.setText(String.valueOf(Total));	
    }
	
	@FXML
	void onSaveClicked(ActionEvent event) {
		g1.setText(e1.getText());
		g2.setText(e2.getText());
		g3.setText(e3.getText());
		g4.setText(e4.getText());
		g5.setText(e5.getText());
		g6.setText(e6.getText());
		gt.setText(gt1.getText());
    	switch(index) {
    	case 0:  getQuery(); insert();break;
		case 1:  getQuery1(); insert(); break;
		case 2:  getQuery2(); insert1(); break;
		case 3:  getQuery3(); insert1(); break;
		case 4:  getQuery4(); insert1(); break;
		case 5:  getQuery(); insert(); break;
    	}
	}

	private void getQuery(){
	     query = "UPDATE `info` SET "
	             + "`OOPGp`=?,"
	             + "`OOPLT1`=?,"
	             + "`OOPLT2`=?,"
	             + "`OOPQ1`=?,"
	             + "`OOPQ2`=?,"
	             + "`OOPproj`=?,"
	             + "`OOPT`=? WHERE Name = '"+name+"'";
	}
	
	private void getQuery1(){
	     query = "UPDATE `info` SET "
	             + "`DSAGp`=?,"
	             + "`DSALT1`=?,"
	             + "`DSALT2`=?,"
	             + "`DSAQ1`=?,"
	             + "`DSAQ2`=?,"
	             + "`DSAproj`=?,"
	             + "`DSAT`=? WHERE Name = '"+name+"'";
	}
	
	private void getQuery2(){
	     query = "UPDATE `info` SET "
	             + "`SAECA1`=?,"
	             + "`SAECA2`=?,"
	             + "`SAEMST`=?,"
	             + "`SAEproj`=?,"
	             + "`SAEEST`=?,"
	             + "`SAET`=? WHERE Name = '"+name+"'";
	}
	
	private void getQuery3(){
	     query = "UPDATE `info` SET "
	             + "`CINTERGp`=?,"
	             + "`CINTERLT1`=?,"
	             + "`CINTERLT2`=?,"
	             + "`CINTERMST`=?,"
	             + "`CINTEREST`=?,"
	             + "`CINTERT`=? WHERE Name = '"+name+"'";
	}
	
	private void getQuery4(){
	     query = "UPDATE `info` SET "
	             + "`DEVOPSGp`=?,"
	             + "`DEVOPSLT1`=?,"
	             + "`DEVOPSLT2`=?,"
	             + "`DEVOPSQ`=?,"
	             + "`DEVOPSProj`=?,"
	             + "`DEVOPST`=? WHERE Name = '"+name+"'";
	}
	private void insert() {
	
	    try {
	        preparedstatement = con.prepareStatement(query);
	        preparedstatement.setString(1, String.valueOf(g1.getText()));
	        preparedstatement.setString(2, String.valueOf(g2.getText()));
	        preparedstatement.setString(3, String.valueOf(g3.getText()));
	        preparedstatement.setString(4, String.valueOf(g4.getText()));
	        preparedstatement.setString(5, String.valueOf(g5.getText()));
	        preparedstatement.setString(6, String.valueOf(g6.getText()));
	        preparedstatement.setString(7, String.valueOf(gt.getText()));
	        resultSet = preparedstatement.executeUpdate();
	        
	        if (resultSet == 1) output.setText("Updated successfully");
	        else output.setText("Update failed");
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    }
	
	}
	private void insert1() {
		
	    try {
	        preparedstatement = con.prepareStatement(query);
	        preparedstatement.setString(1, String.valueOf(g1.getText()));
	        preparedstatement.setString(2, String.valueOf(g2.getText()));
	        preparedstatement.setString(3, String.valueOf(g3.getText()));
	        preparedstatement.setString(4, String.valueOf(g4.getText()));
	        preparedstatement.setString(5, String.valueOf(g5.getText()));
	        preparedstatement.setString(6, String.valueOf(gt.getText()));
	        resultSet = preparedstatement.executeUpdate();
	        
	        if (resultSet == 1) output.setText("Updated successfully");
	        else output.setText("Update failed");
	    } catch (SQLException ex) {
	    	System.err.println(ex.getMessage());
	    }
	
	}
	
	protected void getInfo(String a, String b, String c, String d, String e, String f, int i, int en) {
	   w1.setText(a);
	   w2.setText(b);
	   w3.setText(c);
	   w4.setText(d);
	   w5.setText(e);
	   w6.setText(f);
		String SQL = "SELECT *  FROM info WHERE Name = '"+name+"'";
		try {
		PreparedStatement ps = con.prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();			
			while(rs.next())
		    {        
				while(i<=en) {
				g1.setText(rs.getString(i)); i++;
				g2.setText(rs.getString(i)); i++; 
				g3.setText(rs.getString(i)); i++; 
				g4.setText(rs.getString(i)); i++;
				g5.setText(rs.getString(i)); i++;
				if (w6.getText()!= "-")
					g6.setText(rs.getString(i)); i++;
				gt.setText(rs.getString(i)); i++;
				}
		    }
		}catch(SQLException ex) {
	    	System.err.println(ex.getMessage());
		}
	
	}
	
	protected void getInfo1(String a, String b, String c, String d, String e, int i, int en) {
		   w1.setText(a);
		   w2.setText(b);
		   w3.setText(c);
		   w4.setText(d);
		   w5.setText(e);
			String SQL = "SELECT *  FROM info WHERE Name = '"+name+"'";
			try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();			
				while(rs.next())
			    {        
					while(i<=en) {
					g1.setText(rs.getString(i)); i++;
					g2.setText(rs.getString(i)); i++; 
					g3.setText(rs.getString(i)); i++; 
					g4.setText(rs.getString(i)); i++;
					g5.setText(rs.getString(i)); i++;
					gt.setText(rs.getString(i)); i++;
					}
			    }
			}catch(SQLException ex) {
		    	System.err.println(ex.getMessage());
			}
		
		}
}
