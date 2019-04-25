package controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pojo.Product;
import pojo.User;
import pojo.page.ProductPage;
import service.UserService;
import util.ErrorMessage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<Product> getProducts(@RequestParam ProductPage page){
        return null;
    }
}
