package com.ejemplo;

public class OrderService {
    private DiscountService discountService;

    public OrderService(DiscountService discountService) {
        this.discountService = discountService;
    }
    
    /**
     * Calcula el total de un pedido considerando descuentos y tipo de envío
     * 
     * @param subtotal El subtotal del pedido
     * @param discountCode Código de descuento (puede ser null)
     * @param isExpressShipment Si es envío express (costo adicional)
     * @return El total calculado
     */
    public double getTotal(double subtotal, String discountCode, boolean isExpressShipment) {
        // Si el subtotal es negativo, devolverlo tal cual
        if (subtotal < 0) {
            return subtotal;
        }
        
        double total = subtotal;
        
        // Aplicar descuento si se proporciona un código válido
        if (discountCode != null) {
            double discountRate = discountService.getRate(discountCode);
            total = total - (subtotal * discountRate);
        }
        
        // Aplicar costo de envío
        if (isExpressShipment) {
            total = total + 20.0; // Envío express: $20
        } else {
            total = total + 10.0; // Envío estándar: $10
        }
        
        return total;
    }
} 