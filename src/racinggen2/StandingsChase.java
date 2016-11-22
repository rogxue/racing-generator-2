package racinggen2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StandingsChase {

    DecimalFormat df = new DecimalFormat("0.000");
    List<SeasonData> standings = new ArrayList<>();
    int races = 0;

    public StandingsChase(Season season) {
        for (Team team : season.teams) {
            standings.add(new SeasonData(team));
        }
    }

    public void makeStandings(Race race) {
        races++;
        int points = 43;
        for (int i = 0; i < race.race.size(); i++) {
            for (SeasonData sd : standings) {
                if (sd.t.equals(race.race.get(i).getTeam())) {
                    sd.addPoints(points);
                    sd.addPosition(i + 1);
                    if (races > 26 && sd.chase == true && sd.t.equals(race.race.get(0).getTeam())) {
                        sd.chaseWin = true;
                    }
                    if (race.race.get(i).getLapsLead() > 0) {
                        if (sd.chase != true || races != 36) {
                            sd.addPoints(1);
                        }
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
                                if (sd.chase != true || races != 36) {
                                    sd.addPoints(3);
                                }
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

        if (races == 26) {
            for (int i = 0; i < standings.size(); i++) {
                if (standings.get(i).wins > 0) {
                    standings.add(0, standings.remove(i));
                }
            }
            for (int i = 0; i < 16; i++) {
                standings.get(i).setPoints(2000);
                standings.get(i).addPoints(3 * standings.get(i).wins);
                standings.get(i).chase = true;
                standings.get(i).t.boost();
            }
        }
        if (races == 29) {
            for (int i = 0; i < 16; i++) {
                if (standings.get(i).chaseWin == true) {
                    standings.get(i).chaseWin = false;
                    standings.add(0, standings.remove(i));
                }
            }
            for (int i = 0; i < 12; i++) {
                standings.get(i).addedPoints += 3000 - standings.get(i).getPoints();
                standings.get(i).setPoints(3000);
            }
            for (int i = 12; i < 16; i++) {
                standings.get(i).chase = false;
                standings.get(i).t.unboost();
            }
        }
        if (races == 32) {
            for (int i = 0; i < 12; i++) {
                if (standings.get(i).chaseWin == true) {
                    standings.get(i).chaseWin = false;
                    standings.add(0, standings.remove(i));
                }
            }
            for (int i = 0; i < 8; i++) {
                standings.get(i).addedPoints += 4000 - standings.get(i).getPoints();
                standings.get(i).setPoints(4000);
            }
            for (int i = 8; i < 12; i++) {
                standings.get(i).chase = false;
                standings.get(i).addPoints(standings.get(i).addedPoints * -1);
                standings.get(i).t.unboost();
            }
        }
        if (races == 35) {
            for (int i = 0; i < 8; i++) {
                if (standings.get(i).chaseWin == true) {
                    standings.get(i).chaseWin = false;
                    standings.add(0, standings.remove(i));
                }
            }
            for (int i = 0; i < 4; i++) {
                standings.get(i).setPoints(5000);
                standings.get(i).t.boost();
            }
            for (int i = 4; i < 8; i++) {
                standings.get(i).chase = false;
                standings.get(i).addPoints(standings.get(i).addedPoints * -1);
                standings.get(i).t.unboost();
            }
        }
        sortResult();
    }

    public Team getLeader() {
        sortResult();
        return standings.get(0).t;
    }

    private void checkMostLapsLead(Race race) {
        Team t = null;
        int lapsLead = 0;
        for (int i = 0; i < race.race.size(); i++) {
            if (race.race.get(i).getLapsLead() > lapsLead) {
                lapsLead = race.race.get(i).getLapsLead();
                t = race.race.get(i).getTeam();
            }
        }
        for (SeasonData sd : standings) {
            if (sd.t.equals(t)) {
                if (sd.chase != true || races != 36) {
                    sd.addPoints(1);
                }
            }
        }
    }

    private void sortResult() {
        Collections.sort(standings, (SeasonData sd1, SeasonData sd2)
                -> Double.compare(sd2.points, sd1.points));
    }

    public void printResult() {
        System.out.println("====STANDINGS====");
        System.out.println("Pos.\tCar #\tPoints\tWins\tT5\tT10\tPoles\tLead\tAvg\tDNF");
        int i = 1;
        for (SeasonData p : standings) {
            System.out.println(i + "\t" + p.t.getNumber() + "\t" + p.points + "\t"
                    + p.wins + "\t" + p.top5 + "\t" + p.top10 + "\t" + p.poles + "\t"
                    + p.lapsLead + "\t" + df.format(p.cumulative / (double) races)
                    + "\t" + p.dnf);
            i++;
        }
    }

    @Override
    public String toString() {
        String result = "====STANDINGS====" + "\n";
        result += "Pos.\tCar #\tPoints\tWins\tT5\tT10\tPoles\tLaps Lead\tAvg\tDNF" + "\n";
        int i = 1;
        for (SeasonData p : standings) {
            result += i + "\t" + p.t.getNumber() + "\t" + p.points + "\t" + p.wins
                    + "\t" + p.top5 + "\t" + p.top10 + "\t" + p.poles + "\t" + p.lapsLead
                    + "\t" + df.format(p.cumulative / (double) races) + "\t" + p.dnf + "\n";
            i++;
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
        private boolean chase = false;
        private boolean chaseWin = false;
        private int addedPoints = 0;

        public SeasonData(Team t) {
            this.t = t;
        }

        public int getPoints() {
            return points;
        }

        public void addPoints(int points) {
            this.points += points;
        }

        private void setPoints(int points) {
            this.points = points;
        }

        public void addPosition(int pos) {
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
