package com.ejemplo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    private final DiscountService discountService = new DiscountService();
    private final OrderService service = new OrderService(discountService);

    @Test
    public void testWithoutDiscountAndStandardShipment() {
        double result = service.getTotal(100.0, null, false);
        assertEquals(110.0, result, 0.001);
    }

    @Test
    public void testWithDiscountAndStandardShipment() {
        double result = service.getTotal(100.0, "SALES10", false);
        assertEquals(100.0, result, 0.001);  // 100 - 10 + 10
    }

    @Test
    public void testWithoutDiscountAndExpressShipment() {
        double result = service.getTotal(100.0, null, true);
        assertEquals(120.0, result, 0.001);
    }

    @Test
    public void testWithDiscountAndExpressShipment() {
        double result = service.getTotal(100.0, "SALES10", true);
        assertEquals(110.0, result, 0.001); // 100 - 10 + 20
    }

    @Test
    public void testZeroSubtotal() {
        double result = service.getTotal(0.0, "SALES10", true);
        assertEquals(20.0, result, 0.001); // solo el costo de envío
    }

    @Test
    public void testNegativeSubtotal() {
        double result = service.getTotal(-100.0, "SALES10", false);
        assertEquals(-100.0, result, 0.001); // comportamiento tal como está definido
    }
}