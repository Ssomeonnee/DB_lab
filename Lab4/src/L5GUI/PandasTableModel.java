package L5GUI;

import javax.swing.table.AbstractTableModel;

import PandaZooData.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class PandasTableModel extends AbstractTableModel {

    protected static PandaZoo data;
    public PandasTableModel(PandaZoo zoo){
        data = zoo;
    }

    @Override
    public int getRowCount() {
        return data.getZooSize();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int column) {
        if (column==0)
            return "Кличка";
        if (column==1)
            return "Вид";
        if (column==2)
            return "Описание";
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex==2)
            return getRowSummary(rowIndex);
        else {
            if (columnIndex==0)
                return getRowName(rowIndex);
            else {
                return getRowSpecie(rowIndex);
            }
        }
    }

    public void deleteRow(int index) throws SQLException {
        data.removePanda(data.getPanda(index));
        fireTableDataChanged();
    }

    public void dropAll() throws SQLException
    {
        data.removeAllMembers();
        fireTableDataChanged();
    }
    public void addRow (String name, String specie, String job) throws SQLException
    {
        switch (specie)
        {
            case "Большая панда":
                data.addPanda(new BigPanda(name));
                break;
            case "Красная панда":
                data.addPanda(new SmallPanda(name));
                break;
            case "Рыба-панда":
                data.addPanda(new PandaFish(name));
                break;
            case "Панда-муравей":
                data.addPanda(new PandaAnt(name,job));
                break;
        }
        fireTableDataChanged();
    }

    public String getRowName(int index)
    {
        ArrayList<String> word = new ArrayList<>(Arrays.asList(data.getPanda(index).getName().split(" ")));
        word.remove(0);
        if (!(data.getPanda(index) instanceof PandaAnt))
        {
            word.remove(0);
        }
        return word.stream().collect(Collectors.joining(" "));
    }
    public String getRowSpecie(int index)
    {
        String[] word=data.getPanda(index).getName().split(" ");
        if (word.length==3)
            return word[0]+" "+word[1];
        else
            return word[0];
    }
    public String getRowSummary(int index)
    {
        return data.getPanda(index).getSummary();
    }
    public String getRowDescription(int index){return data.getPanda(index).getDescription();}

    public int getRowClass(int index)
    {
        if (data.getPanda(index) instanceof Mammal)
            return 1;
        else if (data.getPanda(index) instanceof Fish)
            return 0;
        else
            return 2;
    }

    public String getRowAction(int index)
    {
        switch (getRowClass(index))
        {
            case 1: return "Охотиться";
            case 0: return "Плавать";
            case 2: return "Строить муравейник";
            default: return null;
        }

    }
    public String getPhotoPath(int index) {return data.getPanda(index).getPhotoPath();}
    public String getGifPath(int index){return data.getPanda(index).getGifPath();}

    public void changeName(String name,int index) throws SQLException
    {
        data.changeMemberName(data.getPanda(index),name);
        fireTableDataChanged();
    }
    public void writeToFile(String path) throws IOException, ZooIsEmpty
    {data.writeToFile(path);}
    public void readFromFile(String path) throws ClassNotFoundException, IOException, SQLException
    {
        data.readFromFile(path);
        fireTableDataChanged();
    }
    public void writeMemberToFile(String path, int index) throws IOException
    {data.writeMemberToFile(path,index);}

}
