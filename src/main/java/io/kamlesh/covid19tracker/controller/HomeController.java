package io.kamlesh.covid19tracker.controller;

import io.kamlesh.covid19tracker.models.LocationStats;
import io.kamlesh.covid19tracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllstats();
        int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();

        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases",totalCases);
        return "home";
    }
}
