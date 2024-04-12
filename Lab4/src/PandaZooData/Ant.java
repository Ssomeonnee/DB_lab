package PandaZooData;
import java.io.Serializable;

abstract public class Ant implements Pandable, Serializable {

    protected String name; // кличка

    protected String specie;
    protected String position;

    protected String region;

    public Ant(String name, String specie, String position, String region){
        this.name=name;
        this.specie=specie;
        this.position=position;
        this.region=region;
    }

    abstract protected void buildAnthill();

    public abstract String getOnlyName();
    public abstract String getOnlyPosition();


}
