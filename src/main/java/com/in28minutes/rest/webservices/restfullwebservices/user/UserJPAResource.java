package com.in28minutes.rest.webservices.restfullwebservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserJPAResource {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserDaoService service;
    // GET /users
    // retrieveAllUsers
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    // GET /users/{id}
    // retrieveUser(int id)
    @GetMapping("/jpa/users/{id}")
    public Resource<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent())
            throw new UserNotFoundException("id-" + id);
        Resource<User> resource = new Resource<User>(user.get());
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        resource.add(linkTo.withRel("all-users"));
        return resource;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        @Valid User savedUser = userRepository.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
