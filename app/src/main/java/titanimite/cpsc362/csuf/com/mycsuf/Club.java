package titanimite.cpsc362.csuf.com.mycsuf;

/**
 * Created by Daniel on 11/7/17.
 */

public class Club {

    private long id;
    private String clubName;
    private String meetDay;
    private String meetTime;
    private String meetPlace;
    private String email;
    private String desc;
    private String link;
    private String imgUri;

    public Club() {
    }

    public Club(long id, String clubName, String meetDay, String meetTime, String meetPlace, String email, String desc, String imgUri) {
        this.id = id;
        this.clubName = clubName;
        this.meetDay = meetDay;
        this.meetTime = meetTime;
        this.meetPlace = meetPlace;
        this.email = email;
        this.desc = desc;
        this.imgUri = imgUri;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getMeetDay() {
        return meetDay;
    }

    public void setMeetDay(String meetDay) {
        this.meetDay = meetDay;
    }

    public String getMeetTime() {
        return meetTime;
    }

    public void setMeetTime(String meetTime) {
        this.meetTime = meetTime;
    }

    public String getMeetPlace() {
        return meetPlace;
    }

    public void setMeetPlace(String meetPlace) {
        this.meetPlace = meetPlace;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", clubName='" + clubName + '\'' +
                ", meetDay='" + meetDay + '\'' +
                ", meetTime='" + meetTime + '\'' +
                ", meetPlace='" + meetPlace + '\'' +
                ", email='" + email + '\'' +
                ", desc='" + desc + '\'' +
                ", imgUri='" + imgUri + '\'' +
                '}';
    }
}
