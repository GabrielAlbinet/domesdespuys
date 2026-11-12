package fr.simplon.domesdespuys.service;

import fr.simplon.domesdespuys.dto.OrderProductDTO;
import fr.simplon.domesdespuys.entity.Order;
import fr.simplon.domesdespuys.entity.OrderProduct;
import fr.simplon.domesdespuys.entity.Product;
import fr.simplon.domesdespuys.repository.ProductRepository;
import fr.simplon.domesdespuys.service.interfaces.iCreateOrderProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderProductServiceImpl implements iCreateOrderProduct {

    private ProductRepository productRepository;

    @Override
    public OrderProduct create(OrderProductDTO dto, Order order) {
        if (dto.getIdProduct() == null) {
            return null;
        }
        Product product = productRepository.findById(dto.getIdProduct()).orElse(null);
        if (product == null) {
            return null;
        }
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrder(order);
        orderProduct.setProduct(product);
        orderProduct.setQuantity(dto.getQty());
        orderProduct.setPrice(dto.getPrice());
        return orderProduct;
    }
}