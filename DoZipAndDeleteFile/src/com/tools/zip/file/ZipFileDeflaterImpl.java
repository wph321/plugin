package com.tools.zip.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.zip.*;
import org.apache.tools.ant.*;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.zip.ZipFile;



/*
 * ���ߣ�κ����
 * ʱ�䣺2017.9.14
 * 
 * Ϊ�����Ŀ�ļ��ϴ���zip�ļ���ѹ�Ľӿڷ���ʵ�֣���ѹ�鿴�����ɾ������ɾ����ѹ�����ļ��У�����������洢ѹ����
 * ���¹��ܰ������˶�̬���ݹ��JAVA���ļ��������ԶԵ����ļ������⼶���ļ��н���ѹ���ͽ�ѹ�� ���ڴ������Զ���Դ����·����Ŀ�����·����
 * 
 * ע�������ӷ������ڴ�ѹ��
 * 
 */
public class ZipFileDeflaterImpl implements ZipFileDeflater {

	private int k = 1;//����ݹ����
	
//	��ѹ����ʵ��
	@Override
	public void Decompression(String zipFilepath,String outputPath) throws Exception {

		/*
		 * Java.util.zip�µĸ�ʽת�������� ��jdk�е�zip�����ַ���������⡣
		 * ����zip java.lang.IllegalArgumentException: MALFORMED ����
		 * 
		 * ����������޸�jdkԴ��:
		 * 			Ҫ�޸�ZipInputStream������getUTF8String�������

					���������ǰ�����һ�δ���

					try
					{
  						String s= new String(b,off,len,"gbk");
  						return s;

					}catch(){
  						.....
					}
		 * 			
		 * 		     ʹ��ant�µ�zip��
		 * 
		 * 
		 * ��Ϊʹ��org.apache.ant�µ�zip����ѹ�ļ���������ѹ
		 */
//		long startTime=System.currentTimeMillis();  
//        try {  
//        	ZipFile zipFile = new ZipFile(zipFilePath);
//        	ZipInputStream Zin=new ZipInputStream(new FileInputStream(  
//                    zipFilePath));//����Դzip·��  
//            BufferedInputStream Bin=new BufferedInputStream(Zin);  
//            String Parent=outputPath; //���·�����ļ���Ŀ¼��  
//            File Fout=null;  
//            ZipEntry entry;  
//            try {  
//                while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){  
//                    Fout=new File(Parent,entry.getName());  
//                    if(!Fout.exists()){  
//                        (new File(Fout.getParent())).mkdirs();  
//                    }  
//                    FileOutputStream out=new FileOutputStream(Fout);  
//                    BufferedOutputStream Bout=new BufferedOutputStream(out);  
//                    int b;  
//                    while((b=Bin.read())!=-1){  
//                        Bout.write(b);  
//                    }  
//                    Bout.close();  
//                    out.close();  
//                    System.out.println(Fout+"��ѹ�ɹ�");      
//                }  
//                Bin.close();  
//                Zin.close();  
//            } catch (IOException e) {  
//                // TODO Auto-generated catch block  
//                e.printStackTrace();  
//            }  
//        } catch (FileNotFoundException e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
		
		 if (!new File(zipFilepath).exists()) {
	            throw new RuntimeException("zip file " + zipFilepath + " does not exist.");
	        }

	        Project proj = new Project();
	        Expand expand = new Expand();
	        expand.setProject(proj);
	        expand.setTaskType("unzip");
	        expand.setTaskName("unzip");
	        expand.setEncoding("utf-8");

	        expand.setSrc(new File(zipFilepath));
	        expand.setDest(new File(outputPath));
	        expand.execute();
	        
	        System.out.println("uncompress successed.");
	    }

//  ѹ���ļ�����ʵ��
//	ZipFileName:ѹ������;  inputFile:��Ҫѹ�����ļ�;
	@Override
	public void Compression(String ZipFileName,File inputFile) throws Exception {

//		ѹ������ִ��״̬
		System.out.println("ѹ����........");
//		����ѹ���ļ���ѹ���ļ���
		ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(ZipFileName));
		BufferedOutputStream bos = new BufferedOutputStream(zipOut);
		
//		ִ��ѹ��
		zip(zipOut, inputFile, inputFile.getName(), bos);  
//      �رչ�����
		bos.close(); 
	    zipOut.close();
	    System.out.println("�������ر�����");
//		ѹ������ִ��״̬
		System.out.println("ѹ�����"); 
	}
//  ɾ���ļ���/�ļ�������ʵ��
	@Override
	public boolean deleteFile(String srcFile) throws Exception {
		// TODO Auto-generated method stub

		boolean success = deleteDir(new File(srcFile));
        if (success) {
            System.out.println("Successfully deleted populated directory: " + srcFile);
        } else {
            System.out.println("Failed to delete populated directory: " + srcFile);
        }     
		return success;
        
	} 
	
	
//	ѹ��ִ�з���
	public void zip(ZipOutputStream out, File f,String base, BufferedOutputStream bo)throws Exception{
		
		if (f.isDirectory()) {  
            File[] fl = f.listFiles();  
            if (fl.length == 0) {  
                out.putNextEntry(new ZipEntry(base + "/")); // ����zipѹ�������base  
                System.out.println(base + "/");  
            }  
            for (int i = 0; i < fl.length; i++) {  
                zip(out, fl[i], base + "/" + fl[i].getName(), bo); // �ݹ�������ļ���  
            }  
            System.out.println("��" + k + "�εݹ�");  
            k++;  
        } else {  
            out.putNextEntry(new ZipEntry(base)); // ����zipѹ�������base  
            System.out.println(base);  
            FileInputStream in = new FileInputStream(f);  
            BufferedInputStream bi = new BufferedInputStream(in);  
            int b;  
            while ((b = bi.read()) != -1) {  
                bo.write(b); // ���ֽ���д�뵱ǰzipĿ¼  
            }  
            bi.close();  
            in.close(); // �������ر�  
        }  
    }  
		
/*
*  �ݹ�ɾ��Ŀ¼�µ������ļ�����Ŀ¼�������ļ�
*  @param dir ��Ҫɾ�����ļ�Ŀ¼
*  @return boolean Returns "true" if all deletions were successful.
*                 If a deletion fails, the method stops attempting to
*                 delete and returns "false".
*/
	 private static boolean deleteDir(File dir) {
	        if (dir.isDirectory()) {
	            String[] children = dir.list();
//      �ݹ�ɾ��Ŀ¼�е���Ŀ¼��
	            for (int i=0; i<children.length; i++) {
	                boolean success = deleteDir(new File(dir, children[i]));
	                if (!success) {
	                    return false;
	                }
	            }
	        }
// 		Ŀ¼��ʱΪ�գ�����ɾ��
	        return dir.delete();
	    }
	 
/*
*  ɾ����Ŀ¼
*  @param dir ��Ҫɾ����Ŀ¼·��
*/	 
	 
	 private static void doDeleteEmptyDir(String dir) {
	        boolean success = (new File(dir)).delete();
	        if (success) {
	            System.out.println("Successfully deleted empty directory: " + dir);
	        } else {
	            System.out.println("Failed to delete empty directory: " + dir);
	        }
	    }
	 
		
	}
