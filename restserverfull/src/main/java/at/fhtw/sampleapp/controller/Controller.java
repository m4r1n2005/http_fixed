package at.fhtw.sampleapp.controller;

    import lombok.Getter;
    import tools.jackson.databind.ObjectMapper;

public class Controller {
    @Getter
    private final ObjectMapper objectMapper;

    public Controller() {
        this.objectMapper = new ObjectMapper();
    }
}
