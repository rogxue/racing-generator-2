package racinggen2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;

public class Qualifying {

    protected List<LapData> qResults = new ArrayList<>();
    protected List<LapData> dnq = new ArrayList<>();
    protected DecimalFormat df = new DecimalFormat("#.000");
    protected Track track;

    public Qualifying(List<LapData> lineup, Track track) {
        qResults = lineup;
        this.track = track;
    }

    public Qualifying(Season season, Track track) {
        this.track = track;
        double time, temp, first = 0, second = 0;
        for (Team t : season.getTeams()) {
            time = 10000;
            for (int i = 0; i < 2; i++) {
                temp = getLapTime(t);
                if (i == 0) {
                    first = temp;
                } else {
                    second = temp;
                }
                time = (temp < time) ? temp : time;
            }
            qResults.add(new LapData(t, time, first, second, t.isCharter()));

        }
        for (Team t : season.getPartTimeTeams()) {
            time = 10000;
            for (int i = 0; i < 2; i++) {
                temp = getLapTime(t);
                if (i == 0) {
                    first = temp;
                } else {
                    second = temp;
                }
                time = (temp < time) ? temp : time;
            }
            qResults.add(new LapData(t, time, first, second, t.isCharter()));
        }
        sortResult();
        removeDnq();
    }

    protected double getLapTime(Team t) {
        int driverStat = 0;

        switch (track.getType()) {
            case 0:
                driverStat = t.getDriver().getSs();
                break;
            case 1:
                driverStat = t.getDriver().getS();
                break;
            case 2:
                driverStat = t.getDriver().getSt();
                break;
            default:
                driverStat = t.getDriver().getRc();
                break;
        }
        double raw = t.getFunding() * 0.85 + driverStat * 0.55;
        double TEST = raw / 50 + Math.random() - 2.2;
        double speed = track.getMaxSpeed() + 2 * TEST - 3;
        double time = 1 / (speed / 3600 / track.getLength());
        return time;
    }

    protected void sortResult() {
        Collections.sort(qResults, (LapData ld1, LapData ld2) -> Double.compare(ld1.getTime(), ld2.getTime()));
    }

    protected void removeDnq() {
        for (int i = qResults.size() - 1; i > 0; i--) {
            boolean goodSize = qResults.size() <= 40;
//            JOptionPane.showMessageDialog(null, goodSize + " " + qResults.get(i).lockedIn);
            if (!goodSize && !qResults.get(i).isLockedIn()) {
                dnq.add(qResults.remove(i));
            }
        }
        Collections.reverse(dnq);
    }

    public List<LapData> getqResults() {
        return qResults;
    }

    public Track getTrack() {
        return track;
    }

    public void printResult() {
        System.out.println("====QUALIFYING RESULTS====");
        System.out.println("Pos.\tCar #\tSpeed\tBest\t1st Lap\t2nd Lap");
        int i = 1;
        for (LapData lp : qResults) {
            double speed = 1 / (lp.getTime() / 3600 / track.getLength());
            System.out.println(i + "\t" + lp.t.getNumber() + "\t" + df.format(speed) + "\t" + df.format(lp.getTime()) + "\t" + df.format(lp.getFirst()) + "\t" + df.format(lp.getSecond()));
            i++;
        }
        System.out.println("==DNQ==");
        for (LapData lp : dnq) {
            double speed = 1 / (lp.getTime() / 3600 / track.getLength());
            System.out.println(i + "\t" + lp.t.getNumber() + "\t" + df.format(speed) + "\t" + df.format(lp.getTime()) + "\t" + df.format(lp.getFirst()) + "\t" + df.format(lp.getSecond()));
            i++;
        }
    }

    public String toString() {
        String result = "";
        result += "====QUALIFYING RESULTS====" + "\n";
        result += "Pos.\tCar #\tSpeed\tBest\t1st Lap\t2nd Lap" + "\n";
        int i = 1;
        for (LapData lp : qResults) {
            double speed = 1 / (lp.getTime() / 3600 / track.getLength());
            result += i + "\t" + lp.t.getNumber() + "\t" + df.format(speed) + "\t" + df.format(lp.getTime()) + "\t" + df.format(lp.getFirst()) + "\t" + df.format(lp.getSecond()) + "\n";
            i++;
        }
        result += "==DNQ==" + "\n";
        for (LapData lp : dnq) {
            double speed = 1 / (lp.getTime() / 3600 / track.getLength());
            result += i + "\t" + lp.t.getNumber() + "\t" + df.format(speed) + "\t" + df.format(lp.getTime()) + "\t" + df.format(lp.getFirst()) + "\t" + df.format(lp.getSecond());
            i++;
        }
        return result;
    }

    protected class LapData {

        private Team t;
        private double time, first, second;
        private boolean lockedIn = true;

        public LapData(Team t, double time, double first, double second) {
            this.t = t;
            this.time = time;
            this.first = first;
            this.second = second;
        }

        public LapData(Team t, double time, double first, double second, boolean lockedIn) {
            this.t = t;
            this.time = time;
            this.first = first;
            this.second = second;
            this.lockedIn = lockedIn;
        }

        public Team getTeam() {
            return t;
        }

        public double getTime() {
            return time;
        }

        public void setTime(double time) {
            this.time = time;
        }

        public double getFirst() {
            return first;
        }

        public void setFirst(double first) {
            this.first = first;
        }

        public double getSecond() {
            return second;
        }

        public void setSecond(double second) {
            this.second = second;
        }

        public boolean isLockedIn() {
            return lockedIn;
        }
    }
}
