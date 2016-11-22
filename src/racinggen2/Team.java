package racinggen2;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private Driver driver;
    private int number;
    private int funding;
    private String organization;
    private String manu;
    private List<String> sponsor = new ArrayList<>();

    public Team(Driver driver, int number, int funding, String organization, String manu) {
        this.driver = driver;
        this.number = number;
        this.funding = funding;
        this.organization = organization;
        this.manu = manu;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getDriverName() {
        return driver.getName();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getFunding() {
        return funding;
    }

    public void setFunding(int funding) {
        this.funding = funding;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void addSponsor(String... sponsor) {
        for (String s : sponsor) {
            this.sponsor.add(s);
        }
    }

    public boolean removeSponsor(String sponsor) {
        return this.sponsor.remove(sponsor);
    }

    public String getSponsor() {
        String result = "";
        if (sponsor.isEmpty()) {
            return "Unsponsored";
        }
        for (String s : sponsor) {
            result += s + " / ";
        }
        return result.substring(0, result.length() - 3);
    }

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public void boost() {
        funding *= 1.03;
    }

    public void unboost() {
        funding /= 1.03;
    }

    @Override
    public String toString() {
        return "#" + number + " " + driver.getName() + " " + getSponsor() + " " + getManu();
    }
}
