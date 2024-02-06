package com.BookStorePTD.BookStorePTD.services;

import com.BookStorePTD.BookStorePTD.dtos.OrderDetailDto;
import com.BookStorePTD.BookStorePTD.exception.DataNotFound;
import com.BookStorePTD.BookStorePTD.models.Order;
import com.BookStorePTD.BookStorePTD.models.OrderDetail;
import com.BookStorePTD.BookStorePTD.models.Product;
import com.BookStorePTD.BookStorePTD.repositories.OrderDetailRepository;
import com.BookStorePTD.BookStorePTD.repositories.OrderRepository;
import com.BookStorePTD.BookStorePTD.repositories.ProductRepository;
import com.BookStorePTD.BookStorePTD.responses.OrderDetailResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailService implements IOrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<OrderDetailResponse> getAllByOrderId() {
        return null;
    }

    @Override
    public OrderDetail getById(Long id) {
        return orderDetailRepository.findById(id).orElseThrow(()-> new DataNotFound("Not found OrderDetail with id = "+id));
    }

    @Override
    @Transactional
    public OrderDetailResponse create(OrderDetailDto orderDetailDto) {

        Order order = orderRepository.findById(orderDetailDto.getOrderId()).orElseThrow(()-> new DataNotFound("Not found Order with id = "+orderDetailDto.getOrderId()));
        Product product= productRepository.findById(orderDetailDto.getProductId()).orElseThrow(()-> new DataNotFound("Not found Product with id = "+orderDetailDto.getProductId()));
        OrderDetail orderDetail=OrderDetail.builder()
                .price(orderDetailDto.getPrice())
                .numberOfProduct(orderDetailDto.getNumberOfProduct())
                .totalMoney(orderDetailDto.getTotalMoney())
                .product(product)
                .order(order)
                .build();
        OrderDetailResponse orderDetailResponse= OrderDetailResponse.convertToResponse(orderDetailRepository.save(orderDetail));
        return orderDetailResponse;
    }

    @Override
    @Transactional
    public OrderDetailResponse update(Long id,OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail= orderDetailRepository.findById(id).orElseThrow(()-> new DataNotFound("Not found OrderDetail with id = "+id));
        Product product= productRepository.findById(orderDetailDto.getProductId()).orElseThrow(()-> new DataNotFound("Not found Product with id = "+orderDetailDto.getProductId()));
        Order order= orderRepository.findById(orderDetailDto.getOrderId()).orElseThrow(()-> new DataNotFound("Not found Order with id = "+ orderDetailDto.getOrderId()));
        orderDetail.setProduct(product);
        orderDetail.setOrder(order);
        orderDetail.setPrice(orderDetailDto.getPrice());
        orderDetail.setTotalMoney(orderDetailDto.getTotalMoney());
        orderDetail.setNumberOfProduct(orderDetailDto.getNumberOfProduct());
        return OrderDetailResponse.convertToResponse(orderDetail);

    }

    @Override
    @Transactional
    public String delete(Long id) {
        Optional<OrderDetail> orderDetail= orderDetailRepository.findById(id);
        if(orderDetail.isPresent()){
            orderDetailRepository.deleteById(id);

            return "Delete OrderDetail with id = "+ id+" success";
        }
        return "Can't delete OrderDetail with id = "+id;
    }
}
