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
        System.out.println(season.toStringFormal());
        System.out.println("Running schedule...");
        int i = 1;
        while (!races.isEmpty()) {
            RaceInfo ri = races.remove();

            PrintStream out = new PrintStream(new FileOutputStream((i < 10) ? "0" + i + " " + ri.getName() + ".txt" : i + " " + ri.getName() + ".txt"));
            System.setOut(out);

            Qualifying q = !ri.getName().equalsIgnoreCase("Daytona 500") ? new QualifyingNew(season, ri.getTrack()) : new QualifyingDuels(season, ri.getTrack());
            q.printResult();

            Race r = new Race(q, ri.getLaps());
//            System.out.println(r.toString());
            s.makeStandings(r);
            s.printResult();
            out.close();
            i++;
        }
    }

    public void runTestSchedule(Season season) throws FileNotFoundException {
        Standings s = new Standings(season);
        RaceInfo ri = new RaceInfo(tl.daytona, "Daytona 500", 200);
//        System.out.println(season.toStringFormal());
        System.out.println("Running schedule...");
        for (int i = 0; i < 5000; i++) {
            PrintStream out = new PrintStream(new FileOutputStream("Daytona 500.txt"));
            System.setOut(out);
            Qualifying q = new QualifyingNew(season, ri.getTrack());
            q.printResult();
            Race r = new Race(q, ri.getLaps());
//            System.out.println(r.toString());
            s.makeStandings(r);
            s.printResult();
            out.close();
        }
    }

    public static class RaceInfo {

        private final Track track;
        private final String name;
        private final int laps;

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
