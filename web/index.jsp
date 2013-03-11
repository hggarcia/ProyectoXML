<%-- 
    Document   : index
    Created on : 05-mar-2013, 20:44:05
    Author     : hernangarcia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>
    <h1>File Upload Form</h1> 
    <fieldset>
        <legend>Upload File</legend>
        <form action="FileUpload" method="post" enctype="multipart/form-data">
            <label for="fileName">Select File: </label>
            <input id="fileName" type="file" name="fileName" size="30"/><br/>            
            <input type="submit" value="Upload"/>
        </form>
    </fieldset>
</body>
</html>