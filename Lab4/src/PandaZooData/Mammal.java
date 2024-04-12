package PandaZooData;
import java.io.Serializable;

abstract public class Mammal implements Pandable, Serializable {

    protected String name;
    protected String specie;
    protected  String lifestyle;
    protected String ration;
    protected String fur;
    protected String size;

    public Mammal(String name, String specie,String lifestyle, String ration,String fur,String size)
    {
        this.name=name;
        this.specie=specie;
        this.lifestyle=lifestyle;
        this.ration=ration;
        this.fur=fur;
        this.size=size;
    }
    public abstract void hunt();

    public abstract String getOnlyName();

}


