package re.edu.hackathon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import re.edu.hackathon.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
