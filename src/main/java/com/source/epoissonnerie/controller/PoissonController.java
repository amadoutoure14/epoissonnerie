package com.source.epoissonnerie.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/poisson",consumes = MediaType.APPLICATION_JSON_VALUE)
public class PoissonController {
}
