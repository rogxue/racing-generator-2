package racinggen2.data;

import racinggen2.Driver;
import racinggen2.Season;
import racinggen2.Team;

public class ClassOf2016 extends Season {

    int year = 2016;

    public ClassOf2016() {
        teams.clear();
        partTimeTeams.clear();

        bkRacing();
        chipGanassiRacing();
        csLeavine();
        frontRowMotorsports();
        furnitureRowRacing();
        germainRacing();
        goFasRacing();
        hendrickMotorsports();
        hillmanRacing();
        hScottMotorsports();
        joeGibbsRacing();
        jtgRacing();
        penskeRacing();
        premiumMotorsports();
        richardChildressRacing();
        richardPettyMotorports();
        roushRacing();
        stewartHaasRacing();
        //  testTeam();
        tommyBaldwinRacing();
        woodBrothers();

        theMotorsportsGroup();
    }

    private void bkRacing() {
        String s = "BK Racing";
        Driver dr = new Driver("David", "Ragan", 90, 80, 80, 80);
        Driver md = new Driver("Matt", "DiBenedetto", 75, 75, 75, 75);
        Driver mw = new Driver("Michael", "Waltrip", 90, 85, 85, 80);
        Driver rr = new Driver("Robert", "Richardson Jr.", 75, 75, 75, 75);
        teams.add(new Team(dr, 23, 75, s, "Toyota"));
        partTimeTeams.add(new Team(rr, 26, 75, s, "Toyota", false));
        teams.add(new Team(md, 83, 75, s, "Toyota"));
        partTimeTeams.add(new Team(mw, 93, 80, s, "Toyota", false));
        getTeam(23).addSponsor("Dr. Pepper");
        getTeam(83).addSponsor("Dustless Blasting");
        getTeam(93).addSponsor("Maxwell House");
    }

    private void chipGanassiRacing() {
        String s = "Chip Ganassi Racing";
        Driver mac = new Driver("Jamie", "McMurray", 95, 85, 80, 75);
        Driver larson = new Driver("Kyle", "Larson", 85, 90, 85, 80);
        teams.add(new Team(mac, 1, 100, s, "Chevrolet"));
        teams.add(new Team(larson, 42, 100, s, "Chevrolet"));
        getTeam(1).addSponsor("McDonald's", "Cessna");
        getTeam(42).addSponsor("Target");
    }

    private void csLeavine() {
        String s = "Circle Sport / Leavine Family Racing";
        Driver mm = new Driver("Michael", "McDowell", 80, 80, 80, 80);
        teams.add(new Team(mm, 95, 85, s, "Chevrolet"));
        getTeam(95).addSponsor("Thrivent Financial");
    }

    private void frontRowMotorsports() {
        String s = "Front Row Motorsports";
        Driver cb = new Driver("Chris", "Buescher", 75, 75, 75, 75);
        Driver lc = new Driver("Landon", "Cassill", 85, 80, 80, 75);
        Driver dg = new Driver("David", "Gillland", 80, 80, 80, 80);
        teams.add(new Team(cb, 34, 85, s, "Ford"));
        partTimeTeams.add(new Team(dg, 35, 80, s, "Ford", false));
        teams.add(new Team(lc, 38, 85, s, "Ford"));
        getTeam(34).addSponsor("Love's Travel Stops");
        getTeam(38).addSponsor("Snap Fitness");
    }

    private void furnitureRowRacing() {
        String s = "Furniture Row Racing";
        Driver truex = new Driver("Martin", "Truex Jr.", 90, 90, 85, 80);
        teams.add(new Team(truex, 78, 100, s, "Toyota"));
        getTeam(78).addSponsor("Furniture Row", "Bass Pro Shops");
    }

    private void germainRacing() {
        String s = "Germain Racing";
        Driver mears = new Driver("Casey", "Mears", 80, 85, 80, 75);
        teams.add(new Team(mears, 13, 80, s, "Chevrolet"));
        getTeam(13).addSponsor("GEICO");
    }

    private void goFasRacing() {
        String s = "Go FAS Racing";
        Driver je = new Driver("Jeffrey", "Earnhardt", 75, 75, 75, 75);
        teams.add(new Team(je, 32, 75, s, "Ford"));
        getTeam(32).addSponsor("Can-Am", "Corvetteparts.net");
    }

