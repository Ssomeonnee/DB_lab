package MainPandaZoo;
import L5GUI.MainWindow;
import PandaZooData.*;
import PandaZooDataBase.DBManager;

import java.sql.*;
import java.util.ArrayList;

public class L4_6 {

    public static void main(String[] args) {

        new MainWindow();

        ArrayList<Pandable> list = new ArrayList<>();
        list.add(new BigPanda("1"));
        list.add(new BigPanda("1"));
        System.out.println(list.get(0).hashCode());
        System.out.println(list.get(1).hashCode());


       try{
            ZooRepository zooRepository = new DBManager();
            ArrayList<Pandable> list1 = zooRepository.getAllPandas();
/*
            for (Pandable panda: list)
            {
                System.out.println(list.get(list.indexOf(panda)).getName()+" "+list.get(list.indexOf(panda)).hashCode());
            }*/

            /*Connection connection = DriverManager.getConnection("jdbc:sqlite:/C:\\SQLite\\sqlite-tools-win-x64-3450200\\pendasdb.db");
            System.out.println("БД подключена!");

            Statement statement2 = connection.createStatement();
            ResultSet result1 = statement2.executeQuery("select count(id) from PandaZoo;");
            int amount = result1.getInt(1);
            result1.close();
            statement2.close();

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
            int id=9000;
            int i=1;
            Statement statement1 = connection.createStatement();
            while (i<=amount) {
                result.next();
                System.out.println(result.getInt("Code")+" "+result.getString("Name"));
                String specie="";
                String position="";
                switch (result.getString("Specie")) {
                    case "BigPanda":
                        specie="BigPanda";
                        *//*statement1.addBatch("delete from BigPanda where id="+result.getInt("Code"));
                        statement1.addBatch("delete from PandaZoo where id="+result.getInt("Code"));
                        statement1.addBatch("insert into PandaZoo values ("+id+", 'BigPanda')");
                        statement1.addBatch("insert into BigPanda values ("+id+", '"+result.getString("Name")+"')");
                        statement1.executeBatch();*//*
                        break;

                   case "SmallPanda":
                       specie="SmallPanda";
                        *//*statement1.addBatch("delete from SmallPanda where id="+result.getInt("Code"));
                        statement1.addBatch("delete from PandaZoo where id="+result.getInt("Code"));
                        statement1.addBatch("insert into PandaZoo values ("+id+", 'SmallPanda')");
                        statement1.addBatch("insert into SmallPanda values ("+id+", '"+result.getString("Name")+"')");
                        statement1.executeBatch();*//*
                        break;

                    case "PandaAnt":
                        specie="PandaAnt";
                        position="', '"+result.getString("Position");
                        *//*statement1.addBatch("delete from PandaAnt where id="+result.getInt("Code"));
                        statement1.addBatch("delete from PandaZoo where id="+result.getInt("Code"));
                        statement1.addBatch("insert into PandaZoo values ("+id+", 'PandaAnt')");
                        statement1.addBatch("insert into PandaAnt values ("+id+", '"+result.getString("Name")+"', '"+result.getString("Position")+"')");
                        statement1.executeBatch();*//*
                        break;
                    case "PandaFish":
                        specie="PandaFish";

                        *//*statement1.addBatch("delete from PandaFish where id="+result.getInt("Code"));
                        statement1.addBatch("delete from PandaZoo where id="+result.getInt("Code"));
                        statement1.addBatch("insert into PandaZoo values ("+id+", 'PandaFish')");
                        statement1.addBatch("insert into PandaFish values ("+id+", '"+result.getString("Name")+"')");
                        statement1.executeBatch();*//*
                        break;
                }
                statement1.addBatch("delete from "+specie+" where id="+result.getInt("Code"));
                statement1.addBatch("delete from PandaZoo where id="+result.getInt("Code"));
                statement1.addBatch("insert into PandaZoo values ("+id+", '"+specie+"')");
                statement1.addBatch("insert into "+specie+" values ("+id+", '"+result.getString("Name")+position+"')");
                statement1.executeBatch();
                id++;
                i++;


            }
            statement1.close();
           result.close();*/
            /*System.out.println("11111111111111111");
            while (result.next()) {

                System.out.println("222222222222");
                switch (result.getString("Specie"))
                {
                    case "BigPanda":
                        System.out.println("33333333333333333");
                        list.add(new BigPanda(result.getString("Name")));
                        System.out.println((list.get(list.size()-1)).getName());
                        System.out.println((list.get(list.size()-1)).hashCode());
                        int code = result.getInt("Code");
                        statement.executeUpdate("delete from PandaZoo where id="+code);
                        statement.executeUpdate("delete from BigPanda where id="+code);
                        statement.executeUpdate("insert into PandaZoo values ("+(list.get(list.size()-1)).hashCode()+", 'BigPanda');");
                        statement.executeUpdate("insert into BigPanda values ("+(list.get(list.size()-1)).hashCode()+", '"+((BigPanda)list.get(list.size()-1)).getOnlyName()+"');");
                        //statement.executeUpdate("update PandaZoo set id="+(list.get(list.size()-1)).hashCode()+" where id="+result.getString("Code"));
                        //statement.executeUpdate("update BigPanda set id="+(list.get(list.size()-1)).hashCode()+" where id="+result.getString("Code"));
                        break;
                    case "SmallPanda":
                        list.add(new SmallPanda(result.getString("Name")));
                        statement.executeUpdate("update PandaZoo set id="+(list.get(list.size()-1)).hashCode()+" where id="+result.getString("Code"));
                        statement.executeUpdate("update SmallPanda set id="+(list.get(list.size()-1)).hashCode()+" where id="+result.getString("Code"));
                        break;
                    case "PandaAnt":
                        list.add(new PandaAnt(result.getString("Name"), result.getString("Position")));
                        statement.executeUpdate("update PandaZoo set id="+(list.get(list.size()-1)).hashCode()+" where id="+result.getString("Code"));
                        statement.executeUpdate("update PandaAnt set id="+(list.get(list.size()-1)).hashCode()+" where id="+result.getString("Code"));
                        break;
                    case "PandaFish":
                        list.add(new PandaFish(result.getString("Name")));
                        statement.executeUpdate("update PandaZoo set id="+(list.get(list.size()-1)).hashCode()+" where id="+result.getString("Code"));
                        statement.executeUpdate("update PandaFish set id="+(list.get(list.size()-1)).hashCode()+" where id="+result.getString("Code"));
                        break;
                }

            }*/
          /*  statement.close();

            connection.close();
            System.out.println("Соединения закрыты");*/

        }
        catch (SQLException ex)
        {ex.printStackTrace();}



        //new MainWindow();

        //ArrayList<String> list = new ArrayList<>(); //не может быть одинаковыых эементов

       /* ArrayList<Pandable> list = new ArrayList<>();

      //  BigPanda panda = new BigPanda("Masha");
      //  BigPanda panda1 = new BigPanda("Sasha");



        try{
            Connection connection = DriverManager.getConnection("jdbc:sqlite:/C:\\SQLite\\sqlite-tools-win-x64-3450200\\temporarydb.db");
            ArrayList<BigPanda> list = new ArrayList<>();

            BigPanda panda = new BigPanda("Masha");
            System.out.println(panda.hashCode());

            Statement statement2 = connection.createStatement();
            statement2.executeUpdate("insert into PandaZoo values ("+panda.hashCode()+", 'BigPanda');");

            Statement statement = connection.createStatement();
             //result = statement.executeQuery("select count(id) from PandaZoo;");
            ResultSet result = statement.executeQuery("select id, Specie from PandaZoo");


            while (result.next())
            {
                list.add(new BigPanda(result.getString("Specie")));
                //Statement statement3 = connection.createStatement();
                System.out.println(result.getString("id")+" считывание из базы");
                System.out.println((list.get(list.size()-1)).hashCode()+" должно записаться");
                statement.executeUpdate("update PandaZoo set id="+(list.get(list.size()-1)).hashCode()+" where id="+result.getString("id"));
                

            }

            System.out.println("list "+list.get(0).hashCode()+" "+list.get(0));
            //System.out.println("list "+list.get(list.size()-1).hashCode()+" "+list.get(0));

            ArrayList<Pandable> list1 = new ArrayList<>();

            list1.addAll(list);
            System.out.println("list1 "+list1.get(0).hashCode()+" "+list1.get(0));

           Statement statement1 = connection.createStatement();
            ResultSet result1= statement1.executeQuery("select id, Specie from PandaZoo where id="+list1.get(0).hashCode()+";");

            System.out.println(result1.getString(1)+" "+result1.getString(2));








        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }


*/

        /*ArrayList<Pandable> list = new ArrayList<>();
        list.add(new BigPanda("Masha"));
        list.add(new BigPanda("Dasha"));
        list.add(new BigPanda("Sasha"));


        ArrayList<Pandable> list1 = new ArrayList<>();
        list1.addAll(list);
        list.add(new BigPanda("Glasha"));
        for (Pandable panda: list)
        {
            System.out.println(list.get(list.indexOf(panda)).hashCode());
        }


        for (Pandable panda: list1)
        {
            System.out.println(list1.get(list1.indexOf(panda)).hashCode());
        }
*/
        //BigPanda panda2 = new BigPanda("Masha");
        //System.out.println(new BigPanda("Masha").hashCode());
        //System.out.println(new BigPanda("Masha").hashCode());
        //System.out.println(new BigPanda("Dima").hashCode());

        // открывается 2 окна, не считывает из файла нормально

      //  System.out.println(new BigPanda("Masha").getOnlyName());

       /*try {
            PandaZooRepository.connectBD();
            PandaZooRepository.addPanda(new BigPanda("Masha"));
        }
        catch (SQLException e)
        {
            System.out.println("jib,rf");
        };*/



    }
}






