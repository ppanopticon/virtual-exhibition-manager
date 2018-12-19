package ch.unibas.dmi.dbis.vrem.importer;

import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TODO: write JavaDoc
 *
 * @author loris.sauter
 */
@Command(name = "mn19-import", description = "Imports a demo exhibition.")
public class MuseumNightImporter implements Runnable{

  public static final Logger LOGGER = LogManager.getLogger();

  public static final String NORTH_WALL_NAME = "north";
  public static final String EAST_WALL_NAME = "east";
  public static final String SOUTH_WALL_NAME = "south";
  public static final String WEST_WALL_NAME = "west";

  @Option(title = "Exhibition-Path", name = {"--path", "-p"}, description = "Path to the exhibition folders")
  private String exhibitionPath;

  @Option(title = "Exhibition-File", name = {"--exhibition", "-e"}, description = "Path to the exhibition file")
  private String exhibitionFile;


  @Override
  public void run() {

  }
}
