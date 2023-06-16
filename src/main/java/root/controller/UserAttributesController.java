package root.controller;

import root.model.UserAttributes;

import root.model.Response;
import root.service.UserAttributesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/userAttributes")
public class UserAttributesController {
    @Autowired
    private UserAttributesService service;

    @PostMapping()
    public Response predict(@RequestBody UserAttributes userAttributes) throws Exception {
        return service.predict(userAttributes);
    }
}
