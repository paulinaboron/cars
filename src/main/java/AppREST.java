import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controler.PhotoService;
import controler.PhotoServiceImpl;
import model.Photo;
import response.ResponseEntity;
import response.ResponseStatus;
import spark.Request;
import spark.Response;

import java.io.File;
import java.util.HashMap;

import static spark.Spark.*;

public class AppREST {

    static PhotoServiceImpl photoService = new PhotoServiceImpl();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        port(7777);

        get("/api/photos", AppREST::getPhotos);
        get("/api/photos/:id", AppREST::getPhotoById);
    }

    static String getPhotos(Request req, Response res){
        HashMap<String, Photo> data = photoService.getPhotos();
        if(data != null){
            return gson.toJson(new ResponseEntity(
                    ResponseStatus.SUCCESS,
                    "list of photos",
                    gson.toJsonTree(data)
            ));
        }
        return  gson.toJson(new ResponseEntity(
                ResponseStatus.ERROR,
                "not found"
        ));
    }
    static String getPhotoById(Request req, Response res){
        String id = req.params("id");
        Photo photo = photoService.getPhotoById(id);
        if(photo != null){
            return gson.toJson(new ResponseEntity(
                    ResponseStatus.SUCCESS,
                    "photo",
                    gson.toJsonTree(photo)
            ));
        }
        return  gson.toJson(new ResponseEntity(
                ResponseStatus.ERROR,
                "not found"
        ));
    }
}
