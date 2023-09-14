package com.cookerytech.service;

import com.cookerytech.domain.Product;
import com.cookerytech.dto.ProductDTO;
import com.cookerytech.dto.response.DashboardResponse;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ReportService {
    private final CategoryService categoryService;
    private final BrandService brandService;
    private final ProductService productService;
    private final UserService userService;
    private final OfferService offerService;

    public ReportService(CategoryService categoryService, BrandService brandService, ProductService productService, UserService userService, OfferService offerService) {
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.productService = productService;
        this.userService = userService;
        this.offerService = offerService;
    }

    public DashboardResponse getIstatistics() {
    long getNumberOfCategories= categoryService.getNumberOfCategories();
    long getNumberOfBrands= brandService.getNumberOfBrands();
    long getNumberOfProducts= productService.getNumberOfProducts();
    long getNumberOfCustomers= userService.getNumberOfCustomers();
    long numberOfOffersPerDay= offerService.numberOfOffersPerDay();





    DashboardResponse dashboardResponse =
            new DashboardResponse(
                    getNumberOfCategories,
                    getNumberOfBrands,
                    getNumberOfProducts,
                    numberOfOffersPerDay,
                    getNumberOfCustomers);
return dashboardResponse;
    }

    public List<ProductDTO> getProductsNoOffer() {  // G04

        List<ProductDTO> productsNoOffer = productService.getProductsNoOffer();
        return productsNoOffer;

    }

    public List<ProductDTO> getMostPopularProducts(int amount) {
      return   productService.getMostPopularProducts(amount);
    }
}
