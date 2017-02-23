<html>
	<body>
		<%
			String fileName = request.getParameter("filename");
			System.out.println("Start request for file:" + fileName);
			java.util.List<String> items = new java.util.LinkedList<String>();
			items.add("c:\\wks\\prg\\Graphviz\\bin\\dot.exe");
			items.add("-v");
			items.add("-Tpng");
			items.add("-o..\\webapps\\dot\\out.png");
			items.add(fileName);

			System.out.println("Try execute command: " + items.toString());

			Process prc = Runtime.getRuntime().exec(items.toArray(new String[0]));

			int exitCode = prc.waitFor();

			System.out.println("Exit code: " + exitCode);

			if(exitCode != 0 ) {
				throw new RuntimeException("Program run with error");
			}
		%>
		<img src="out.png"/>
	</body>
</html>
