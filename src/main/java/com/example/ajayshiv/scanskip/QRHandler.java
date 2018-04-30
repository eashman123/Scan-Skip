package com.example.ajayshiv.scanskip;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class QRHandler {
    String store;
    static List<String> barcodes = new ArrayList<>();
    int mintime=5;

    public QRHandler(String store, String[] barcodes) {
        this.store = store;
        for (String code: barcodes) {
            this.barcodes.add(code);
        }
    }
    public QRHandler(String store) {
        this.store = store;
    }
    public QRHandler(String store, int mintime){
        this.store = store;
        this.mintime = mintime;
    }
    public String createQR(int size) {
        String a = "https://api.qrserver.com/v1/create-qr-code/?size=" + size+ "x" + size + "&data=";
        for (String code: barcodes) {
            a+=code;
        }
        a+=","+String.valueOf(new Date().getTime());
        return a;
    }

    public boolean parseQR(String code) {
        String[] a = code.split(",");
        barcodes = new ArrayList<>();
        for (String barcode: a[0].split("(?<=\\G.{12})")) {
            barcodes.add(barcode);
        }
        long ts = Long.parseLong(a[1]);
        return Math.abs(new Date().getTime()-ts)/60000 < mintime;
    }
    public Product[] readCart(List<String> barcodes) {
        Product[] shoppingCart = new Product[barcodes.size()+1];
        Database a = new Database(store);
        double tot=0;
        int i=0;
        while(i<barcodes.size()) {
            Product prod = a.get(barcodes.get(i));
            shoppingCart[i] = prod;
            tot += prod.price;
            i++;
        }
        shoppingCart[i] = new Product("Total", tot);
        return shoppingCart;
    }
    public void addBarcode(String barCode){
        barcodes.add(barCode);

    }

    public static void main(String[] args) {
        QRHandler a = new QRHandler("ExampleStore", new String[]{"632432737775", "017082877321", "858369006177", "858369006191"});
        System.out.println(a.barcodes);
        System.out.println(a.createQR(300));
        System.out.println(a.parseQR("632432737775017082877321858369006177858369006191,1523779377700"));
        System.out.println(a.barcodes);
        System.out.println(Arrays.toString(a.readCart(a.barcodes)));
    }
}