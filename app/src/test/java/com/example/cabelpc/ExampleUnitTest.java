package com.example.cabelpc;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cabelpc.Model.Products;
import com.example.cabelpc.Model.Users;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void getView_Phone() {
        Users user = new Users("Kirill", "7961274621", "1124124", "image");
        assertEquals("7961274621", user.getPhone());
    }

    @Test
    public void getView_Name() {
        Users user = new Users("Kirill", "7961274621", "1124124", "image");
        assertEquals("Kirill", user.getName());
    }

    @Test
    public void getView_Password() {
        Users user = new Users("Kirill", "7961274621", "1124124", "image");
        assertEquals("1124124", user.getPassword());
    }

    @Test
    public void getView_Image() {
        Users user = new Users("Kirill", "7961274621", "1124124", "image");
        assertEquals("image", user.getImage());
    }

    @Test
    public void getView_ProductNameFail() {
        Products products = new Products("", "", "", "", "", "", "", "");
        products.setPname("cabel");
        products.setDescription("long");
        products.setCategory("silovoy");
        assertEquals("admin", products.getPname());
        assertEquals("123", products.getDescription());
        assertEquals("123", products.getCategory());
    }

    @Test
    public void getView_ProductNameFail2() {
        Products products = new Products("", "", "", "", "", "", "", "");
        products.setPname("123");
        products.setDescription("long");
        products.setCategory("silovoy");
        assertEquals(123, products.getPname());
        assertEquals(234, products.getDescription());
        assertEquals(123, products.getCategory());
    }

    @Test
    public void getView_ProductPrice() {
        Products products = new Products("asd", "asd", "4500", "asd", "asd", "asd", "asd", "asd");
        assertEquals("4500", products.getPrice());
    }

    @Test
    public void getView_ProductFail() {
        Products products = new Products("asd", "asd", "4500", "asd", "asd", "asd", "asd", "asd");
        assertEquals(4500, products.getPrice());
    }

    @Test
    public void getView_ProductFail2() {
        Products products = new Products("11", "11", "11", "11", "11", "11", "11", "11");
        assertEquals("admin", products.getPname());
        assertEquals(123, products.getDescription());
        assertEquals("123", products.getCategory());
    }
}
