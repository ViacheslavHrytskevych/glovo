package com.example.glovo.controller;

import com.example.glovo.model.Product;
import com.example.glovo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {



