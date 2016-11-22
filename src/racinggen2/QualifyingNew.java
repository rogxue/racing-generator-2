package racinggen2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QualifyingNew extends Qualifying {

    List<LapData> qResultsRound2 = new ArrayList<>();
    List<LapData> qResultsRound3 = new ArrayList<>();

    public QualifyingNew(Season season, Track track) {
        super(season, track);
        secondRound();
        thirdRound();
        qResults.addAll(0, qResultsRound2);
        qResults.addAll(0, qResultsRound3);
    }

    private void secondRound() {
        double time, temp, first = 0, second = 0;
        for (int i = 0; i < 24; i++) {
            qResultsRound2.add(qResults.remove(0));
            time = 10000;
            for (int j = 0; j < 2; j++) {
                temp = getLapTime(qResultsRound2.get(i).getTeam()) / 1.0005;
                if (j == 0) {
                    first = temp;
                } else {
                    second = temp;
                }
                time = (temp < time) ? temp : time;
            }
            setResultRound2(i, time, first, second);
        }
        sortResult(qResultsRound2);
    }

    private void thirdRound() {
        double time, temp, first = 0, second = 0;
        for (int i = 0; i < 12; i++) {
            qResultsRound3.add(qResultsRound2.remove(0));
            time = 10000;
            for (int j = 0; j < 2; j++) {
                temp = getLapTime(qResultsRound3.get(i).getTeam()) / 1.001;
                if (j == 0) {
                    first = temp;
                } else {
                    second = temp;
                }
                time = (temp < time) ? temp : time;
            }
            setResultRound3(i, time, first, second);
        }
        sortResult(qResultsRound3);
    }

    protected void sortResult(List<LapData> list) {
        Collections.sort(list, (LapData ld1, LapData ld2) -> Double.compare(ld1.getTime(), ld2.getTime()));
    }

    private void setResultRound2(int position, double time, double first, double second) {
        qResultsRound2.get(position).setFirst(first);
        qResultsRound2.get(position).setSecond(second);
        qResultsRound2.get(position).setTime(time);

    }

    private void setResultRound3(int position, double time, double first, double second) {
        qResultsRound3.get(position).setFirst(first);
        qResultsRound3.get(position).setSecond(second);
        qResultsRound3.get(position).setTime(time);

    }

    @Override
    public void printResult() {
        System.out.println("====QUALIFYING RESULTS====");
        System.out.println("Pos.\tCar #\tSpeed\tBest");
        int i = 1;
        for (LapData lp : qResults) {
            double speed = 1 / (lp.getTime() / 3600 / track.getLength());
            System.out.println(i + "\t" + lp.getTeam().getNumber() + "\t" + df.format(speed) + "\t" + df.format(lp.getTime()));
            if (i == 12) {
                System.out.println("====ROUND 3 CUT-OFF====");
            }
            if (i == 24) {
                System.out.println("====ROUND 2 CUT-OFF====");
            }
            i++;
        }
    }

    @Override
    public String toString() {
        String result = "";
        result += "====QUALIFYING RESULTS====" + "\n";
        result += "Pos.\tCar #\tSpeed\tBest" + "\n";
        int i = 1;
        for (LapData lp : qResults) {
            double speed = 1 / (lp.getTime() / 3600 / track.getLength());
            result += i + "\t" + lp.getTeam().getNumber() + "\t" + df.format(speed) + "\t" + df.format(lp.getTime()) + "\n";
            if (i == 12) {
                result += "====ROUND 3 CUT-OFF====" + "\n";
            }
            if (i == 24) {
                result += "====ROUND 2 CUT-OFF====" + "\n";
            }
            i++;
        }
        return result;
    }
}
