
<html>
<head>
<title>Tax Calculator</title>
</head>
<body>
	<h1 align="center">Tax calculator</h1>
    <p><font color="red">${errorMessage}</font></p>
    <form action="/taxCalculate" method="POST">
    	<table>
		    <tr>
		        <td>First Name :</td>      
		        <td><input name="firstName" type="text"/></td>
		    </tr>
		    <tr>
		        <td>Last Name :</td>      
		        <td><input name="lastName" type="text"/></td>
		  	</tr>
		  	<tr>
		        <td>Annual Income :</td>      
		        <td><input name="annualIncome" type="number" min="0"/></td>
	 	    </tr>
		    <tr>
		        <td>Super :</td>      
		        <td><input name="superAnnuation" type="number" min="0"/></td>
		    </tr>
		    <tr>
		        <td>Select Month :</td>      
		        <td><select name = "month" style="margin-top:20px;">
						<option value=0>JAN</option>
						<option value=1>FEB</option>
						<option value=2>MAR</option>
						<option value=3>APR</option>
						<option value=4>MAY</option>
						<option value=5>JUN</option>
						<option value=6>JUL</option>
						<option value=7>AUG</option>
						<option value=8>SEP</option>
						<option value=9>OCT</option>
						<option value=10>NOV</option>
						<option value=11>DEC</option>
					</select> <br/> <br/></td>
		    </tr>
    	</table>
        <input type="submit" />
    </form>
    <table border="1" cellpadding="5" width="500">
    <tr>
        <th>Pay Period</th>
        <th>Gross Income</th>
        <th>Income Tax</th>
        <th>Net Income</th>
        <th>Super Annuation</th>
    </tr>
    <tr>
        <td style="text-align:center;">${taxDetails.fromDate} - ${taxDetails.toDate}</td>
        <td style="text-align:center;">${taxDetails.grossIncome}</td>
        <td style="text-align:center;">${taxDetails.incomeTax}</td>
        <td style="text-align:center;">${taxDetails.netIncome}</td>
        <td style="text-align:center;">${taxDetails.superannuation}</td>
    </tr>
</table>
</body>
</html>
