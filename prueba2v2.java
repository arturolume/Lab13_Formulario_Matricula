import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.CallableStatement;

/*import javax.naming.spi.DirStateFactory;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.TableView;*/
import javax.xml.transform.Result;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;  
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


import javafx.geometry.Insets;

import javafx.scene.control.Label;  
import javafx.scene.control.TextField;  
import javafx.scene.layout.GridPane;  

import javafx.scene.control.Alert;

//para la tabla

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



class Alumnos {
    private String codigo;
    private String apellidos;
    private String nombres;
    private String direccion;
    private String distrito;
    public Alumnos() {}

    public Alumnos(String codigo, String apellidos, String nombres, String direccion, String distrito) {
        this.codigo=codigo;
        this.apellidos=apellidos;
        this.nombres=nombres;
        this.direccion=direccion;
        this.distrito=distrito;
    }

    
    public String getCodigo(){
        return codigo;
    }
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public String getApellidos(){
        return apellidos;
    }
    public void setApellidos(String apellidos){
        this.apellidos = apellidos;
    }

    public String getNombres(){
        return nombres;
    }
    public void setNombres(String nombres){
        this.nombres = nombres;
    }
    
    public String getDireccion(){
        return direccion;
    }
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }

    public String getDistrito(){
        return distrito;
    }
    public void setDistrito(String distrito){
        this.distrito = distrito;
    }

}

/*class PersonDataAccessor {

    private Connection connection ;

    public PersonDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }
}*/



public class prueba2v2 extends Application{
    

    public static void main(String[] args) {
        
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //boton
        
        /*TableView tableView = new TableView();
        TableColumn<Alumnos, String> column1 = new TableColumn<>("CODIGO");
        TableColumn<Alumnos, String> column2 = new TableColumn<>("APELLIDO");
        TableColumn<Alumnos, String> column3 = new TableColumn<>("NOMBRE");
        TableColumn<Alumnos, String> column4 = new TableColumn<>("DIRECCION");
        TableColumn<Alumnos, String> column5 = new TableColumn<>("DISTRITO");
*/
        Button btn1=new Button("Mostrar datos");
        TableView tableView = new TableView();

    TableColumn<Alumnos, String> column1 = new TableColumn<>("CODIGO");
    column1.setCellValueFactory(new PropertyValueFactory<>("codigo"));
    TableColumn<Alumnos, String> column2 = new TableColumn<>("APELLIDO");
    column2.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
    TableColumn<Alumnos, String> column3 = new TableColumn<>("NOMBRE");
    column3.setCellValueFactory(new PropertyValueFactory<>("nombres"));
    TableColumn<Alumnos, String> column4 = new TableColumn<>("DIRECCION");
    column4.setCellValueFactory(new PropertyValueFactory<>("direccion"));
    TableColumn<Alumnos, String> column5 = new TableColumn<>("DISTRITO");
    column5.setCellValueFactory(new PropertyValueFactory<>("distrito"));


    tableView.getColumns().add(column1);
    tableView.getColumns().add(column2);
    tableView.getColumns().add(column3);
    tableView.getColumns().add(column4);
    tableView.getColumns().add(column5);




        final Label codigo1 = new Label();
        final Label apellido1 = new Label();
        final Label nombre1 = new Label();
        final Label direccion1 = new Label();
        final Label distrito1 = new Label();

        btn1.setOnAction(new EventHandler<ActionEvent>() {  
         
        @Override  
        public void handle(ActionEvent arg0) {  
            // TODO Auto-generated method stub  

            //mostrar datos
            Connection conn = null;
            
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Matricula","root","Reyes562");
                CallableStatement procedimiento = conn.prepareCall("{ call Mostrar_Alumnos() }");
                procedimiento.execute();
                ResultSet rs = procedimiento.executeQuery();


                ArrayList<Alumnos> alumnoList = new ArrayList<>();
            
                //tableView.getItems().addAll(new Alumnos("a","a","a","a","a"));
                while(rs.next()){
                    
                    String codigo = rs.getString("codigo");
                    String apellidos= rs.getString("apellidos");
                    String nombres= rs.getString("nombres");
                    String direccion= rs.getString("direccion");
                    String distrito= rs.getString("distrito");

                    Alumnos alumno = new Alumnos(codigo, apellidos, nombres, direccion, distrito);
                    alumnoList.add(alumno);
                    
                    int i = 0;

                    //tableView.getItems().add(alumno.get(0));
                        System.out.println("Codigo: "+rs.getString("codigo")
                        +"\nApellido: "+rs.getString("apellidos")
                        +"\nNombre: "+rs.getString("nombres")
                        +"\nDireccion: "+rs.getString("direccion")
                        +"\nDistrito: "+rs.getString("distrito")+"\n");
                        
                        codigo1.setText(alumnoList.get(i).getCodigo());
                        apellido1.setText(alumnoList.get(i).getApellidos());
                        nombre1.setText(alumnoList.get(i).getNombres());
                        direccion1.setText(alumnoList.get(i).getDireccion());
                        distrito1.setText(alumnoList.get(i).getDistrito());

                }
             

            } catch (SQLException e) {
               
                System.out.println("SQLException: "+e.getMessage());
                System.out.println("SQLState: "+e.getSQLState());
                System.out.println("VendorError: "+e.getErrorCode());
            }
        }
        
          
    });
    
