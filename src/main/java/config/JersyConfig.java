package config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import resource.JSONService;

import javax.ws.rs.ApplicationPath;

/**
 * Created by yang on 10/05/16.
 */
@Component
@ApplicationPath("/api")
public class JersyConfig extends ResourceConfig {

    public JersyConfig() {
        // Scan APIs under package recursively
        packages(true, "resource");
        // Register Swagger endpoint for /swagger.json and /swagger.yaml to get REST specification
        register(JSONService.class);
    }
}
