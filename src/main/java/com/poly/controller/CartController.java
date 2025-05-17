package com.poly.controller;

import com.poly.global.GlobalData;
import com.poly.model.Product;
import com.poly.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    @Autowired
    ProductService productService;

    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);
        return "cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    } 

    @GetMapping("/checkout")
    public String checkout(Model model) {
        if (GlobalData.cart.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng trống! Vui lòng thêm sản phẩm.");
            return "cart"; 
        } 
        GlobalData.cart.clear();
        model.addAttribute("message", "Thanh toán thành công!");
        
        return "cart"; 
    }


}
