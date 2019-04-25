package util;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessage {
    public static ResponseEntity getErrorResponse(int status, String message){
        Map errResponse = new HashMap();
        errResponse.put("message", message);
        return ResponseEntity.status(status).body(errResponse);
    }
}
