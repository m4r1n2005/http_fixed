package at.fhtw.sampleapp.service.weather;

import at.fhtw.restserver.http.ContentType;
import at.fhtw.restserver.http.HttpStatus;
import at.fhtw.restserver.http.Method;
import at.fhtw.restserver.server.Request;
import at.fhtw.restserver.server.Response;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class WeatherHandler implements HttpHandler {
    private final WeatherController weatherController;

    public WeatherHandler() {
        this.weatherController = new WeatherController();
    }

    @Override
    public void handle(HttpExchange httpExchange) {
        try {
            Request request = new Request(httpExchange.getRequestURI());

            Response response;

            if (Method.valueOf(httpExchange.getRequestMethod()) == Method.GET &&
                    request.getPathParts().size() > 1) {
                response = this.weatherController.getWeather(request.getPathParts().get(1));
            } else if (Method.valueOf(httpExchange.getRequestMethod()) == Method.GET) {
                response = this.weatherController.getWeather();
//                response = this.weatherController.getWeatherPerRepository();
            } else if (Method.valueOf(httpExchange.getRequestMethod()) == Method.POST) {
                    response = this.weatherController.addWeather(IOUtils.toString(httpExchange.getRequestBody(), StandardCharsets.UTF_8));

            } else {
                response = new Response(
                        HttpStatus.BAD_REQUEST,
                        ContentType.JSON,
                        "[]"
                );
            }

            response.send(httpExchange);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
