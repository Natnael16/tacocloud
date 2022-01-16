package com.natnael.tacocloud;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface TacoOrderRepository extends CrudRepository<TacoOrder, String> {
    List<TacoOrder> findByDeliveryZip(String deliveryZip);
}
