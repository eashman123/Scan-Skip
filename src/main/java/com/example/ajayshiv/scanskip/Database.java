package com.example.ajayshiv.scanskip;

import java.io.*;
import java.io.ObjectInputStream;
import java.util.HashMap;
public class Database {
    HashMap<String, Product> upc = new HashMap<>();
    File f;
    public Database(String filename) {
        this.f = new File("./" + filename);
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                this.upc = (HashMap<String, Product>) os.readObject();
                os.close();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
            } catch (IOException e) {
                System.out.println(e);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
            }
        }
    }

    public Product get(String barcode) {
        return upc.get(barcode);
    }

    private void post(String barcode, Product prod) {
        upc.put(barcode, prod);
    }
    private void save() {
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(upc);
            os.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Database a = new Database("ExampleStore");
        a.post("096619756803", new Product("Kirkland Water Bottle", 2.00));
        a.post("858369006177", new Product("Soylent, Cacao", 2.99));
        a.post("017082877321", new Product("Jack Link's Beef Jerky", 1.79));
        a.post("632432757773", new Product("Yerba Mate, Enlighten Mint", 1.89));
        a.post("632432737775", new Product("Yerba Mate, Revel Berry", 1.89));
        a.post("028400008617", new Product("Lay's BBQ Chips", 0.50));
        a.post("038000265013", new Product("Kellog's Rice Krispies Treats, Original", 0.38));
        a.post("858369006191", new Product("Soylent, Cafe Vanilla", 2.99));
        a.post("028400091510", new Product("Dorito's, Cool Ranch", 0.49));
        a.save();
    }
}
