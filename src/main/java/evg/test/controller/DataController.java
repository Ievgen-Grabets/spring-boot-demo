package evg.test.controller;

import evg.test.dto.DataDTO;
import evg.test.exception.RestException;
import evg.test.service.DataService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class DataController extends ExceptionHandlerController {

    private static final Logger LOG = Logger.getLogger(DataController.class);

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/feed",  method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DataDTO>> handle(
            @RequestParam("accessToken") String accessToken,
            @RequestParam(value = "category[]", required = false) String[] category,
            @RequestParam(value = "tags[]", required = false) String[] tags,
            @RequestParam(value = "studio", required = false) String studio,
            @RequestParam(value = "promotedIds[]", required = false) String[] promotedIds,
            @RequestParam(value = "order", required = false, defaultValue = "relevancy") String order)
            throws RestException {

        System.out.println("category " + Arrays.toString(category));
        System.out.println("tags " + Arrays.toString(tags));
        System.out.println("studio " + studio);
        System.out.println("publishTime " + order);
        System.out.println("promotedIds " + Arrays.toString(promotedIds));
        List<DataDTO> dataDTOS = dataService.getDataList(category, tags, studio, promotedIds, order);

        return ResponseEntity.ok().body(dataDTOS);

    }

}