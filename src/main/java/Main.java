import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Victor on 03.10.2018.
 */
public class Main {


    @SneakyThrows
    public static void main(String[] args) throws IOException {
        String url = "https://uk.wikipedia.org/wiki/%D0%9C%D1%96%D1%81%D1%82%D0%B0_%D0%A3%D0%BA%D1%80%D0%B0%D1%97%D0%BD%D0%B8_(%D0%B7%D0%B0_%D0%B0%D0%BB%D1%84%D0%B0%D0%B2%D1%96%D1%82%D0%BE%D0%BC)";
        Document doc = Jsoup.connect(url).get();
       
        Elements cities = doc.select("table tr");
        City[] parsedCities = new City[cities.size()]; // You can use List`s or other java Collections
        int counter = 0;
        for (Element city : cities) {
            City myCity;
            myCity = City.parse(city);
            if (myCity != null) {
                System.out.println(myCity);
                parsedCities[counter] = myCity;
                counter++;
            }
        }
        
        String city_name = "Львів";
        
        WeatherForecaster weather_forecast = new WeatherForecaster();
        
        for (City _city : parsedCities) {
            if (city_name.equalsIgnoreCase(_city.getName())){
                _city.printInfo();
                weather_forecast.forecast(_city);
                break;
            }
        }
        
        
    }

}
//https://uk.wikipedia.org/wiki/%D0%91%D0%B0%D1%85%D1%87%D0%B8%D1%81%D0%B0%D1%80%D0%B0%D0%B9
