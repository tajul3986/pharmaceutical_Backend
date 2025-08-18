package com.spring.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.spring.model.Product;
import com.spring.repository.ProductRepository;



@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pharma")
public class ProductController {
	
	@Autowired
    private ProductRepository productRepository;

    @GetMapping("/product")
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long productId) {
    	Product product = productRepository.getById(productId);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/product")
    public Product createProduct(@Validated @RequestBody Product product) {
        //return employeeDAO.save(employee);
    	return productRepository.save(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(value = "id") Long productId,
         @Validated @RequestBody Product productDetails) {
    	Product product = productRepository.getById(productId);
    	product.setProductCode(productDetails.getProductCode());
    	product.setName(productDetails.getName());
    	product.setPrice(productDetails.getPrice());
    	product.setDescription(productDetails.getDescription());
    	product.setCategory(productDetails.getCategory());
    	product.setSubcategory(productDetails.getSubcategory());
    	product.setStock(productDetails.getStock());
    	product.setImage(productDetails.getImage());
    	System.out.println("gfgf");
        final Product updatedProduct = productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
        
    }

    @DeleteMapping("/product/{id}")
    public Map<String, Boolean> deleteProduct(@PathVariable(value = "id") Long productId){
    	Product product = productRepository.getById(productId);
    	productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    
    //image upload
    @PostMapping("/saveProductWithImage")
    public Product saveProductWithImage(@RequestPart("product") Product product,
                                        @RequestPart("image") MultipartFile file) throws IOException {
        
        // Save image to static folder
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
        String imagePath = "src/main/resources/static/image/" + file.getOriginalFilename();
        ImageIO.write(image, "jpg", new File(imagePath));
        
        // Save product to DB
        product.setImage(file.getOriginalFilename());
        return productRepository.save(product);
    }
    

}
