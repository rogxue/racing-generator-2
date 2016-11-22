package racinggen2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Race {

    List<RaceData> race = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("0.000");
    DecimalFormat dfMin = new DecimalFormat("00");
    DecimalFormat dfSec = new DecimalFormat("00.000");
    int totalLaps, cautions = 0, leadChanges = 0;
    double lapDownTime;
    Track track;
    String result = "====RACE====\r\n";

    public Race(Qualifying q, int laps) {
        System.out.println("====RACE====");
        track = q.getTrack();
        totalLaps = laps;
        lapDownTime = 1 / (track.getMaxSpeed() / 3600 / track.getLength());
        initializeStart(q);
        runRace();
    }

    private void initializeStart(Qualifying q) {
        double timeBehind;
        for (int i = 0; i < q.getqResults().size(); i++) {
            timeBehind = (i == 0) ? 0 : (i % 2 == 0) ? ((int) i / 2) * 0.2 - Math.random() * 0.02 : ((int) i / 2) * 0.2 + Math.random() * 0.02;
            race.add(new RaceData(q.getqResults().get(i).getTeam(), i + 1, timeBehind));
        }
    }

    private void runRace() {
        printResult();
        do {
            RaceData causedCaution = null;
            boolean caution = false;
            Team leader = race.get(0).getTeam();
            int pos = 1;
            for (RaceData rd : race) {
                if (rd.isActive()) {
                    rd.newLap(getLapTime(rd) + dirtyAir(pos));
                    if (rd.troubleRoll()) {
                        causedCaution = rd;
                        caution = true;
                        if (!race.get(0).isActive()) {     // if leader crashed
//                            JOptionPane.showMessageDialog(null, track.getName() + " " + race.get(0).getTeam().getNumber() + " trouble");

//                            int j = 1;
//                            while (!race.get(0).isActive) {
//                                double lead = race.get(j).time;
//                                race.get(j - 1).lapsLead--;
//                                race.get(j).lapsLead++;
//                                race.get(0).time = lead + lapDownTime * Math.random();
//                                race.add(0, race.remove(j));
//                                j++;
//                            }
                        }
                    }
                }
                pos++;
            }
            sortResult();
            race.get(0).leadLap();
            if (!race.get(0).getTeam().equals(leader)) {
                leadChanges++;
            }
            if (caution == true) {
                throwCaution(causedCaution);
            } else if (Math.random() < 0.0075) {
                throwDebrisCaution();
            }
            if (!race.get(0).isActive()) {     // if leader crashed
                int j = 1;
                while (!race.get(0).isActive()) {
                    double lead = race.get(j).getTime();
                    race.get(j - 1).lapsLead--;
                    race.get(j).leadLap();
                    race.get(0).setTime(lead + lapDownTime * Math.random());
                    race.add(0, race.remove(j));
                    j++;
                }
            }
            printResult();
        } while (race.get(0).getLaps() < totalLaps);
        printRaceReport();
    }

    private void throwCaution(RaceData causedCaution) {
        boolean luckyDog = true;
        double cautionTime = 1 / (track.getCautionSpeed() / 3600 / track.getLength());
        cautions++;
        System.out.println("CAUTION " + cautions + " THROWN ON LAP " + race.get(0).getLaps());
        result += "CAUTION " + cautions + " THROWN ON LAP " + race.get(0).getLaps() + "\r\n";
        double relTime = causedCaution.getTime() % lapDownTime;
        int involved = 1;
        for (RaceData rd : race) {
            if (!rd.isActive() || rd.equals(causedCaution)) {
                continue;
            }
            if (rd.getTime() % lapDownTime - relTime > 0 && rd.getTime() % lapDownTime - relTime < 4) {
                involved = rd.involvedRoll(rd.getTime() % lapDownTime - relTime) ? involved + 1 : involved;
            } else if (rd.getTime() % lapDownTime + lapDownTime - relTime > 0 && rd.getTime() % lapDownTime + lapDownTime - relTime < 4) {
                involved = rd.involvedRoll(rd.getTime() % lapDownTime + lapDownTime - relTime) ? involved + 1 : involved;
            }
        }
        System.out.println(involved + " cars involved.");
//        if (involved > 11) {
//            JOptionPane.showMessageDialog(null, track.getName() + " " + involved);
//        }
        double lead = race.get(0).getTime();
        if (!race.get(0).isActive()) {     // if leader crashed
//            try {
//                if (!race.get(0).isActive) {
//                    throw new Exception();
//                }
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(null, track.getName() + " " + race.get(0).t.getNumber());
//            }
            int j = 1;
            while (!race.get(0).isActive()) {
                lead = race.get(j).getTime();
                race.get(j - 1).lapsLead--;
                race.get(j).leadLap();
                race.get(0).setTime(lead + lapDownTime * Math.random());
                race.add(0, race.remove(j));
                j++;
            }
        }
        for (int i = 0; i < race.size(); i++) {
            double timeBehind = (i % 2 == 0) ? ((int) i / 2) * 0.2 + Math.random() * 0.02 : ((int) i / 2) * 0.2 - Math.random() * 0.02;
            int lostLaps;
            if (race.get(i).isActive()) {
                if (race.get(i).getTime() - lead > lapDownTime) {
                    lostLaps = (int) ((race.get(i).getTime() - lead) / lapDownTime);
                    if (luckyDog == true) {
                        luckyDog = false;
                        if (lostLaps > 0 && !race.get(i).equals(causedCaution)) {
                            race.get(i).setTime(race.get(0).getTime() + timeBehind);
                            race.get(i).addTime(lapDownTime * (lostLaps - 1));
                        }
                    } else {
                        race.get(i).setTime(race.get(0).getTime() + timeBehind);
                        race.get(i).addTime(lapDownTime * lostLaps);
                    }
                } else {
                    race.get(i).setTime(race.get(0).getTime() + timeBehind);
                }
            } else if (race.get(i).getLaps() == race.get(0).getLaps()) {
                race.get(i).setInactiveLaps(race.get(i).getLaps() - (int) ((race.get(i).getTime() - lead) / lapDownTime));
            }
        }
        for (int j = 0; j < 5; j++) {
            sortResult();
            printResult();
            race.stream().filter((rd) -> (rd.isActive())).forEach((rd) -> {
                rd.newLap(cautionTime + Math.random() * 0.01);
            });
            race.get(0).leadLap();
        }
        if (totalLaps - race.get(0).getLaps() < 2) {     // GWC
            totalLaps = race.get(0).getLaps() + 2;
        }
    }

    private void throwDebrisCaution() {
        boolean luckyDog = true;
        double cautionTime = 1 / (track.getCautionSpeed() / 3600 / track.getLength());
        cautions++;
        System.out.println("DEBRIS CAUTION " + cautions + " THROWN ON LAP " + race.get(0).getLaps());
        result += "DEBRIS CAUTION " + cautions + " THROWN ON LAP " + race.get(0).getLaps() + "\r\n";

        for (int i = 0; i < race.size(); i++) {
            double timeBehind = (i % 2 == 0) ? ((int) i / 2) * 0.2 + Math.random() * 0.02 : ((int) i / 2) * 0.2 - Math.random() * 0.02;
            double lead = race.get(0).getTime();
            int lostLaps;
            if (race.get(i).isActive()) {
                if (race.get(i).getTime() - lead > lapDownTime) {
                    lostLaps = (int) ((race.get(i).getTime() - lead) / lapDownTime);
                    if (luckyDog == true) {
                        luckyDog = false;
                        if (lostLaps > 0) {
                            race.get(i).setTime(race.get(0).getTime() + timeBehind);
                            race.get(i).addTime(lapDownTime * (lostLaps - 1));
                        }
                    } else {
                        race.get(i).setTime(race.get(0).getTime() + timeBehind);
                        race.get(i).addTime(lapDownTime * lostLaps);
                    }
                } else {
                    race.get(i).setTime(race.get(0).getTime() + timeBehind);
                }
            }
        }
        for (int j = 0; j < 5; j++) {
            sortResult();
            printResult();
            race.stream().filter((rd) -> (rd.isActive())).forEach((rd) -> {
                rd.newLap(cautionTime);
            });
            race.get(0).leadLap();
        }
        if (totalLaps - race.get(0).getLaps() < 2) {     // GWC
            totalLaps = race.get(0).getLaps() + 2;
        }
    }

    private double getLapTime(RaceData rd) {
        int driverStat;

        switch (track.getType()) {
            case 0:
                driverStat = rd.t.getDriver().getSs();

                double raw = rd.t.getFunding() * 0.04 + driverStat * 0.08 - rd.getDamage();
                double TEST = raw / 50 - Math.random() * 1.6;
                double speed = track.getMaxSpeed() + TEST;
                double time = 1 / (speed / 3600 / track.getLength());

                return time;
            case 1:
                driverStat = rd.t.getDriver().getS();
                break;
            case 2:
                driverStat = rd.t.getDriver().getSt();
                break;
            default:
                driverStat = rd.t.getDriver().getRc();
                break;
        }
        double raw = rd.t.getFunding() * 0.64 + driverStat * 0.54 - rd.getDamage();
        double TEST = raw / 50 - Math.random() * 2.2;
        double speed = track.getMaxSpeed() + 6 * TEST - 5;
        double time = 1 / (speed / 3600 / track.getLength());

        return time;
    }

    private double dirtyAir(int pos) {
        if (pos == 1 && track.getType() != 0) {
            return -0.05;
        } else {
            return 0;
        }
        //return (track.getType() == 0) ? 0 : Math.sqrt(pos) / 3 * Math.random();
    }

    public void sortResult() {
        double leadTime = race.get(0).getTime();
        Collections.sort(race, (RaceData rd1, RaceData rd2) -> Double.compare(rd1.getTime(), rd2.getTime()));
//        Collections.sort(race, (RaceData rd1, RaceData rd2) -> Integer.compare(rd2.laps, rd1.laps));
        Collections.sort(race, (RaceData rd1, RaceData rd2) -> Integer.compare(calcActualLap(rd2, leadTime), calcActualLap(rd1, leadTime)));

    }

//    private void printResult() {
//            result += "Lap " + race.get(0).laps + "\r\n";
//        result += "Pos.\tStart\tCar #\tTime\t\tLaps\tBehind\tLaps Lead" + "\r\n";
//        int i = 1;
//        for (RaceData rd : race) {
//            result += i + "\t" + rd.start + "\t" + rd.t.getNumber()
//                    + "\t" + getFormatTime(rd.time) + "\t" + calcLap(rd) + "\t"
//                    + calcBehindToString(rd) + "\t" + rd.lapsLead + "\r\n";
//            i++;
//        }
//    }
    private void printResult() {
        System.out.println("Lap " + race.get(0).getLaps());
        System.out.println("Pos.\tStart\tCar #\tTime\t\tLaps\tBehind\tLaps Lead");
        int i = 1;
        for (RaceData rd : race) {
            System.out.println(i + "\t" + rd.getStart() + "\t" + rd.t.getNumber()
                    + "\t" + getFormatTime(rd.getTime()) + "\t" + calcLap(rd) + "\t"
                    + calcBehindToString(rd) + "\t" + rd.getLapsLead());
            i++;
        }
    }

    private void printRaceReport() {
        System.out.println(totalLaps + " Laps Compeleted");
        System.out.println("Cautions: " + cautions);
        System.out.println("Lead Changes: " + leadChanges);

    }

    private int calcLap(RaceData rd) {
        if (rd.isActive()) {
            if (rd.getTime() - race.get(0).getTime() > lapDownTime) {
                return rd.getLaps() - (int) ((rd.getTime() - race.get(0).getTime()) / lapDownTime);
            }
        }
        return rd.getLaps();
    }

    private int calcActualLap(RaceData rd, double leadTime) {
        if (rd.isActive()) {
            if (rd.getTime() - leadTime > lapDownTime) {
                return rd.getLaps() - (int) ((rd.getTime() - race.get(0).getTime()) / lapDownTime);
            }
        }
        return rd.getLaps();
    }
//    private Number calcBehind(RaceData rd) {
//        int laps = calcLap(rd);
//        if (laps == race.get(0).laps) {
//            return (double) race.get(0).time - rd.time;
//        } else {
//            return (int) laps - race.get(0).laps;
//        }
//    }

    private String calcBehindToString(RaceData rd) {
        int laps = calcLap(rd);
        if (laps == race.get(0).getLaps()) {
            return df.format((double) race.get(0).getTime() - rd.getTime());
        } else {
            return Integer.toString((int) laps - race.get(0).getLaps());
        }
    }

    private String getFormatTime(double time) {
        final int MINUTES = 60, SECONDS = 60;
        int minutes = (int) (time / SECONDS);
        time -= minutes * SECONDS;
        int hours = minutes / MINUTES;
        minutes -= hours * MINUTES;

        return hours + ":" + dfMin.format(minutes) + ":" + dfSec.format(time);
    }

    @Override
    public String toString() {
        return result;
    }

    public class RaceData {

        private final Team t;
        private double time;
        private int laps = 0;
        private int lapsLead = 0;
        private int damage = 0;
        private boolean active = true;
        private final int start;

        public RaceData(Team t, int start, double time) {
            this.t = t;
            this.start = start;
            this.time = time;
        }

        public Team getTeam() {
            return t;
        }

        public double getTime() {
            return time;
        }

        public void addTime(double time) {
            this.time += time;
        }

        public void setTime(double time) {
            this.time = time;
        }

        public void newLap(double time) {
            this.time += time;
            laps++;
        }

        public void leadLap() {
            lapsLead++;
        }

        public int getLaps() {
            return laps;
        }

        public void setInactiveLaps(int laps) {
            this.laps = laps;
        }

        public int getLapsLead() {
            return lapsLead;
        }

        public int getStart() {
            return start;
        }

        public int getDamage() {
            return damage;
        }

        public boolean isActive() {
            return active;
        }

        private boolean troubleRoll() {
            if (Math.random() < 0.0004 && Math.random() * 150 > t.getFunding()) {
                String[] list = {"engine", "engine", "power steering", "gearbox", "clutch", "electrical", "electrical"};
                Random r = new Random();
                String problem = list[r.nextInt(list.length)];
                System.out.println("#" + t.getNumber() + " " + t.getDriverName()
                        + " suffered " + problem + " failure.");
                active = false;
                if (problem.equalsIgnoreCase("engine")) {
                    return true;
                }
            } else if (Math.random() > 0.9988 && Math.random() * 125 > t.getFunding()) {
                int newDamage = (int) (Math.random() * 100);
                damage += newDamage;
                System.out.println("#" + t.getNumber() + " " + t.getDriverName()
                        + " has crashed and took damage: " + newDamage + " Total Damage: " + damage);
                if (damage > 75) {
                    active = false;
                }
                if (damage > 25) {
                    return true;
                }
            }
            return false;
        }

        private boolean involvedRoll(double behind) {
            double probability = 1 - behind / 4;
            if (Math.random() * 1.5 < probability) {
                int newDamage = (int) (100 - 10 * Math.pow(Math.random() * t.getDriver().getSs(), 0.5));
                damage += newDamage;
                System.out.println("#" + t.getNumber() + " " + t.getDriverName()
                        + " also involved and took damage: " + newDamage + " Total Damage: " + damage);
                if (damage > 75) {
                    active = false;
                }
                return true;
            }
            return false;
        }
    }
}
