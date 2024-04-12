package PandaZooData;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class PandaZoo {  // сделать сериализацию
    protected ArrayList<Pandable> list = new ArrayList<>();
    protected ZooRepository zooRepository;
    public PandaZoo(ZooRepository zooRepository) throws SQLException
    {
        this.zooRepository = zooRepository;
        ArrayList<Pandable> tmp = zooRepository.getAllPandas();
        if (!(tmp==null))
            list.addAll(zooRepository.getAllPandas());
    }

    public void addPanda(Pandable panda) throws SQLException {
        zooRepository.addPanda(panda);
        list.add(panda);
    }

    public void removePanda(Pandable panda) throws SQLException{
        zooRepository.removePanda(panda);
        list.remove(panda);
    }

    public int getZooSize() {return list.size();}
    public Pandable getPanda(int index) {return list.get(index);}

    public void outputPanda()
    {
        int i=0;
        for (Pandable panda: list)
        {
            System.out.println(i+" "+panda.getName());
            i++;
        }
    }
    public void getMemberDescription(int index)
    {
        list.get(index).describe_panda();
    }
    public void getMemberPhoto(int index) {list.get(index).getPhotos();}

    public void changeMemberName(Pandable panda, String name) throws SQLException
    {
        zooRepository.changeName(panda,name);
        panda.changeName(name);
    }

    public void removeAllMembers() throws SQLException
    {
        zooRepository.deleteAll();
        list.clear();
    }
    public void makeMemberHunt()
    {
        for (Pandable panda: list) {
            if (panda instanceof Mammal) {
                System.out.println(panda.getName() + " охотится");
                ((Mammal) panda).hunt();
            }
        }
    }
    public void makeMemberSwim()
    {
        for (Pandable panda: list) {
            if (panda instanceof Fish) {
                System.out.println(panda.getName() + " плавает");
                ((Fish) panda).swim();
            }
        }
    }
    public void makeMemberBuildAntHill()
    {
        for (Pandable panda: list) {
            if (panda instanceof Ant) {
                System.out.println(panda.getName() + " строит муравейник");
                ((Ant) panda).buildAnthill();
            }
        }
    }
    public void readFromFile(String path) throws ClassNotFoundException, IOException, SQLException
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        int amount = ois.readInt();
        int c;
        for (int i = 0; i < amount; i++) {
            c=ois.readInt();
            if (c==0)
                list.add((BigPanda) ois.readObject());
            else if (c==1)
                list.add((SmallPanda)ois.readObject());
            else if (c==2)
                list.add((PandaFish)ois.readObject());
            else
                list.add((PandaAnt)ois.readObject());
            zooRepository.addPanda(list.get(list.size()-1));
        }
    }

    public void writeToFile(String path) throws IOException, ZooIsEmpty {
        if (!list.isEmpty()) {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeInt(list.size());
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) instanceof BigPanda) {
                    oos.writeInt(0);
                    oos.writeObject((BigPanda) list.get(i));
                } else if (list.get(i) instanceof SmallPanda) {
                    oos.writeInt(1);
                    oos.writeObject((SmallPanda) list.get(i));
                } else if (list.get(i) instanceof PandaFish) {
                    oos.writeInt(2);
                    oos.writeObject((PandaFish) list.get(i));
                } else {
                    oos.writeInt(3);
                    oos.writeObject((PandaAnt) list.get(i));
                }
            }
        }
        else
            throw new ZooIsEmpty("В зоопарке нет панд!");
    }
    public void writeMemberToFile(String path, int id) throws IOException
    {
       ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
        oos.writeInt(1);
        if (list.get(id) instanceof BigPanda)
        {
            oos.writeInt(0);
            oos.writeObject((BigPanda)list.get(id));
        }
        else if (list.get(id) instanceof SmallPanda)
        {
            oos.writeInt(1);
            oos.writeObject((SmallPanda)list.get(id));
        }
        else if (list.get(id) instanceof PandaFish)
        {
            oos.writeInt(2);
            oos.writeObject((PandaFish)list.get(id));
        }
        else
        {
            oos.writeInt(3);
            oos.writeObject((PandaAnt)list.get(id));
        }
    }

}
