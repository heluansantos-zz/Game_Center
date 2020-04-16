package gui.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import model.entities.Sales;
import model.entities.ServiceSales;

public class genereteReportsTxt {
	/**
	 * Classe com métodos que geram relatórios em txt.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	private static Double valueTotality = 0.0;
	private static int valueSales = 0;
	private static String m = null;
	private static String date = null;

	/**
	 * Método que gera o relatório de venda de Produtos no formato txt.
	 * 
	 * @param path  - String
	 * @param list  - List<Sales>
	 * @param month - Integer
	 * @param year  - integer
	 */
	public static void genereteReportsSalesProductsText(String path, List<Sales> list, Integer month, Integer year) {
		date = Utils.dateCurrent();
		m = Utils.month(month);
		for (Sales obj : list) {
			if (month == Utils.whichMonth(obj.getDateSale()) && year == Utils.whichYEAR(obj.getDateSale())) {
				valueTotality += obj.getProduct().getValue();
				valueSales++;
			}
		}
		try {
			FileWriter arq = new FileWriter(path);
			PrintWriter genereteArchive = new PrintWriter(arq);

			genereteArchive.print(date + "\n\n");
			genereteArchive.print("ELETRO GAMES CENTER\n\n");
			genereteArchive.print("RELATÓRIO REFERENTE A VENDAS DE PRODUTOS.\n\n\n");
			genereteArchive.print("RESUMO\n");
			genereteArchive.print("================================================================================\n");
			genereteArchive.print(String.format("| %-10s|  ", "ANO") + String.format("%-20s|   ", "MÊS")
					+ String.format("%-20s|  ", "TOTAL DE VENDAS") + String.format("%-5s|  ", "VALOR TOTAL EM R$"));
			genereteArchive
					.print("\n================================================================================\n");
			genereteArchive.print(String.format("| %-10s|  ", year) + String.format("%-20s|  ", m)
					+ String.format("%-21s|   ", valueSales) + String.format("%-16s|\n", valueTotality));
			genereteArchive.print("================================================================================\n");
			genereteArchive.print("\n\n\nVENDAS REFERENTE AO MÊS DE " + m + "\n");
			genereteArchive.print(
					"==============================================================================================================================================\n");
			genereteArchive.print(String.format("| %-8s|  ", "ID") + String.format("%-30s|  ", "NOME")
					+ String.format("%-16s|  ", "TELEFONE") + String.format("%-15s|  ", "CPF")
					+ String.format("%-30s|  ", "PRODUTO") + String.format("%-12s|  ", "DATA")
					+ String.format("%-10s|  ", "VALOR R$"));
			genereteArchive.print(
					"\n==============================================================================================================================================\n");
			for (Sales obj : list) {
				if (month == Utils.whichMonth(obj.getDateSale()) && year == Utils.whichYEAR(obj.getDateSale())) {
					genereteArchive.printf(String.format("| %-8s|  ", obj.getId())
							+ String.format("%-30s|  ", obj.getClient().getName())
							+ String.format("%-16s|  ", obj.getClient().getTelephone())
							+ String.format("%-15s|  ", obj.getClient().getCpf())
							+ String.format("%-30s|  ", obj.getProduct().getName())
							+ String.format("%-12s|  ", obj.getDateSale())
							+ String.format("%-10s|  ", obj.getProduct().getValue()));
					genereteArchive.print(
							"\n----------------------------------------------------------------------------------------------------------------------------------------------");
					genereteArchive.print("\n");
				}
			}
			genereteArchive.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que gera o relatório de venda de Serviçõs no formato txt.
	 * 
	 * @param path  - String
	 * @param list  - List<ServiceSales>
	 * @param month - Integer
	 * @param year  - Integer
	 */
	public static void genereteReportsSalesServiceText(String path, List<ServiceSales> list, Integer month,
			Integer year) {
		date = Utils.dateCurrent();
		m = Utils.month(month);
		for (ServiceSales obj : list) {
			if (month == Utils.whichMonth(obj.getDateSale()) && year == Utils.whichYEAR(obj.getDateSale())) {
				valueTotality += obj.getService().getValue();
				valueSales++;
			}
		}
		try {
			FileWriter arq = new FileWriter(path);
			PrintWriter genereteArchive = new PrintWriter(arq);
			genereteArchive.print(date + "\n\n");
			genereteArchive.print("ELETRO GAMES CENTER\n\n");
			genereteArchive.print("RELATÓRIO REFERENTE A VENDAS DE SERVIÇOS.\n\n");
			genereteArchive.print("RESUMO\n");
			genereteArchive.print("================================================================================\n");
			genereteArchive.print(String.format("| %-10s|  ", "ANO") + String.format("%-20s|   ", "MÊS")
					+ String.format("%-20s|  ", "TOTAL DE VENDAS") + String.format("%-5s|  ", "VALOR TOTAL EM R$"));
			genereteArchive
					.print("\n================================================================================\n");
			genereteArchive.print(String.format("| %-10s|  ", year) + String.format("%-20s|  ", m)
					+ String.format("%-21s|   ", valueSales) + String.format("%-16s|\n", valueTotality));
			genereteArchive.print("================================================================================\n");
			genereteArchive.print("\n\n\nVENDAS REFERENTE AO MÊS DE " + m + "\n");
			genereteArchive.print("==================================================================="
					+ "============================================================"
					+ "==========================================================\n");
			genereteArchive.print(String.format("| %-5s|  ", "ID") + String.format("%-30s|  ", "NOME")
					+ String.format("%-16s|  ", "TELEFONE") + String.format("%-16s|  ", "CPF")
					+ String.format("%-20s|  ", "TIPO DE SERVIÇO") + String.format("%-30s|  ", "DESCRIÇÃO")
					+ String.format("%-20s|  ", "GARANTIA") + String.format("%-11s|  ", "DATA")
					+ String.format("%-10s|  ", "VALOR"));
			genereteArchive.print("\n============================================="
					+ "=============================================================="
					+ "==============================================================================\n");
			for (ServiceSales obj : list) {
				if (month == Utils.whichMonth(obj.getDateSale()) && year == Utils.whichYEAR(obj.getDateSale())) {
					genereteArchive.printf(String.format("| %-5s|  ", obj.getId())
							+ String.format("%-30s|  ", obj.getClient().getName())
							+ String.format("%-16s|  ", obj.getClient().getTelephone())
							+ String.format("%-16s|  ", obj.getClient().getCpf())
							+ String.format("%-20s|  ", obj.getService().getName())
							+ String.format("%-30s|  ", obj.getService().getProblemDescription())
							+ String.format("%-20s|  ", obj.getService().getWarranty())
							+ String.format("%-11s|  ", obj.getDateSale())
							+ String.format("%-10s|  ", obj.getService().getValue()));
					genereteArchive.print("\n----------------------------------"
							+ "------------------------------------------------------------"
							+ "---------------------------------------------------"
							+ "----------------------------------------");
					genereteArchive.print("\n");
				}
			}
			genereteArchive.close();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
	}
}