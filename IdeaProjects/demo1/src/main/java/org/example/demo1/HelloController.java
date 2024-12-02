package org.example.demo1;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML private TextField nombreEquipoField;
    @FXML private TextField descripcionEquipoField;
    @FXML private ComboBox<String> tipoEquipoComboBox;
    @FXML private TableView<Reserva> reservasTableView;
    @FXML private ComboBox<String> equipoComboBox;
    @FXML private TextField horaReservaField;

    private List<String> equipos = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();

    @FXML
    public void registrarEquipo() {
        String nombre = nombreEquipoField.getText();
        String descripcion = descripcionEquipoField.getText();
        String tipo = tipoEquipoComboBox.getValue();

        if (nombre.isEmpty() || descripcion.isEmpty() || tipo == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }

        equipos.add(nombre);
        equipoComboBox.setItems(FXCollections.observableArrayList(equipos));
        nombreEquipoField.clear();
        descripcionEquipoField.clear();
        tipoEquipoComboBox.setValue(null);

        showAlert(Alert.AlertType.INFORMATION, "Éxito", "Equipo registrado correctamente.");
    }

    @FXML
    public void mostrarReservas() {
        reservas.add(new Reserva("Computadora", "10:00 AM"));
        reservas.add(new Reserva("Herramienta", "2:00 PM"));

        reservasTableView.setItems(FXCollections.observableArrayList(reservas));
    }

    @FXML
    public void asignarReserva() {
        String equipo = equipoComboBox.getValue();
        String hora = horaReservaField.getText();

        if (equipo == null || hora.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Todos los campos son obligatorios.");
            return;
        }

        reservas.add(new Reserva(equipo, hora));
        showAlert(Alert.AlertType.INFORMATION, "Reserva Exitosa", "Reserva asignada para " + equipo + " a las " + hora);
        horaReservaField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static class Reserva {
        private final String equipo;
        private final String hora;

        public Reserva(String equipo, String hora) {
            this.equipo = equipo;
            this.hora = hora;
        }

        public String getEquipo() {
            return equipo;
        }

        public String getHora() {
            return hora;
        }
    }
}

