package com.tools.zip.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.util.zip.ZipOutputStream;

public interface ZipFileDeflater {

/*
 * ��ѹzip�ļ�������ӿ�
 * 
 * ���ߣ� κ����
 * ʱ�䣺2017.9.13 ���� 15:59
 * ���ã���ѹ��ѹ����ɾ���ļ����رչ������ȷ����ṩ�ӿ�
 * 
 */
	
//	��ѹ
	public void Decompression(String zipFilePath,String outputPath) throws Exception;
//	ѹ��
	public void Compression(String ZipFileName,File inputFile) throws Exception;
//	ɾ����ѹ����ļ�
	public boolean deleteFile(String srcFile) throws Exception;
	
}
 