package L5GUI;

import PandaZooData.PandaZoo;
import PandaZooData.ZooIsEmpty;
import PandaZooDataBase.DBManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;

public class MainWindow extends JFrame {

    protected JTable table;
    protected static PandasTableModel model;
    protected JMenuBar mb;
    protected JMenu fileMenu;
    protected JMenu groupMenu;
    protected JMenuItem saveMenuOption;
    protected JMenuItem readMenuOption;
    protected JMenuItem addMenuOption;
    protected JMenuItem deleteMenuOption;
    protected JMenuItem editMenuOption;
    protected JMenuItem profileMenuOption;
    protected JMenuItem dropPandasOption;
    protected JMenuItem deletePop;
    protected JMenuItem editPop;
    protected JMenuItem profilePop;
    protected JScrollPane scroll;
    protected JPopupMenu popupMenu;
    protected JButton addButton;

    protected AddWindow addWindow;
    public MainWindow()
    {
        super("Зоопарк панд");
        windowInit();
        componentsInitAndLocate();
        tableInit();
        buttonActionsInit();

        this.setVisible(true);
    }
    private void windowInit()
    {
        this.setSize(600,400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        JFrame jFrame = this;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(jFrame,
                    "Вы уверены, что хотите выйти?", "Выход", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == JOptionPane.YES_OPTION) {
                    jFrame.dispose();
                }
            }
        });
    }
    private void componentsInitAndLocate()
    {
        // меню
        mb = new JMenuBar();
        fileMenu = new JMenu("Работа с файлом");
        groupMenu = new JMenu("Управление группой");
        mb.add(fileMenu);
        mb.add(groupMenu);
        saveMenuOption = new JMenuItem("Сериализовать в файл");
        readMenuOption = new JMenuItem("Десериализовать из файла");
        fileMenu.add(saveMenuOption);
        fileMenu.add(readMenuOption);
        addMenuOption = new JMenuItem("Добавить панду");
        deleteMenuOption = new JMenuItem("Удалить панду");
        editMenuOption = new JMenuItem("Редактировать кличку");
        profileMenuOption = new JMenuItem("Открыть профиль панды");
        dropPandasOption = new JMenuItem("Сбросить всех участников");
        groupMenu.add(addMenuOption);
        groupMenu.add(deleteMenuOption);
        groupMenu.add(editMenuOption);
        groupMenu.add(profileMenuOption);
        groupMenu.add(dropPandasOption);
        this.add(BorderLayout.NORTH,mb);

        // кнопки
        addButton = new JButton("Добавить панду");
        this.add(BorderLayout.SOUTH,addButton);

        // окно добавления
        addWindow = new AddWindow();
    }
    private void tableInit()
   {
        try
        {model = new PandasTableModel(new PandaZoo(new DBManager()));}
        catch (SQLException ex)
        {
            System.out.println("TableInit");
            ex.printStackTrace();
        }
        table = new JTable();
        scroll = new JScrollPane(table);
        table.setModel(model);
        popupMenu = new JPopupMenu();
        deletePop = new JMenuItem("Удалить панду");
        editPop = new JMenuItem("Редактировать кличку");
        profilePop = new JMenuItem("Открыть профиль панды");
        popupMenu.add(deletePop);
        popupMenu.add(editPop);
        popupMenu.add(profilePop);
        table.setComponentPopupMenu(popupMenu);
        this.add(scroll);
    }
    private void buttonActionsInit()
    {
        readMenuOption.addActionListener(e-> //чтение из файла
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Выбор файла");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION )
                try{
                    model.readFromFile(fileChooser.getSelectedFile().getPath().replace('\\','/'));
                }
                catch (ClassNotFoundException ex)
                {
                    JOptionPane.showMessageDialog(this,"В файле находится нераспознаваемый класс", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }
                catch (IOException ex)
                {
                    JOptionPane.showMessageDialog(this,"Выбран некорректный файл", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }
                catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(this,"Ошибка записи в базу данных", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                    ex.printStackTrace();
                }
        });

        saveMenuOption.addActionListener(e -> //сохранение в файл
        {
                try{
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Сохранение файла");
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int result = fileChooser.showSaveDialog(this);
                    if (result == JFileChooser.APPROVE_OPTION )
                        model.writeToFile(fileChooser.getSelectedFile().getPath().replace('\\','/'));
                }
                catch (IOException ex)
                {
                    JOptionPane.showMessageDialog(this,"Некорректный ввод пути", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }
                catch (ZooIsEmpty ex)
                {
                    JOptionPane.showMessageDialog(this,"В зоопарк не добалено ни одной панды!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }
        });

        addMenuOption.addActionListener(e -> addWindow.setVisible(true)); //добавление панды через меню
        deleteMenuOption.addActionListener(e-> deletePanda()); // удаление панды через меню
        deletePop.addActionListener(e-> deletePanda());// удаление панды через попап меню
        editMenuOption.addActionListener(e -> changeName());//изменение клички через меню
        editPop.addActionListener(e -> changeName());//изменение клички через попап меню
        profileMenuOption.addActionListener(e -> openProfile());//открытие профиля панды через меню
        profilePop.addActionListener(e -> openProfile());//открытие профиля панды через попап меню
        addButton.addActionListener(e -> addWindow.setVisible(true)); //добавление панды через кнопку
        dropPandasOption.addActionListener(e -> {
            try{model.dropAll();}
            catch (SQLException ex)
            {
                JOptionPane.showMessageDialog(this,"Ошибка очистки в базе данных", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                ex.printStackTrace();
            }
        });
    }
    private void deletePanda()
    {
        try {
            int confirm = JOptionPane.showOptionDialog(this,
                    "Вы уверены, что хотите удалить панду?", "Удаление панды", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (confirm == JOptionPane.YES_OPTION) {
                try {model.deleteRow(table.getSelectedRow());}
                catch (SQLException e)
                {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this,"Ошибка удаления из базы данных!", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                };
            }
        }
        catch (IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this,"Выделите строку", "Ошибка", JOptionPane.PLAIN_MESSAGE);
        }
    }
    private void changeName()
    {
        try {
            String result = JOptionPane.showInputDialog(this, "Текущая кличка "+model.getRowName(table.getSelectedRow()), "Редактирование", JOptionPane.PLAIN_MESSAGE);
            if (result!=null && !result.isEmpty())
                model.changeName(result, table.getSelectedRow());
            else if (result.isEmpty())
                JOptionPane.showMessageDialog(this,"Кличка не может быть пустой строкой", "Ошибка", JOptionPane.PLAIN_MESSAGE);
        }
        catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,"Выделите строку", "Ошибка", JOptionPane.PLAIN_MESSAGE);
        }
        catch (NullPointerException ex) {}
        catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,"Ошибка записи в базу данных", "Ошибка", JOptionPane.PLAIN_MESSAGE);}
    }
    private void openProfile()
    {
        try {new DescriptionWindow(table.getSelectedRow());}
        catch (IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this,"Выделите строку", "Ошибка", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
