import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import org.jdom2.Document;         // |
import org.jdom2.Element;          // |\ Librerías
import org.jdom2.JDOMException;    // |/ JDOM
import org.jdom2.input.SAXBuilder; // |
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;



public class FileUpload extends HttpServlet {

    public void processXml(String ruta)
    {   
    //Se crea un SAXBuilder para poder parsear el archivo
    SAXBuilder builder = new SAXBuilder();
    File xmlFile = new File(ruta);
    try
    {
        //Se crea el documento a traves del archivo
        Document document = (Document) builder.build(xmlFile);
 
       
        
         XMLOutputter xmlOutput = new XMLOutputter();
 
		// display nice nice
		xmlOutput.setFormat(Format.getPrettyFormat());
		xmlOutput.output(document, new FileWriter("resultado.xml"));
 
		System.out.println("File Saved!");
    }catch ( IOException io ) {
        System.out.println( io.getMessage() );
    }catch ( JDOMException jdomex ) {
        System.out.println( jdomex.getMessage() );
    }
}	 
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
                String ruta = null;
	        if (isMultipart) {
	        	// Create a factory for disk-based file items
	        	FileItemFactory factory = new DiskFileItemFactory();

	        	// Create a new file upload handler
	        	ServletFileUpload upload = new ServletFileUpload(factory);
	 
	            try {
	            	// Parse the request
	            	List /* FileItem */ items = upload.parseRequest(request);
	                Iterator iterator = items.iterator();
	                while (iterator.hasNext()) {
	                    FileItem item = (FileItem) iterator.next();
	                    if (!item.isFormField()) {
	                        String fileName = item.getName();	 
	                        String root = getServletContext().getRealPath("/");
	                        File path = new File(root + "/uploads");
	                        if (!path.exists()) {
	                            boolean status = path.mkdirs();
	                        }
	 
	                        File uploadedFile = new File(path + "/" + fileName);
	                        System.out.println(uploadedFile.getAbsolutePath());
	                        item.write(uploadedFile);
                                ruta = path + "/" + fileName;
	                    }
	                }
	            } catch (FileUploadException e) {
	                e.printStackTrace();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
                
                processXml(ruta);
                
	    }

}