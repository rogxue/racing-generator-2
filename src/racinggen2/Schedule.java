package racinggen2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Queue;
import racinggen2.data.TrackList;

public class Schedule {

    public Queue<RaceInfo> races = new LinkedList<>();
    public TrackList tl = new TrackList();

    public Schedule() {
        races.add(new RaceInfo(tl.daytona, "Daytona 500", 200));
    }

    public void runSchedule(Season season) throws FileNotFoundException {
        StandingsChase s = new StandingsChase(season);
        System.out.println("Running schedule...");
        int i = 1;
        while (!races.isEmpty()) {
            RaceInfo ri = races.remove();

            PrintStream out = new PrintStream(new FileOutputStream((i < 10) ? "0" + i + " " + ri.getName() + ".txt" : i + " " + ri.getName() + ".txt"));
            System.setOut(out);

            Qualifying q = new QualifyingNew(season, ri.getTrack());
            q.printResult();
            Race r = new Race(q, ri.getLaps());
//            System.out.println(r.toString());
            s.makeStandings(r);
            s.printResult();
            out.close();
            i++;
        }
    }

    public static class RaceInfo {

        private Track track;
        private String name;
        private int laps;

        public RaceInfo(Track track, String name, int laps) {
            this.track = track;
            this.name = name;
            this.laps = laps;
        }

        public Track getTrack() {
            return track;
        }

        public String getName() {
            return name;
        }

        public int getLaps() {
            return laps;
        }
    }

}
