<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>Androidsx brothers: apps for android</title>
	<meta name="description" content="Androidsx brothers, android apps, widgets" />
	<meta name="keywords" content="Androidsx brothers, android apps, widgets" />
	<link href="web/css/main.css" rel="stylesheet" type="text/css" />
	
	<!--  gallery plugin -->
	<link href="web/lib/galleria.css" rel="stylesheet" type="text/css" media="screen"> 
	<script type="text/javascript" src="web/lib/jquery-1.3.2.min.js"></script> 
	<script type="text/javascript" src="web/lib/jquery.galleria.js"></script> 
	<script type="text/javascript"> 

	
	jQuery(function($) {
		//$('.gallery_demo_unstyled').addClass('gallery_demo'); // adds new class name to maintain degradability
		
		$('ul.gallery').galleria({
			history: false, // the containing selector for our main image
		});
	});
	
	</script>
	
</head>

<body>
	<div id="header">
		<h1><a href="./">Androidsx brothers</a></h1>
		<ul id="menu">
		
			
			<li <?php echo $homeActive; ?>><a href="./">home</a></li>
			<li <?php echo $contactActive; ?>><a href="./index.php?controller=contact">about</a></li>
		</ul>
	</div>

	<div id="teaser">
		<div class="wrap">
			<div id="image"></div>
			<div class="box">
				<p>
					This is a space where we share the applications we have developed for Android platform.
				</p>
			</div>
		</div>
	</div>