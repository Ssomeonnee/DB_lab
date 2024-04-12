package PandaZooData;
import java.util.Random;

public class PandaAnt extends Ant {

    public PandaAnt(String name, String position){
        super(name,"Муравей Панда",position, "Латиская Америка");

    }
    @Override
    public void buildAnthill()
    {
        System.out.println(
                "⠀⠀⠀⠀  ⡆⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⡠⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"+
                        "⠀⠀⠀⠀⠀⢠⣷⡀⠀⠀⠀⠀⣰⣧⣀⣠⣴⣶⣶⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"+
                        "⠀⠀⠀⠀⠀⣾⣿⣿⣆⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"+
                        "⠀⠀⠀⢰⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"+
                        "⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀"+
                        "⠀⠀⢤⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣤⣤⣤⣶⣶⠀⠀⠀⠀⠀⠀⠀\n"+
                        "⠀⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⠀⠀⠀⠀\n"+
                        "⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠶⠆⠀\n"+
                        "⡠⠤⠔⠒⠊⣩⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠁⠀⠀\n⠀"+
                        "⠀⠀⠀⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⡿⠏⠀⠀⠀⠀⠀\n"+
                        "⠀⠀⠀⠚⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"+
                        "⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"+
                        "⠀⠀⠀⠀⠀⠘⠻⣿⣿⣿⡿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀\n"+
                        "⠀⠀⠀⠀⠀⠀⠀⠘⢿⡿⠃⠀⠀⠙⠻⣿⠛⠛⠻⠿⠿⢷⡀⠀⠀⠀⠀⠀⠀⠀\n"+
                        "⠀⠀⠀⠀⠀⠀⠀⠀⠈⠇⠀⠀⠀⠀⠀⠘⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀\n");

        System.out.println("Несу лист...");
        Random random=new Random();
        try{Thread.sleep(random.nextInt(6)*1000);}
        catch (InterruptedException e){}
        System.out.println("Отнес!...");
    }

    @Override
    public String getOnlyName() {
        return this.name;
    }

    @Override
    public String getOnlyPosition() {
        return this.position;
    }

    @Override
    public void describe_panda() {
        System.out.println(specie+" (разновидность бархатных муравьёв) по кличке "+name+" :");
        System.out.println("* "+region+" - основной регион их распространения");
        System.out.println("* Занимаемая должность в муравейнике - "+position);
    }

    @Override
    public void getPhotos() {
        Reference.openReference("https://ru.pinterest.com/pin/830069775047305235/");
    }

    public String getName(){
        return "Муравей-панда "+name;
    }

    @Override
    public String getDescription() {
        return "<html>"+specie+" (разновидность бархатных муравьёв) по кличке "+name+" : "+"<br>* "+region+" - основной регион их распространения"+"<br>* Занимаемая должность в муравейнике - "+position+"<html>";
    }

    @Override
    public String getSummary() {
        return position+", место обитания - "+region;
    }
    @Override
    public String getPhotoPath() {
        return "C:/Users/Admin/IdeaProjects/Lab4/муравей.jpg";
    }
    @Override
    public String getGifPath() {
        return "C:/Users/Admin/IdeaProjects/Lab4/гиф/муравей.gif";
    }
    @Override
    public void changeName(String name) {
        this.name=name;
    }
}
