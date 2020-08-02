package entities;

public class Report {

	private static String     ruler = "123456789012345678901234567890123456789012345678901234567890";
	private static String separator = "============================================================";
	private static String    title1 = "REPORT: Complete List of Products - Input File              ";
	private static String    title2 = "REPORT: Complete List of Products - Output File             ";
	private static String   cabecIn = "Product                                 Unit Price  Quantity";
	private static String  cabecOut = "Product                                Total Price          ";
	private static String  trailer1 = "Total quantity of records.....: ";
	private static String  trailer2 = "Total quantity of products....: ";
	private static String  trailer3 = "Total price of products.......: ";
	
	public Report() {
	}

	public static void imprimirHeader (int reportType) {

		System.out.println();
		System.out.println(ruler);

		if (reportType == 1) {
			System.out.println(separator);
			System.out.println(title1);
			System.out.println(separator);
			System.out.println(cabecIn);
			System.out.println(separator);
		} else {
			System.out.println(separator);
			System.out.println(title2);
			System.out.println(separator);
			System.out.println(cabecOut);
			System.out.println(separator);
		}
	}

	public static String imprimirDetail (int reportTipe, Product product) {

		String     msgName;
		String    msgPrice;
		String msgQuantity;

 		msgName = String.format("%-29s", product.getName());
		
		if (reportTipe == 1) {
			
  		    msgPrice = String.format("%18.2f", product.getPrice());
			msgQuantity = String.format("%06d", product.getQuantity());
			
			return msgName + " | " + msgPrice + " | " + msgQuantity;
			
		} else {
		
			msgPrice = String.format("%18.2f", product.getTotalPrice());
			
			return msgName + " | " + msgPrice;
		}		
	}

	public static void imprimirTrailer (int records, int quantity, Double totalPrice) {

		System.out.println(separator);
		System.out.println(trailer1 + records);
		System.out.println(trailer2 + quantity);
		System.out.println(trailer3 + totalPrice);
		System.out.println(separator);

	}
}
