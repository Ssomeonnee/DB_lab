package PandaZooDataBase;

import PandaZooData.*;

import java.sql.*;
import java.util.ArrayList;

public class DBManager implements ZooRepository{
    private static Connection connection;
    private static String URL = "jdbc:sqlite:/C:\\SQLite\\sqlite-tools-win-x64-3450200\\pendasdb.db";
   @Override
    public ArrayList<Pandable> getAllPandas() throws SQLException
    {
        Connection connection = DriverManager.getConnection(URL);
        System.out.println("БД подключена!");

        Statement statement_amount = connection.createStatement();
        ResultSet result_amount = statement_amount.executeQuery("select count(id) from PandaZoo;");
        int amount = result_amount.getInt(1);
        System.out.println("Количество строк при запуске "+amount);
        result_amount.close();
        statement_amount.close();

        ArrayList<Pandable> list = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select Code, Specie, Name, Position from\n" +
                "(SELECT PandaZoo.id as Code, PandaZoo.specie as Specie,              \n" +
                "CASE \n" +
                "WHEN PandaZoo.specie = 'BigPanda' \n" +
                "THEN (SELECT name FROM BigPanda WHERE BigPanda.id=PandaZoo.id)\n" +
                "          WHEN PandaZoo.specie = 'SmallPanda' \n" +
                "THEN (SELECT name FROM SmallPanda WHERE SmallPanda.id=PandaZoo.id)\n" +
                "WHEN PandaZoo.specie = 'PandaAnt' \n" +
                "THEN (SELECT name FROM PandaAnt WHERE PandaAnt.id=PandaZoo.id)\n" +
                "WHEN PandaZoo.specie = 'PandaFish' \n" +
                "THEN (SELECT name FROM PandaFish WHERE PandaFish.id=PandaZoo.id)\n" +
                "END AS Name\n" +
                "FROM PandaZoo) as table1\n" +
                "join \n" +
                "(SELECT PandaZoo.id, \n" +
                "CASE WHEN PandaZoo.specie = 'PandaAnt' \n" +
                "THEN (SELECT position FROM PandaAnt WHERE PandaAnt.id=PandaZoo.id)\n" +
                "END AS Position\n" +
                "FROM PandaZoo) as table2 \n" +
                "on table1.Code = table2.id;");
        int i=0;
        while (i<amount) {
            result.next();
            //System.out.println(result.getInt("Code")+" "+result.getString("Name"));
            switch (result.getString("Specie")) {
                case "BigPanda":
                    list.add(new BigPanda(result.getString("Name")));
                    break;
                case "SmallPanda":
                    list.add(new SmallPanda(result.getString("Name")));
                    break;
                case "PandaAnt":
                    list.add(new PandaAnt(result.getString("Name"), result.getString("Position")));
                    break;
                case "PandaFish":
                    list.add(new PandaFish(result.getString("Name")));
                    break;
            }
            // System.out.println(list.get(list.size()-1).getName()+" "+list.get(list.size()-1).hashCode());
            i++;
        }
        result.close();
        System.out.println("Данные выгружены");

        statement.close();
        connection.close();
        System.out.println("Соединения закрыты");

        return list;
    }
    @Override
    public void deleteAll() throws SQLException
    {
        connection = DriverManager.getConnection(URL);
        System.out.println("БД подключена!");

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select count(id) from PandaZoo;");
        statement.executeUpdate("delete from BigPanda;\n" +
                "delete from SmallPanda;\n" +
                "delete from PandaAnt;\n" +
                "delete from PandaFish;\n" +
                "delete from PandaZoo;");
        result.close();
        statement.close();
        System.out.println("Данные из таблиц удалены");

        connection.close();
        System.out.println("Соединения закрыты");
    }

    @Override
    public void addPanda(Pandable panda) throws SQLException
    {
        connection = DriverManager.getConnection(URL);
        System.out.println("БД подключена!");

        Statement statement = connection.createStatement();
        if (panda instanceof BigPanda)
        {
            statement.executeUpdate("insert into PandaZoo values ("+panda.hashCode()+", 'BigPanda');");
            statement.executeUpdate("insert into BigPanda values ("+panda.hashCode()+", '"+((BigPanda)panda).getOnlyName()+"');");
            System.out.println("Большая панда записана");
        }
        else if (panda instanceof SmallPanda)
        {
            statement.executeUpdate("insert into PandaZoo values ("+panda.hashCode()+", 'SmallPanda');");
            statement.executeUpdate("insert into SmallPanda values ("+panda.hashCode()+", '"+((SmallPanda)panda).getOnlyName()+"');");
            System.out.println("Малая панда записана");
        }
        else if (panda instanceof PandaFish)
        {
            statement.executeUpdate("insert into PandaZoo values ("+panda.hashCode()+", 'PandaFish');");
            statement.executeUpdate("insert into PandaFish values ("+panda.hashCode()+", '"+((PandaFish)panda).getOnlyName()+"');");
            System.out.println("Рыба панда записана");
        }
        else
        {
            statement.executeUpdate("insert into PandaZoo values ("+panda.hashCode()+", 'PandaAnt');");
            statement.executeUpdate("insert into PandaAnt values ("+panda.hashCode()+", '"+((PandaAnt)panda).getOnlyName()+"', '"+((PandaAnt)panda).getOnlyPosition()+"');");
            System.out.println("Муравей панда записана");
        }
        statement.close();

        connection.close();
        System.out.println("Соединения закрыты");
    }

    @Override
    public void changeName(Pandable panda, String name) throws SQLException
    {
        connection = DriverManager.getConnection(URL);
        System.out.println("БД подключена!");

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select specie from PandaZoo where id="+panda.hashCode());

        switch (result.getString(1)){

            case "BigPanda":
                statement.executeUpdate("update BigPanda set name='"+name+"' where id="+panda.hashCode());
                break;
            case "SmallPanda":
                statement.executeUpdate("update SmallPanda set name='"+name+"' where id="+panda.hashCode());
                break;
            case "PandaFish":
                statement.executeUpdate("update PandaFish set name='"+name+"' where id="+panda.hashCode());
                break;
            case "PandaAnt":
                statement.executeUpdate("update PandaAnt set name='"+name+"' where id="+panda.hashCode());
                break;
        }
        System.out.println("Имя панды изменено");
        result.close();
        statement.close();

        connection.close();
        System.out.println("Соединения закрыты");
    }

    @Override
    public void removePanda(Pandable panda) throws SQLException
    {
        connection = DriverManager.getConnection(URL);
        System.out.println("БД подключена!");

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("select specie from PandaZoo where id="+ panda.hashCode());
        if (result.getString("specie").equals("BigPanda"))
            statement.executeUpdate("delete from BigPanda where id="+panda.hashCode());
        else if (result.getString("specie").equals("SmallPanda"))
            statement.executeUpdate("delete from SmallPanda where id="+panda.hashCode());
        else if (result.getString("specie").equals("PandaFish"))
            statement.executeUpdate("delete from PandaFish where id="+panda.hashCode());
        else
            statement.executeUpdate("delete from PandaAnt where id="+panda.hashCode());

        statement.executeUpdate("delete from PandaZoo where id="+panda.hashCode());
        System.out.println("Панда удалена");
        result.close();
        statement.close();

        connection.close();
        System.out.println("Соединения закрыты");
    }
}
