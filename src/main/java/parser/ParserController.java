package parser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by joseph on 02.03.2017.
 */
@RestController
@RequestMapping(path = "parse")
public class ParserController {

    private final Parser parser;
    @Autowired
    public ParserController(Parser parser) {
        this.parser = parser;
    }

    @GetMapping(path = "/{query}")
    public String getGivenElement(@PathVariable String query) throws IOException {

        return parser.executeQuery(query);

    }

}
