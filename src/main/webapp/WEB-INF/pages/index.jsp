<%@page contentType="text/html; charset=utf-8" language="java"%>
<!DOCTYPE html>
<html>
<body>
	<form method="POST" enctype="multipart/form-data" action="upload.do">
		File to upload: <input type="file" name="file"><br /> Name: <input
			type="text" name="name"><br /> <br /> <input type="submit"
			value="Upload"> Press here to upload the file!
	</form>
</body>
</html>