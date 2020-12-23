package test;
import java.util.*;
import java.io.*;
import java.security.MessageDigest;

/* С������һҪʵ�֣�
��װ��һ����
	1. ����value����洢�����Ӷ�Ӧ��key-value
	2. ����key�����ҵõ���Ӧ��valueֵ

����һ��Blob��:
������
	-ԭʼ�ļ���·���� filePath
	-���ļ��Ĺ�ϣֵ��key
	-�µ��ļ�·����newPath
	-�ļ������ݣ�value

������
	+1 getFilePath
	+2 getNewPath
	+3 getKey
	+4 ����value����洢�����Ӷ�Ӧ��key-value�ĺ�����Ҳ���Ǵ���ͬ�����ݵ��ļ���Ϊkey�����ļ���addFile(String filePath)
		����ֵΪkey;���е�����Gen_hash���е�hash����
	+5 ����key������GetValue�࣬���Ҷ�Ӧ��value,���ص����ļ���key����ַ�����ʽ��ȫ�����ݣ�getValue(String key)	
*/

/*�ٵ�����װһ��GetValue�ࣺ
 * 	����·�������ж��ļ����������Ҷ�Ӧ��value��getValue(String key)	
 * 
 */

public class Blob {
	
	private String filePath;   
	private  String newPath;
	private  String key;
	
	Blob(){};
	//���캯��������ԭʼ·����������Ҫ�Ž��ļ�����·������
	//�ɹ��캯���������ɰ�ԭʼ·����Ӧ��Blob, ������������·����
	Blob( String filePath, String newPath) throws IOException{
		this.filePath = filePath;
		this.newPath  = newPath;
		key = addFile( );   //����������������·����д���ļ�����
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public String getNewPath() {
		return newPath;
	}
	
	public String getKey() {
		return key;
	}
	 
	//4 ����value��ԭ�ļ�������洢�����Ӷ�Ӧ��key-value�ĺ�����addFile(String filePath)�����ص���ԭ�ļ���key
	 public  String addFile() throws IOException{
		 Gen_hash t = new Gen_hash();
		 this.key = t.hash (filePath);
		 File newFile = new File( newPath + "\\"+ key);   //�����е����µ��ļ�·����
		 newFile.createNewFile();
		
		 FileInputStream fileis = new FileInputStream(filePath);
		 FileOutputStream fileos = new FileOutputStream(newFile);
		 
		 byte[] buffer = new byte[1024];  //����һ���ֽڻ�����
		 int numRead = 0;
		 
		 do {
			 numRead = fileis.read(buffer);
			 if( numRead > 0 ) {
				fileos.write(buffer, 0, numRead); 
				 
			 }
		 } while ( numRead != -1);
		 
		fileis.close();
		fileos.close();
		 
		 
		 return key;
	 }
	 
	//	+5 ����key������GetValue�࣬���Ҷ�Ӧ��value,���ص����ļ���key����ַ�����ʽ��ȫ�����ݣ�getValue(String key)	
	 public  String getValue ( ) throws FileNotFoundException {
		 GetValue t = new GetValue();
		 return t.getValue( newPath +"//"+key );
		
	 }
	 
	

}