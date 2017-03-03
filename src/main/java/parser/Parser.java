package parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by joseph on 02.03.2017.
 */
@Service
public class Parser {
    public static final String GOOGLE_SEARCH_URL = "https://www.google.com/search";
    private final ParserModel parserModel;

    @Autowired
    public Parser(ParserModel parserModel) {
        this.parserModel = parserModel;
    }

    public String executeQuery(String query) throws IOException {
        String searchUrl = GOOGLE_SEARCH_URL + "?q=" + query + "&num=" + 1;
        Document doc = Jsoup.connect(searchUrl).userAgent("Mozilla/5.0").get();
        Elements results = doc.select("h3.r > a");

        for (Element element : results) {
            String linkHref = element.attr("href");
            String linkText = element.text();
            System.out.println("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
            if (linkHref.substring(7, linkHref.indexOf("&")).startsWith("h")) {
                parserModel.getUrls().add(linkHref.substring(7, linkHref.indexOf("&")));
            }
        }

        for(String s : parserModel.getUrls()) {
            Connection connection1 = Jsoup.connect(s).ignoreContentType(true).userAgent("Mozilla/5.0").timeout(10000);
            Connection.Response response = connection1.execute();
            if (response.statusCode() == 200) {
                doc = connection1.get();

            }
        }
        System.out.println(Arrays.asList(parserModel.getUrls()));
        return doc.html();
    }
}
