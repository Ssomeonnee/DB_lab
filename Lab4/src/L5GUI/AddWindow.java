package L5GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class AddWindow extends JFrame{

    protected JTextField nameField;
    protected JLabel name;
    protected JLabel title;
    protected JComboBox<String> comboBox;
    protected JButton addButton;
    protected JLabel tempTitle;
    protected JTextField tempText;
    protected JLabel specie;

    public AddWindow()
    {
        super("Добавление панды");
        windowInit();
        componentsInitAndLocate();
        layoutInit();
    }
    private void windowInit()
    {
        this.setSize(290,325);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        JFrame jFrame = this;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(jFrame,
                        "Вы уверены, что не хотите добавлять панду?", "Выход", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    nameField.setText("");
                    comboBox.setSelectedItem("Выберите...");
                    if (tempText.isEnabled())
                    {
                        tempText.setText("");
                        tempText.setEnabled(false);
                        tempTitle.setEnabled(false);
                    }
                    jFrame.dispose();
                }
            }
        });
        this.setResizable(false);
    }
    private void layoutInit()
    {
        SpringLayout layout = new SpringLayout();
        // установка title
        layout.putConstraint(SpringLayout.WEST, title, 50, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, title, 25, SpringLayout.NORTH, this);

        // установка name, nameField
        layout.putConstraint(SpringLayout.WEST, name, 25, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, name, 35, SpringLayout.NORTH, title);

        layout.putConstraint(SpringLayout.WEST, nameField, 10, SpringLayout.EAST, name);
        layout.putConstraint(SpringLayout.NORTH, nameField,20, SpringLayout.NORTH, name);

        // установка comboBox
        layout.putConstraint(SpringLayout.WEST,specie, 25, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, specie, 25, SpringLayout.NORTH, nameField);

        layout.putConstraint(SpringLayout.WEST,comboBox, 50, SpringLayout.WEST,specie);
        layout.putConstraint(SpringLayout.NORTH, comboBox, 20, SpringLayout.NORTH, specie);

        // установка temp
        layout.putConstraint(SpringLayout.WEST,tempTitle, 25, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, tempTitle, 35, SpringLayout.NORTH, comboBox);

        layout.putConstraint(SpringLayout.WEST,tempText, 50, SpringLayout.WEST,tempTitle);
        layout.putConstraint(SpringLayout.NORTH, tempText, 30, SpringLayout.NORTH, tempTitle);

        // установка addButton
        layout.putConstraint(SpringLayout.WEST,addButton, 95, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, addButton, 40, SpringLayout.NORTH, tempText);

        this.setLayout(layout);
    }
    private void componentsInitAndLocate()
    {
        nameField = new JTextField(15);
        name = new JLabel("Кличка");
        specie = new JLabel("Вид");
        title = new JLabel("Добавление панды в зоопарк");
        comboBox = new JComboBox<String>(new String[] {"Выберите...","Большая панда", "Красная панда","Рыба-панда","Панда-муравей"});
        comboBox.setEditable(false);
        addButton = new JButton("Добавить");
        //combobox.addItemListener(this);
        tempTitle = new JLabel("Должность в муравейнике");
        tempText = new JTextField(15);
        tempText.setEnabled(false);
        tempTitle.setEnabled(false);

        comboBox.addActionListener(e ->
        {
            if(comboBox.getSelectedItem()=="Панда-муравей")
            {
                tempText.setEnabled(true);
                tempTitle.setEnabled(true);
                tempTitle.setForeground(Color.RED);
            }

        });
        addButton.addActionListener(e ->
                {
                    if (nameField.getText().isEmpty())
                        JOptionPane.showMessageDialog(this,"Введите кличку", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                    else if (comboBox.getSelectedItem()=="Панда-муравей" && tempText.getText().isEmpty())
                        JOptionPane.showMessageDialog(this,"Введите должность в муравейнике", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                    else if (comboBox.getSelectedItem()=="Выберите...")
                        JOptionPane.showMessageDialog(this,"Выберите вид", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                    else {

                        try {
                            MainWindow.model.addRow(nameField.getText(), comboBox.getSelectedItem().toString(), tempText.getText());
                            nameField.setText("");
                            comboBox.setSelectedItem("Выберите...");
                            if (tempText.isEnabled())
                            {
                                tempText.setText("");
                                tempText.setEnabled(false);
                                tempTitle.setEnabled(false);
                            }
                            this.dispose();
                        }
                        catch (SQLException ex)
                        {
                            JOptionPane.showMessageDialog(this,"Ошибка записи в базу данных!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                            ex.printStackTrace();
                        };

                    }
                });

        this.add(name);
        this.add(nameField);
        this.add(comboBox);
        this.add(title);
        this.add(addButton);
        this.add(tempTitle);
        this.add(tempText);
        this.add(specie);
    }


}
