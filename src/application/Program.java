package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;
import entities.Report;

public class Program {

	public static void main(String[] args) throws ParseException {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Product> listIn = new ArrayList<>();
		List<Product> listOut = new ArrayList<>();
		
		double generalTotalPrice = 0.00;
		int qtdProduct = 0;
		
		System.out.println("Enter file path: ");
		String sourceFileStr = sc.nextLine();

		File sourceFile = new File(sourceFileStr);
		String sourceFolderStr = sourceFile.getParent();
		
		boolean success = new File(sourceFolderStr + "\\out").mkdir();
		
		String targetFileStr = sourceFolderStr + "\\out\\summary.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) {

			String itemCsv = br.readLine();
			
			while (itemCsv != null) {

				String[] fields = itemCsv.split(";");
				
				//System.out.println("itemCsv : " + itemCsv);
				
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);

				listIn.add(new Product(name, price, quantity));
				
				itemCsv = br.readLine();
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {

				for (Product item : listIn) {
					bw.write(item.getName() + ";" + String.format("%.2f", item.total()));
					bw.newLine();
				}

				System.out.println(targetFileStr + " CREATED!");
				System.out.println();
				
				Report.imprimirHeader(1);
				
				for(Product product : listIn) {
					System.out.println(Report.imprimirDetail(1, product));
					generalTotalPrice = generalTotalPrice + product.getPrice();
					qtdProduct = qtdProduct + product.getQuantity();
				} 
				
				Report.imprimirTrailer(listIn.size(), qtdProduct, generalTotalPrice);
				
			} catch (IOException e) {
				System.out.println("Error writing file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

		try (BufferedReader br = new BufferedReader(new FileReader(targetFileStr))) {

			String itemCsv = br.readLine();
			
			while (itemCsv != null) {

				String[] fields = itemCsv.split(";");
				
//				System.out.println("itemCsv : " + itemCsv);
				
				String name = fields[0];
				double totalPrice = Double.parseDouble(fields[1]);
				listOut.add(new Product(name, totalPrice));
				
				itemCsv = br.readLine();
			}

			Report.imprimirHeader(2);
			
			generalTotalPrice = 0.00;
			//quantity = 0;
			
			for(Product product : listOut) {
				System.out.println(Report.imprimirDetail(2, product));
				generalTotalPrice = generalTotalPrice + product.getTotalPrice();
				//quantity = quantity + product.getQuantity();
			} 
			
			Report.imprimirTrailer(listOut.size(), qtdProduct, generalTotalPrice);
			
		} catch (IOException e) {
			System.out.println("Error reading output file: " + e.getMessage());
		}
		
		sc.close();
	}
}