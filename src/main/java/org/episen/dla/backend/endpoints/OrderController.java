package org.episen.dla.backend.endpoints;

import lombok.extern.slf4j.Slf4j;
import org.episen.dla.backend.dto.OrderDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@Slf4j
@CrossOrigin("*")
public class OrderController {

    private final File orderFile;

    public OrderController() {
        orderFile = new File("orders.txt");
        log.info("Chemin du fichier orders.txt : {}", orderFile.getAbsolutePath());
    }

    @PostMapping(path = "/add/order")
    public ResponseEntity<Object> add_order(@RequestBody OrderDTO order){
        log.info("Recu : {}",order.toString());
        try {
            FileOutputStream outputStream = new FileOutputStream(orderFile,true);
            outputStream.write(order.getValue().getBytes("UTF-8"));
            outputStream.write("\n".getBytes());
            outputStream.close();
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
