package com.example.piinfo.service;

import com.example.piinfo.model.Account;
import com.example.piinfo.model.Product_User;
import com.example.piinfo.model.Reminder;
import com.example.piinfo.repository.MovementRepository;
import com.example.piinfo.repository.Product_UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Product_UserService {

    private final Product_UserRepository product_userRepository;

    public Product_UserService(Product_UserRepository product_userRepository){
        this.product_userRepository = product_userRepository;
    }

    public void save(String product_number) {
        Product_User product_user = new Product_User(product_number, "");
        product_userRepository.save(product_user);
    }

    public String verifyProduct(String product_number) {

        List<Product_User> product_users = product_userRepository.findAll();

        String productFlag = "No existe el producto";

        for (Product_User p : product_users) {
            if(p.getProduct_number().equals(product_number)){
                productFlag = p.getId();
            }
        }

        return productFlag;

    }

    public void registerUserProduct(String id_product_user, String id_user) {

        Optional<Product_User> obj = product_userRepository.findById(id_product_user);
        Product_User product_user = obj.get();

        product_user.setId_user(id_user);
        product_userRepository.save(product_user);

    }

    public String sync(String product_number) {

        List<Product_User> product_users = product_userRepository.findAll();

        String info = "No se ha sincronizado un usuario al dispositivo";

        for (Product_User p : product_users) {
            if(p.getProduct_number().equals(product_number)){
                if(!p.getId_user().equals("")){
                    info = p.getId_user();
                }
            }
        }

        return info;
    }
}
