package ch.unibas.dmi.dbis.vrem.importer;

import ch.unibas.dmi.dbis.vrem.config.Config;
import ch.unibas.dmi.dbis.vrem.database.codec.VREMCodecProvider;
import ch.unibas.dmi.dbis.vrem.database.dao.VREMWriter;
import ch.unibas.dmi.dbis.vrem.io.readers.CSVHReader;
import ch.unibas.dmi.dbis.vrem.model.Vector3f;
import ch.unibas.dmi.dbis.vrem.model.exhibition.*;
import ch.unibas.dmi.dbis.vrem.model.objects.CulturalHeritageObject;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import com.google.gson.Gson;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Command(name = "demo-import", description = "Imports a demo exhibition.")
public class DemoImporter implements Runnable {



    public static final Logger LOGGER = LogManager.getLogger();

    public static final String FIELD_NAME_IMAGE = "\uFEFFBildcode";
    public static final String FIELD_NAME_OBJECT_NUMBER = "Inventarnummer";
    public static final String FIELD_NAME_ROOM = "Raum";
    public static final String FIELD_NAME_WALL = "Wand";
    public static final String FIELD_NAME_TITLE = "Titel";
    public static final String FIELD_NAME_DATE = "Datierung";
    public static final String FIELD_NAME_ARTIST = "Kuenstler";


    public static final String FIELD_NAME_STYLE = "Gattung";
    public static final String FIELD_NAME_MATERIAL_TECHNIK = "Material_Technik";
    public static final String FIELD_NAME_MASSANGABEN = "Massangaben";

    public static final String FIELD_NAME_X = "X";
    public static final String FIELD_NAME_Y = "Y";

    public static final int MAX_WIDTH = 1280;
    public static final int MAX_HEIGHT = 1280;

    @Option(title = "Configuration", name = {"--config", "-c"}, description = "Path to configuration file")
    private String config;


    @Option(title = "Exhibition", name = {"--exhibition", "-e"}, description = "Path to the exhibition file")
    private String exhibitionFile;

