package Entities;

import Entities.enums.Products;

import java.util.*;

public abstract class Company {

    private String name;
    private String address;
    private Integer nativeProduct;

    private Depot[] depotsArr = new Depot[40];

    public Company(String name, String address, Integer nativeProduct) {
        this.name = name;
        this.address = address;
        this.nativeProduct = nativeProduct;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Products getNativeProduct() {
        return Products.toEnum(nativeProduct);
    }

    public void setNativeProduct(Products nativeProduct) {
        this.nativeProduct = nativeProduct.getCode();
    }

    public Depot[] getDepotsArr() {
        return depotsArr;
    }

    public void setDepotsArr(Depot[] depotsArr) {
        this.depotsArr = depotsArr;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nativeProduct=" + nativeProduct +
                ", depotsArr=" + Arrays.toString(depotsArr) +
                '}';
    }
}