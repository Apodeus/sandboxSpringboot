package fr.pfe.visgen;

import fr.pfe.visgen.dao.VoitureDAO;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.TimerTask;


public class MyTask extends TimerTask {

    private final VoitureDAO voitureDAO;

    public MyTask(VoitureDAO voitureDAO){
        this.voitureDAO = voitureDAO;
    }

    @Override
    public void run() {
        //voitureDAO.deleteAfterTwoDays();
        Timestamp t = Timestamp.valueOf(LocalDateTime.now().minusMinutes(1));

        System.out.println("Running task ... " + t);
        voitureDAO.deleteVoituresByTimeBefore(t);
    }
}
