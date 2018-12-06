package view;

import controller.Controller;
import model.tablemodel.CarTableModel;
import model.tablemodel.CheckupTabelModel;
import model.tablemodel.DriverTableModel;
import model.tablemodel.GaiStaffTableModel;
import view.dialogs.AddNewCheckupDialog;
import view.dialogs.AnswerTableDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    private Controller controller;
    private JFrame frame;
    private JPanel functional;
    private JTabbedPane tabs;

    private JButton editButton;
    private JButton deleteButton;
    private JButton addNewRecordButton;
    private JButton calcNumCarInTime;
    private JButton infoAboutCheckupInDate;
    private JButton historyCheckupcar;
    private JButton saveToDB;

    private JTable driverTable;
    private JTable gaiEmployeeTable;
    private JTable carTable;
    private JTable technicalCheckupTable;

    private DriverTableModel driverTableModel;
    private CarTableModel carTableModel;
    private GaiStaffTableModel gaiStaffTableModel;
    private CheckupTabelModel checkupTabelModel;

    public MainFrame(DriverTableModel driverTableModel,
                     CarTableModel carTableModel,
                     GaiStaffTableModel gaiStaffTableModel,
                     CheckupTabelModel checkupTabelModel,
                     Controller controller) {
        this.controller = controller;
        this.driverTableModel = driverTableModel;
        this.carTableModel = carTableModel;
        this.gaiStaffTableModel = gaiStaffTableModel;
        this.checkupTabelModel = checkupTabelModel;
    }

    public static MainFrame of(DriverTableModel driverTableModel,
                               CarTableModel carTableModel,
                               GaiStaffTableModel gaiStaffTableModel,
                               CheckupTabelModel checkupTabelModel,
                               Controller controller) {
        MainFrame mainFrame =
                new MainFrame(driverTableModel, carTableModel, gaiStaffTableModel, checkupTabelModel, controller);
        mainFrame.create();
        return mainFrame;
    }

    public void create() {
        frame = new JFrame("GAI");
        frame.setSize(1000, 500);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
        tabs = new JTabbedPane();
        functional = new JPanel();
        functional.setPreferredSize(new Dimension(200, 500));
        functional.setMaximumSize(new Dimension(200, 500));
        functional.setLayout(new BoxLayout(functional, BoxLayout.Y_AXIS));

        editButton = new JButton("Edit");
        editButton.addActionListener(new EditListener());
        functional.add(editButton);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeleteListener());
        functional.add(deleteButton);

        addNewRecordButton = new JButton("New Record");
        addNewRecordButton.addActionListener(new NewRecordListener());
        functional.add(addNewRecordButton);

        calcNumCarInTime = new JButton("Number cars in time");
        calcNumCarInTime.addActionListener(new CalcCarListener());
        functional.add(calcNumCarInTime);

        infoAboutCheckupInDate = new JButton("Checkups in time");
        infoAboutCheckupInDate.addActionListener(new InfoCheckupInDateListener());
        functional.add(infoAboutCheckupInDate);

        historyCheckupcar = new JButton("History car's checkup");
        historyCheckupcar.addActionListener(new HistoryCheckupListener());
        functional.add(historyCheckupcar);


        saveToDB = new JButton("Save to DB");
        saveToDB.addActionListener(new SaveListener());
        functional.add(saveToDB);

        driverTable = new JTable(driverTableModel);
        carTable = new JTable(carTableModel);
        gaiEmployeeTable = new JTable(gaiStaffTableModel);
        technicalCheckupTable = new JTable(checkupTabelModel);

        tabs.add(("Drivers"), new JScrollPane(driverTable));
        tabs.add(("Cars"), new JScrollPane(carTable));
        tabs.add(("Officers"), new JScrollPane(gaiEmployeeTable));
        tabs.add(("Checkups"), new JScrollPane(technicalCheckupTable));

        frame.add(functional);
        frame.add(tabs);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    class EditListener implements ActionListener {
        private JTable currentTable;
        private int currentRow;
        private int currentColumn;

        @Override
        public void actionPerformed(ActionEvent e) {
            currentTable = (JTable) ((JScrollPane) tabs.getSelectedComponent()).getViewport().getView();
            currentRow = currentTable.getSelectedRow();
            currentColumn = currentTable.getSelectedColumn();
            if (currentRow < 0 || currentColumn < 0) {
                return;
            }
            if (currentTable == technicalCheckupTable) {
                editTechnicalCheckupTable();
                return;
            }
            String newValue = JOptionPane.showInputDialog(frame, "Input new value:");
            if (newValue.trim().isEmpty()) {
                return;
            }
            if (currentTable.equals(driverTable) && currentColumn == 3) {
                JOptionPane.showMessageDialog(frame, "Incorrect value!");
                return;
            }
            controller.changeValue(currentTable.getModel(), currentRow, currentColumn, newValue);
        }

        private void editTechnicalCheckupTable() {

        }
    }

    class DeleteListener implements ActionListener {
        private int currentRow;
        private JTable currentTable;

        @Override
        public void actionPerformed(ActionEvent e) {
            currentTable = (JTable) ((JScrollPane) tabs.getSelectedComponent()).getViewport().getView();
            currentRow = currentTable.getSelectedRow();
            if (currentRow < 0) {
                return;
            }

            controller.deleteRecord(currentTable.getModel(), currentRow);
        }
    }

    class NewRecordListener implements ActionListener {
        private JTable currentTable;

        @Override
        public void actionPerformed(ActionEvent e) {
            currentTable = (JTable) ((JScrollPane) tabs.getSelectedComponent()).getViewport().getView();

            if (currentTable.equals(driverTable)) {
                addNewDriver();
            } else if (currentTable.equals(carTable)) {
                addNewCar();
            } else if (currentTable.equals(gaiEmployeeTable)) {
                addNewGaiEmployee();
            } else if (currentTable.equals(technicalCheckupTable)) {
                addNewTechnicalCheckup();
            }
        }

        private void addNewDriver() {
            String name = JOptionPane.showInputDialog(frame, "Input name:");
            String surname = JOptionPane.showInputDialog(frame, "Input surname:");
            String driverLicense = JOptionPane.showInputDialog(frame, "Input number of license:");
            String sex = JOptionPane.showInputDialog(frame, "Input sex:");
            String yearBirth = JOptionPane.showInputDialog(frame, "Input year of birth:");
            String address = JOptionPane.showInputDialog(frame, "Input address:");

            String country = JOptionPane.showInputDialog(frame, "Input country:");
            String city = JOptionPane.showInputDialog(frame, "Input city:");
            String street = JOptionPane.showInputDialog(frame, "Input street:");
            String house = JOptionPane.showInputDialog(frame, "Input house:");
            String flat = JOptionPane.showInputDialog(frame, "Input flat:");

            if (name == null ||
                    surname == null ||
                    driverLicense == null ||
                    sex == null ||
                    yearBirth == null ||
                    country == null ||
                    city == null ||
                    street == null ||
                    house == null ||
                    flat == null) {
                JOptionPane.showMessageDialog(frame, "Incorrect values!!!");
                return;
            }
            if (name.trim().isEmpty() ||
                    surname.trim().isEmpty() ||
                    driverLicense.trim().isEmpty() ||
                    sex.trim().isEmpty() ||
                    yearBirth.trim().isEmpty() ||
                    address.trim().isEmpty() ||
                    city.trim().isEmpty() ||
                    country.trim().isEmpty() ||
                    street.trim().isEmpty() ||
                    flat.trim().isEmpty() ||
                    house.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Incorrect values!!!");
                return;
            }

            controller.addDriver(name,
                    surname,
                    driverLicense,
                    sex,
                    yearBirth,
                    country,
                    city,
                    street,
                    house,
                    flat);
        }

        private void addNewCar() {
            String number = JOptionPane.showInputDialog(frame, "Input number:");
            String regCertificate = JOptionPane.showInputDialog(frame, "Input number of certificate:");
            String engineNumber = JOptionPane.showInputDialog(frame, "Input number of engine:");
            String color = JOptionPane.showInputDialog(frame, "Input color:");
            String model = JOptionPane.showInputDialog(frame, "Input model:");

            if (number == null ||
                    regCertificate == null ||
                    engineNumber == null ||
                    color == null ||
                    model == null) {
                JOptionPane.showMessageDialog(frame, "Incorrect values!!!");
                return;
            }

            if (number.trim().isEmpty() ||
                    regCertificate.trim().isEmpty() ||
                    engineNumber.trim().isEmpty() ||
                    color.trim().isEmpty() ||
                    model.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Incorrect values!!!");
                return;
            }
            controller.addCar(number,
                    regCertificate,
                    engineNumber,
                    color,
                    model);
        }

        private void addNewGaiEmployee() {
            String name = JOptionPane.showInputDialog(frame, "Input name:");
            String surname = JOptionPane.showInputDialog(frame, "Input surname:");
            String rank = JOptionPane.showInputDialog(frame, "Input rank:");
            String position = JOptionPane.showInputDialog(frame, "Input position:");

            if (name == null ||
                    surname == null ||
                    rank == null ||
                    position == null) {
                JOptionPane.showMessageDialog(frame, "Incorrect values!!!");
                return;
            }

            if (name.trim().isEmpty() ||
                    surname.trim().isEmpty() ||
                    rank.trim().isEmpty() ||
                    position.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Incorrect values!!!");
                return;
            }
            controller.addOfficer(name,
                    surname,
                    rank,
                    position);
        }

        private void addNewTechnicalCheckup() {
            new AddNewCheckupDialog(controller);
        }
    }

    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.saveToDb();
        }
    }

    class CalcCarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String firstDate = JOptionPane.showInputDialog(frame, "Input first date (1999-03-25):");
            String lastDate = JOptionPane.showInputDialog(frame, "Input last date (2018-11-27):");
            if (firstDate.trim().isEmpty() || lastDate.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Incorrect value!!!");
                return;
            }
            if (!firstDate.matches("\\d{4}-(0\\d|1[0-2])-([0-2]\\d|3[0-1])") ||
                    !lastDate.matches("\\d{4}-(0\\d|1[0-2])-([0-2]\\d|3[0-1])")) {
                JOptionPane.showMessageDialog(frame, "Incorrect value!!!");
                return;
            }
            new AnswerTableDialog(new CarTableModel(controller.getCarInTime(firstDate, lastDate)));
        }
    }

    class InfoCheckupInDateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String currentDate = JOptionPane.showInputDialog(frame, "Input need date (1999-03-25):");
            if (currentDate == null || currentDate.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Incorrect value!!!");
                return;
            }
            if (!currentDate.matches("\\d{4}-(0\\d|1[0-2])-([0-2]\\d|3[0-1])")) {
                JOptionPane.showMessageDialog(frame, "Incorrect value!!!");
                return;
            }
            new AnswerTableDialog(new CheckupTabelModel(controller.getCheckupInTime(currentDate)));
        }
    }

    class HistoryCheckupListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String number = JOptionPane.showInputDialog(frame, "Input car's number ():");
            if (number == null || number.trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Incorrect value!!!");
                return;
            }
            if (!number.matches("\\d{4}\\s[A-Z]{2}-[1-7]")) {
                JOptionPane.showMessageDialog(frame, "Incorrect value!!!");
                return;
            }
            new AnswerTableDialog(new CheckupTabelModel(controller.getCarCheckupsHistory(number)));
        }
    }
}
