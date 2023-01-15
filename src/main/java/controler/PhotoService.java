package controler;

import model.Photo;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.util.HashMap;

public interface PhotoService {

    HashMap<String, Photo> getPhotos();
    Photo getPhotoById(String id);
    Photo getPhotoByName(String name);
    Boolean deletePhoto(String id);
    OutputStream getPhotoFile(String id) throws FileNotFoundException;
    Boolean updateName(String id, String newName);
}