    @Override
    public void run() {
        try {
            /* Parse user input. */
            final Gson gson = new Gson();
            final String json = new String(Files.readAllBytes(Paths.get(this.config)), StandardCharsets.UTF_8);
            final Config config = gson.fromJson(json, Config.class);

            /* */
            final Path exhibitionFile = Paths.get(this.exhibitionFile);
            final Path documentRoot = config.server.getDocumentRoot();

            /* Prepare database & DAO. */
            final CodecRegistry registry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(new VREMCodecProvider()));
            final ConnectionString connectionString = config.database.getConnectionString();
            final MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(registry).applyConnectionString(connectionString).applicationName("VREM").build();

            final MongoClient client = MongoClients.create(settings);
            final MongoDatabase db = client.getDatabase(config.database.database);
            final VREMWriter writer = new VREMWriter(db);

            /* Extract exhibition name. */
            final String name = exhibitionFile.getFileName().toString().replace(".csv", "");
            final Exhibition exhibition = new Exhibition(name, "Our demo exhibition!");

            /* Prepare walls for Room 1. */
            final List<Wall> walls = new ArrayList<>();
            walls.add(new Wall(Direction.NORTH, Texture.WALLPAPER.toString()));
            walls.add(new Wall(Direction.EAST, Texture.WALLPAPER.toString()));
            walls.add(new Wall(Direction.SOUTH, Texture.WALLPAPER.toString()));
            walls.add(new Wall(Direction.WEST, Texture.WALLPAPER.toString()));

            /* Add new Room 1. */
            final Room room1 = new Room("Room 1", walls, Texture.WOOD1, Texture.STARS, new Vector3f(10.0f, 3.0f, 10.0f), Vector3f.ORIGIN, new Vector3f(0.0f, 1.0f, 0.0f));
            final Room room2 = new Room("Room 2", walls, Texture.WOOD1, Texture.STARS, new Vector3f(10.0f, 3.0f, 10.0f), Vector3f.ORIGIN, new Vector3f(0.0f, 1.0f, 0.0f));
            exhibition.addRoom(room1);
            exhibition.addRoom(room2);

            /* Parse the file and create the room. */
            try (final CSVHReader reader = new CSVHReader(Files.newBufferedReader(exhibitionFile))) {
                HashMap<String, String> next;
                while ((next = reader.readNextWithHeader()) != null) {
                    if (next.get(FIELD_NAME_ROOM).equals("1")) {
                        final String title = String.format("%s (%s)", next.get(FIELD_NAME_TITLE), next.get(FIELD_NAME_X));
                        final String description = String.format("Object number: %s\nArtist: %s\nStyle: %s", next.get(FIELD_NAME_OBJECT_NUMBER), next.get(FIELD_NAME_ARTIST), next.get(FIELD_NAME_STYLE));

                        final String destPath = String.format("%s/%d/%s/%s", exhibition.id, 1, next.get(FIELD_NAME_WALL), next.get(FIELD_NAME_IMAGE));
                        final String srcPath = String.format("%s/images/%s", next.get(FIELD_NAME_WALL), next.get(FIELD_NAME_IMAGE));

                        /* Resize and move the images. */
                        final Path imgSrcAbsPath = exhibitionFile.getParent().resolve(srcPath);
                        final Path imgDestAbsPath = documentRoot.resolve(destPath);
                        this.scaleAndCopy(imgSrcAbsPath, imgDestAbsPath);

                        final Vector3f size = this.parseSize(next.get(FIELD_NAME_MASSANGABEN));
                        final Vector3f position = new Vector3f(Float.parseFloat(next.get(FIELD_NAME_X))/10.0f, Float.parseFloat(next.get(FIELD_NAME_Y))/10.0f, 0.0f);
                        switch (next.get(FIELD_NAME_WALL)) {
                            case "N":
                                room1.getNorth().placeExhibit(new Exhibit(title, description, destPath, CulturalHeritageObject.CHOType.IMAGE, position, size, null, true));
                                break;
                            case "E":
                                room1.getEast().placeExhibit(new Exhibit(title, description, destPath, CulturalHeritageObject.CHOType.IMAGE, position, size, null, true));
                                break;
                            case "S":
                                room1.getSouth().placeExhibit(new Exhibit(title, description, destPath, CulturalHeritageObject.CHOType.IMAGE, position, size, null, true));
                                break;
                            case "W":
                                room1.getWest().placeExhibit(new Exhibit(title, description, destPath, CulturalHeritageObject.CHOType.IMAGE, position, size, null, true));
                                break;
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            /* Save exhibition. */
            writer.saveExhibition(exhibition);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param src
     * @param dst
     * @throws IOException
     */
    public void scaleAndCopy(Path src, Path dst) throws IOException {
        final BufferedImage original;
        try {
            original = ImageIO.read(src.toFile());
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, "Could not read image {} due to an exception: {}", src, e);
            return;
        }


        final BufferedImage scaled = this.resize(original, MAX_WIDTH, MAX_HEIGHT);

        /* Create folders if missing. */
        if (!Files.exists(dst.getParent())) {
            Files.createDirectories(dst.getParent());
        }

        /* Export image. */
        ImageIO.write(scaled, "jpeg", dst.toFile());
    }

    /**
     *
     * @return
     */
    public BufferedImage resize(BufferedImage original, int boundWidth, int boundHeight) {
        int new_width = 0;
        int new_height = 0;
        if (original.getWidth() < boundWidth && original.getHeight() < boundHeight) {
            return original;
        } else if (original.getHeight() >= original.getWidth()) {
            new_height = boundHeight;
            new_width = (int)(original.getWidth() * ((float)(new_height)/(float)original.getHeight()));
        } else if (original.getHeight() < original.getWidth()) {
            new_width = boundWidth;
            new_height = (int)(original.getHeight() * ((float)(new_width)/(float)original.getWidth()));
        }


        final BufferedImage scaled = new BufferedImage(new_width, new_height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D g = scaled.createGraphics();
        g.drawImage(original, 0, 0, new_width, new_height, null);
        g.dispose();
        return scaled;
    }


    /**
     *
     * @param size
     * @return
     */
    public Vector3f parseSize(String size) {
        final String[] entries = size.split("; ");

        String entry = null;
        for (String s : entries) {
            if (s.startsWith("Blattgrösse: ")) {
                entry = s.replace("Blattgrösse: ","");
                break;
            }
        }

        /* Case 1: 'Blattmass' found. */
        if (entry != null) {
            final String[] components = entry.split(" x ");
            return new Vector3f(Float.parseFloat(components[1])/100.0f, Float.parseFloat(components[0])/100.0f, 0.0f);
        }


        /* Case 2: 'Blattmass' not found. Try 'Durchmesser'. */
        for (String s : entries) {
            if (s.startsWith("Durchmesser: ")) {
                entry = s.replace("Durchmesser: ","");
                break;
            }
        }
        if (entry != null) {
            final String[] components = entry.split(" x ");
            return new Vector3f(Float.parseFloat(components[0])/100.0f, Float.parseFloat(components[0])/100.0f, 0.0f);
        }

        return Vector3f.ORIGIN;
    }
}
