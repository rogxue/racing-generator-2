package racinggen2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Standings {

    DecimalFormat df = new DecimalFormat("0.000");
    List<SeasonData> standings = new ArrayList<>();
    int races = 0;

    public Standings(Season season) {
        for (Team team : season.teams) {
            standings.add(new SeasonData(team));
        }
        for (Team team : season.partTimeTeams) {
            standings.add(new SeasonData(team));
        }
    }

    public void makeStandings(Race race) {
        races++;
        int points = 40;
        for (int i = 0; i < race.race.size(); i++) {
            for (SeasonData sd : standings) {
                if (sd.t.equals(race.race.get(i).getTeam())) {
                    sd.addPoints(points);
                    sd.addPosition(i + 1);
                    if (race.race.get(i).getLapsLead() > 0) {
                        sd.addPoints(1);
                        sd.lapsLead += race.race.get(i).getLapsLead();
                    }
                    if (race.race.get(i).getStart() == 1) {
                        sd.poles++;
                    }
                    if (i < 10) {
                        sd.top10++;
                        if (i < 5) {
                            sd.top5++;
                            if (i < 1) {
                                sd.addPoints(3);
                                sd.wins++;
                            }
                        }
                    }
                    if (!race.race.get(i).isActive()) {
                        sd.dnf++;
                    }
                    points--;
                }
            }
        }
        checkMostLapsLead(race);
        sortResult();
    }

    private void checkMostLapsLead(Race race) {
        Team t = null;
        int lapsLead = 0;
        for (Race.RaceData rd : race.race) {
            if (rd.getLapsLead() > lapsLead) {
                lapsLead = rd.getLapsLead();
                t = rd.getTeam();
            }
        }
        for (SeasonData sd : standings) {
            if (sd.t.equals(t)) {
                sd.addPoints(1);
            }
        }
    }

    private void sortResult() {
        Collections.sort(standings, (SeasonData sd1, SeasonData sd2)
                -> Double.compare(sd2.points, sd1.points));
    }

    public void printResult() {
        System.out.println("====STANDINGS====");
        System.out.println("Pos.\tCar #\tPoints\tWins\tT5\tT10\tPoles\tLead\tAvg\tStarts\tDNF");
        int i = 1;
        for (SeasonData p : standings) {
            if (p.starts > 0) {
                System.out.println(i + "\t" + p.t.getNumber() + "\t" + p.points + "\t"
                        + p.wins + "\t" + p.top5 + "\t" + p.top10 + "\t" + p.poles + "\t"
                        + p.lapsLead + "\t" + df.format(p.cumulative / (double) p.starts)
                        + "\t" + p.starts + "\t" + p.dnf);
                i++;
            }
        }
    }

    @Override
    public String toString() {
        String result = "====STANDINGS====" + "\n";
        result += "Pos.\tCar #\tPoints\tWins\tT5\tT10\tPoles\tLaps Lead\tAvg\tStarts\tDNF" + "\n";
        int i = 1;
        for (SeasonData p : standings) {
            if (p.starts > 0) {
                result += i + "\t" + p.t.getNumber() + "\t" + p.points + "\t" + p.wins
                        + "\t" + p.top5 + "\t" + p.top10 + "\t" + p.poles + "\t" + p.lapsLead
                        + "\t" + df.format(p.cumulative / (double) p.starts) + "\t" + p.starts + "\t"
                        + p.dnf + "\n";
                i++;
            }
        }
        return result;
    }

    public class SeasonData {

        private Team t;
        private int points = 0;
        private int wins = 0;
        private int top5 = 0;
        private int top10 = 0;
        private int poles = 0;
        private int lapsLead = 0;
        private int cumulative = 0;
        private int dnf = 0;
        private int starts = 0;

        public SeasonData(Team t) {
            this.t = t;
        }

        public int getPoints() {
            return points;
        }

        public void addPoints(int points) {
            this.points += points;
        }

        public void addPosition(int pos) {
            starts++;
            this.cumulative += pos;
        }

        public int getWins() {
            return wins;
        }

        public int getTop5() {
            return top5;
        }

        public int getTop10() {
            return top10;
        }

        public int getPoles() {
            return poles;
        }

        public int getDnf() {
            return dnf;
        }
    }
}
