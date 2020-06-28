package com.dk.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/swing")
public class Controller {

    @Autowired
    private SwingDataService swingDataService;

    @PostMapping(path = "/load", consumes = "text/plain")
    public void loadData(@RequestBody String filePath) {
        swingDataService.loadDataFile(filePath);
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "column") String column,
                         @RequestParam(name = "start") String start,
                         @RequestParam(name = "end") String end,
                         @RequestParam(name = "threshold") String threshold,
                         @RequestParam(name = "winLength") String winLength
    ) throws InvalidInputExcpeption {

        int index = swingDataService.searchContinuityAboveValue(column, Integer.parseInt(start), Integer.parseInt(end),
                Double.parseDouble(threshold), Integer.parseInt(winLength));
        return String.valueOf(index);
    }

    @GetMapping("/backSearch")
    public String backSearch(@RequestParam(name = "column") String column,
                             @RequestParam(name = "start") String start,
                             @RequestParam(name = "end") String end,
                             @RequestParam(name = "thresholdLo") String thresholdLo,
                             @RequestParam(name = "thresholdHi") String thresholdHi,
                             @RequestParam(name = "winLength") String winLength
    ) throws InvalidInputExcpeption {

        int index = swingDataService.backSearchContinuityWithinRange(column, Integer.parseInt(start), Integer.parseInt(end),
                Double.parseDouble(thresholdLo), Double.parseDouble(thresholdHi), Integer.parseInt(winLength));
        return String.valueOf(index);
    }

    @GetMapping("/searchTwo")
    public String searchTwo(@RequestParam(name = "column1") String column1,
                            @RequestParam(name = "column2") String column2,
                            @RequestParam(name = "start") String start,
                            @RequestParam(name = "end") String end,
                            @RequestParam(name = "threshold1") String threshold1,
                            @RequestParam(name = "threshold2") String threshold2,
                            @RequestParam(name = "winLength") String winLength
    ) throws InvalidInputExcpeption {

        int index = swingDataService.searchContinuityAboveValueTwoSignals(column1, column2, Integer.parseInt(start),
                Integer.parseInt(end),
                Double.parseDouble(threshold1), Double.parseDouble(threshold2), Integer.parseInt(winLength));
        return String.valueOf(index);
    }

    @GetMapping("/searchMulti")
    public String searchMulti(@RequestParam(name = "column") String column,
                              @RequestParam(name = "start") String start,
                              @RequestParam(name = "end") String end,
                              @RequestParam(name = "thresholdLo") String thresholdLo,
                              @RequestParam(name = "thresholdHi") String thresholdHi,
                              @RequestParam(name = "winLength") String winLength
    ) throws InvalidInputExcpeption {

        List<Range> ranges = swingDataService.searchMultiContinuityWithinRange(column,
                Integer.parseInt(start),
                Integer.parseInt(end),
                Double.parseDouble(thresholdLo), Double.parseDouble(thresholdHi), Integer.parseInt(winLength));
        return ranges.toString();
    }
}