    TextField tf1=new TextField();  
    TextField tf2=new TextField(); 
    TextField tf3=new TextField();  
    TextField tf4=new TextField();  
    TextField tf5=new TextField(); 
    

    Button btn2=new Button("Ingresar datos");
        btn2.setOnAction(new EventHandler<ActionEvent>(){
            @Override  
        public void handle(ActionEvent arg1) {  
            // TODO Auto-generated method stub  
            int i = 0;

            Connection conn = null;
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Matricula","root","Reyes562");
                CallableStatement procedimiento = conn.prepareCall("{ call Registrar_Alumnos(?, ?, ?, ?, ?) }");
                                
                String codigo = tf1.getText();                                
                String apellidos = tf2.getText();                                
                String nombres = tf3.getText();                               
                String direccion = tf4.getText();                               
                String distrito = tf5.getText();
                                
                procedimiento.setString(1, codigo);
                procedimiento.setString(2, apellidos);
                procedimiento.setString(3, nombres);
                procedimiento.setString(4, direccion);
                procedimiento.setString(5, distrito);
                procedimiento.execute();
                procedimiento.close();

                i++;
                

            } catch (SQLException e) {
               
                System.out.println("SQLException: "+e.getMessage());
                System.out.println("SQLState: "+e.getSQLState());
                System.out.println("VendorError: "+e.getErrorCode());
                i = 0;
            }

            if(i > 0){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("NICE");
                alert.setContentText("Alumno registrado exitosamente");
                alert.showAndWait();
                i = 0;
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("OH NO");
                alert.setContentText("No se pudo agregar al alumno");
                alert.showAndWait();
                i++;
            }

        }
        });


        Label codigo =new Label("Codigo");  
        Label apellido = new Label("Apellido");    
        Label nombre = new Label("Nombre");  
        Label direccion = new Label("Direccion");  
        Label distrito = new Label("Distrito");

        GridPane root = new GridPane();

        root.addRow(0, codigo, tf1);  
        root.addRow(1, apellido, tf2);  
        root.addRow(2, nombre, tf3); 
        root.addRow(3, direccion, tf4); 
        root.addRow(4, distrito, tf5); 
        root.addRow(5, btn1); 
        root.addRow(6, btn2);
        
        root.addRow(7, codigo1);
        root.addRow(8, apellido1);
        root.addRow(9, nombre1);
        root.addRow(10, direccion1);
        root.addRow(11, distrito1);
        root.addRow(18, tableView);
        //lbl.setText("Hello, World.");
        
        stage.setTitle("HELLO");
        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }    
}
