package at.fhtw.restserver.server;

import at.fhtw.sampleapp.service.echo.EchoHandler;
import at.fhtw.sampleapp.service.weather.WeatherHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public void start() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(10001), 10);

        server.createContext("/weather", new WeatherHandler());
        server.createContext("/echo", new EchoHandler());

        server.start();
    }
}
