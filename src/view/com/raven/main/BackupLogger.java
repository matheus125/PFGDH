package view.com.raven.main;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupLogger {

    private static final String LOG_PATH = "backups/backup_log.txt";

    public static void log(String mensagem) {
        try ( FileWriter fw = new FileWriter(LOG_PATH, true)) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            fw.write("[" + timestamp + "] " + mensagem + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
