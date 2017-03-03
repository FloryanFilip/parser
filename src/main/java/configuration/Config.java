package configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import parser.ParserController;

/**
 * Created by joseph on 02.03.2017.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = ParserController.class)
public class Config {
}
