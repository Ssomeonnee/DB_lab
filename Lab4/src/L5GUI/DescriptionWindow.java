package L5GUI;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class DescriptionWindow extends JFrame {

    protected int index;
    protected JLabel image;
    protected JLabel name;
    protected JLabel specie;
    protected JLabel description;
    protected JButton actionButton;
    protected JButton fileButton;

    public DescriptionWindow(int index)
    {
        super("Профиль панды");
        this.index = index;
        windowInit();
        componentsInitAndLocate();
        layoutInit();

        this.setVisible(true);
    }
    private void windowInit()
    {
        if (MainWindow.model.getRowClass(index)==1)
            this.setSize(1050,340);
        else
            this.setSize(790,340);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        this.setResizable(false);
    }
    private void layoutInit()
    {
        SpringLayout layout = new SpringLayout();
        // установка image
        layout.putConstraint(SpringLayout.WEST,image, 25, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, image, 25, SpringLayout.NORTH, this);
        // установка name
        layout.putConstraint(SpringLayout.WEST, name, 310, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, name, 55, SpringLayout.NORTH, this);
        // установка specie
        layout.putConstraint(SpringLayout.WEST, specie, 310, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, specie, 35, SpringLayout.NORTH, name);
        // установка description
        layout.putConstraint(SpringLayout.WEST, description, 310, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, description, 35, SpringLayout.NORTH, specie);
        // установка fileButton
        layout.putConstraint(SpringLayout.WEST, fileButton, 310, SpringLayout.WEST,this);
        layout.putConstraint(SpringLayout.NORTH, fileButton, 90, SpringLayout.NORTH, description);
        // установка actionButton
        layout.putConstraint(SpringLayout.WEST, actionButton, 165, SpringLayout.WEST,fileButton);
        layout.putConstraint(SpringLayout.NORTH, actionButton, 90, SpringLayout.NORTH, description);

        this.setLayout(layout);
    }
    private void componentsInitAndLocate()
    {
        image = new JLabel(new ImageIcon(MainWindow.model.getPhotoPath(index)));

        name = new JLabel(MainWindow.model.getRowName(index));
        name.setFont(new Font("Alias", Font.BOLD,20));

        specie = new JLabel(MainWindow.model.getRowSpecie(index));
        specie.setFont(new Font("Alias", Font.PLAIN,18));

        description = new JLabel(MainWindow.model.getRowDescription(index));

        actionButton = new JButton(MainWindow.model.getRowAction(index));
        actionButton.addActionListener(e -> new PandaAction(MainWindow.model.getGifPath(index),MainWindow.model.getRowAction(index)));

        fileButton = new JButton("Записать в файл");
        fileButton.addActionListener(e ->
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Сохранение файла");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION )
                try{
                    MainWindow.model.writeMemberToFile(fileChooser.getSelectedFile().getPath().replace('\\','/'),index);
                }
                catch (IOException ex)
                {
                    JOptionPane.showMessageDialog(this,"Выбран некорректный файл", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }
    });

        this.add(image);
        this.add(name);
        this.add(specie);
        this.add(description);
        this.add(fileButton);
        this.add(actionButton);
    }

}
