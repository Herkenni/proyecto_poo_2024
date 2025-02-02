package proyecto.tienda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;

public class InicioController extends Component {
    @FXML
    private Button button_no_inicio;

    @FXML
    private Button button_si_inicio;

    @FXML
    private void button_no_createfile(ActionEvent event) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.db", "db");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Elige donde guardar los datos de la partida");
        int seleccion = fileChooser.showSaveDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            Shop.Path_save_usurious = fichero.getAbsolutePath();
            Conexion.crear_guardado_usuarios_db(Shop.Path_save_usurious);
            next_window();
        }

    }
    @FXML
    private void button_si_choosefile(ActionEvent event) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.db", "db");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogTitle("Elige los datos de la partida");
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File fichero = fileChooser.getSelectedFile();
            Shop.Path_save_usurious = fichero.getAbsolutePath();
            next_window();
        }
    }
    private void next_window(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-view.fxml"));
            Parent root = fxmlLoader.load();
            LoginController Controller = fxmlLoader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            //descomentar siguientes 2 lineas si se compilara en un jar
            //javafx.scene.image.Image icon = new Image("proyecto/tienda/imagenes/icono.jpg");
            //stage.getIcons().add(icon);
            Controller.close_window1();
            stage.show();
            Stage myStage = (Stage) this.button_no_inicio.getScene().getWindow();
            myStage.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
    }
}
