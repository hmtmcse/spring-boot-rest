package util;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_ERROR = "error";

    public static Map<String, Object> makeResponse(Object responseData) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", STATUS_SUCCESS);
        map.put("data", responseData);

        return map;
    }

    public static Map<String, Object> makeResponse(String status, String responseMessage) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("message", responseMessage);
        return map;
    }
}
