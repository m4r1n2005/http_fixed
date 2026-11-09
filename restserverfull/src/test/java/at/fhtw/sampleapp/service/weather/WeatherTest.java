package at.fhtw.sampleapp.service.weather;

import at.fhtw.sampleapp.model.Weather;
import org.junit.jupiter.api.Test;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherTest {

    @Test
    void testWeatherServiceGetCompleteList() throws Exception {
        URL url = new URI("http://localhost:10001/weather").toURL();
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            //Weather[] weatherList = new ObjectMapper().readValue(bufferedReader.readLine(), Weather[].class);
            //assertEquals(3, weatherList.length);
            List<Weather> weatherList = new ObjectMapper().readValue(bufferedReader.readLine(), new TypeReference<>(){});
            assertEquals(3, weatherList.size());
        } catch (JacksonException e) {
            e.printStackTrace();
        }

        bufferedReader.close();
    }

    @Test
    void testWeatherServiceGetByIdCheckString() throws Exception {
        URL url = new URI("http://localhost:10001/weather/1").toURL();
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        assertEquals("{\"city\":\"Vienna\",\"id\":1,\"region\":\"Europe\",\"temperature\":9.0}", bufferedReader.readLine());

        bufferedReader.close();
    }

    @Test
    void testWeatherServiceGetById() throws Exception {
        URL url = new URI("http://localhost:10001/weather/1").toURL();
        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            Weather weather = new ObjectMapper().readValue(bufferedReader.readLine(), Weather.class);
            assertEquals(1, weather.getId());
            assertEquals("Vienna", weather.getCity());
        } catch (JacksonException e) {
            e.printStackTrace();
        }

        bufferedReader.close();
    }

}