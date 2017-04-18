package evg.test.controller;

import evg.test.exception.RestException;
import evg.test.util.Ajax;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
public class DataController extends ExceptionHandlerController {

    private static final Logger LOG = Logger.getLogger(DataController.class);

    @RequestMapping(value = "/getData",  method = RequestMethod.GET)
    public ResponseEntity String(@RequestParam(value = "category", required = false) String[] category,
                                  @RequestParam(value = "tags", required = false) String[] tags,
                                  @RequestParam(value = "studio", required = false) String[] studio,
                                  @RequestParam(value = "publishTime", required = false, defaultValue = "relevancy") String publishTime,
                                  @RequestParam(value = "promotedIds", required = false) String[] promotedIds) throws RestException {

        System.out.println("category " + Arrays.toString(category));
        System.out.println("tags " + Arrays.toString(tags));
        System.out.println("studio " + Arrays.toString(studio));
        System.out.println("publishTime " + publishTime);
        System.out.println("promotedIds " + Arrays.toString(promotedIds));

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("HeaderKey", "HeaderData");
        return new ResponseEntity<>(
                "<i>This is</i> the <h2>Page value</h2> (ResponseBody)", responseHeaders,
                HttpStatus.OK);

    }

}