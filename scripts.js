jQuery(document).ready(function() { 
  
	// Lightbox images
	$('a.fancybox').fancybox();
             
                
    // Carousel            
    $('#carousel').elastislide({
		imageW 	: 180,
		minItems	: 5
	});
		// Fix carousel arrows
		$('.es-nav-prev').html('<i class="icon-chevron-left icon-white"></i>');
		$('.es-nav-next').html('<i class="icon-chevron-right icon-white"></i>');
		
	
	// Countdown
	
		// Please refer to http://keith-wood.name/countdown.html for more info on how to set the date
		// Example : launchTime = new Date(2010, 12-1, 25) 
		// Months range from 0 to 11, so subtract 1 to the desired month
    	launchTime = new Date(); // Set launch: [year], [month], [day], [hour]...
	
		// For demo only, remove after you have set the date above
		launchTime.setDate(launchTime.getDate() +15);
		
		$("#countdown").countdown({
			until: launchTime, 
			format: 'odHMS',
			
			// Do not modify below
			layout: '<div class="desc"><div class="mask"></div><span class="image">{d10}</span><span class="image">{d1}</span><span class="text">days</span></div><div class="desc"><div class="mask"></div><span class="image">{h10}</span><span class="image">{h1}</span><span class="text">hours</span></div><div class="desc"><div class="mask"></div><span class="image">{m10}</span><span class="image">{m1}</span><span class="text">months</span></div><div class="desc"><div class="mask"></div><span class="image">{s10}</span><span class="image">{s1}</span><span class="text">seconds</span></div>'
		});

 
}); /* end of as page load scripts */
