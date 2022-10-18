package vn.com.viettel.core.rest;

import vn.com.viettel.core.utils.RestUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController extends BaseRestController {

    @GetMapping("/health/status")
    public ResponseEntity<String> getHealthStatus(){
        return RestUtils.responseOk("ok");
    }

}
