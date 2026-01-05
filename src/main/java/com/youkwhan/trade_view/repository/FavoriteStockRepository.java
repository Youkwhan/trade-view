package com.youkwhan.trade_view.repository;

import com.youkwhan.trade_view.entity.FavoriteStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteStockRepository extends JpaRepository<FavoriteStock, Long> {
    boolean existsBySymbol(String symbol);

}
