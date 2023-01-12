package controler;

import model.Photo;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class PhotoServiceImpl implements PhotoService {

    public static HashMap<String, Photo> data = new HashMap<>();


    @Override
    public HashMap<String, Photo> getPhotos() {

        File folder = new File("images");
        File[] listOfFiles = folder.listFiles();

        int id = 0;
        assert listOfFiles != null;
        data.clear();
        for (File file : listOfFiles) {
            System.out.println("File " + file.getName());
            Photo photo = new Photo(String.valueOf(id), file.getName(), file.getPath());
            data.put(String.valueOf(id), photo);
            id++;
        }

        return data;
    }

    @Override
    public Photo getPhotoById(String id) {

        for (Photo photo: data.values()) {
            if(Objects.equals(photo.getId(), id)) return photo;
        }

        return null;
    }
}
