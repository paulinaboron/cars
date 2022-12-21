import com.google.gson.Gson;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Imaging {
    String fileName;

    public Imaging(String fileName) {
        this.fileName = fileName;
    }

    public String rotate() throws IOException {
        File sourceFile = new File("images/" + fileName);
        BufferedImage originalImage = ImageIO.read(sourceFile);

        BufferedImage resizedImage = Scalr.rotate(originalImage, Scalr.Rotation.CW_90);


        File targetFile = new File("images/" + fileName);
        ImageIO.write(resizedImage, "jpg", targetFile);

        resizedImage.flush();
        originalImage.flush();
        String[] data = {fileName, String.valueOf(resizedImage.getWidth()), String.valueOf(resizedImage.getHeight())};
        return new Gson().toJson(data);
    }

    public String flipV() throws IOException {
        File sourceFile = new File("images/" + fileName);
        BufferedImage originalImage = ImageIO.read(sourceFile);

        BufferedImage targetImage = Scalr.rotate(originalImage, Scalr.Rotation.FLIP_HORZ);
//BufferedImage targetImage = Scalr.rotate(originalImage, Scalr.Rotation.FLIP_VERT);

        File targetFile = new File("images/" + fileName);
        ImageIO.write(targetImage, "jpg", targetFile);

        originalImage.flush();
        targetImage.flush();
        return fileName;
    }
}
