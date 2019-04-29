package controller.topic;

import dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import pojo.Product;
import pojo.page.ProductPage;
import service.ProductService;
import vo.ProductDiscount;
import vo.ProductVo;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    private ProductDao productDao;

    @RequestMapping(value = "/slides", method = RequestMethod.GET)
    public List<Product> getSlides(){
        return productDao.selectSlides();
    }
    @RequestMapping(value = "/seckill", method = RequestMethod.GET)
    public List<ProductDiscount> getSeckill(){
        return productDao.selectSeckill();
    }

}
