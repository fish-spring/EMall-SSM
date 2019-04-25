package controller.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserService;

@RestController
@RequestMapping("/shops")
public class ShopsController {
    @Autowired
    private UserService userService;
}
