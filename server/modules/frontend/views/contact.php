<?php 
	$contactActive= 'class="active"';

	include $config->get('frontendViewsDir') . 'header.php'; 
?>
	
<div id="bar">
	<div class="wrap" style="width: 80%;">
		<!-- <span class="stepApp">Apps: </span> -->

		<span class="step"><a href="#anyrss">AnyRSS reader</a><span class="new-text">New!</span></span>
		<span class="step"><a href="#fmylife">F*ck my life</a><span class="new-text">New!</span></span>
		<span class="step"><a href="#dailystuff">Daily stuff</a></span>
		

	</div>
</div>
	
	
	<div class="wrap">
		<div class="col">
			<h3>About</h3>
			<p>
				Meanwhile we think a good <em>About</em> you can follow us on twitter, 
				<a href="http://twitter.com/espinchi">@espinchi</a> and <a href="http://twitter.com/ompemi">@ompemi</a>
				
				
			</p>
		</div>
	</div>
	
<?php 	
	include $config->get('frontendViewsDir') . 'footer.php'; 
?>