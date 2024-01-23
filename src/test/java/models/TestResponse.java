package models;

import lombok.Data;

@Data
public class TestResponse {
    private String duration;
    private String method;
    private String name;
    private String startTime;
    private String endTime;
    private String status;
}
