package com.example.glovo.service;

import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderService {
