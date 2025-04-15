<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, height=device-height, initial-scale=1.0, maximum-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/froala-editor@latest/css/froala_editor.pkgd.min.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/froala-editor@latest/js/froala_editor.pkgd.min.js"></script>
</head>
<body>
	<div id="content"></div>
	<script>
        let editor = new FroalaEditor("div#content", {
            heightMin:150,
            heightMax:150
        }, function () {
            console.log(editor.html.get());
        });
        $("#btnInsert").click(() => {
            console.log(editor.html.get());
        });
	</script>
</body>
</html>
