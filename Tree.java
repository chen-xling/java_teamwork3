package test;
/*
С������2��
	����һ���ļ���Ŀ¼������ת��������tree��blob
	������ȱ�����Ŀ¼
	�������ļ���ת����blob������
	�������ļ��о͵ݹ�������ڲ������ļ�/�ļ��������tree������

	ʹ������1�ṩ�Ľӿ� --- hash��
	��Ԫ���� 
 */

/*���Ŀ�꣺
 * (P1��ԭʼ·����P2��Ŀ��·��)
 * (1)��P1��������һ���ļ�blob�����blob��P1������P2�����ݲ��䣬�ļ����ĳ�blob�Ĺ�ϣ��
 * (2)��P1��������һ���ļ���tree������P2������һ��txt�ļ����ļ�����Ϊ:
 * 		ԭ�ļ���������ļ������֡����ļ��Ĺ�ϣ�롢���ļ������ͣ�blob or tree)
 * 		P2������ļ�������Ϊ�������ݵĹ�ϣֵ
 * 
 */

/*���˼·��
 * 	����һ������Tree���ࣺ
 * 	������
 * 		-�ܵ�key
 * 		-ԭʼ·��P1
 * 		-��·��P2
 * 		-value
 * 			
 * ������
 * 		+���췽��( P1, P2 )
 * 		+void gen_tree()  //�����Ҫ����
 * 		+String getValue()  //���������ɵ��ļ�����
 * 		+String getKey()
 * 		+String getFilePath()
 * 		+String getNewFilePath()
 * 
 * 
 * �ٶ���һ�������࣬Tree_content��
 * 	������
 * 		-���ͣ�blob or tree
 * 		-��ϣ��
 * 		-�ļ�ԭ��������׺����
 * 	������
 * 		+���캯�����������ļ���·���������������������
 * 			���洢ԭ�ļ�����ġ����ļ��������֡������ļ����Ĺ�ϣ�롢�����ļ��������ͣ�blob or tree)
 * 		+String output()  �������������������
 * 		+getFilePath
		+getNewFilePath
		+getKey
		+getValue
 */

import java.io.*;

public class Tree {
	
	private String key;
	private String filePath;
	private String newFilePath;
	private String value;
	
	Tree(){};
	Tree(String P1, String P2){
		this.filePath = P1;
		this.newFilePath = P2;
		gen_tree();
		
	};
	
	public void gen_tree() {
		//�ֳ����������1.filePath���ļ�blob��2.filePath���ļ���
		File file1 = new File( filePath );
 
		try {
			if( file1.isFile()) {
				Blob b = new Blob( filePath, newFilePath );
				value = b.getValue();
			}
			else if (file1.isDirectory()) {
				File newFile = new File( newFilePath + "\\"+ "temp");   
				newFile.createNewFile();
				
				FileOutputStream fos = new FileOutputStream( newFile );
				File[] file2 = file1.listFiles();
				value="";
				for(int i=0; i<file2.length; i++) {
					Tree_content g = new Tree_content( file2[i].getPath());
					value += g.output();
					fos.write( g.output().getBytes() );	
				}
				fos.close();
				
				//���������ɵ��ļ�����Ҫ���ʵ��value��key
				Gen_hash t = new Gen_hash();
				key = t.hash(newFile.getPath());
				File dest = new File(newFilePath + "\\" + key);
				newFile.renameTo(dest);
			
			}	
		}
		catch (IOException e) {	
			e.printStackTrace();
		}  		
	}
	
	
	public String getFilePath() {
		return filePath;
	}
	
	public String getNewFilePath() {
		return newFilePath;
	}
	
	public String getKey() {
		return key;
	}
	
	public  String getValue ( ) {
		 return this.value;	
	 }
}