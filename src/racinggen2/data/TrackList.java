package racinggen2.data;

import racinggen2.Track;

public class TrackList {

    public Track atlanta, bristol, charlotte, chicago, darlington, daytona,
            dover, fontana, homestead, indy, kansas, kentucky, loudon, martinsville, 
            michigan, phoenix, pocono, richmond, sonoma, talladega, texas,
            vegas, watkinsGlen;

    public TrackList() {
        generateTrackList();
    }

    private void generateTrackList() {
        atlanta = new Track("Atlanta Motor Speedway", 1.54, 192, 1);
        bristol = new Track("Bristol Motor Speedway", 0.533, 130, 2);
        charlotte = new Track("Charlotte Motor Speedway", 1.5, 196, 1);
        chicago = new Track("Chicagoland Speedway", 1.5, 188, 1);
        darlington = new Track("Darlington Raceway", 1.366, 183, 1);
        daytona = new Track("Daytona International Speedway", 2.5, 200, 0);
        dover = new Track("Dover International Speedway", 1, 160, 2);
        fontana = new Track("Auto Club Speedway", 2, 190, 1);
        homestead = new Track("Homestead-Miami Speedway", 1.5, 180, 1);
        indy = new Track("Indianapolis Motor Speedawy", 2.5, 180, 1);
        kansas = new Track("Kansas Speedway", 1.5, 190, 1);
        kentucky = new Track("Kentucky Speedway", 1.5, 180, 1);
        loudon = new Track("New Hampshire Motor Speedway", 1.058, 136, 2);
        martinsville = new Track("Martinsville Speedway", 0.526, 98, 2);
        michigan = new Track("Michigan International Speedway", 2, 202, 1);
        phoenix = new Track("Phoenix International Raceway", 1, 140, 2);
        pocono = new Track("Pocono Raceway", 2.5, 180, 1);
        richmond = new Track("Richmond International Raceway", 0.75, 128, 2);
        sonoma = new Track("Sonoma Raceway", 1.99, 96, 3);
        talladega = new Track("Talladega Superspeedway", 2.666, 200, 0);
        texas = new Track("Texas Motor Speedway", 1.5, 190, 1);
        vegas = new Track("Las Vegas Motor Speedway", 1.5, 190, 1);
        watkinsGlen = new Track("Watkins Glen International", 2.45, 127, 3);
    }
}
