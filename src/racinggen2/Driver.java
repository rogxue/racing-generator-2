package racinggen2;

public class Driver {

    private String first, last;
    private final int ss, s, st, rc;

    public Driver(String first, String last) {
        this.first = first;
        this.last = last;
        ss = 70;
        s = 70;
        st = 70;
        rc = 70;
    }

    public Driver(String first, String last, int ss, int s, int st, int rc) {
        this.first = first;
        this.last = last;
        this.ss = ss;
        this.s = s;
        this.st = st;
        this.rc = rc;
//        this.ss = 85;
//        this.s = 85;
//        this.st = 85;
//        this.rc = 85;
    }

    public String getName() {
        return first + " " + last;
    }

    public String getLastNameFirst() {
        return last + ", " + first;
    }

    public void setName(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public int getSs() {
        return ss;
    }

    public int getS() {
        return s;
    }

    public int getSt() {
        return st;
    }

    public int getRc() {
        return rc;
    }
}
