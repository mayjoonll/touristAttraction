//package com.my.touristAttraction.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//public class AccommodationController {
//
//    private final AccommodationService service;
//
//    @GetMapping("/accommodations/fetch")
//    public String fetchAccommodations() {
//        service.fetchAndSaveAccommodations();
//        return "Accommodation data fetched and saved!";
//    }
//}