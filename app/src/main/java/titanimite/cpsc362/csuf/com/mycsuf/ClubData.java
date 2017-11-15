package titanimite.cpsc362.csuf.com.mycsuf;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Daniel on 10/11/17.
 */

public class ClubData {

    private JSONArray clubs;
    private ArrayList<Club> clubArr;

    public ClubData() {
        clubs = new JSONArray();
        clubArr = new ArrayList<Club>();
        loadData(0);
    }

    private void loadData() {
        JSONObject club1 = new JSONObject();
        JSONObject club2 = new JSONObject();
        JSONObject club3 = new JSONObject();
        JSONObject club4 = new JSONObject();
        JSONObject club5 = new JSONObject();
        JSONObject club6 = new JSONObject();
        JSONObject club7 = new JSONObject();

        try {
            club1.put("name", "ACM-W");
            club1.put("desc", "ACM-W supports, celebrates, and advocates internationally for the full engagement of women in all aspects of the computing field, providing a wide range of programs and services to ACM members and working in the larger community to advance the contributions of technical women.");
            club1.put("link", "http://acmw.ecs.fullerton.edu/");
            club1.put("image", "link_to_image");

            club2.put("name", "ACM");
            club2.put("desc", "ACM (founded 1947) is an international scientific and educational organization dedicated to advancing the art, science, engineering, and application of information technology, serving both professional and public interests by fostering the open interchange of information and by promoting the highest professional and ethical standards. \n" +
                    "\n" +
                    "The Student Chapter of ACM at CSUF provides a continuing forum for the exchange of ideas and announcements for all the students on campus that share an interest in the diversified aspects of computing.\n" +
                    "\n" +
                    "The Student Chapter of ACM at CSUF is an open organization to any student with interest in computers and/or computing. The ACM holds monthly general meetings throughout the semester. These meetings provide an excellent environment for students in which to exchange views, opinions, meet new friends and trade secret techniques. In addition to regular meetings, CSUF's Student Chapter of the ACM hosts selected speaker forums, monthly contests, software and hardware workshops, arranges company tours and plans social activities throughout the semester. Everyone is welcome, and encouraged to attend the meetings!\n");
            club2.put("link", "http://ecs.fullerton.edu/~acm/");
            club2.put("image", "link_to_image");

            club3.put("name", "Titan roover");
            club3.put("desc", "Titan Rover is a multidisciplinary collaboration of mechanical, electrical, computer engineers, and many other disciplines from California State University, Fullerton including computer science, geology, biology, and business majors. Together students join forces to research, design, manufacture, and test a Mars Exploration Rover (MER) to compete in the University Rover Challenge (URC). The rover features a robotic arm with interchangeable end effectors that are capable of performing tasks such as flipping switches, turning valves, and screwing threaded features into place. The main design consists of a six wheel direct suspension, GPS navigation, and wireless communication systems.");
            club3.put("link", "http://www.fullerton.edu/titanrover/about/");
            club3.put("image", "link_to_image");

            club4.put("name", "Society of Women Engineers");
            club4.put("desc", "The Society of Women Engineers (SWE) is a not-for-profit educational and service organization that empowers women to succeed and advance in the field of engineering, and to be recognized for their life-changing contributions as engineers and leaders.");
            club4.put("link", "https://www.facebook.com/csufswe/");
            club4.put("image", "link_to_image");

            club5.put("name", "Nation Society of Black Engineers");
            club5.put("desc", "To increase the number of culturally responsible Black Engineers who excel academically, succeed professionally, and positively impact the community. The goal for the Cal State Fullerton Chapter is not only to uphold our organizations mission, but to include those students willing to promote higher education and community service throughout their educational experiences, and careers without regard to their major or race.");
            club5.put("link", "http://www.fullerton.edu/aarc/clubs/nsbe.php");
            club5.put("image", "link_to_image");

            club6.put("name", "MAEs");
            club6.put("desc", "It all began in 1974. MAES was founded in Los Angeles at CSU Fullerton in 1974 to increase the number of Mexican Americans and other Hispanics in the technical and scientific fields. The idea to establish a professional society for Mexican American engineers originated with Robert Von Hatten, an aerospace electronics engineer with TRW Defense Space Systems in Redondo Beach, California. Mr. Von Hatten had for several years served as volunteer for programs directed at combating the alarming number of high school dropouts. He envisioned a national organization that would serve as a source for role models, address of the needs of its members, and become a resource for industry and students.");
            club6.put("link", "http://www.ecs.fullerton.edu/~maes/");
            club6.put("image", "link_to_image");

            club7.put("name", "SHPE (Society of Hispanic Professional Engineers)");
            club7.put("desc", "SHPE changes lives by empowering the Hispanic community to realize its fullest potential and to impact the world through STEM awareness, access, support and development.");
            club7.put("link", "http://csufshpe.org/");
            club7.put("image", "link_to_image");


            clubs.put(club1);
            clubs.put(club2);
            clubs.put(club3);
            clubs.put(club4);
            clubs.put(club5);
            clubs.put(club6);
            clubs.put(club7);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadData(int i) {
        Club club1 = new Club();
        Club club2 = new Club();
        Club club3 = new Club();
        Club club4 = new Club();
        Club club5 = new Club();
        Club club6 = new Club();
        Club club7 = new Club();

        club1.setClubName("ACM-W");
        club1.setDesc("ACM-W supports, celebrates, and advocates internationally for the full engagement of women in all aspects of the computing field, providing a wide range of programs and services to ACM members and working in the larger community to advance the contributions of technical women.");
        club1.setLink("http://acmw.ecs.fullerton.edu/");
        club1.setImgUri("link_to_image");
        club1.setMeetDay("Monday");
        club1.setMeetTime("1:00pm");
        club1.setMeetPlace("TSU 101");
        club1.setEmail("acmw@gmail.com");


        club2.setClubName("ACM");
        club2.setDesc("ACM (founded 1947) is an international scientific and educational organization dedicated to advancing the art, science, engineering, and application of information technology, serving both professional and public interests by fostering the open interchange of information and by promoting the highest professional and ethical standards. \n" +
                "\n" +
                "The Student Chapter of ACM at CSUF provides a continuing forum for the exchange of ideas and announcements for all the students on campus that share an interest in the diversified aspects of computing.\n" +
                "\n" +
                "The Student Chapter of ACM at CSUF is an open organization to any student with interest in computers and/or computing. The ACM holds monthly general meetings throughout the semester. These meetings provide an excellent environment for students in which to exchange views, opinions, meet new friends and trade secret techniques. In addition to regular meetings, CSUF's Student Chapter of the ACM hosts selected speaker forums, monthly contests, software and hardware workshops, arranges company tours and plans social activities throughout the semester. Everyone is welcome, and encouraged to attend the meetings!\n");
        club2.setLink("http://ecs.fullerton.edu/~acm/");
        club2.setImgUri("link_to_image");
        club2.setMeetDay("Tuesday");
        club2.setMeetTime("2:00pm");
        club2.setMeetPlace("TSU 102");
        club2.setEmail("acm@gmail.com");

        club3.setClubName("Titan Rover");
        club3.setDesc("Titan Rover is a multidisciplinary collaboration of mechanical, electrical, computer engineers, and many other disciplines from California State University, Fullerton including computer science, geology, biology, and business majors. Together students join forces to research, design, manufacture, and test a Mars Exploration Rover (MER) to compete in the University Rover Challenge (URC). The rover features a robotic arm with interchangeable end effectors that are capable of performing tasks such as flipping switches, turning valves, and screwing threaded features into place. The main design consists of a six wheel direct suspension, GPS navigation, and wireless communication systems.");
        club3.setLink("http://www.fullerton.edu/titanrover/about/");
        club3.setImgUri("link_to_image");
        club3.setMeetDay("Wednesday");
        club3.setMeetTime("3:00pm");
        club3.setMeetPlace("TSU 103");
        club3.setEmail("titanrover@gmail.com");

        club4.setClubName("Society of Women Engineers");
        club4.setDesc("The Society of Women Engineers (SWE) is a not-for-profit educational and service organization that empowers women to succeed and advance in the field of engineering, and to be recognized for their life-changing contributions as engineers and leaders.");
        club4.setLink("https://www.facebook.com/csufswe/");
        club4.setImgUri("link_to_image");
        club4.setMeetDay("Thursday");
        club4.setMeetTime("4:00pm");
        club4.setMeetPlace("TSU 104");
        club4.setEmail("swe@gmail.com");

        club5.setClubName("Nation Society of Black Engineers");
        club5.setDesc("To increase the number of culturally responsible Black Engineers who excel academically, succeed professionally, and positively impact the community. The goal for the Cal State Fullerton Chapter is not only to uphold our organizations mission, but to include those students willing to promote higher education and community service throughout their educational experiences, and careers without regard to their major or race.");
        club5.setLink("http://www.fullerton.edu/aarc/clubs/nsbe.php");
        club5.setImgUri("link_to_image");
        club5.setMeetDay("Friday");
        club5.setMeetTime("5:00pm");
        club5.setMeetPlace("TSU 105");
        club5.setEmail("nsbe@gmail.com");

        club6.setClubName("MAEs");
        club6.setDesc("It all began in 1974. MAES was founded in Los Angeles at CSU Fullerton in 1974 to increase the number of Mexican Americans and other Hispanics in the technical and scientific fields. The idea to establish a professional society for Mexican American engineers originated with Robert Von Hatten, an aerospace electronics engineer with TRW Defense Space Systems in Redondo Beach, California. Mr. Von Hatten had for several years served as volunteer for programs directed at combating the alarming number of high school dropouts. He envisioned a national organization that would serve as a source for role models, address of the needs of its members, and become a resource for industry and students.");
        club6.setLink("http://www.ecs.fullerton.edu/~maes/");
        club6.setImgUri("link_to_image");
        club6.setMeetDay("Monday");
        club6.setMeetTime("6:00pm");
        club6.setMeetPlace("TSU 106");
        club6.setEmail("mae@gmail.com");

        club7.setClubName("SHPE (Society of Hispanic Professional Engineers)");
        club7.setDesc("SHPE changes lives by empowering the Hispanic community to realize its fullest potential and to impact the world through STEM awareness, access, support and development.");
        club7.setLink("http://csufshpe.org/");
        club7.setImgUri("link_to_image");
        club7.setMeetDay("Tuesday");
        club7.setMeetTime("7:00pm");
        club7.setMeetPlace("TSU 107");
        club7.setEmail("shpe@gmail.com");


        clubArr.add(club1);
        clubArr.add(club2);
        clubArr.add(club3);
        clubArr.add(club4);
        clubArr.add(club5);
        clubArr.add(club6);
        clubArr.add(club7);
    }

    public JSONArray getData() {
        return clubs;
    }

    public ArrayList<Club> getClubArr() {
        return clubArr;
    }

    public JSONObject getDataFor(int position) {
        try {
            return clubs.getJSONObject(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new JSONObject();
    }

    public Club getClub(int i) {
        Club c = new Club();
        try {
            c = (Club) clubs.get(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return c;
    }
}
