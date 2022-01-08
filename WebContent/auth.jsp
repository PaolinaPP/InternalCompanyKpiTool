<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Authenticator</title>
<link rel="stylesheet" type="text/css" href="kpi-tool.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>
	<form action="VerifyController" method="post">
		<div class="row">
			<label for="code" style="color: #0e1526">Enter your key*:</label><br /> <input id="code" class="input" name="code" type="text" value=""
				size="50" /><br /> <br />

		</div>
		<input class="submit_button" name="authButton" type="submit"
					value="Authenticate" />
	</form>

</body>
</html>