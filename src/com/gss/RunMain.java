package com.gss;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class RunMain {

	public static void main(String arg[]) {

	    String destFile = (System.getProperty("os.name").contains("Mac")
	    		? "/Users/nicole/Downloads/" // Mac
	    		: "D:/Downloads/")
				+ "plain_tmp.txt";
		System.out.println("產出的文字檔路徑為: " + destFile);
	    
	    String encStr = "", str = "";
	    FileOutputStream fos = null ;
	    PrintWriter pw = null;
    	int batLine = 10001;

		try (Scanner s = new Scanner(System.in)) {

		    System.out.print("請輸入要產出的ROW: ");
	    	int totalRow = s.nextInt();
		    System.out.print("請輸入要產出的COL: ");
	    	int totalCol = s.nextInt();
	    	
	    	File f = new File(destFile);

	    	/**
	    	 * createNewFile
	    	 * true: 表示檔案不存在，並會自動產生檔案
	    	 * false: 表示檔案已存在
	    	 */
	    	if(f.createNewFile())
	    		System.out.println("檔案不存在，已自動產生檔案");

			fos = new FileOutputStream(f); // 第二參數設定是保留原有內容(預設false會刪)
			
			for (int i = totalRow; i >= 1;) {
				str = "";
				System.out.println("尚餘行數: " + i);
				
				// 一次先寫10000行，以免程式爆掉
				for (int ii = 0; ii < batLine && i >= 1 ; ii++) {
					encStr = fillLen(String.valueOf(i--), 9);
					for (int j = 1; j <= totalCol; j++)
						str += "A" + encStr + (j < totalCol ? "," : "");
					str += "\n";
				}

//				System.out.println("write");

				// 將整理好的內容寫入檔案內
				fos.write(str.getBytes());
			}
			
			fos.flush();
			// 若要設定編碼則需透過OutputStreamWriter
			pw = new PrintWriter(new OutputStreamWriter(fos, StandardCharsets.UTF_8));
			System.out.println("Done");
		} catch (Exception ex) {
			System.out.println("== Exception ==> " + ex.getMessage());
		} finally {
			try {
				fos.close();
				pw.close();
			} catch (IOException e) {
				System.out.println("== Finally Exception ==> " + e.getMessage());
			}
		}
	}
	
	/**
	 * 不足長度則前面補0
	 * 
	 * @param str
	 * @return
	 */
	private static String fillLen(String str, Integer len) {
		for (int i = 0; i < len; i++)
			str = str.length() < len ? "0" + str : str;
		return str;
	}

}
