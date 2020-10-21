package yotakbae;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DeliveryBoardRepository extends CrudRepository<DeliveryBoard, Long> {

    List<DeliveryBoard> findByRequestId(Long requestId);

}