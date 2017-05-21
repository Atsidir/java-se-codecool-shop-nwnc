package com.codecool.shop.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShoppingCart {
    int id;
    HashMap<Product, Integer> Products;
    private static int currentid = 1;
    final Logger logger = LoggerFactory.getLogger(ShoppingCart.class);



    public ShoppingCart() {
        this.id = currentid;
        currentid++;
        Products = new HashMap<>();
        logger.debug("ShoppingCart created with id: "+currentid);
    }

    public ShoppingCart(int id,HashMap<Product, Integer> products) {
        this.id = id;
        this.Products = products;

        logger.info("ShoppingCart created with id: "+id);
    }

    public int getId() {
        return id;
    }

    Integer sizeOfProducts() {
        return this.Products.size();
    }


    public HashMap<Product, Integer> getAll() {
        return this.Products;
    }

    public void add(Product product) {

        List<Integer> productidlist = this.Products.entrySet().stream().map(p -> p.getKey().getId()).collect(Collectors.toList());
        if (productidlist.contains(product.getId())) {
            for (Product prod : this.Products.keySet()) {
                if (prod.getId() == product.getId()) {
                    this.Products.put(prod, this.Products.get(prod) + 1);
                    logger.info("Product number in "+this.getId()+ ". increased: "+product.getName());
                }
            }
        } else {
            this.Products.put(product, 1);
            logger.info("New product added to "+this.getId()+ ". shoppingcart, name: "+product.getName());
        }

    }


    public Product find(int id) {
        return this.Products.keySet().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }


    public int getAllProducts() {
        int count = 0;
        if (this.Products==null) {
            return count;
        }
        for (Product prod : this.Products.keySet()) {
            count += this.Products.get(prod);
        }
        return count;
    }


    public float getTotalPrice() {
        float total = 0;

        for (Product prod : this.Products.keySet()) {
            total += this.Products.get(prod) * prod.getDefaultPrice();
        }
        return total;
    }


    public void decrease(int id) {

        if (this.Products.get(find(id)) > 1) {
            this.Products.put(find(id), this.Products.get(find(id)) - 1);
        } else {
            remove(id);
            logger.info("Product removed from "+getId()+ " cart,id: "+id);
        }

    }


    public void remove(int id) {
        this.Products.remove(find(id));
    }


}
