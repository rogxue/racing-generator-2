package racinggen2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QualifyingDuels extends Qualifying {

    List<LapData> duel1 = new ArrayList<>();
    List<LapData> duel2 = new ArrayList<>();
    List<LapData> all = new ArrayList<>();

    public QualifyingDuels(Season season, Track track) {
        super(season, track);
        LapData pole = qResults.get(0);
        LapData outside = qResults.get(1);
        all = new ArrayList<>(qResults);
        all.addAll(dnq);
        Collections.sort(all, (LapData ld1, LapData ld2) -> Double.compare(ld1.getTime(), ld2.getTime()));
        printResultNoDnq();
        while (!all.isEmpty()) {
            duel1.add(all.remove(0));
            if (!all.isEmpty()) {
                duel2.add(all.remove(0));
            }
        }
        Race results1 = runDuel(duel1);
        Race results2 = runDuel(duel2);
        all.add(pole);
        all.add(outside);
        int i = 1;
        while (!results1.race.isEmpty()) {
            Team temp = results1.race.remove(0).getTeam();
            all.add(new LapData(temp, 1, i, 0, temp.isCharter()));
            if (!results2.race.isEmpty()) {
                temp = results2.race.remove(0).getTeam();
                all.add(new LapData(temp, 2, i, 0, temp.isCharter()));
            }
            i++;
        }
        for (int j = all.size() - 1; j > 1; j--) {
            if (all.get(j).getTeam().equals(all.get(0).getTeam()) || all.get(j).getTeam().equals(all.get(1).getTeam())) {
                all.remove(j);
            }
        }
        qResults.clear();
        dnq.clear();
        qResults.addAll(all);
        removeDnq();
    }

    protected void sortResult(List<LapData> list) {
        Collections.sort(list, (LapData ld1, LapData ld2) -> Double.compare(ld1.getTime(), ld2.getTime()));
    }

    public void printResultNoDnq() {
        System.out.println("====QUALIFYING RESULTS====");
        System.out.println("Pos.\tCar #\tSpeed\tBest\t1st Lap\t2nd Lap");
        int i = 1;
        for (LapData lp : all) {
            double speed = 1 / (lp.getTime() / 3600 / track.getLength());
            System.out.println(i + "\t" + lp.getTeam().getNumber() + "\t" + df.format(speed) + "\t" + df.format(lp.getTime()) + "\t" + df.format(lp.getFirst()) + "\t" + df.format(lp.getSecond()));
            i++;
        }
    }

    @Override
    public void printResult() {
        System.out.println("====QUALIFYING RESULTS====");
        System.out.println("Pos.\tCar #\tSpeed\tBest\t1st Lap\t2nd Lap");
        int i = 1;
        for (LapData lp : qResults) {
            double speed = 1 / (lp.getTime() / 3600 / track.getLength());
            if (lp.getSecond() == 0) {
                System.out.println(i + "\t" + lp.getTeam().getNumber() + "\tDuel: " + (int) lp.getTime() + " Finish: " + (int) lp.getFirst());
            } else {
                System.out.println(i + "\t" + lp.getTeam().getNumber() + "\t" + df.format(speed) + "\t" + df.format(lp.getTime()) + "\t" + df.format(lp.getFirst()) + "\t" + df.format(lp.getSecond()));
            }
            i++;
        }
        System.out.println("==DNQ==");
        for (LapData lp : dnq) {
            System.out.println(i + "\t" + lp.getTeam().getNumber() + "\tDuel: " + (int) lp.getTime() + " Finish: " + (int) lp.getFirst());
            i++;
        }
    }

    @Override
    public String toString() {
        String result = "";
        result += "====QUALIFYING RESULTS====" + "\n";
        result += "Pos.\tCar #\tSpeed\tBest\t1st Lap\t2nd Lap" + "\n";
        int i = 1;
        for (LapData lp : all) {
            double speed = 1 / (lp.getTime() / 3600 / track.getLength());
            result += i + "\t" + lp.getTeam().getNumber() + "\t" + df.format(speed) + "\t" + df.format(lp.getTime()) + "\t" + df.format(lp.getFirst()) + "\t" + df.format(lp.getSecond()) + "\n";
            i++;
        }
        return result;
    }

    private Race runDuel(List<LapData> duel1) {
        Qualifying duelLineup = new Qualifying(duel1, track);
        return new Race(duelLineup, 60);
    }
}
