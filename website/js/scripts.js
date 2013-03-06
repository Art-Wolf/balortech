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
 
}); /* end of as page load scripts */
