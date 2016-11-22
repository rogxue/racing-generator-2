package racinggen2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Season {

    public List<Team> teams = new ArrayList<>();
    public List<Team> partTimeTeams = new ArrayList<>();

    public Season() {
        teams.add(new Team(new Driver("Test", "Driver"), 00, 70, "Test Team", "Chevrolet"));
    }

    public Team getTeam(int number) {
        for (Team t : teams) {
            if (t.getNumber() == number) {
                return t;
            }
        }
        for (Team t : partTimeTeams) {
            if (t.getNumber() == number) {
                return t;
            }
        }
        return null;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Team> getPartTimeTeams() {
        return partTimeTeams;
    }

    public void sortByNumber() {
        Collections.sort(teams, (Team p1, Team p2) -> p1.getNumber() - p2.getNumber());
        Collections.sort(partTimeTeams, (Team p1, Team p2) -> p1.getNumber() - p2.getNumber());
    }

    public void sortByNumber(List<Team> all) {
        Collections.sort(all, (Team p1, Team p2) -> p1.getNumber() - p2.getNumber());
    }

    @Override
    public String toString() {
        String result = "";
        List<Team> all = new ArrayList<>(teams);
        all.addAll(partTimeTeams);
        sortByNumber(all);
        for (Team t : all) {
            result += "#" + t.getNumber() + " " + t.getDriver().getName() + " " + t.getSponsor() + " " + t.getManu() + "\n";
        }
        return result;
    }

    public String toStringFormal() {
        String result = "";
        List<Team> all = new ArrayList<>(teams);
        all.addAll(partTimeTeams);
        sortByNumber(all);
        System.out.println("Field Size: " + all.size() + "\n");
        for (Team t : all) {
            result += t.getDriver().getName() + ", driver of the #" + t.getNumber() + " " + t.getSponsor() + " " + t.getOrganization() + " " + t.getManu() + ".\n";
        }
        return result;
    }
}
