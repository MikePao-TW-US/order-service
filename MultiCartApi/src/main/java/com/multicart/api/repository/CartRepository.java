package com.multicart.api.repository;

import com.multicart.api.entities.Cart;
import com.multicart.api.entities.CartCompositeKey;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CartRepository extends CrudRepository<Cart, CartCompositeKey> {

    public void deleteByUserId(Integer userId);

    public List<Cart> findByUserId(Integer userId);
}
