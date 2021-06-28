package com.example.demo.controller;

import com.example.demo.Mapper.CategoryMapper;
import com.example.demo.Models.Product;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Models.ProductCategory;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.exception.ProductNotFound;
import com.example.demo.exception.ProductNameExisted;
import org.springframework.web.bind.annotation.ModelAttribute;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService ProductService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/products")
    List<ProductDTO> getAll() {
        return ProductService.getAllProducts();
    }



    public boolean NameExisted(String name) {
        List<ProductDTO> list = ProductService.getAllProducts();
        for (ProductDTO i: list ) {
            if (i.getProductname().equals(name)) {
                return false;
            }
        }
        return true;
    }



    public boolean NameExistedWithId(String name, int id) {
        List<Product> list = ProductService.findAllProducts();
        for (Product i: list ) {
            if (i.getProductid() == id) {
                break;
            }
            if (i.getProductname().equals(name)) {
                return false;
            }
        }
        return true;
    }

    @PutMapping(value = "/products/{id}")
    ResponseEntity<String> updateProduct(@PathVariable("id") @Min(1) int id,
                                          @Valid @RequestBody ProductDTO prodDTO) {
        List<Product> list =  ProductService.findAllProducts();
        AtomicBoolean c = new AtomicBoolean(false);
        list.stream().forEach(
                (s) -> {
                    if(id == s.getProductid())
                    {
                        c.set(true);
                        @Valid
                        Product newProd = ProductMapper.DtoToEntity(prodDTO);
                        if (!NameExistedWithId(newProd.getProductname(), id)) {
                            throw new ProductNameExisted("Product name already existed");
                        }
                        newProd.setCategoryyy(s.getCategoryyy());
                        newProd.setProductid(s.getProductid());
                        ProductService.save(newProd);

                    }
                }
        );
        if(c.get() == false){
            throw  new ProductNotFound("Product Not Found");
        }
        else return ResponseEntity.ok().body("newProd");
    }

    @DeleteMapping(value = "/products/{id}")
    ResponseEntity<String> deleteProduct(@PathVariable("id") @Min(1) int id) {
        List<Product> list =  ProductService.findAllProducts();
        AtomicBoolean c = new AtomicBoolean(false);

        list.stream().forEach(
                (s) -> {
                    if(id == s.getProductid())
                    {
                        Product prod = s;
                        ProductService.delete(prod.getProductid());
                        c.set(true);
                    }
                }
        );
        if(c.get() == false){
            throw  new ProductNotFound("Product Not Found");
        }

        return ResponseEntity.ok().body("Deleted prod with id : " +id);
    }



    @GetMapping("/products/get-by-name/{name}")
    public ProductDTO getProductByProductname(@PathVariable String name) {
        return ProductService.getProductByProductname(name);
    }




}
