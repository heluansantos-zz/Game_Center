package gui.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.control.Alert.AlertType;
import model.entities.Sales;
import model.entities.ServiceSales;

public class genereteReportsPDF {
	/**
	 * Classe com m�todos respons�veis por gerar relat�rios em PDF.
	 * 
	 * @author Adriano Queiroz
	 * @version 1.0
	 */
	
	private static Double valueTotality = 0.0;
	private static int n = 0;
	private static Integer valueSales = 0;

	/**
	 * M�todo que gera o relat�rio de venda de Produtos no formato PDF.
	 * 
	 * @param list  - List<Sales>
	 * @param month - Integer
	 * @param year  - Integer
	 * @param path  - String
	 */
	public static void genereteReportsSalesProduct(List<Sales> list, Integer month, Integer year, String path) {

		String m = Utils.month(month);

		Document document = new Document(PageSize.A4, 72, 72, 72, 72);
		String dateCurrent = Utils.dateCurrent();

		for (Sales obj : list) {
			if (month == Utils.whichMonth(obj.getDateSale()) && year == Utils.whichYEAR(obj.getDateSale())) {
				valueTotality += obj.getProduct().getValue();
				valueSales++;
			}
		}
		try {
			PdfWriter.getInstance(document,
					new FileOutputStream(
							"C:\\Users\\adria\\eclipse-workspace\\Projeto-POO-2019.2\\Relat�rio - Vendas - Produtos -"
									+ m + ".pdf"));
			document.open();
			document.add(new Paragraph(String.format("%-105s", "") + dateCurrent + "\n\n\n",
					FontFactory.getFont(FontFactory.COURIER_BOLD, 12)));
			document.add(
					new Paragraph("\n\n\nELETRO GAMES CENTER\n\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 14)));
			document.add(new Paragraph("\n\n\nRELAT�RIO: VENDAS DE PRODUTOS\n\n",
					FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 12)));
			document.add(new Paragraph("RESUMO", FontFactory.getFont(FontFactory.COURIER_BOLD, 12)));
			document.add(new Paragraph("------------------------------------------------------------------------"));
			document.add(new Paragraph(String.format("%-10s", "ANO:") + year + "\n",
					FontFactory.getFont(FontFactory.COURIER_BOLD, 11)));
			document.add(new Paragraph(String.format("%-10s", "M�S:") + m + "\n",
					FontFactory.getFont(FontFactory.COURIER_BOLD, 11)));
			document.add(new Paragraph(String.format("%-20s", "TOTAL DE VENDAS:") + valueSales,
					FontFactory.getFont(FontFactory.COURIER_BOLD, 11)));
			document.add(new Paragraph(String.format("%-20s", "TOTAL EM R$:") + valueTotality,
					FontFactory.getFont(FontFactory.COURIER_BOLD, 11)));
			document.add(new Paragraph("\n------------------------------------------------------------------------"));
			document.add(new Paragraph(
					"\n\n\n\nAS INFORMA��ES RESUMIDAS ABAIXO LEVOU EM CONSIDERA��O OS DADOS MAIS RELEVANTES TAIS COMO: "
							+ "C�DIGO DA VENDA, NOME DO CLIENTE, TELEFONE, CPF, NOME DO PRODUTO, DATA DA VENDA E O VALOR EM R$ DA VENDA."
							+ "\n\n",
					FontFactory.getFont(FontFactory.COURIER, 11)));
			document.add(new Paragraph(
					"\n\n\n\n\n\n\n\nABAIXO SEGUE AS INFORMA��ES RESUMIDAS DE CADA VENDA REFERENTE AO MES DE " + m + " "
							+ year + ". \n\n",
					FontFactory.getFont(FontFactory.COURIER, 11)));
			document.newPage();
			document.add(new Paragraph(String.format("%-148s", "") + "ELETRO GAMES CENTER" + "\n\n\n",
					FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 9)));
			for (Sales obj : list) {
				if (month == Utils.whichMonth(obj.getDateSale()) && year == Utils.whichYEAR(obj.getDateSale())) {
					n += 1;
					document.add(new Paragraph(
							"\n---------------------------------------------------------------------------"
									+ String.format("%-10s", "ID:") + String.format("%-10s\n", obj.getId())
									+ String.format("%-20s", "NOME DO CLIENTE:")
									+ String.format("%-30s\n", obj.getClient().getName())
									+ String.format("%-10s", "TELEFONE:")
									+ String.format("%-20s\n", obj.getClient().getTelephone())
									+ String.format("%-10s", "CPF:")
									+ String.format("%-20s\n", obj.getClient().getCpf())
									+ String.format("%-20s", "NOME DO PRODUTO:")
									+ String.format("%-30s\n", obj.getProduct().getName())
									+ String.format("%-10s", "DATA:") + String.format("%-20s\n", obj.getDateSale())
									+ String.format("%-10s", "VALOR:")
									+ String.format("%-10s\n", obj.getProduct().getValue()),
							FontFactory.getFont(FontFactory.COURIER, 10)));
					if (n == 4) {
						document.newPage();
						document.add(new Paragraph(String.format("%-148s", "") + "ELETRO GAMES CENTER" + "\n\n\n",
								FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 9)));
					}
				}
			}
		} catch (DocumentException e) {
			Alerts.showAlert("DocumentException", null, e.getMessage(), AlertType.ERROR);
		} catch (IOException e) {
			Alerts.showAlert("IOException", null, e.getMessage(), AlertType.ERROR);
		} finally {
			document.close();
		}
	}

	/**
	 * M�todo que gera relat�rio da venda de Servi�os no formato PDF.
	 * 
	 * @param list  - List<ServiceSales>
	 * @param month - Integer
	 * @param year  - Integer
	 * @param path  - String
	 */
	public static void genereteReportsSalesService(List<ServiceSales> list, Integer month, Integer year, String path) {
		String m = Utils.month(month);

		Document document = new Document(PageSize.A4, 72, 72, 72, 72);
		String dateCurrent = Utils.dateCurrent();

		for (ServiceSales obj : list) {
			if (month == Utils.whichMonth(obj.getDateSale()) && year == Utils.whichYEAR(obj.getDateSale())) {
				valueTotality += obj.getService().getValue();
				valueSales++;
			}
		}
		try {
			PdfWriter.getInstance(document,
					new FileOutputStream(
							"C:\\Users\\adria\\eclipse-workspace\\Projeto-POO-2019.2\\Relat�rio - Vendas - Servi�os -"
									+ m + ".pdf"));
			document.open();
			document.add(new Paragraph(String.format("%-105s", "") + dateCurrent + "\n\n\n",
					FontFactory.getFont(FontFactory.COURIER_BOLD, 12)));
			document.add(
					new Paragraph("\n\n\nELETRO GAMES CENTER\n\n", FontFactory.getFont(FontFactory.COURIER_BOLD, 14)));
			document.add(new Paragraph("\n\n\nRELAT�RIO: VENDAS DE SERVI�OS\n\n",
					FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 12)));
			document.add(new Paragraph("RESUMO", FontFactory.getFont(FontFactory.COURIER_BOLD, 12)));
			document.add(new Paragraph("------------------------------------------------------------------------"));
			document.add(new Paragraph(String.format("%-10s", "ANO:") + year + "\n",
					FontFactory.getFont(FontFactory.COURIER_BOLD, 11)));
			document.add(new Paragraph(String.format("%-10s", "M�S:") + m + "\n",
					FontFactory.getFont(FontFactory.COURIER_BOLD, 11)));
			document.add(new Paragraph(String.format("%-20s", "TOTAL DE VENDAS:") + valueSales,
					FontFactory.getFont(FontFactory.COURIER_BOLD, 11)));
			document.add(new Paragraph(String.format("%-20s", "TOTAL EM R$:") + valueTotality,
					FontFactory.getFont(FontFactory.COURIER_BOLD, 11)));
			document.add(new Paragraph("\n------------------------------------------------------------------------"));
			document.add(new Paragraph(
					"\n\n\n\nAS INFORMA��ES RESUMIDAS ABAIXO LEVOU EM CONSIDERA��O OS DADOS MAIS RELEVANTES TAIS COMO: "
							+ "C�DIGO DO SERVI�O, NOME DO CLIENTE, TELEFONE, CPF, TIPO DE SERVI�O, DESCRI��O, GARANTIA, DATA E VALOR EM R$ DO SERVI�O."
							+ "\n\n",
					FontFactory.getFont(FontFactory.COURIER, 11)));
			document.add(new Paragraph(
					"\n\n\n\n\n\n\n\nABAIXO SEGUE AS INFORMA��ES RESUMIDAS DE CADA VENDA REFERENTE AO MES DE " + m + " "
							+ year + ". \n\n",
					FontFactory.getFont(FontFactory.COURIER, 11)));
			document.newPage();
			document.add(new Paragraph(String.format("%-148s", "") + "ELETRO GAMES CENTER" + "\n\n\n",
					FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 9)));
			for (ServiceSales obj : list) {
				if (month == Utils.whichMonth(obj.getDateSale()) && year == Utils.whichYEAR(obj.getDateSale())) {
					n += 1;
					document.add(new Paragraph(
							"\n---------------------------------------------------------------------------"
									+ String.format("%-10s", "ID:") + String.format("%-10s\n", obj.getId())
									+ String.format("%-20s", "NOME DO CLIENTE:")
									+ String.format("%-30s\n", obj.getClient().getName())
									+ String.format("%-10s", "TELEFONE:")
									+ String.format("%-20s\n", obj.getClient().getTelephone())
									+ String.format("%-10s", "CPF:")
									+ String.format("%-20s\n", obj.getClient().getCpf())
									+ String.format("%-20s", "TIPO DE SERVI�O:")
									+ String.format("%-30s\n", obj.getService().getName())
									+ String.format("%-20s", "DESCRI��O:")
									+ String.format("%-20s\n", obj.getService().getProblemDescription())
									+ String.format("%-20s", "GARANTIA:")
									+ String.format("%-10s\n", obj.getService().getWarranty())
									+ String.format("%-15s", "DATA:") + String.format("%-10s\n", obj.getDateSale())
									+ String.format("%-10s", "VALOR:")
									+ String.format("%-10s\n", obj.getService().getValue()),
							FontFactory.getFont(FontFactory.COURIER, 10)));
					if (n == 4) {
						document.newPage();
						document.add(new Paragraph(String.format("%-148s", "") + "ELETRO GAMES CENTER" + "\n\n\n",
								FontFactory.getFont(FontFactory.COURIER_BOLDOBLIQUE, 9)));
					}
				}
			}
		} catch (DocumentException e) {
			Alerts.showAlert("DocumentException", null, e.getMessage(), AlertType.ERROR);
		} catch (IOException e) {
			Alerts.showAlert("IOException", null, e.getMessage(), AlertType.ERROR);
		} finally {
			document.close();
		}
	}
}
