<?php
$homeActive= 'class="active"';

include $config->get('frontendViewsDir') . 'header.php';
?>

<div class="wrap">
<div class="colMain">
<h3 id="dailystuff">Daily stuff</h3>
<p>Have your daily dose of culture and curiosities in your home screen
with these small, simple <span style="font-weight: bold;">widgets:</span>


<ul class="list">
	<li><span style="font-weight: bold;">What happened today:</span> things
	that happened on today's date, but years ago</li>
	<li><span style="font-weight: bold;">Article of the day:</span> article
	of the day from Wikipedia</li>
	<li><span style="font-weight: bold;">Picture of the day:</span> picture
	of the day from Wikipedia</li>
</ul>

</p>


<!-- 
			<p> 
				The widget is available on the market ( Free ). More information on
				<a href="http://www.cyrket.com/package/com.lottodroid.widgets.wikiarticle">
					Cyrket entry of Wikiarticle
				</a> 
			</p>
			 -->


</div>
</div>

<div class="wrap">
<div class="colMain">

<ul class="gallery">
	<li><a class="selected"
	href="web/img/screenshot_dailystuff/addwidgets.png"
		title="Select the widget: Article of the day, Picture of the day or
		 What hapenned today."> <img
		 
		 
		src="web/img/screenshot_dailystuff/addwidgets_thumb.png" alt="Select the widget: Article of the day, Picture of the day and
		 What hapenned today.">
	</a></li>

	<li><a 
		href="web/img/screenshot_dailystuff/home.png" title="The 3 widgets of Daily Stuff"> <img
		src="web/img/screenshot_dailystuff/home_thumb.png" alt="The 3 widgets of Daily Stuff"> </a></li>

	<li><a href="web/img/screenshot_dailystuff/article.png"
		title="When you click on the 'Article of the day' title it shows
		the last featured articles. You can click on any link for more information."> <img
		src="web/img/screenshot_dailystuff/article_thumb.png" alt="When you click on the 'Picture of the day' title it shows
		the last featured pictures."> </a></li>

	<li><a href="web/img/screenshot_dailystuff/picture.png"
		title="When you click on the 'Picture of the day' widget it shows
		the last pictures of the last 20 days."> <img
		src="web/img/screenshot_dailystuff/picture_thumb.png" alt="When you click on the 'Picture of the day' widget it shows
		the last pictures of the last 20 days."> </a></li>


	<li><a href="web/img/screenshot_dailystuff/event.png" title="When you click on the 'What happened today' widget it shows
		the relevant events that happened today. Also you can browse the last days information.">
	<img src="web/img/screenshot_dailystuff/event_thumb.png" alt="When you click on the 'What happened today' widget it shows
		the relevant events that happened today. Also you can browse the last days information.">
	</a></li>

	<li><a href="web/img/screenshot_dailystuff/no_connection.png"
		title="If there was an error on the network or is not available, you can click to try again. Also you can
		read the information off-line"> <img
		src="web/img/screenshot_dailystuff/no_connection_thumb.png"
		alt="Image01"> </a></li>

</ul>

</div>
</div>
<?php
include $config->get('frontendViewsDir') . 'footer.php';
?>