package com.example.demo.controller;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Mapper.CategoryMapper;
import com.example.demo.Mapper.ProductMapper;
import com.example.demo.Models.Product;
import com.example.demo.Models.ProductCategory;
import com.example.demo.DTO.CategoryDTO;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.exception.ProductNotFound;
import com.example.demo.exception.ProductNameExisted;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;


@RestController
@RequestMapping("/api")
public class ProductCategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService ProductService;


    @GetMapping(value = "/productcategories")
    List<CategoryDTO> getCategory() {
        return categoryService.getAllCategory();
    }





    public boolean NameExisted(String name) {
        List<CategoryDTO> list = categoryService.getAllCategory();
        for (CategoryDTO i: list ) {
            if (i.getCategoryname().equals(name)) {
                return false;
            }
        }
        return true;
    }

    @PostMapping(value = "productcategories")
    ResponseEntity<String> createProductCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        @Valid
        ProductCategory productCategory = CategoryMapper.DtoToEntity(categoryDTO);
        if (!NameExisted(productCategory.getCatename())) {
            throw new ProductNameExisted("This cate name already existed");
        }
        ProductCategory add = categoryService.save(productCategory);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(add.getCateid()).toUri();
        return ResponseEntity.ok().body("ID cate:  " + add.getCateid() + " .Added");
    }

    public boolean NameExistedWithId(String name, int id) {
        List<ProductCategory> list = categoryService.findAllCategories();
        for (ProductCategory i: list ) {
            if (i.getCateid() == id) {
                break;
            }
            if (i.getCatename().equals(name)) {
                return false;
            }
        }
        return true;
    }

    @PutMapping(value = "/productcategories/{id}")
    ResponseEntity<String> updateProductCategory(@PathVariable("id") @Min(1) int id,
                                          @Valid @RequestBody CategoryDTO categoryDTO) {
        List<ProductCategory> list =  categoryService.findAllCategories();
        AtomicBoolean c = new AtomicBoolean(false);
        list.stream().forEach(
                (s) -> {
                    if(id == s.getCateid())
                    {
                        c.set(true);
                        @Valid
                        ProductCategory newProd = CategoryMapper.DtoToEntity(categoryDTO);
                        if (!NameExistedWithId(newProd.getCatename(), id)) {
                            throw new ProductNameExisted("cate name already existed");
                        }
                        newProd.setCateid(s.getCateid());
                        categoryService.save(newProd);

                    }
                }
        );
        if(c.get() == false){
            throw  new ProductNotFound("cate Not Found");
        }
        else return ResponseEntity.ok().body("newProdcate");
    }


    @PutMapping(value = "/productcategories/{id}/product")
    ResponseEntity<String> createProduct(@PathVariable("id") @Min(1) int id, @Valid @RequestBody ProductDTO productDTO) {
        ProductCategory prodcate = categoryService.findProductcategoryById(id);


        @Valid
        Product prod = ProductMapper.DtoToEntity(productDTO);
        if (!NameExisted(prod.getProductname())) {
            throw new ProductNameExisted("This product name already existed");
        }
        prod.setCategoryyy(prodcate);
        Product prodAdd = ProductService.save(prod);
        List <Product> prodcl = new ArrayList<Product>();
        prodcate.getProducts().stream().forEach(
                (s)->{
                    prodcl.add(s);
                }
        );
        prodcl.add(prodAdd);
        prodcate.setProducts(prodcl);

        ProductCategory add = categoryService.save(prodcate);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(add.getCateid()).toUri();
        return ResponseEntity.ok().body("ID product:  " + prod.getProductid() + " .Added");
    }

    @DeleteMapping(value = "/productcategories/{id}")
    ResponseEntity<String> deleteProductCate(@PathVariable("id") @Min(1) int id) {
        List<ProductCategory> list =  categoryService.findAllCategories();
        AtomicBoolean c = new AtomicBoolean(false);

        list.stream().forEach(
                (s) -> {
                    if(s.getCateid().equals(id))
                    {
                        ProductCategory prod = s;
                        categoryService.delete(prod.getCateid());
                        c.set(true);
                    }
                }
        );
        if(c.get() == false){
            throw  new ProductNotFound("Product Not Found");
        }

        return ResponseEntity.ok().body("Deleted prodcate with id : " +id);
    }



    @GetMapping("/productcategories/get-by-name/{name}")
    public CategoryDTO getProductCategoryByCatename(@PathVariable String name) {
        return categoryService.getProductCategoryByCatename(name);
    }




}
