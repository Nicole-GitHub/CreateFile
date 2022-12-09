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
			String fileOriName = s.nextLine();
			System.out.print("請輸入影片來源名稱: ");
			String source = s.nextLine();
			System.out.print("請輸入要批次變更的檔案數量: ");
			int cnt = s.nextInt();
			
			String fileNameOri = fileOriName.substring(0, fileOriName.lastIndexOf("."));
			String fileNameAft = fileOriName.substring(0, fileOriName.lastIndexOf("_"));
			String aux = fileOriName.substring(fileOriName.lastIndexOf(".") + 1);
			
			/**
			 * 这是因为在调用nextLine()函数前调用了Scanner的另一个函数nextInt()（或是nextDouble()）。
			 * 出现这种情况的原因是两个函数的处理机制不同，
			 * nextInt()函数在缓冲区中遇到“空格”、“回车符”等空白字符时会将空白字符前的数据读取走，但空白字符不会被处理掉，
			 * 而nextLine()函数是在缓冲区中读取一行数据，这行数据以“回车符”为结束标志，nextLine()会把包括回车符在内的数据提走。
			 * 所以nextInt()后的nextLine()函数并非读取不到数据，因为nextInt()将“回车符”留在了缓冲区，nextLine()读取时遇到的第一个字符便是“回车符”，所以直接结束了。
			 */
			s.nextLine();
			System.out.print("請問是否欲變更檔案名稱? [y,n]: ");
			String chgFinalName = s.nextLine().toUpperCase();
			if ("Y".equals(chgFinalName)) {
				System.out.print("請輸入欲變更後的檔案名稱: ");
				fileNameAft = s.nextLine();
			}
			System.out.print("請輸入檔案起始值(初始為1): ");
			int start = s.nextInt();


			for (int i = 0; i < cnt; i++) {
				if (i == 0) {
					fileOriName = fileNameOri + "." + aux;
				} else {
					fileOriName = fileNameOri + " (" + i + ")." + aux;
				}
				File f = new File(outputPath + fileOriName);
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
