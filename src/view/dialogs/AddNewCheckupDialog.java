package view.dialogs;

import controller.Controller;
import model.entity.Car;
import model.entity.Driver;
import model.entity.Officer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddNewCheckupDialog extends JDialog {
    private Controller controller;
    private JComboBox<String> drivers;
    private JComboBox<String> officers;
    private JComboBox<String> cars;
    private JComboBox<String> result;
    private JButton addRecord;

    public AddNewCheckupDialog(Controller controller) {
        this.controller = controller;
        drivers = new JComboBox<>(getStringDrivers());
        officers = new JComboBox<>(getStringOfficers());
        cars = new JComboBox<>(getStringCars());
        result = new JComboBox<>(new String[]{"fail", "success"});
        addRecord = new JButton("Add");
        addRecord.addActionListener(new AddListener());

        setSize(300, 400);
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        add(new JLabel("Select driver:"));
        add(drivers);
        add(new JLabel("Select car:"));
        add(cars);
        add(new JLabel("Select officer:"));
        add(officers);
        add(new JLabel("Select result:"));
        add(result);
        add(addRecord);

        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        setVisible(true);
    }

    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (drivers.getSelectedItem() == null &&
                    cars.getSelectedItem() == null &&
                    officers.getSelectedItem() == null) {
                return;
            }

            controller.addCheckup(
                    controller.getDrivers().get(drivers.getSelectedIndex()),
                    controller.getCars().get(cars.getSelectedIndex()),
                    controller.getOfficers().get(officers.getSelectedIndex()),
                    result.getSelectedIndex()
                    );
            setVisible(false);
        }
    }

    private String[] getStringDrivers() {
        List<String> drStr = new ArrayList<>();
        for (Driver dr : controller.getDrivers()) {
            drStr.add(dr.getName() + " " + dr.getSurname() + ", " + dr.getDriverLicense());
        }
        return drStr.toArray(new String[0]);
    }

    private String[] getStringCars() {
        List<String> carStr = new ArrayList<>();
        for (Car car : controller.getCars()) {
            carStr.add(car.getNumber());
        }
        return carStr.toArray(new String[0]);
    }

    private String[] getStringOfficers() {
        List<String> offStr = new ArrayList<>();
        for (Officer off : controller.getOfficers()) {
            offStr.add(off.getRank() + " " + off.getName() + " " + off.getSurname());
        }
        return offStr.toArray(new String[0]);
    }
}
