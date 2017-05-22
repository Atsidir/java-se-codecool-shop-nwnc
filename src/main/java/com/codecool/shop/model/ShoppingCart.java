package com.codecool.shop.model;
/** javadocumentation */

/**
 *@author tori
 * */


import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


public class ShoppingCart {
    int id;
    HashMap<Product, Integer> Products;
    private static int currentid = 1;

    /**
     * create new shoppingcart
     * */
    public ShoppingCart() {
        this.id = currentid;
        currentid++;
        Products = new HashMap<>();
    }


    /**
     *  shoppingcart id,
     *  shoppincart content(product and the number of product)
     * */
    public ShoppingCart(int id,HashMap<Product, Integer> products) {
        this.id = id;
        this.Products = products;
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
                }
            }
        } else {
            this.Products.put(product, 1);
        }

    }


    public Product find(int id) {
        return this.Products.keySet().stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }


    /**
     *  integer number of product in shoppingcart
     *  */
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
        }

    }


    public void remove(int id) {
        this.Products.remove(find(id));
    }


}
