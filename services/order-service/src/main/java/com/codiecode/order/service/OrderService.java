package com.codiecode.order.service;

import com.codiecode.common.dto.FoodItemDTO;
import com.codiecode.common.dto.OrderDTO;
import com.codiecode.common.dto.RestaurantDTO;
import com.codiecode.common.dto.UserDTO;
import com.codiecode.order.entity.Order;
import com.codiecode.order.repo.OrderRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {

    private static final String USER_SERVICE_URL = "http://USER-SERVICE";
    private static final String GET_USER_ENDPOINT = "user/fetchById";

    private final OrderRepo orderRepo;
    private final SequenceGenerator sequenceGenerator;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepo orderRepo, SequenceGenerator sequenceGenerator, RestTemplate restTemplate) {
        this.orderRepo = orderRepo;
        this.sequenceGenerator = sequenceGenerator;
        this.restTemplate = restTemplate;
    }


    private UserDTO fetchUserById(Long userId) {
        String url = String.format("%s/%s/%d", USER_SERVICE_URL, GET_USER_ENDPOINT, userId);
        return restTemplate.getForObject(url, UserDTO.class);
    }

    public OrderDTO addOrder(OrderDTO order) {
        Integer newOrderId = sequenceGenerator.generateNextOrderId();
        UserDTO user = fetchUserById(order.getUserId());
        Order orderToAdd = new Order(
                newOrderId,
                order.getFoodItemsList(),
                order.getRestaurant(),
                user
        );
        Order orderSaved = orderRepo.save(orderToAdd);
        return new OrderDTO(
                orderSaved.getOrderId(),
                orderSaved.getFoodItemDTOList(),
                orderSaved.getUser().getUserId(),
                orderSaved.getRestaurant()
        );
    }

    public List<OrderDTO> fetchAll() {
        List<Order> orderList = orderRepo.findAll();
        return orderList.stream()
                .map(order -> new OrderDTO(
                        order.getOrderId(),
                        order.getFoodItemDTOList(),
                        order.getUser().getUserId(),
                        order.getRestaurant()
                ))
                .toList();
    }

}
