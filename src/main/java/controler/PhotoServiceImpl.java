package controler;

import model.Photo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
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

    @Override
    public Photo getPhotoByName(String name) {
        for (Photo photo: data.values()) {
            if(Objects.equals(photo.getName(), name)) return photo;
        }

        return null;
    }

    @Override
    public Boolean deletePhoto(String id) {
        for (Photo photo: data.values()) {
            if(Objects.equals(photo.getId(), id)){
                data.remove(id);
                File file = new File(photo.getPath());
                return file.delete();
            }
        }
        return false;
    }

    @Override
    public OutputStream getPhotoFile(String id) throws FileNotFoundException {

        for (Photo photo: data.values()) {
            if(Objects.equals(photo.getId(), id)){
                File file = new File(photo.getPath());
                return new FileOutputStream(file);
            }
        }
        return null;
    }

    @Override
    public Boolean updateName(String id, String newName) {
        for (Photo photo: data.values()) {
            if(Objects.equals(photo.getId(), id)){
                File file = new File(photo.getPath());
                boolean success = file.renameTo(new File(newName));
                photo.setPath(file.getPath());
                photo.setName(newName);
                return success;
            }
        }
        return false;
    }
}
