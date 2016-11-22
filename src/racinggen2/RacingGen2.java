package racinggen2;

import java.io.FileNotFoundException;
import racinggen2.data.*;

public class RacingGen2 {

    public static void main(String[] args) {
        try {
            Schedule schedule = new Schedule2016();
            schedule.runSchedule(new ClassOf2016());
        } catch (FileNotFoundException ex) {
        }
    }
}
