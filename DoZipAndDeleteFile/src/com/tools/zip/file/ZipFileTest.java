package com.tools.zip.file;

import java.io.File;


public class ZipFileTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ZipFileDeflater zfd = new ZipFileDeflaterImpl();

////		��ѹ����
//		String zipFilePath = "D:\\�½��ļ��� (4)\\����.zip";//�ļ�ȫ�����ļ�·��+�ļ�����
//		String outputPath = "D:\\�½��ļ��� (4)";//��ѹ����·����
//		try {
//			long startTime=System.currentTimeMillis();  
//			zfd.Decompression(zipFilePath, outputPath);
//			  long endTime=System.currentTimeMillis();  
//		        System.out.println("�ķ�ʱ�䣺 "+(endTime-startTime)+" ms");
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
////		ѹ������
//		File inputFile = new File("D:\\�½��ļ��� (4)\\����");//Ҫѹ�����ļ���·��
//		String zipFileName = "D:\\�½��ļ��� (4)\\"+inputFile.getName()+".zip";//Ҫ��ѹ����zip�ļ���·��+zip�ļ�
//		try {
//			long startTime = System.currentTimeMillis();
//			zfd.Compression(zipFileName, inputFile);
//			long endTime=System.currentTimeMillis();  
//	        System.out.println("�ķ�ʱ�䣺 "+(endTime-startTime)+" ms");
//			System.out.println(zipFileName);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		ɾ���ļ�����
		
		String srcFile ="D:\\�½��ļ��� (4)\\����";
		try {
			System.out.println(zfd.deleteFile(srcFile));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
