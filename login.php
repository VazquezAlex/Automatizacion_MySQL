<?php
	define('DB_SERVER', 'localhost:8889');
	define('DB_USERNAME', 'root');
	define('DB_PASSWORD', 'root');
	define('DB_DATABASE', 'selenium_automation');

	$db = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);

	if($db === false){
		die("ERROR: Could not connect. " . mysqli_connect_error());
	} 

	session_start();

	if($_SERVER["REQUEST_METHOD"] == "POST") {
		// username and password sent from form 
		
		$myusername = mysqli_real_escape_string($db,$_POST['username']);
		$mypassword = mysqli_real_escape_string($db,$_POST['password']); 

		// echo "'$myusername'  '$mypassword'";
		
		$sql = "SELECT nombre FROM users WHERE usuario = '".$myusername."' AND PASSWORD = '".$mypassword."'";
	
		$result = mysqli_query($db,$sql);
		$row = mysqli_fetch_array($result,MYSQLI_ASSOC);
		$active = $row['active'];
		
		$count = mysqli_num_rows($result);

	  // If result matched $myusername and $mypassword, table row must be 1 row
		
		if ($count >= 1) {
			
			echo '<script type="text/javascript">';
			echo ' alert("Sesión iniciada correctamente")';  
			echo '</script>';
			
		} else {
			$error = "Tu usuario o contraseña son invalidos.";
			alert("No se pudo iniciar sesión.");
		}
   }
?>
<html>
   
   <head>
	  <title>Login Page</title>
	  
	  <style type = "text/css">
		 body {
			font-family:Arial, Helvetica, sans-serif;
			font-size:14px;
		 }
		 label {
			font-weight:bold;
			width:100px;
			font-size:14px;
		 }
		 .box {
			border:#666666 solid 1px;
		 }
	  </style>
	  
   </head>
   
   <body bgcolor = "#FFFFFF">
	
	  <div>
		 <div style = "width:300px; border: solid 1px #333333; " align = "left">
			<div style = "background-color:#333333; color:#FFFFFF; padding:3px;"><b>Login</b></div>
				
			<div style = "margin:30px">
			   
			   <form action = "" method = "post">
				  <label>UserName  :</label><input type = "text" name = "username" class = "box"/><br /><br />
				  <label>Password  :</label><input type = "password" name = "password" class = "box" /><br/><br />
				  <input type = "submit" value = " Submit "/><br />
			   </form>
			   
			   <div style = "font-size:11px; color:#cc0000; margin-top:10px"><?php echo $error; ?></div>
					
			</div>
				
		 </div>
			
	  </div>

   </body>
</html>