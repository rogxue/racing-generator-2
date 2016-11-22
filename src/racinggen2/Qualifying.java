package racinggen2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Qualifying {

    List<LapData> qResults = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("#.000");
    Track track;

    public Qualifying(Season season, Track track) {
        this.track = track;
        double time, temp, first = 0, second = 0;
        for (Team t : season.teams) {
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
            qResults.add(new LapData(t, time, first, second));
        }
        sortResult();
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
        double raw = t.getFunding() * 0.55 + driverStat * 0.65;
        double TEST = raw / 50 + Math.random() * 2 - 2;
        double speed = track.getMaxSpeed() + TEST;
        double time = 1 / (speed / 3600 / track.getLength());
        return time;
    }

    protected void sortResult() {
        Collections.sort(qResults, (LapData ld1, LapData ld2) -> Double.compare(ld1.time, ld2.time));
    }

    public void printResult() {
        System.out.println("====QUALIFYING RESULTS====");
        System.out.println("Pos.\tCar #\tSpeed\tBest\t1st Lap\t2nd Lap");
        int i = 1;
        for (LapData lp : qResults) {
            double speed = 1 / (lp.time / 3600 / track.getLength());
            System.out.println(i + "\t" + lp.t.getNumber() + "\t" + df.format(speed) + "\t" + df.format(lp.time) + "\t" + df.format(lp.first) + "\t" + df.format(lp.second));
            i++;
        }
    }

    public String toString() {
        String result = "";
        result += "====QUALIFYING RESULTS====" + "\n";
        result += "Pos.\tCar #\tSpeed\tBest\t1st Lap\t2nd Lap" + "\n";
        int i = 1;
        for (LapData lp : qResults) {
            double speed = 1 / (lp.time / 3600 / track.getLength());
            result += i + "\t" + lp.t.getNumber() + "\t" + df.format(speed) + "\t" + df.format(lp.time) + "\t" + df.format(lp.first) + "\t" + df.format(lp.second) + "\n";
            i++;
        }
        return result;
    }

    protected class LapData {

        private Team t;
        private double time, first, second;

        public LapData(Team t, double time, double first, double second) {
            this.t = t;
            this.time = time;
            this.first = first;
            this.second = second;
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

        public void setFirst(double first) {
            this.first = first;
        }

        public void setSecond(double second) {
            this.second = second;
        }
    }
}
