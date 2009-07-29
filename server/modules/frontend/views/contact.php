<?php 
	$contactActive= 'class="active"';

	include $config->get('frontendViewsDir') . 'header.php'; 
?>
	
<div id="bar">
	<div class="wrap" style="width: 80%;">
		<!-- <span class="stepApp">Apps: </span> -->

		<span class="step"><a href="index.php#anyrss">AnyRSS reader</a><span class="new-text">New!</span></span>
		<span class="step"><a href="index.php#fmylife">F*ck my life</a><span class="new-text">New!</span></span>
		<span class="step"><a href="index.php#dailystuff">Daily stuff</a></span>
		

	</div>
</div>
	
	
	<div class="wrap">
		<div class="col">
			<h3>About</h3>
			<p>
				We are <a href="http://www.linkedin.com/in/omarpera">Omar Pera Mira</a> 
				and <a href="http://www.linkedin.com/in/pablopera">Pablo Pera Mira</a>, developers starting
				out in the Android world. 
			</p>
			<p>
				You can follow us on twitter, 
				<a href="http://twitter.com/espinchi">@espinchi</a> and <a href="http://twitter.com/ompemi">@ompemi</a>.
			</p>
			<p>	
				Send us an email to <img alt="androidsx at the google mail server" src="web/img/email.png" />	
			</p>
		</div>
	</div>
	
<?php 	
	include $config->get('frontendViewsDir') . 'footer.php'; 
?>