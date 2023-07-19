package com.example.glodo_lection36.service;

import com.example.glodo_lection36.dto.OrderDto;
import com.example.glodo_lection36.entity.Order;
import com.example.glodo_lection36.entity.Product;
import com.example.glodo_lection36.repository.OrderRepository;
import com.example.glodo_lection36.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

    private OrderRepository orderRepository;  // декларируем два репозитория от которых он зависит
    private ProductRepository productRepository;  // декларируем два репозитория от которых он зависит
    private OrderService orderService;  // декларируем класс, который хотим тестировать


    // замтем мы мокаем эти объекты  ?  что это значит  ?
    // Mock-объекты создаются с помощью Mockito для имитации реальных объектов в тестировании.
    // Вместо того, чтобы создавать настоящие объекты для этих зависимостей, можно создать mock-объекты, которые ведут себя так, как задано в тестах.
    @BeforeEach
    public void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);  // Mockito.mock  можно заменить аннотацией @Mockito перед классом
        // т.о. мы передаем в Mockito класс  и он реадизует его своими инструментами
        productRepository = Mockito.spy(Mockito.mock(ProductRepository.class));
        orderService = new OrderService(orderRepository, productRepository);
    }

    @Test
    void getByIdTest() {  // тест всегда возвращает void ?  т.к. тестируемый метод возвращает OrderDto
        var orderId = 11;
        List<Product> products = List.of(
                Product.builder().cost(12).name("tomato").orderId(orderId).build(),  // почему тут у всех одинаковый Id  ?  What's the point here?  ?
                Product.builder().cost(5).name("lemon").orderId(orderId).build(),    // потому что это foreign key  )
                Product.builder().cost(62).name("apple").orderId(orderId).build()    // т.е. все эти продукты будут соответствовать одному заказу
        );
        Order order = Order.builder().id(orderId).date(Date.valueOf(LocalDate.now())).build();
        // в id передается order.id, т.к. orderId продукта должет соответствовать id заказа
        // т.е. на данный момент мы создали тестовый заказ, который уже содержит продукты
        when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
        when(productRepository.findAllByOrderId(anyInt())).thenReturn(products);
        // when это статический метод в классе Mockito
        // "когда" будет вызываться orderRepository.findById,  а он принимает параметр (int id)
        // "верни тогда" (thenReturn)  Optional.of(order)  /   объект Order order который мы создали выше
        // ??? что такое Optional.of()   ->  иначе order может вернуть null
        // ??? и почему у products нет Optional.of()
        // ??? не понял про anyInt    в смысле, любое значение?
        // в следующей строка походу таже история: как только логикой теста будет вызываться метод productRepository.findAllByOrder()
        // должен быть возвращен List<Product> products, который мы создали в этом тесте.

        OrderDto orderDto = orderService.getById(orderId);

        Assertions.assertEquals(79, orderDto.getCost());
    }

    @Test
    void getAllTest() {

        List<Order> orders = List.of(
                Order.builder().id(1).date(Date.valueOf(LocalDate.now())).build(),
                Order.builder().id(2).date(Date.valueOf(LocalDate.now())).build(),
                Order.builder().id(3).date(Date.valueOf(LocalDate.now())).build()
        );

        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderDto> resultList = orderService.getAll();

        verify(orderRepository).findAll();    // проверяет что данный метод вызывается,  если нет - тест просто упадет

        Assertions.assertEquals(3, resultList.size());
        Assertions.assertEquals(Date.valueOf(LocalDate.now()), resultList.get(0).getDate());
        Assertions.assertEquals(Date.valueOf(LocalDate.now()), resultList.get(1).getDate());
        Assertions.assertEquals(Date.valueOf(LocalDate.now()), resultList.get(2).getDate());
        Assertions.assertEquals(1, resultList.get(0).getId());
        Assertions.assertEquals(2, resultList.get(1).getId());
        Assertions.assertEquals(3, resultList.get(2).getId());
    }

    @Test
    void saveTest() {

        OrderDto orderDto = OrderDto.builder().id(1).date(Date.valueOf(LocalDate.now())).cost(0).products(new ArrayList<>()).build();

        Order savedOrder = Order.builder().id(1).date(Date.valueOf(LocalDate.now())).build();

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        OrderDto result = orderService.save(orderDto);

        verify(orderRepository).save(any(Order.class));

        Assertions.assertEquals(1, result.getId());
        Assertions.assertEquals(Date.valueOf(LocalDate.now()), result.getDate());

    }
}
