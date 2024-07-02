package com.example.project.common;

import com.example.project.exception.DeliveredProductsNotExistException;
import com.example.project.service.OrderedProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class ScheduledTasks {
    private OrderedProductService orderedProductService;


    @Scheduled(cron = "0 0/15 * * * *")
    public void changeDeliveryStatus() {
        try {
            orderedProductService.updateDeliveryStatuses();
        } catch (DeliveredProductsNotExistException e) {
            log.info("Продукты для обновления не найдены");
        } catch (Exception ignored) {
        }
    }
}
