package controller.order;

import dao.OrderDetailDao;
import dao.OrderInfoDao;
import dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pojo.OrderDetail;
import pojo.OrderInfo;
import pojo.Product;
import pojo.User;
import pojo.page.ProductPage;
import service.ProductService;
import util.payjs.NativeResponse;
import util.payjs.PayJS;
import vo.ProductVo;
import vo.UserOrderItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private PayJS payJS;

    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private OrderDetailDao orderDetailDao;

    // List of order detail
    // 服务端计算出总价，生成对应订单

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Integer productId){
        return productDao.selectByPrimaryKey(productId);
    }

    @PostMapping
    private Object publishProduct(@RequestBody List<UserOrderItem> orderItemList,
                                  @RequestAttribute("VerifiedUsername") User user){

        int totalPrice = 0;

        // 遍历一遍列表，拿到每个商品的详细信息并且计算总价
        for (UserOrderItem item : orderItemList){
            Product product = productDao.selectByPrimaryKey(item.getProductId());
            totalPrice += item.getCount() * product.getPrice();
            item.setProduct(product);
        }

        // 在数据库中创建订单,这样可以拿到id作为用户订单号
        OrderInfo orderInfo = new OrderInfo();
        // 设置用户id
        orderInfo.setUserId(user.getId());
        // 默认就是payJS
        orderInfo.setPaymentMethod(4);
        // 字符金额
        orderInfo.setPaymentMoney(totalPrice);

        orderInfoDao.insertSelective(orderInfo);

        // 将订单详情插入数据库
        for (UserOrderItem item : orderItemList){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderInfoId(orderInfo.getId());
            orderDetail.setProductId(item.getProductId());
            orderDetail.setProductCount(item.getCount());
            orderDetail.setProductPrice(item.getProduct().getPrice());
            orderDetail.setShopId(item.getProduct().getShopId());
            orderDetail.setShippingUsername("Jon Snow");
            orderDetail.setAddress(item.getAddress());
            orderDetail.setUserId(user.getId());

            orderDetailDao.insertSelective(orderDetail);
        }

        NativeResponse response = payJS.apiNative(
                String.valueOf(orderInfo.getId()), totalPrice);

        // todo 异常处理

        // 向数据库中补充相关数据
        orderInfo.setQrcode(response.getQrcode());
        orderInfo.setPaymentOrderId(response.getPayjs_order_id());
        orderInfoDao.updateSelective(orderInfo);

        return response;
    }
}
