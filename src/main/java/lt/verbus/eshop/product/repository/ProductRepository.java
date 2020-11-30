package lt.verbus.eshop.product.repository;

import lt.verbus.eshop.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional
    @Modifying
    @Query("update Product p set p.name = :name where p.id = :id")
    int updateNameById(@Param("id") long id, @Param("name") String name);


}
