package PandaZooData;
import java.io.Serializable;

abstract public class Fish implements Pandable, Serializable {

    protected String name; // кличка

    protected String specie;
    protected int fin_amount;
    protected String place_of_living;

    public Fish(String name, String specie, int fin_amount, String place_of_living){
        this.name=name;
        this.specie=specie;
        this.fin_amount=fin_amount;
        this.place_of_living=place_of_living;
    }


    protected abstract void  swim();

    public abstract String getOnlyName();


}
