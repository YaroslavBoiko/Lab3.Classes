/**
 * Created by Victor on 03.10.2018.
 */
import lombok.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@Getter
@Setter
@ToString
public class City {
    private String name;
    private String url;
    private String administrativeArea;
    private int numberOfCitizens;
//    private String numberOfCitizen;
    private String yearOfFound;
    private Coordinates coordinates; // Set this
    private double area;
//    private String area;

    private static final int INFO_SIZE = 6;


    public static City parse(Element city) {
        Elements info = city.select("td");

        if (info.size() == INFO_SIZE) {
            Element anchor = info.get(1).select("a").get(0);
            Element anchor1 = info.get(2).select("a").get(0);
            Element anchor2 = info.get(3);
            Element anchor3 = info.get(4).select("a").get(0);
            Element anchor4 = info.get(5);

            City myCity = new City();

            myCity.setName(anchor.attr("title"));
            myCity.setUrl(String.format("https://uk.wikipedia.org%s", anchor.attr("href")));
            myCity.setAdministrativeArea(anchor1.attr("title"));
            myCity.setNumberOfCitizens(Integer.parseInt(anchor2.text().replaceAll("\\s+","")));
            myCity.setYearOfFound(anchor3.attr("title"));
            myCity.setArea(Double.valueOf(anchor4.text()));


            Coordinates coordinates = new Coordinates();
            String url = myCity.url;
            double[] s = coordinates.Coordinat(url);
            coordinates.setlan(s[0]);
            coordinates.setlon(s[1]);
            myCity.setCoordinates(coordinates);

            return myCity;
        }
        return null;
    }

}