    private void hendrickMotorsports() {
        String s = "Hendrick Motorsports";
        Driver kk = new Driver("Kasey", "Kahne", 85, 85, 85, 80);
        Driver chase = new Driver("Chase", "Elliott", 80, 80, 80, 80);
        Driver jj = new Driver("Jimmie", "Johnson", 95, 95, 95, 95);
        Driver jr = new Driver("Dale", "Earnhardt Jr.", 95, 90, 90, 85);
        teams.add(new Team(kk, 5, 100, s, "Chevrolet"));
        teams.add(new Team(chase, 24, 100, s, "Chevrolet"));
        teams.add(new Team(jj, 48, 100, s, "Chevrolet"));
        teams.add(new Team(jr, 88, 100, s, "Chevrolet"));
        getTeam(5).addSponsor("Farmer's Insurance");
        getTeam(24).addSponsor("NAPA Auto Parts");
        getTeam(48).addSponsor("Lowe's");
        getTeam(88).addSponsor("Nationwide", "Mountain Dew");
    }

    private void hillmanRacing() {
        String s = "Hillman Racing";
        Driver rs = new Driver("Reed", "Sorenson", 80, 80, 80, 80);
        partTimeTeams.add(new Team(rs, 40, 80, s, "Chevrolet", false));
        getTeam(40).addSponsor("CRC BRAKLEEN");
    }

    private void hScottMotorsports() {
        String s = "HScott Motorsports";
        Driver cb = new Driver("Clint", "Bowyer", 85, 85, 85, 80);
        Driver ma = new Driver("Michael", "Annett", 75, 75, 75, 70);
        teams.add(new Team(cb, 15, 90, s, "Chevrolet"));
        teams.add(new Team(ma, 46, 75, s, "Chevrolet"));
        getTeam(15).addSponsor("5-Hour Energy");
        getTeam(46).addSponsor("Pilot", "Flying J");
    }

    private void joeGibbsRacing() {
        String s = "Joe Gibbs Racing";
        Driver dh = new Driver("Denny", "Hamlin", 90, 95, 95, 90);
        Driver kb = new Driver("Kyle", "Busch", 95, 95, 95, 95);
        Driver ce = new Driver("Carl", "Edwards", 90, 95, 90, 85);
        Driver mk = new Driver("Matt", "Kenseth", 95, 95, 95, 85);
        teams.add(new Team(dh, 11, 100, s, "Toyota"));
        teams.add(new Team(kb, 18, 100, s, "Toyota"));
        teams.add(new Team(ce, 19, 100, s, "Toyota"));
        teams.add(new Team(mk, 20, 100, s, "Toyota"));
        getTeam(11).addSponsor("FedEx");
        getTeam(18).addSponsor("M&M's", "Interstate Batteries");
        getTeam(19).addSponsor("Arris", "Stanley", "Subway");
        getTeam(20).addSponsor("Dollar General", "DeWalt");
    }

    private void jtgRacing() {
        String s = "JTG Daugherty Racing";
        Driver aj = new Driver("A.J.", "Allmendinger", 85, 80, 85, 95);
        teams.add(new Team(aj, 47, 90, s, "Chevrolet"));
        getTeam(47).addSponsor("Bush's Baked Beans", "Kingsford", "Scott's");
    }

    private void penskeRacing() {
        String s = "Penske Racing";
        Driver bk = new Driver("Brad", "Keselowski", 95, 95, 90, 90);
        Driver jolo = new Driver("Joey", "Logano", 95, 95, 90, 90);
        teams.add(new Team(bk, 2, 100, s, "Ford"));
        teams.add(new Team(jolo, 22, 100, s, "Ford"));
        getTeam(2).addSponsor("Miller Lite");
        getTeam(22).addSponsor("Shell", "Pennzoil", "AAA");
    }

    private void premiumMotorsports() {
        String s = "Premium Motorsports";
        Driver cw = new Driver("Cole", "Whitt", 75, 75, 75, 75);
        teams.add(new Team(cw, 98, 70, s, "Toyota"));
    }

