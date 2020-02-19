package com.in28minutes.rest.webservices.restfullwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

    @GetMapping("/v1/person")
    public PersonV1 personV1() {
        return new PersonV1("Minh Tan IT");
    }

    @GetMapping("/v2/person")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Tan IT", "Minh"));
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("Minh Tan IT");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Tan IT", "Minh"));
    }

    @GetMapping(value = "/person/header", params = "version=1")
    public PersonV1 headerV1() {
        return new PersonV1("Minh Tan IT");
    }

    @GetMapping(value = "/person/header", params = "version=2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Tan IT", "Minh"));
    }
}
