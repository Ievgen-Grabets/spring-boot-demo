package evg.test.controller;

import evg.test.exception.RestException;
import evg.test.util.Ajax;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
public class DataController extends ExceptionHandlerController {

    private static final Logger LOG = Logger.getLogger(DataController.class);

    @RequestMapping(value = "/getData",  method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getData(@RequestParam(value = "category[]", required = false) String[] category,
                                @RequestParam(value = "tags[]", required = false) String[] tags,
                                @RequestParam(value = "studio[]", required = false) String studio[],
                                @RequestParam(value = "publishTime", required = false, defaultValue = "relevancy") String publishTime,
                                @RequestParam(value = "promotedIds ", required = false) String promotedIds []) throws RestException {

        System.out.println("category " + Arrays.toString(category));
        System.out.println("tags " + Arrays.toString(tags));
        System.out.println("studio " +  Arrays.toString(studio));
        System.out.println("publishTime " + publishTime);
        System.out.println("studio " +  Arrays.toString(promotedIds));

        try {
            return Ajax.emptyResponse();
        } catch (Exception e) {
            LOG.error("Cant execute //getData", e);
            throw new RestException(e);
        }
    }

}