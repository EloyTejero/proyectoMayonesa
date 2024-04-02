package org.example;



public class Venta {

    private Pedido pedido;
    private double valorTotal;

    public Venta(Pedido pedido) {
        this.pedido = pedido;
        valorTotal = calcularValorTotalVenta();
    }
    
    private double calcularValorTotalVenta(){
        double valor=0;
        for(Producto p: pedido.getProductos()){
            valor += p.getPrecio();
        }

        for(Combo c:pedido.getCombos()){
            valor += c.getPrecio();
        }

        return valor;
    }
    
    public void imprimirTicket(){
        System.out.println("VENTA : ----------------");
        for(Combo c: pedido.getCombos()){
            System.out.println(c.toString());
        }
        for(Producto p: pedido.getProductos()){
            System.out.println(p.toString());
        }
        System.out.println("PRECIO TOTAL: "+valorTotal);
    }

    public double getValorTotal() {
        return valorTotal;
    }
}
