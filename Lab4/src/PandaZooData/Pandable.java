package PandaZooData;
public interface Pandable {
    void describe_panda();
    void getPhotos();

    String getName();
    String getDescription();
    String getSummary();
    String getPhotoPath();
    String getGifPath();
    void changeName(String name);
}
