package com.ejemplo;

public class OrderService {
    
    /**
     * Calcula el total de un pedido considerando descuentos y tipo de envío
     * 
     * @param subtotal El subtotal del pedido
     * @param hasDiscount Si aplica descuento del 10%
     * @param isExpressShipment Si es envío express (costo adicional)
     * @return El total calculado
     */
    public double getTotal(double subtotal, boolean hasDiscount, boolean isExpressShipment) {
        // Si el subtotal es negativo, devolverlo tal cual
        if (subtotal < 0) {
            return subtotal;
        }
        double total = subtotal;
        
        // Aplicar descuento del 10% si corresponde (solo para valores positivos)
        if (hasDiscount) {
            total = total - (subtotal * 0.10);
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