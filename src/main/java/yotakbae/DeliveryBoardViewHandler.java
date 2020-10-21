package yotakbae;

import yotakbae.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryBoardViewHandler {


    @Autowired
    private DeliveryBoardRepository deliveryBoardRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRequested_then_CREATE_1 (@Payload Requested requested) {
        try {
            if (requested.isMe()) {
                // view 객체 생성
                DeliveryBoard deliveryBoard = new DeliveryBoard();
                // view 객체에 이벤트의 Value 를 set 함
                deliveryBoard.setRequestId(requested.getId());
                deliveryBoard.setStatus(requested.getStatus());
                // view 레파지 토리에 save
                deliveryBoardRepository.save(deliveryBoard);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenReqCanceled_then_UPDATE_1(@Payload ReqCanceled reqCanceled) {
        try {
            if (reqCanceled.isMe()) {
                // view 객체 조회
                List<DeliveryBoard> deliveryBoardList = deliveryBoardRepository.findByRequestId(reqCanceled.getId());
                for(DeliveryBoard deliveryBoard : deliveryBoardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    deliveryBoard.setStatus(reqCanceled.getStatus());
                    // view 레파지 토리에 save
                    deliveryBoardRepository.save(deliveryBoard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPaid_then_UPDATE_2(@Payload Paid paid) {
        try {
            if (paid.isMe()) {
                // view 객체 조회
                List<DeliveryBoard> deliveryBoardList = deliveryBoardRepository.findByRequestId(paid.getRequestId());
                for(DeliveryBoard deliveryBoard : deliveryBoardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    deliveryBoard.setStatus(paid.getStatus());
                    // view 레파지 토리에 save
                    deliveryBoardRepository.save(deliveryBoard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenPayCanceled_then_UPDATE_3(@Payload PayCanceled payCanceled) {
        try {
            if (payCanceled.isMe()) {
                // view 객체 조회
                List<DeliveryBoard> deliveryBoardList = deliveryBoardRepository.findByRequestId(payCanceled.getRequestId());
                for(DeliveryBoard deliveryBoard : deliveryBoardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    deliveryBoard.setStatus(payCanceled.getStatus());
                    // view 레파지 토리에 save
                    deliveryBoardRepository.save(deliveryBoard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDelivered_then_UPDATE_4(@Payload Delivered delivered) {
        try {
            if (delivered.isMe()) {
                // view 객체 조회
                List<DeliveryBoard> deliveryBoardList = deliveryBoardRepository.findByRequestId(delivered.getRequestId());
                for(DeliveryBoard deliveryBoard : deliveryBoardList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    deliveryBoard.setStatus(delivered.getStatus());
                    deliveryBoard.setLocation(delivered.getLocation());
                    deliveryBoard.setCourierName(delivered.getCourierName());
                    // view 레파지 토리에 save
                    deliveryBoardRepository.save(deliveryBoard);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}