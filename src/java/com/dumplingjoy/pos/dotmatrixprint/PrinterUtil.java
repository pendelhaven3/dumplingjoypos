package com.dumplingjoy.pos.dotmatrixprint;

import java.io.ByteArrayInputStream;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;

public class PrinterUtil {

	private static DocFlavor DOC_FLAVOR = new DocFlavor("application/octet-stream", "java.io.InputStream");
	private static String CARRIAGE_RETURN = "\r";
	private static String FORM_FEED = "\f";
	
	public static void print(String data) throws PrintException {
		print(data.getBytes());
	}
	
	public static void print(byte[] data) throws PrintException {
		PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
		
		DocPrintJob printJob = printService.createPrintJob();
		Doc doc = new SimpleDoc(new ByteArrayInputStream(CARRIAGE_RETURN.getBytes()), DOC_FLAVOR, null);
		printJob.print(doc, null);
		
		printJob = printService.createPrintJob();
		doc = new SimpleDoc(new ByteArrayInputStream(data), DOC_FLAVOR, null);
		printJob.print(doc, null);
		
		printJob = PrintServiceLookup.lookupDefaultPrintService().createPrintJob();
		doc = new SimpleDoc(new ByteArrayInputStream(FORM_FEED.getBytes()), DOC_FLAVOR, null);
		printJob.print(doc, null);
	}

}
