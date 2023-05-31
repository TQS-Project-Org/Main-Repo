package tqs.amanacu.picky.controllers;

import java.util.ArrayList;

import tqs.amanacu.picky.models.PickupPoint;
import tqs.amanacu.picky.services.PointService;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ecom")
public class  eCommerceController {

    @Autowired
    PointService pointService;

    @GetMapping("/get-points")
    public ArrayList<PickupPoint> getPoints() {
        return (ArrayList)pointService.getPoints();
    }

}
