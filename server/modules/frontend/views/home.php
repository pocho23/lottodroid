<?php
$homeActive= 'class="active"';

include $config->get('frontendViewsDir') . 'header.php';
?>

<div class="wrap">

<div class="colMain" style="float:left;">
<h3 id="dailystuff">Daily stuff widget</h3>
<p>Have your daily dose of culture and curiosities in your home screen
with these small, simple <span style="font-weight: bold;">widgets:</span>
</p>


<ul class="list">
	<li><span style="font-weight: bold;">What happened today:</span> things
	that happened on today's date, but years ago</li>
	<li><span style="font-weight: bold;">Article of the day:</span> article
	of the day from Wikipedia</li>
	<li><span style="font-weight: bold;">Picture of the day:</span> picture
	of the day from Wikipedia</li>
	<li><span style="font-weight: bold;">Quote of the day:</span>
	daily random quotes</li>
	<li><span style="font-weight: bold;">Joke of the day:</span>
	daily random jokes</li>
</ul>



<p>The widget is available on the market ( Free ). More information on <a
	href="http://www.cyrket.com/package/com.androidsx.dailystuff"> Cyrket
entry of Daily stuff widgets </a></p>

</div>

<div class="col" style="float:right;width:20%;border:1px solid grey;padding:5px;">
<p>Version 2.1:


<ul class="list">
	<li>Added quote of the day</li>
	<li>Added joke of the day</li>
	<li>Added author of the quotes, and a link to see more about him.</li>
	<li>You can read off-line previous days</li>
</ul>
</div>
</div>

<div class="wrap">
<div class="colMain">

<ul class="gallery">
	<li><a class="selected"
		href="web/img/screenshot_dailystuff/addwidgets.png"> <img
		src="web/img/screenshot_dailystuff/addwidgets_thumb.png"> </a></li>
		
	<li><a href="web/img/screenshot_dailystuff/home2.png"> <img
		src="web/img/screenshot_dailystuff/home2_thumb.png"> </a></li>

	<li><a href="web/img/screenshot_dailystuff/home.png"> <img
		src="web/img/screenshot_dailystuff/home_thumb.png"> </a></li>
		
	<li><a href="web/img/screenshot_dailystuff/jokes.png"> <img
		src="web/img/screenshot_dailystuff/jokes_thumb.png"> </a></li>
		
	<li><a href="web/img/screenshot_dailystuff/quotes.png"> <img
		src="web/img/screenshot_dailystuff/quotes_thumb.png"> </a></li>				

	<li><a href="web/img/screenshot_dailystuff/article.png"> <img
		src="web/img/screenshot_dailystuff/article_thumb.png"> </a></li>

	<li><a href="web/img/screenshot_dailystuff/picture.png"> <img
		src="web/img/screenshot_dailystuff/picture_thumb.png"> </a></li>

	<li><a href="web/img/screenshot_dailystuff/event.png"> <img
		src="web/img/screenshot_dailystuff/event_thumb.png"> </a></li>
</ul>

</div>
</div>
<?php
include $config->get('frontendViewsDir') . 'footer.php';
?>