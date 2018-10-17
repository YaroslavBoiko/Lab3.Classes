import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

//TODO Create class that represents coordinates
public class Coordinates {
    double lon;
    double lat;

    public String url;


        @SneakyThrows
        public static double[] Coordinat(String url) {
            Document doc = Jsoup.connect(url).get();
            Element anchor = doc.getElementsByClass("geo").get(0);
            String [] array1 = anchor.text().replaceAll(";", "").split(" ");
            String Lat = array1[0];
            String Long = array1[1];
            double[] result = {Double.valueOf(Lat),Double.valueOf(Long)};
            return result;

    }

    public void setlan(double v) {
            this.lat = v;
    }

    public void setlon(double v) {
            this.lon = v;
    }

}

