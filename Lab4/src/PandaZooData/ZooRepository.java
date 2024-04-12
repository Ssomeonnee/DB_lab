package PandaZooData;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ZooRepository {
    ArrayList<Pandable> getAllPandas() throws SQLException;
    void deleteAll() throws SQLException;
    void addPanda(Pandable panda) throws SQLException;
    void changeName(Pandable panda, String name) throws SQLException;
    void removePanda(Pandable panda) throws SQLException;
}
