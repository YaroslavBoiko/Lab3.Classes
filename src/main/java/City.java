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
    
    public void printInfo() {
        System.out.println("City name: " + this.getName());
        System.out.println("City administrativeArea: " + this.getAdministrativeArea());
        System.out.println("City number Of Citizens: " + this.getNumberOfCitizens());
        System.out.println("City coordinates: " + this.getCoordinates().lat + " " + this.getCoordinates().lon);
        System.out.println("City area: " + this.getArea());
    }
    
    
    public static int parsePopulation(Element element) {
        
        int element_size = element.getAllElements().size();
        
        if (element_size > 1) {
                element = element.getAllElements().get(element_size - 1);
            }
            String _anchor2 = element.text().replaceAll("(&nbsp;|\\s)","").replace("\u00a0","");
        
            return Integer.parseInt(_anchor2);
    
    }


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
            myCity.setNumberOfCitizens(parsePopulation(anchor2));
            myCity.setYearOfFound(anchor3.attr("title"));
            myCity.setArea(Double.valueOf(anchor4.text()));


            Coordinates coordinates = new Coordinates();
            String url = myCity.url;
            double[] s = coordinates.Coordinat(url);
            
            if (s != null) {
                coordinates.setlan(s[0]);
                coordinates.setlon(s[1]);
                myCity.setCoordinates(coordinates);

                return myCity;
            } else {
                System.out.println("Sorry, we didn`t support this city!");
                return null;
            }
        }
        return null;
    }

}
