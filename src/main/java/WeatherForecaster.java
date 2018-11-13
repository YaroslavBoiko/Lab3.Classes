import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WeatherForecaster {
  
    private static final String KEY = "6460438d1ecf455996a183258181610";
    private static final String URL = "http://api.apixu.com/v1/current.json";



    //TODO Apply forecast logic for city
    public String forecast(City city) throws UnirestException{
        
        HttpResponse<String> response = Unirest.get(URL)
            .queryString("key", KEY)
            .queryString("q", city.getCoordinates().lat +" "+ city.getCoordinates().lon)
            .asString();
            
        System.out.println(response.getBody());
        
        return null;

    }


}