    private void richardChildressRacing() {
        String s = "Richard Childress Racing";
        Driver ad = new Driver("Austin", "Dillon", 85, 80, 75, 75);
        Driver pm = new Driver("Paul", "Menard", 85, 80, 80, 80);
        Driver rn = new Driver("Ryan", "Newman", 85, 90, 90, 90);
        teams.add(new Team(ad, 3, 100, s, "Chevrolet"));
        teams.add(new Team(pm, 27, 100, s, "Chevrolet"));
        teams.add(new Team(rn, 31, 95, s, "Chevrolet"));
        getTeam(3).addSponsor("DOW", "American Ethanol");
        getTeam(27).addSponsor("Menards");
        getTeam(31).addSponsor("Caterpiller");

    }

    private void richardPettyMotorports() {
        String s = "Richard Petty Motorsports";
        Driver bs = new Driver("Brian", "Scott", 80, 75, 75, 75);
        Driver aa = new Driver("Aric", "Almirola", 90, 80, 80, 80);
        teams.add(new Team(bs, 44, 90, s, "Ford"));
        teams.add(new Team(aa, 43, 90, s, "Ford"));
        getTeam(44).addSponsor("Twisted Tea");
        getTeam(43).addSponsor("Smithfield");
    }

    private void roushRacing() {
        String s = "Roush Fenway Racing";
        Driver tb = new Driver("Trevor", "Bayne", 80, 75, 75, 75);
        Driver gb = new Driver("Greg", "Biffle", 85, 80, 80, 80);
        Driver rs = new Driver("Ricky", "Stenhouse Jr.", 80, 75, 75, 75);
        teams.add(new Team(tb, 6, 87, s, "Ford"));
        teams.add(new Team(gb, 16, 90, s, "Ford"));
        teams.add(new Team(rs, 17, 87, s, "Ford"));
        getTeam(6).addSponsor("Advocare");
        getTeam(16).addSponsor("Ortho");
        getTeam(17).addSponsor("Fastenal", "Zest");
    }

    private void stewartHaasRacing() {
        String s = "Stewart-Haas Racing";
        Driver kh = new Driver("Kevin", "Harvick", 95, 95, 95, 95);
        Driver dp = new Driver("Danica", "Patrick", 80, 75, 75, 75);
        Driver ts = new Driver("Tony", "Stewart", 80, 80, 80, 80);
        Driver kb = new Driver("Kurt", "Busch", 95, 95, 90, 90);
        teams.add(new Team(kh, 4, 100, s, "Chevrolet"));
        teams.add(new Team(dp, 10, 100, s, "Chevrolet"));
        teams.add(new Team(ts, 14, 100, s, "Chevrolet"));
        teams.add(new Team(kb, 41, 100, s, "Chevrolet"));
        getTeam(4).addSponsor("Busch Beer");
        getTeam(10).addSponsor("Nature's Bakery");
        getTeam(14).addSponsor("Mobil 1", "Bass Pro Shops");
        getTeam(41).addSponsor("Haas Automation");
    }

    private void testTeam() {
        String s = "Test Team";
        Driver cb = new Driver("Christian", "Bruns", 100, 100, 100, 100);
        Driver rx = new Driver("Roger", "Xue", 100, 100, 100, 100);
        Driver jr = new Driver("Justin", "Rose", 100, 100, 100, 100);
        Driver jm = new Driver("John", "Matthews", 100, 100, 100, 100);
        teams.add(new Team(cb, 199, 100, s, "Chevrolet"));
        teams.add(new Team(rx, 139, 100, s, "Chevrolet"));
        teams.add(new Team(jr, 169, 100, s, "Chevrolet"));
        teams.add(new Team(jm, 109, 100, s, "Chevrolet"));
    }

    private void tommyBaldwinRacing() {
        String s = "Tommy Baldwin Racing";
        Driver rs = new Driver("Regan", "Smith", 85, 80, 80, 80);
        teams.add(new Team(rs, 7, 80, s, "Chevrolet"));
        getTeam(7).addSponsor("Toy State");
    }

    private void woodBrothers() {
        String s = "Wood Brothers Racing";
        Driver rb = new Driver("Ryan", "Blaney", 85, 80, 80, 80);
        teams.add(new Team(rb, 21, 95, s, "Ford", false));
        getTeam(21).addSponsor("Motorcraft", "Quick Lane");
    }
//================================================================================
    private void theMotorsportsGroup() {
        String s = "The Motorsports Group";
        Driver jw = new Driver("Josh", "Wise", 80, 75, 75, 75);
        partTimeTeams.add(new Team(jw, 30, 75, s, "Chevrolet", false));
    }

}
