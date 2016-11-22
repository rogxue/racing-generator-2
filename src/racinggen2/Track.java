package racinggen2;

public class Track {

    private double length;
    private double maxSpeed;
    private String name;
    private int type;

    public Track(String name, double length, double maxSpeed, int type) {
        this.length = length;
        this.maxSpeed = maxSpeed;
        this.name = name;
        this.type = type; // 0 = SS, 1 = S, 2 = ST, 3 = RC
    }

    public double getLength() {
        return length;
    }

    public double getCautionSpeed() {
        switch (type) {
            case 0:
                return 65;
            case 1:
                return 55;
            case 2:
                return 35;
            case 3:
                return 45;
            default:
                return 45;
        }
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }
}
