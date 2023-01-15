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
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;

import static spark.Spark.*;

public class AppREST {

    static PhotoServiceImpl photoService = new PhotoServiceImpl();
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        port(7777);

        get("/api/photos", AppREST::getPhotos);
        get("/api/photos/:id", AppREST::getPhotoById);
        get("/api/photos/photo/:name", AppREST::getPhotoByName);
        delete("/api/photos/:id", AppREST::deletePhoto);
        get("/api/photos/data/:id", AppREST::getPhotoFile);
        put("/api/photos/:id", AppREST::updateName);
    }

    static String updateName(Request req, Response res) {
        res.header("Access-Control-Allow-Origin", "*");
        res.type("application/json");
        String name = (String) gson.fromJson(req.body(), HashMap.class).get("name");
        System.out.println(name);

        if(photoService.updateName(req.params("id"), name)){
            return gson.toJson(new ResponseEntity(
                    ResponseStatus.SUCCESS,
                    "photo",
                    gson.toJsonTree(photoService.getPhotoById(req.params("id")))
            ));
        }

        return  gson.toJson(new ResponseEntity(
                ResponseStatus.ERROR,
                "file rename error"
        ));
    }

    static OutputStream getPhotoFile(Request req, Response res) throws FileNotFoundException {
        res.header("Access-Control-Allow-Origin", "*");
        res.type("image/jpeg");

        return photoService.getPhotoFile(req.params("id"));
    }

    static String getPhotos(Request req, Response res){
        res.header("Access-Control-Allow-Origin", "*");
        res.type("application/json");

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
        res.header("Access-Control-Allow-Origin", "*");
        res.type("application/json");

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

    static String getPhotoByName(Request req, Response res) {
        res.header("Access-Control-Allow-Origin", "*");
        res.type("application/json");

        Photo photo = photoService.getPhotoByName(req.params("name"));
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
    static String deletePhoto(Request req, Response res){
        if(photoService.deletePhoto(req.params("id"))){
            return gson.toJson(new ResponseEntity(
                    ResponseStatus.SUCCESS,
                    "photo deleted"
            ));
        }
        return  gson.toJson(new ResponseEntity(
                ResponseStatus.ERROR,
                "not found"
        ));
    }
}
