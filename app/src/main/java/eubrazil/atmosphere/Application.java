package eubrazil.atmosphere;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;
import java.util.TimeZone;
import javax.annotation.PostConstruct;

@ComponentScan(basePackages =
    {"eubrazil.atmosphere.config",
        "eubrazil.atmosphere.controller",
        "eubrazil.atmosphere.rest",
        "eubrazil.atmosphere.security",
        "eubrazil.atmosphere.service",
        "eubrazil.atmosphere.util"}
        )
@EntityScan(basePackages = {"eubrazil.atmosphere.model"})
@SpringBootApplication
public class Application {

  private static final Logger LOGGER = LogManager.getLogger(Application.class);
  @Value("${app.settings.timezone}")
  private String timezone;

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getTimeZone(timezone));
    LOGGER.info("Starting Application in timezone " + timezone + " (" + new Date() + ")");
  }

}
