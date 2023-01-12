package controler;

import model.Photo;

import java.util.HashMap;

public interface PhotoService {

    HashMap<String, Photo> getPhotos();
    Photo getPhotoById(String id);
}
