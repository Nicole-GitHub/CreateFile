package com.gss;

import java.io.File;
import java.util.Scanner;

public class ChangeFileName {

	public static void changeFileName(String outputPath) {
		System.out.println("欲變更的檔案路徑預設為: " + outputPath);

		try (Scanner s = new Scanner(System.in)) {

			System.out.print("請問預設路徑後是否還有下一層目錄? [y,n]: ");
			String endFolder = s.nextLine().toUpperCase();
			if ("Y".equals(endFolder)) {
				System.out.print("請輸入目錄結構(含/): ");
				outputPath += s.nextLine();
				outputPath = outputPath.endsWith("/") ? outputPath : outputPath + "/";
			}
			System.out.println("欲變更的檔案路徑為: " + outputPath);

			System.out.print("請輸入要批次變更的第一個檔案名稱(含副檔名): ");
			String fileName = s.nextLine();
			System.out.print("請輸入影片來源名稱: ");
			String source = s.nextLine();
			System.out.print("請輸入要批次變更的檔案數量: ");
			int cnt = s.nextInt();
			System.out.print("請輸入檔案起始值(初始為1): ");
			int start = s.nextInt();

			String fileNameOri = fileName.substring(0, fileName.lastIndexOf("."));
			String fileNameAft = fileName.substring(0, fileName.lastIndexOf("_"));
			String aux = fileName.substring(fileName.lastIndexOf(".") + 1);

			for (int i = 0; i < cnt; i++) {
				if (i == 0) {
					fileName = fileNameOri + "." + aux;
				} else {
					fileName = fileNameOri + " (" + i + ")." + aux;
				}
				File f = new File(outputPath + fileName);
				if (f.exists())
					f.renameTo(new File(outputPath + fileNameAft + " - " + source + " - " + start++ + "." + aux));
				else
					throw new Exception("no file");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
