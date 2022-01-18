package np.qa.lesson7.base;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ZipHelper {
    public final String resourceRoot="src/test/resources/";
    public final String zipName="sample-zip-file.zip";
    public final ZipFile zipFile=new ZipFile(resourceRoot+zipName);
    public ClassLoader cl = ZipHelper.class.getClassLoader();



    public ArrayList<String> getListFileNames () throws Exception {
    ArrayList<String> arrayList=new ArrayList<>();
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if(entry.isDirectory()){
                    //System.out.println("dir  : " + entry.getName());
                } else {
                    //System.out.println("file : " + entry.getName());
                    arrayList.add(entry.getName());
                }
            }
   return arrayList; }




    public ZipHelper() throws IOException {
    }
}
