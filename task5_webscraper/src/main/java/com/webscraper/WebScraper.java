package com.webscraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;

public class WebScraper extends Thread {
    
    public static void main(String[] args) throws InterruptedException {
        String url = "https://www.amazon.in/s?k=phone";
        
        try {
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("####################################################################");
            System.out.println("===================================================================");
            System.out.println("The Scraped Data from The URL is : "+ url);
            System.out.println("===================================================================");
            System.out.print("Processing");
            Thread.sleep(800);
            System.out.print(".");
            Thread.sleep(800);
            System.out.print(".");
            Thread.sleep(800);
            System.out.println(".");
            System.out.println("-------------------------------------------------------------------");

                        // Connect to the webpage and get the HTML content.
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0").get();
            Elements products = doc.select(".s-main-slot .s-result-item");
            
            CSVWriter writer = new CSVWriter(new FileWriter("products.csv"));
            String[] header = { "Product Name", "Price", "Rating"};
            writer.writeNext(header);
            
            for (Element product : products) {
                String name = product.select(".a-size-medium.a-text-normal").text();
                String price = product.select(".a-price-whole").text();
                String rating = product.select(".a-icon-alt").text();

                
                String[] data = { name, price, rating };
                writer.writeNext(data);
            }
            
            writer.close();
            System.out.println("Data extraction and CSV writing completed.\nopen products.csv file genrated in project folder" );
            System.out.println("===================================================================");

            System.out.println("####################################################################");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
