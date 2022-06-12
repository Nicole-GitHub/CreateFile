package com.gss;

import java.io.File;
import java.util.Scanner;

public class RunMain {

	public static void main(String arg[]) {

		// 判斷當前執行的啟動方式是IDE還是jar
		boolean isStartupFromJar = new File(RunMain.class.getProtectionDomain().getCodeSource().getLocation().getPath()).isFile();
		System.out.println("isStartupFromJar: " + isStartupFromJar);

		String path = System.getProperty("user.dir") + File.separator; // Jar
		if(!isStartupFromJar) // IDE
			path = System.getProperty("os.name").contains("Mac") ? "/Users/nicole/Downloads/" // Mac
					: "D:/Downloads/"; // Win
		
		try (Scanner s = new Scanner(System.in)) {

			while (true) {
				System.out.print("CreateFile[1]/ChangeFileName[2]? ");
				String runType = s.next();
				if ("1".equals(runType)) {
					CreateFile.createFile(path);
				} else if ("2".equals(runType)) {
					ChangeFileName.changeFileName(path);
				} else {
					System.out.println("輸入錯誤，請重新輸入");
					continue;
				}
				break;
			}
		}
	}
}
