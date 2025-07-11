package com.ejemplo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceMockTest {
    
    @Test
    void testWithMockDiscountAndExpressShipment() {
        // Crear mock del DiscountService
        DiscountService discountService = mock(DiscountService.class);
        
        // Configurar el comportamiento del mock
        when(discountService.getRate("SALES10")).thenReturn(0.10);
        when(discountService.getRate("INVALID")).thenReturn(0.0);
        
        // Crear OrderService con el mock
        OrderService service = new OrderService(discountService);
        
        // Probar con código válido
        double result = service.getTotal(100.0, "SALES10", true);
        assertEquals(110.0, result, 0.001); // 100 - 10 + 20
        
        // Probar con código inválido
        double resultInvalid = service.getTotal(100.0, "INVALID", false);
        assertEquals(110.0, resultInvalid, 0.001); // 100 + 10 (sin descuento)
    }
    
    @Test
    void testWithMockDiscountAndStandardShipment() {
        DiscountService discountService = mock(DiscountService.class);
        when(discountService.getRate("SALES10")).thenReturn(0.10);
        
        OrderService service = new OrderService(discountService);
        double result = service.getTotal(100.0, "SALES10", false);
        assertEquals(100.0, result, 0.001); // 100 - 10 + 10
    }
    
    @Test
    void testWithMockDiscountAndNegativeSubtotal() {
        DiscountService discountService = mock(DiscountService.class);
        when(discountService.getRate("SALES10")).thenReturn(0.10);
        
        OrderService service = new OrderService(discountService);
        double result = service.getTotal(-100.0, "SALES10", false);
        assertEquals(-100.0, result, 0.001); // Subtotal negativo se devuelve tal cual
    }
}